package gymApp.logica.backup;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import gymApp.bbdd.pojos.Ejercicio;
import gymApp.bbdd.pojos.Usuario;
import gymApp.bbdd.pojos.Workout;

public class BackupProcess {
	
	private static final String RUTA_USERS = "ficheros/backupUsers.dat";
	private static final File FILE_USERS = new File(RUTA_USERS);
	private static final String RUTA_WORKOUT = "ficheros/backupWorkout.dat";
	private static final File FILE_WORKOUT = new File(RUTA_WORKOUT);
	private static final String RUTA_EXERCISES = "ficheros/backupExercises.dat";
	private static final File FILE_EXERCISES = new File(RUTA_EXERCISES);
	private static final String RUTA_XML = "ficheros/history.xml";
	
	public void saveUsers(ArrayList<Usuario> users) throws FileNotFoundException, IOException {
		for (Usuario user : users) {
			saveUser(user);
		}
	}

	public void saveUser(Usuario user) throws FileNotFoundException, IOException {

		DataOutputStream fic = new DataOutputStream(new FileOutputStream(FILE_USERS, true));

		fic.writeUTF("\n" + "name: " + user.getName() + "\n");
		fic.writeUTF("surname: " + user.getSurname() + "\n");
		fic.writeUTF("Birthdate: " + user.getBrithdate() + "\n");
		fic.writeUTF("email: " + user.getEmail() + "\n");
		fic.writeUTF("password: " + user.getPassword() + "\n");
		fic.writeUTF("id: " + user.getId() + "\n");
		fic.writeUTF("***************************");

		fic.close();
	}
	
	public void saveWorkouts(ArrayList<Workout> workouts) throws FileNotFoundException, IOException {
		for (Workout workout : workouts) {
			saveWorkout(workout);
		}
	}

	public void saveWorkout(Workout workout) throws FileNotFoundException, IOException {

		DataOutputStream fic = new DataOutputStream(new FileOutputStream(FILE_WORKOUT, true));

		fic.writeUTF("\n" + "name: " + workout.getName() + "\n");
		fic.writeUTF("nivel: " + workout.getNivel() + "\n");
		fic.writeUTF("video: " + workout.getVideo() + "\n");
		fic.writeUTF("exercises: " + workout.getExercises() + "\n");
		fic.writeUTF("id: " + workout.getId() + "\n");
		fic.writeUTF("***************************");

		fic.close();
	}
	
	public void saveExercises(ArrayList<Ejercicio> exercises) throws FileNotFoundException, IOException {
		for (Ejercicio exercise : exercises) {
			saveExercise(exercise);
		}
	}

	public void saveExercise(Ejercicio exercise) throws FileNotFoundException, IOException {

		DataOutputStream fic = new DataOutputStream(new FileOutputStream(FILE_EXERCISES, true));

		fic.writeUTF("\n" + "name: " + exercise.getName() + "\n");
		fic.writeUTF("image: " + exercise.getImage() + "\n");
		fic.writeUTF("description: " + exercise.getDescription() + "\n");
		fic.writeUTF("rest: " + exercise.getRest() + "\n");

		StringBuilder setsString = new StringBuilder();
		for (Integer set : exercise.getSets()) {
			setsString.append(set).append(",");
		}

		if (setsString.length() > 0) {
			setsString.deleteCharAt(setsString.length() - 1);
		}

		fic.writeUTF("sets: " + setsString.toString() + "\n");
		fic.writeUTF("id: " + exercise.getId() + "\n");
		fic.writeUTF("***************************");

		fic.close();
	}
}
