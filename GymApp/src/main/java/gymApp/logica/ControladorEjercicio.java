package gymApp.logica;

import java.util.List;
import java.util.concurrent.ExecutionException;

import gymApp.bbdd.gestor.GestorEjercicio;
import gymApp.bbdd.pojos.Ejercicio;

public class ControladorEjercicio {
	
	private String id = null;
	
	private static ControladorEjercicio instance = null;
	
	public static ControladorEjercicio getInstance() {
		if (null == instance) {
			instance = new ControladorEjercicio();
		}
		return instance;
	}

	private ControladorEjercicio() {

	}

	public List<Ejercicio> getInfo(String idWorkout) throws InterruptedException, ExecutionException, Exception {
		return new GestorEjercicio().getInfo(idWorkout);
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}