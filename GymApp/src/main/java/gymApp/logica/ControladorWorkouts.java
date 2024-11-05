package gymApp.logica;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import gymApp.bbdd.gestor.GestorEjercicio;
import gymApp.bbdd.gestor.GestorWorkout;
import gymApp.bbdd.pojos.Ejercicio;
import gymApp.bbdd.pojos.Workout;
import gymApp.logica.backup.Backup;

public class ControladorWorkouts {

	public ArrayList<Workout> getAllWorkouts()
			throws FileNotFoundException, IOException, ExecutionException, InterruptedException, Exception {
		
		ArrayList<Workout> ret = new ArrayList<Workout>();
		GestorWorkout gestorWorkout =null;
		Backup backup = new Backup();
		
		boolean conection =backup.isConnectionAvailable();
		
		if(conection) {
			gestorWorkout= new GestorWorkout();
			ret = gestorWorkout.getAllWorkouts();
			backup.saveWorkouts(ret);
		}else{
			ret = backup.getWorkout();
		}
		
		return ret;
	}

	public ArrayList<Ejercicio> getExercisesById(String id)
			throws FileNotFoundException, IOException, ExecutionException, InterruptedException, Exception {
		return new GestorEjercicio().getNameExercisesbyId(id);
	}
}
