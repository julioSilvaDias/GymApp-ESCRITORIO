package gymApp.logica;

import gymApp.bbdd.gestor.GestorUsuario;
import gymApp.bbdd.pojos.Usuario;

public class ControladorPerfil {

	public String manageUserData(String name, String surname, String birthdate, String email, String password) throws Exception {
		
		GestorUsuario gestorUsuario = new GestorUsuario();
		
		Usuario user = gestorUsuario.getAllData(name, surname, birthdate, email, password);
		
		String ret = "";
		
		boolean userLoged = true;
		
		if (userLoged) {
			
		}
		
		return ret;
	}
	
}
