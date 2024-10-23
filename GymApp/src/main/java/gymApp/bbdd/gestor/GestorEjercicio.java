package gymApp.bbdd.gestor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import gymApp.bbdd.pojos.Ejercicio;

public class GestorEjercicio extends GestorAbstract {

	private Firestore firestore = null;

	public GestorEjercicio() {
		super();
	}

	public ArrayList<Ejercicio> getNameExercisesbyId(String id)
			throws FileNotFoundException, IOException, ExecutionException, InterruptedException, Exception {
		firestore = connection.getConnection();
		ArrayList<Ejercicio> ret = new ArrayList<Ejercicio>();

		CollectionReference workouts = firestore.collection(COLLECTION_WORKOUTS);
		DocumentReference workout = workouts.document(id);

		ApiFuture<QuerySnapshot> query = workout.collection(COLLECTION_EXERCISES).get();
		QuerySnapshot querySnapshot = query.get();

		List<QueryDocumentSnapshot> exercises = querySnapshot.getDocuments();
		for (QueryDocumentSnapshot exe : exercises) {
			Ejercicio exercise = new Ejercicio();
			exercise.setName(exe.getString(KEY_NAME));
			ret.add(exercise);
		}
		firestore.close();

		return ret;
	}

	public Ejercicio getInfo() throws Exception {
		firestore = connection.getConnection();
		Ejercicio exercise = new Ejercicio();

		ApiFuture<QuerySnapshot> query = firestore.collection(COLLECTION_EXERCISES).whereEqualTo(KEY_NAME, "Ejer1").get();
		QuerySnapshot querySnapshot = query.get();
		List<QueryDocumentSnapshot> Exercise = querySnapshot.getDocuments();
		for (QueryDocumentSnapshot exer : Exercise) {
			exercise.setName(exer.getString(KEY_NAME));
			exercise.setDescription(exer.getString("description"));
		}
		firestore.close();

		return exercise;
	}
}
