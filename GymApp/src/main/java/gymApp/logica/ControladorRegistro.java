package gymApp.logica;

import gymApp.bbdd.gestor.GestorUsuario;
import gymApp.bbdd.pojos.Usuario;

public class ControladorRegistro {

	public void addUser(String name, String surname, String email, String birthdate, String password) throws Exception {
		new GestorUsuario().addUser(name, surname, email, birthdate, password);		
	}
	
	public Usuario existsUser(String name) throws Exception {
		return new GestorUsuario().exitsUser(name);
	}
}
