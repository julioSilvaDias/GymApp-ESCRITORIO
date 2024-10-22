package gymApp.bbdd;

import java.io.IOException;
import java.io.InputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;

public class Connection {
	
	protected static final String PATH = "/utils/gymapp.json";
	protected static final String PROJECTID = "gymapp-4565e";

	private static Connection conn = null;

	public static Connection getInstance() {
		if (null == conn) {
			conn = new Connection();
		}
		return conn;
	}

	private Connection() {

	}

	/**
	 * Returns the connection to firestore
	 * 
	 * @return the connection
	 */
	public Firestore getConnection() {
		Firestore ret = null;
		try {

			InputStream fileInputStream = getClass().getResourceAsStream(PATH);
			FirestoreOptions firestoreOptions;
			firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder().setProjectId(PROJECTID)
					.setCredentials(GoogleCredentials.fromStream(fileInputStream)).build();
			ret = firestoreOptions.getService();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}
}
