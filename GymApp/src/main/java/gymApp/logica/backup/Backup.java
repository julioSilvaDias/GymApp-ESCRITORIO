package gymApp.logica.backup;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import gymApp.bbdd.pojos.Ejercicio;
import gymApp.bbdd.pojos.History;
import gymApp.bbdd.pojos.Usuario;
import gymApp.bbdd.pojos.Workout;

public class Backup {
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

		DataOutputStream fic = new DataOutputStream(new FileOutputStream(FILE_USERS));

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

		DataOutputStream fic = new DataOutputStream(new FileOutputStream(FILE_WORKOUT));

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

		DataOutputStream fic = new DataOutputStream(new FileOutputStream(FILE_EXERCISES));

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

	public ArrayList<Usuario> getUsers() throws FileNotFoundException, IOException {
		ArrayList<Usuario> ret = new ArrayList<>();

		try (FileInputStream file = new FileInputStream(FILE_USERS);
				DataInputStream dataInput = new DataInputStream(file)) {

			String name = null;
			String surname = null;
			String birthdate = null;
			String email = null;
			String password = null;
			String id = null;

			while (dataInput.available() > 0) {
				String line = dataInput.readUTF().trim();
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
					case "birthdate":
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
		}

		return ret;
	}

	public ArrayList<Ejercicio> getExercises() throws FileNotFoundException, IOException {
		ArrayList<Ejercicio> ret = null;

		FileInputStream file = new FileInputStream(FILE_EXERCISES);
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

				case "sets":
					sets = new ArrayList<>();
					String[] setValues = value.split(",");
					for (String setValue : setValues) {
						sets.add(Integer.parseInt(setValue.trim()));
					}

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

	public ArrayList<Workout> getWorkout() throws FileNotFoundException, IOException {
		ArrayList<Workout> ret = null;

		FileInputStream file = new FileInputStream(FILE_WORKOUT);
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

	public boolean isConnectionAvailable() {
		try {
			URL url = new URL("http://www.google.com");
			HttpURLConnection urlConnect = (HttpURLConnection) url.openConnection();
			urlConnect.setConnectTimeout(3000);
			urlConnect.connect();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public void saveHistories(ArrayList<History> histories)
			throws ParserConfigurationException, SAXException, IOException, TransformerException {
		for (History history : histories) {
			saveHistory(history);
		}
	}

	public void saveHistory(History history)
			throws ParserConfigurationException, SAXException, IOException, TransformerException {

		File fileHistory = new File(RUTA_XML);

		if (!fileHistory.exists()) {
			fileHistory.createNewFile();

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			Element rootElement = doc.createElement("Histories");
			doc.appendChild(rootElement);
		}

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fileHistory);
		doc.getDocumentElement().normalize();

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

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(fileHistory);
		transformer.transform(source, result);
	}

}
