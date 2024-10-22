package gymApp.bbdd.gestor;

import java.util.ArrayList;
import java.util.List;
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

	public ArrayList<Ejercicio> getNameExercisesbyId(String id) throws Exception {
		firestore = connection.getConnection();
		ArrayList<Ejercicio> ret = new ArrayList<Ejercicio>();
		try {
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
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				firestore.close();
			} catch (Exception e) {
				// Nothing to do here...
			}
		}
		return ret;
	}
}
