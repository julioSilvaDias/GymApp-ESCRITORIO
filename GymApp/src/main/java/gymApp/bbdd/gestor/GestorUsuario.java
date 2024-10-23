package gymApp.bbdd.gestor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import gymApp.bbdd.Connection;

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

}
