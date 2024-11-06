package gymApp.logica;

import java.util.ArrayList;

import gymApp.bbdd.gestor.GestorUsuario;
import gymApp.bbdd.pojos.Usuario;
import gymApp.logica.backup.Backup;

public class ControladorPerfil {

	public Usuario manageUserData(String login) throws Exception {
		Backup backup = new Backup();
		Usuario ret = new Usuario();
		boolean conection = backup.isConnectionAvailable();

		if (conection) {
			GestorUsuario gestorUsuario = new GestorUsuario();
			ret = gestorUsuario.getLoginData(login);
		} else {
			ArrayList<Usuario> users = backup.getUsers();
			for(Usuario user : users) {
				if(login.equals(user.getName())) {
					ret = user;
					break;
				}
			}
		}

		return ret;
	}

	public void updatePasswordUser(String login, String newPassword) throws Exception {
		GestorUsuario gestorUsuario = new GestorUsuario();
		gestorUsuario.updatePassword(login, newPassword);
	}

}