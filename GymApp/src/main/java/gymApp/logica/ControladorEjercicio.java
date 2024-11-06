package gymApp.logica;

import java.util.concurrent.ExecutionException;

import gymApp.bbdd.gestor.GestorEjercicio;
import gymApp.bbdd.pojos.Ejercicio;

public class ControladorEjercicio {
	
	private String id = null;
	private String name = null;
	
	private static ControladorEjercicio controller = null;
	
	public static ControladorEjercicio getInstance() {
		if (null == controller) {
			controller = new ControladorEjercicio();
		}
		return controller;
	}

	public ControladorEjercicio() {

	}

	public Ejercicio getInfo(String idWorkout, String nameExercise) throws InterruptedException, ExecutionException, Exception {
		return new GestorEjercicio().getInfo(idWorkout, nameExercise);
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}