package gymApp.logica;

import gymApp.bbdd.gestor.GestorUsuario;

public class ControladorRegistro {
	public void addUser(String name, String surname, String email, String birthdate, String password) throws Exception {
		new GestorUsuario().addUser(name, surname, email, birthdate, password);
	}
}
