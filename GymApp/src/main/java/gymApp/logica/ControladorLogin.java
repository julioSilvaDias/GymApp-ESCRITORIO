package gymApp.logica;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import gymApp.bbdd.gestor.GestorUsuario;
import gymApp.bbdd.pojos.Usuario;
import gymApp.logica.backup.Backup;

public class ControladorLogin {
	private Backup backup = new Backup();

	public String checkLogin(String login, String password)
			throws InterruptedException, ExecutionException, IOException, Exception {
		
		boolean connection = false;
		connection = backup.isConnectionAvailable();
		String ret = "";
		Usuario user = new Usuario();

		if (connection) {
			try {
				
				GestorUsuario gestorUsuario = new GestorUsuario();
				user = gestorUsuario.obtenerUserAndPassword(login, password);

				if (user == null || user.getName() == null) {
					ret = "User does not exist";
					
				} else if (user.getName().equals(login) && user.getPassword().equals(password)) {
					ret = "Correct Login";
					backup.saveUser(user);
					
				} else {
					ret = "Incorrect username or password";
				}
				
			} catch (IOException e) {
				ret = "Error accessing Firestore: " + e.getMessage();
			}
		} else {
			ret = getLocalUser(login, password);
		}

		if (ret.equals("Correct Login") || ret.equals("Correct Login. Local user found")) {
			ControllerInstance.getInstance().setIdUser(user.getId());
		}

		return ret;
	}

	private String getLocalUser(String login, String password) throws FileNotFoundException, IOException {
		String ret = "";
		ArrayList<Usuario> users = backup.getUsers();
		boolean isUser = false;
		for (Usuario user : users) {
			if (user.getName().equals(login) && user.getPassword().equals(password)) {
				isUser = true;
				break;
			}
		}

		if (isUser) {
			ret = "Correct Login. Local user found";
		} else {
			ret = "No Internet connection. No local user found";
		}

		return ret;
	}

}