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

import gymApp.bbdd.pojos.Usuario;

public class GestorUsuario {
	
	public Usuario obtenerUserAndPassword(String login, String password) throws InterruptedException, ExecutionException, IOException {
		
		Usuario usuario = new Usuario();
		
		InputStream serviceAccount = getClass().getResourceAsStream("/utils/gymapp.json");
		
		FirestoreOptions firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder().setProjectId("gymapp-4565e").setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();
		
		Firestore db = firestoreOptions.getService();
		
		ApiFuture<QuerySnapshot> query = db.collection("Users").whereEqualTo("name", login).whereEqualTo("password", password).get();
		QuerySnapshot querySnapshot = query.get();
		List <QueryDocumentSnapshot> users = querySnapshot.getDocuments();
		for(QueryDocumentSnapshot user : users) {
			System.out.println("usuario: " + user.getString(login));
			System.out.println("password: " + user.getString(login));
			usuario.setName(user.getString("name"));
			usuario.setPassword(user.getString("password"));
		}
		
	return usuario;
	}

}
