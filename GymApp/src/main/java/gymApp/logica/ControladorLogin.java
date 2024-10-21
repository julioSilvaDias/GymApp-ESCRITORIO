package gymApp.logica;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import gymApp.bbdd.gestor.GestorUsuario;
import gymApp.bbdd.pojos.Usuario;

public class ControladorLogin {

	public String checkLogin(String login, String password)
			throws InterruptedException, ExecutionException, IOException {

		GestorUsuario gestorUsuario = new GestorUsuario();

		Usuario user = gestorUsuario.obtenerUserAndPassword(login, password);

		String ret = "";

		if (null == user || null == user.getName()) {
			ret = "User does not exist";
		} else if (user.getName().equals(login) && user.getPassword().equals(password)) {
			ret = "Correct Login";
		} else {
			ret = "Incorrect username or password";
		}

		return ret;

	}

}