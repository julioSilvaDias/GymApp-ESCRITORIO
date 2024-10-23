package gymApp.logica;

import gymApp.bbdd.gestor.GestorEjercicio;
import gymApp.bbdd.pojos.Ejercicio;

public class ControladorEjercicio {

	public Ejercicio getInfo() throws Exception {
		return new GestorEjercicio().getInfo();
	}

}