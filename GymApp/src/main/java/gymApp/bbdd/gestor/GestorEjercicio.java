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

	private Connection connection = null;
	private Firestore firestore = null;

	public GestorEjercicio() {
		connection = new Connection();

	}

	public ArrayList<Ejercicio> getNameExercisesbyId(String id) {
		firestore = connection.connection();
		ArrayList<Ejercicio> ret = new ArrayList<Ejercicio>();
		try {

			CollectionReference workouts = firestore.collection("Workouts");
			DocumentReference workout = workouts.document(id);
			ApiFuture<QuerySnapshot> query = workout.collection("Exercises").get();
			QuerySnapshot querySnapshot = query.get();
			List<QueryDocumentSnapshot> exercises = querySnapshot.getDocuments();

			for (QueryDocumentSnapshot exe : exercises) {
				Ejercicio exercise = new Ejercicio();
				exercise.setName(exe.getString("name"));
				ret.add(exercise);

			}
			firestore.close();
		} catch (Exception e) {

		}

		return ret;
	}
}
