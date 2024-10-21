package gymApp.bbdd.gestor;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import gymApp.bbdd.pojos.Ejercicio;

public class GestorEjercicio {
	public ArrayList<Ejercicio> getExercisesbyId(String id) {
		ArrayList<Ejercicio> ret = new ArrayList<Ejercicio>();
		try {
			InputStream fileInputStream = getClass().getResourceAsStream("/utils/gymapp.json");
			;
			FirestoreOptions firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
					.setProjectId("gymapp-4565e").setCredentials(GoogleCredentials.fromStream(fileInputStream)).build();
			Firestore firestore = firestoreOptions.getService();

			CollectionReference workouts = firestore.collection("Workouts");
			DocumentReference workout = workouts.document(id);
			ApiFuture<QuerySnapshot> query = firestore.collection("Exercises").get();
			QuerySnapshot querySnapshot = query.get();
			List<QueryDocumentSnapshot> exercises = querySnapshot.getDocuments();

			for (QueryDocumentSnapshot exe : exercises) {
				Ejercicio exercise = new Ejercicio();
				exercise.setName(exe.getString("name"));

			}

		} catch (Exception e) {

		}
		return ret;
	}
}
