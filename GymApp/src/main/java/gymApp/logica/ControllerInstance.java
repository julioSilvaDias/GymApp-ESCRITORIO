package gymApp.logica;

public class ControllerInstance {
	private static ControllerInstance instance = null;
	private String idUser = null;
	
	private ControllerInstance() {
		
	}
	
	public static ControllerInstance getInstance() {
		if(null == instance) {
			instance = new ControllerInstance();
		}
		return instance;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	
	
}
