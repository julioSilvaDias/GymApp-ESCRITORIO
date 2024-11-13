package gymApp.logica.backup;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.util.Locale;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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
	DateFormat DATE_FORMAT = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

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
		ArrayList<Ejercicio> ret = new ArrayList<Ejercicio>();

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
					sets = new ArrayList<Integer>();
					String[] setValues = value.split(",");
					for (String setValue : setValues) {
						sets.add(Integer.parseInt(setValue.trim()));
					}

				}
			}

			if (line.contains("***************************")) {
				Ejercicio exercise = new Ejercicio();
				
				int[] intArray = new int[sets.size()];
				
				for(int i = 0; i<sets.size(); i++) {
					intArray[i] = sets.get(i);
				}
				
				exercise.setDescription(description);
				exercise.setImage(image);
				exercise.setName(name);
				exercise.setRest(rest);
				exercise.setSets(intArray);

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
		ArrayList<Workout> ret = new ArrayList<Workout>();

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

	@SuppressWarnings("deprecation")
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

	public ArrayList<History> getHistories()
			throws ParserConfigurationException, SAXException, IOException, ParseException {
		ArrayList<History> histories = new ArrayList<>();

		File fileHistory = new File(RUTA_XML);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fileHistory);
		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName("History");

		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				History history = new History();

				history.setCompletedExercises(getElementTextContent(eElement, "CompleteExercises"));
				history.setExpectedTime(getElementTextContent(eElement, "ExpectedTime"));
				history.setId(getElementTextContent(eElement, "Id"));
				history.setNameWorkour(getElementTextContent(eElement, "NameWorkout"));
				history.setTotalTime(getElementTextContent(eElement, "TotalTime"));

				String dateStr = getElementTextContent(eElement, "Date");

				Date date = DATE_FORMAT.parse(dateStr);
				history.setDate(date);

				histories.add(history);
			}
		}

		return histories;
	}

	private String getElementTextContent(Element parent, String tagName) {
		NodeList nodeList = parent.getElementsByTagName(tagName);
		if (nodeList.getLength() > 0) {
			return nodeList.item(0).getTextContent();
		}
		return null;
	}

}
