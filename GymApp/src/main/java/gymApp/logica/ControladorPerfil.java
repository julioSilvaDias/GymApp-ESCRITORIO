package gymApp.logica;

import gymApp.bbdd.gestor.GestorUsuario;
import gymApp.bbdd.pojos.Usuario;

public class ControladorPerfil {

	public Usuario manageUserData(String login) throws Exception {
		GestorUsuario gestorUsuario = new GestorUsuario();
		Usuario user = gestorUsuario.getLoginData(login);
		return user;
	}
	
}
