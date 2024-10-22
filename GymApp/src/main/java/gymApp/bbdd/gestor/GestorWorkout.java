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

import gymApp.bbdd.pojos.Workout;

public class GestorWorkout {
	private Connection connection = null;
	private Firestore firestore = null;

	public GestorWorkout() {
		connection = new Connection();

	}
	
	public ArrayList<Workout> getAllWorkouts() {
		ArrayList<Workout> ret = new ArrayList<Workout>();
		try {

			firestore = connection.connection();

			ApiFuture<QuerySnapshot> query = firestore.collection("Workouts").get();
			QuerySnapshot querySnapshot = query.get();
			List<QueryDocumentSnapshot> workouts = querySnapshot.getDocuments();
			ret = new ArrayList<Workout>();
			
			for (QueryDocumentSnapshot wkt : workouts) {
				Workout workout = new Workout();
				workout.setId(wkt.getId());
				workout.setName(wkt.getString("name"));
				workout.setExercises(wkt.getLong("exercises").intValue());
				workout.setVideo(wkt.getString("URL"));
				workout.setNivel(wkt.getLong("level").intValue());
				
				ret.add(workout);
			}
			
			firestore.close();

		} catch (FileNotFoundException fileNotFoundException) {

		} catch (IOException ioexception) {

		} catch (ExecutionException executionException) {

		} catch (InterruptedException interruptedException) {

		}catch (Exception e) {
			
		}
		
		return ret;
	}
}
