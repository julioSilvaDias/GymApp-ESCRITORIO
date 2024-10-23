package gymApp.logica;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import gymApp.bbdd.gestor.GestorEjercicio;
import gymApp.bbdd.gestor.GestorWorkout;
import gymApp.bbdd.pojos.Ejercicio;
import gymApp.bbdd.pojos.Workout;

public class ControladorWorkouts {

	public ArrayList<Workout> getAllWorkouts()
			throws FileNotFoundException, IOException, ExecutionException, InterruptedException, Exception {
		return new GestorWorkout().getAllWorkouts();
	}

	public ArrayList<Ejercicio> getExercisesById(String id)
			throws FileNotFoundException, IOException, ExecutionException, InterruptedException, Exception {
		return new GestorEjercicio().getNameExercisesbyId(id);
	}
}
