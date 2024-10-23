package gymApp.bbdd.gestor;

import gymApp.bbdd.Connection;

public class GestorAbstract {

	protected static final String COLLECTION_WORKOUTS = "Workouts";
	protected static final String COLLECTION_EXERCISES = "Exercises";
	protected static final String COLLECTION_USERS = "Users";
	protected static final String KEY_NAME = "name";
	protected static final String KEY_EXERCICSES="exercises";
	protected static final String KEY_URL="URL";
	protected static final String KEY_LEVEL="level";
	protected static final String KEY_PASSWORD="password";
	
	protected Connection connection = null;
	
	protected GestorAbstract() {
		connection = Connection.getInstance ();
	}
}
