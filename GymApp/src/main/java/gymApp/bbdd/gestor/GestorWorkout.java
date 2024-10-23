package gymApp.bbdd.gestor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import gymApp.bbdd.Connection;
import gymApp.bbdd.pojos.Workout;

public class GestorWorkout extends GestorAbstract{

	private Firestore firestore = null;

	public GestorWorkout() {
		super();;

	}
	
	public ArrayList<Workout> getAllWorkouts() throws FileNotFoundException, IOException, ExecutionException, InterruptedException, Exception{
		ArrayList<Workout> ret = new ArrayList<Workout>();
			firestore = connection.getConnection();

			ApiFuture<QuerySnapshot> query = firestore.collection(COLLECTION_WORKOUTS).get();
			QuerySnapshot querySnapshot = query.get();
			List<QueryDocumentSnapshot> workouts = querySnapshot.getDocuments();
			ret = new ArrayList<Workout>();
			
			for (QueryDocumentSnapshot wkt : workouts) {
				Workout workout = new Workout();
				workout.setId(wkt.getId());
				workout.setName(wkt.getString(KEY_NAME));
				workout.setExercises(wkt.getLong(KEY_EXERCICSES).intValue());
				workout.setVideo(wkt.getString(KEY_URL));
				workout.setNivel(wkt.getLong(KEY_LEVEL).intValue());
				
				ret.add(workout);
			}
			
			firestore.close();
		
		return ret;
	}
}
