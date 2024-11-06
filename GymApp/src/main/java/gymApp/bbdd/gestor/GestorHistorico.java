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

import gymApp.bbdd.pojos.History;

public class GestorHistorico extends GestorAbstract {

	private Firestore firestore = null;

	public GestorHistorico() {
		super();
	}

	public ArrayList<History> getHistory(String id)
			throws FileNotFoundException, IOException, ExecutionException, InterruptedException, Exception {
		firestore = connection.getConnection();
		ArrayList<History> ret = new ArrayList<History>();

		CollectionReference users = firestore.collection(COLLECTION_USERS);
		DocumentReference user = users.document(id);

		ApiFuture<QuerySnapshot> query = user.collection(COLLECTION_HISTORIC).get();
		QuerySnapshot querySnapshot = query.get();
		
		List<QueryDocumentSnapshot> histories = querySnapshot.getDocuments();
		for (QueryDocumentSnapshot his : histories) {
			History history = new History();
			history.setId(his.getId());
			history.setCompletedExercises(his.getString(KEY_COMPLETEDEXERCISES));
			history.setExpectedTime(his.getString(KEY_EXPECTEDTIME));
			history.setNameWorkour(his.getString(KEY_NAMEWOKOUT));
			history.setTotalTime(his.getString(KEY_TOTALTIME));
			history.setDate(his.getTimestamp(KEY_DATE).toDate());

			ret.add(history);
		}
		firestore.close();

		return ret;
	}

}
