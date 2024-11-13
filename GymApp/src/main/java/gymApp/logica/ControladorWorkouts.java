package gymApp.logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
		GestorWorkout gestorWorkout = null;
		Backup backup = new Backup();

		boolean conection = backup.isConnectionAvailable();

		if (conection) {
			gestorWorkout = new GestorWorkout();
			ret = gestorWorkout.getAllWorkouts();
			launchBackupProcess(ret);
		} else {
			ret = backup.getWorkout();
		}

		return ret;
	}

	public ArrayList<Ejercicio> getExercisesById(String id)
			throws FileNotFoundException, IOException, ExecutionException, InterruptedException, Exception {
		
		GestorEjercicio gestorEjercicio = new GestorEjercicio();
		ArrayList<Ejercicio> ret = new ArrayList<Ejercicio>();
		Backup backup = new Backup();
		boolean conection = backup.isConnectionAvailable();
		
		if(conection) {
			ret = gestorEjercicio.getInfo(id);
		}else {
			ret = backup.getExercises();
		}
		
		
		return ret;
	}

	public void launchBackupProcess(ArrayList<Workout> workouts)
			throws IOException, InterruptedException, ExecutionException {
		File tempWorkoutsFile = File.createTempFile("workoutsBackup", ".tmp");
		tempWorkoutsFile.deleteOnExit();

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tempWorkoutsFile))) {
			oos.writeObject(workouts);
		} catch (IOException e) {
			System.err.println("Error al escribir el ArrayList<Workout> en el archivo temporal.");
			e.printStackTrace();
			throw e;
		}

		ProcessBuilder processBuilder = new ProcessBuilder("java", "-cp", "target/classes",
				"gymApp.logica.backup.BackupProcess", tempWorkoutsFile.getAbsolutePath());

		processBuilder.inheritIO();
		Process process = processBuilder.start();

		int exitCode = process.waitFor();
		if (exitCode != 0) {
			System.err.println("Error al ejecutar el proceso de backup. Código de salida: " + exitCode);
		} else {
			System.out.println("Proceso de backup para workouts completado con éxito.");
		}
	}
}
