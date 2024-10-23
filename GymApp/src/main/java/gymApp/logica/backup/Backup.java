package gymApp.logica.backup;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import gymApp.bbdd.pojos.Ejercicio;
import gymApp.bbdd.pojos.Usuario;
import gymApp.bbdd.pojos.Workout;

public class Backup {
	private static final String RUTA = "/ficheros/backup.dat";
	private static final File FILE = new File(RUTA);

	public void saveUsers(ArrayList<Usuario> users) throws FileNotFoundException, IOException {

		DataOutputStream fic = new DataOutputStream(new FileOutputStream(FILE));

		for (Usuario user : users) {
			fic.writeUTF("\n" + "name: " + user.getName() + "\n");
			fic.writeUTF("surname: " + user.getSurname() + "\n");
			fic.writeUTF("Birthdate: " + user.getBrithdate() + "\n");
			fic.writeUTF("email: " + user.getEmail() + "\n");
			fic.writeUTF("password: " + user.getPassword() + "\n");
			fic.writeUTF("id: " + user.getId() + "\n");
			fic.writeUTF("***************************");

		}

		fic.close();
	}

	public void saveWorkout(ArrayList<Workout> workouts) throws FileNotFoundException, IOException {

		DataOutputStream fic = new DataOutputStream(new FileOutputStream(FILE));

		for (Workout workout : workouts) {
			fic.writeUTF("\n" + "name: " + workout.getName() + "\n");
			fic.writeUTF("nivel: " + workout.getNivel() + "\n");
			fic.writeUTF("video: " + workout.getVideo() + "\n");
			fic.writeUTF("exercises: " + workout.getExercises() + "\n");
			fic.writeUTF("id: " + workout.getId() + "\n");
			fic.writeUTF("***************************");

		}

		fic.close();
	}

	public void saveExercises(ArrayList<Ejercicio> exercises) throws FileNotFoundException, IOException {

		DataOutputStream fic = new DataOutputStream(new FileOutputStream(FILE));

		for (Ejercicio exercice : exercises) {
			
			fic.writeUTF("\n" + "name: " + exercice.getName() + "\n");
			fic.writeUTF("image: " + exercice.getImage() + "\n");
			fic.writeUTF("description: " + exercice.getDescription() + "\n");
			fic.writeUTF("rest: " + exercice.getRest() + "\n");
			fic.writeUTF("sets: " + exercice.getSets() + "\n");
			fic.writeUTF("id: " + exercice.getId() + "\n");
			fic.writeUTF("***************************");

		}

		fic.close();
	}

	public ArrayList<Usuario> getUsers() throws FileNotFoundException, IOException {
		ArrayList<Usuario> ret = null;

		FileInputStream file = new FileInputStream(FILE);
		DataInputStream fic = new DataInputStream(file);

		String name = null;
		String surname = null;
		String birthdate = null;
		String email = null;
		String password = null;
		String id = null;

		while (file.getChannel().position() < file.getChannel().size()) {
			String line = fic.readUTF().trim();
			String[] parts = line.split(":", 2);

			if (parts.length == 2) {
				String key = parts[0].trim().toLowerCase();
				String value = parts[1].trim();

				switch (key) {
				case "name":
					name = value;
					break;

				case "surname":
					surname = value;
					break;

				case "Birthdate":
					birthdate = value;
					break;

				case "email":
					email = value;
					break;

				case "password":
					password = value;
					break;

				case "id":
					id = value;
					break;
				}
			}

			if (line.contains("***************************")) {
				Usuario user = new Usuario();

				user.setBrithdate(birthdate);
				user.setEmail(email);
				user.setId(id);
				user.setName(name);
				user.setSurname(surname);
				user.setPassword(password);

				ret.add(user);

				name = null;
				surname = null;
				birthdate = null;
				email = null;
				password = null;
				id = null;
			}
		}

		fic.close();

		return ret;
	}

	public ArrayList<Ejercicio> getExercises() throws FileNotFoundException, IOException{
		ArrayList<Ejercicio> ret = null;

		FileInputStream file = new FileInputStream(FILE);
		DataInputStream fic = new DataInputStream(file);

		String name = null;
		String image = null;
		String description = null;
		int rest = 0;
		ArrayList<Integer> sets = null;

		while (file.getChannel().position() < file.getChannel().size()) {
			String line = fic.readUTF().trim();
			String[] parts = line.split(":", 2);

			if (parts.length == 2) {
				String key = parts[0].trim().toLowerCase();
				String value = parts[1].trim();

				switch (key) {
				case "name":
					name = value;
					break;

				case "image":
					image = value;
					break;

				case "description":
					description = value;
					break;

				case "rest":
					rest = Integer.parseInt(value);
					break;

				/*case "sets":
					sets = value;
					break;*/
				}
			}

			if (line.contains("***************************")) {
				Ejercicio exercise = new Ejercicio();

				exercise.setDescription(description);
				exercise.setImage(image);
				exercise.setName(name);
				exercise.setRest(rest);
				exercise.setSets(sets);

				ret.add(exercise);

				name = null;
				image = null;
				description = null;
				rest = 0;
				sets = null;
			}
		}

		fic.close();

		return ret;

	}

	public ArrayList<Workout> getWorkout() throws FileNotFoundException, IOException{
		ArrayList<Workout> ret = null;

		FileInputStream file = new FileInputStream(FILE);
		DataInputStream fic = new DataInputStream(file);

		String name = null;
		int nivel = 0;
		String video = null;
		int exercises = 0;
		String id = null;

		while (file.getChannel().position() < file.getChannel().size()) {
			String line = fic.readUTF().trim();
			String[] parts = line.split(":", 2);

			if (parts.length == 2) {
				String key = parts[0].trim().toLowerCase();
				String value = parts[1].trim();

				switch (key) {
				case "name":
					name = value;
					break;

				case "nivel":
					nivel = Integer.parseInt(value);
					break;

				case "video":
					video = value;
					break;

				case "exercises":
					exercises = Integer.parseInt(value);
					break;

				case "id":
					id = value;
					break;
				}
			}

			if (line.contains("***************************")) {
				Workout workout = new Workout();

				workout.setExercises(exercises);
				workout.setId(id);
				workout.setName(name);
				workout.setNivel(nivel);
				workout.setVideo(video);

				ret.add(workout);
				
				name = null;
				nivel = 0;
				video = null;
				exercises = 0;
				id = null;

			}

		}
		
		fic.close();
		return ret;
	}
}
