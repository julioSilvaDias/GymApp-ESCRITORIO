package gymApp.bbdd.gestor;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import gymApp.bbdd.pojos.Usuario;

public class GestorUsuario extends GestorAbstract{
	
	private Firestore db = null;

	public GestorUsuario() {
		super();

	}
	
	public Usuario obtenerUserAndPassword(String login, String password) throws InterruptedException, ExecutionException, IOException, Exception {
		db = connection.getConnection();
		Usuario usuario = new Usuario();
		
		ApiFuture<QuerySnapshot> query = db.collection(COLLECTION_USERS).whereEqualTo(KEY_NAME, login).whereEqualTo(KEY_PASSWORD, password).get();
		QuerySnapshot querySnapshot = query.get();
		List <QueryDocumentSnapshot> users = querySnapshot.getDocuments();
		for(QueryDocumentSnapshot user : users) {
			usuario.setName(user.getString(KEY_NAME));
			usuario.setPassword(user.getString(KEY_PASSWORD));
		
		}
		
		db.close();
		
	return usuario;
	}
	
	public void addUser(String name, String surname, String email, String birthdate, String password) throws Exception {
		db = connection.getConnection();
		ApiFuture<QuerySnapshot> query = db.collection(COLLECTION_USERS).get();
		QuerySnapshot querySnapshot = query.get();
		List<QueryDocumentSnapshot> idUsers  = querySnapshot.getDocuments();
		String lastId = null;
		int id;
		for (QueryDocumentSnapshot idUser : idUsers) {
			lastId = idUser.getId();
		}
		id = Integer.parseInt(lastId) + 1;
		String newId = "00" + String.valueOf(id);
		
		CollectionReference users = db.collection(COLLECTION_USERS);
		DocumentReference user = users.document(newId);

		Map<String, Object> userNew = new HashMap<>();
		userNew.put("name", name);
		userNew.put("surname", surname);
		userNew.put("birthdate", birthdate);
		userNew.put("email", email);
		userNew.put("password", password);
		user.set(userNew);
		
		db.close();

	}

}
