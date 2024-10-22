package gymApp.bbdd.gestor;

import java.io.IOException;
import java.io.InputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;

public class Connection {

	public Firestore ret = null;

	public Firestore connection() {

		try {

			InputStream fileInputStream = getClass().getResourceAsStream("/utils/gymapp.json");

			FirestoreOptions firestoreOptions;

			firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder().setProjectId("gymapp-4565e")
					.setCredentials(GoogleCredentials.fromStream(fileInputStream)).build();

			ret = firestoreOptions.getService();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

}
