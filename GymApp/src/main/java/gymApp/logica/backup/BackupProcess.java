package gymApp.logica.backup;

import java.io.*;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import gymApp.bbdd.pojos.Ejercicio;
import gymApp.bbdd.pojos.History;
import gymApp.bbdd.pojos.Usuario;
import gymApp.bbdd.pojos.Workout;

public class BackupProcess {

	private static final String RUTA_USERS = "ficheros/backupUsers.dat";
	private static final File FILE_USERS = new File(RUTA_USERS);
	private static final String RUTA_WORKOUT = "ficheros/backupWorkout.dat";
	private static final File FILE_WORKOUT = new File(RUTA_WORKOUT);
	private static final String RUTA_EXERCISES = "ficheros/backupExercises.dat";
	private static final File FILE_EXERCISES = new File(RUTA_EXERCISES);
	private static final String RUTA_HISTORY_XML = "ficheros/history.xml";

	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("Usage: java BackupProcess <backup_file>");
			System.exit(1);
		}

		File backupFile = new File(args[0]);

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(backupFile))) {
			Object objeto = ois.readObject();

			BackupProcess backupProcess = new BackupProcess();

			if (objeto instanceof Usuario) {
				backupProcess.saveUser((Usuario) objeto);
				System.out.println("Usuario backed up successfully.");
			} else if (objeto instanceof Workout) {
				backupProcess.saveWorkout((Workout) objeto);
				System.out.println("Workout backed up successfully.");
			} else if (objeto instanceof Ejercicio) {
				backupProcess.saveExercise((Ejercicio) objeto);
				System.out.println("Ejercicio backed up successfully.");
			} else if (objeto instanceof ArrayList<?>) {
				ArrayList<?> list = (ArrayList<?>) objeto;
				if (!list.isEmpty()) {
					Object firstElement = list.get(0);
					if (firstElement instanceof Usuario) {
						backupProcess.saveUsers((ArrayList<Usuario>) list);
						System.out.println("Usuarios backed up successfully.");
					} else if (firstElement instanceof Workout) {
						backupProcess.saveWorkouts((ArrayList<Workout>) list);
						System.out.println("Workouts backed up successfully.");
					} else if (firstElement instanceof Ejercicio) {
						backupProcess.saveExercises((ArrayList<Ejercicio>) list);
						System.out.println("Ejercicios backed up successfully.");
					} else if (firstElement instanceof History) {
						backupProcess.saveHistoriesToXml((ArrayList<History>) list);
						System.out.println("Histories backed up successfully to XML.");
					} else {
						System.err.println("Lista de objetos no compatible.");
						System.exit(1);
					}
				} else {
					System.err.println("Lista vacía, no se realizó ningún backup.");
				}
			} else {
				System.err.println("El archivo no contiene un objeto válido para el respaldo.");
				System.exit(1);
			}
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Error procesando el backup: " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void saveUsers(ArrayList<Usuario> users) throws IOException {
		for (Usuario user : users) {
			saveUser(user);
		}
	}

	public void saveUser(Usuario user) throws IOException {
		try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(FILE_USERS, true))) {
			dos.writeUTF("\n" + "name: " + user.getName() + "\n");
			dos.writeUTF("surname: " + user.getSurname() + "\n");
			dos.writeUTF("Birthdate: " + user.getBrithdate() + "\n");
			dos.writeUTF("email: " + user.getEmail() + "\n");
			dos.writeUTF("password: " + user.getPassword() + "\n");
			dos.writeUTF("id: " + user.getId() + "\n");
			dos.writeUTF("***************************");
		}
	}

	public void saveWorkouts(ArrayList<Workout> workouts) throws IOException {
		for (Workout workout : workouts) {
			saveWorkout(workout);
		}
	}

	public void saveWorkout(Workout workout) throws IOException {
		try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(FILE_WORKOUT, true))) {
			dos.writeUTF("\n" + "name: " + workout.getName() + "\n");
			dos.writeUTF("nivel: " + workout.getNivel() + "\n");
			dos.writeUTF("video: " + workout.getVideo() + "\n");
			dos.writeUTF("exercises: " + workout.getExercises() + "\n");
			dos.writeUTF("id: " + workout.getId() + "\n");
			dos.writeUTF("***************************");
		}
	}

	public void saveExercises(ArrayList<Ejercicio> exercises) throws IOException {
		for (Ejercicio exercise : exercises) {
			saveExercise(exercise);
		}
	}

	public void saveExercise(Ejercicio exercise) throws IOException {
		try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(FILE_EXERCISES, true))) {
			dos.writeUTF("\n" + "name: " + exercise.getName() + "\n");
			dos.writeUTF("image: " + exercise.getImage() + "\n");
			dos.writeUTF("description: " + exercise.getDescription() + "\n");
			dos.writeUTF("rest: " + exercise.getRest() + "\n");

			StringBuilder setsString = new StringBuilder();
			for (Integer set : exercise.getSets()) {
				setsString.append(set).append(",");
			}
			if (setsString.length() > 0) {
				setsString.deleteCharAt(setsString.length() - 1);
			}
			dos.writeUTF("sets: " + setsString.toString() + "\n");
			dos.writeUTF("id: " + exercise.getId() + "\n");
			dos.writeUTF("***************************");
		}
	}

	public void saveHistory(Document doc, History history) {
		Element historyElement = doc.createElement("History");

		Element completedExercises = doc.createElement("CompleteExercises");
		completedExercises.appendChild(doc.createTextNode(history.getCompletedExercises()));
		historyElement.appendChild(completedExercises);

		Element expectedTime = doc.createElement("ExpectedTime");
		expectedTime.appendChild(doc.createTextNode(history.getExpectedTime()));
		historyElement.appendChild(expectedTime);

		Element id = doc.createElement("Id");
		id.appendChild(doc.createTextNode(history.getId()));
		historyElement.appendChild(id);

		Element nameWorkout = doc.createElement("NameWorkout");
		nameWorkout.appendChild(doc.createTextNode(history.getNameWorkour()));
		historyElement.appendChild(nameWorkout);

		Element totalTime = doc.createElement("TotalTime");
		totalTime.appendChild(doc.createTextNode(history.getTotalTime()));
		historyElement.appendChild(totalTime);

		Element date = doc.createElement("Date");
		date.appendChild(doc.createTextNode(history.getDate().toString()));
		historyElement.appendChild(date);

		doc.getDocumentElement().appendChild(historyElement);
	}

	public void saveHistoriesToXml(ArrayList<History> histories) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();

			Element rootElement = doc.createElement("Histories");
			doc.appendChild(rootElement);

			for (History history : histories) {
				saveHistory(doc, history);
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(RUTA_HISTORY_XML));
			transformer.transform(source, result);

			System.out.println("Histories saved to XML successfully.");

		} catch (ParserConfigurationException | TransformerException e) {
			System.err.println("Error saving histories to XML: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
