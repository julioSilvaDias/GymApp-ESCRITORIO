package gymApp.logica;

import java.util.ArrayList;

import gymApp.bbdd.gestor.GestorEjercicio;
import gymApp.bbdd.gestor.GestorWorkout;
import gymApp.bbdd.pojos.Ejercicio;
import gymApp.bbdd.pojos.Workout;

public class ControladorWorkouts {
	
	public ArrayList<Workout> getAllWorkouts() {
		ArrayList<Workout> ret = new ArrayList<Workout>();
		
		GestorWorkout gestorWorkout = new GestorWorkout();
		ret = gestorWorkout.getAllWorkouts(); 
		
		return ret;
	}
	
	public ArrayList<Ejercicio> getExercisesById(String id){
		ArrayList<Ejercicio> ret = new ArrayList<Ejercicio>();
		
		GestorEjercicio gestorEjercicio = new GestorEjercicio();
		ret = gestorEjercicio.getNameExercisesbyId(id);
		
		return ret;
	}
}
