package gymApp.logica;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import gymApp.bbdd.gestor.GestorEjercicio;
import gymApp.bbdd.pojos.Ejercicio;
import gymApp.logica.backup.Backup;
public class ControladorEjercicio {

	private String id = null;

	private static ControladorEjercicio instance = null;

	public static ControladorEjercicio getInstance() {
		if (null == instance) {
			instance = new ControladorEjercicio();
		}
		return instance;
	}

	private ControladorEjercicio() {

	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public ArrayList<Ejercicio> getInfo(String idWorkout) throws InterruptedException, ExecutionException, Exception {

		ArrayList<Ejercicio> ret = new ArrayList<Ejercicio>();
		Backup backup = new Backup();
		boolean conection = backup.isConnectionAvailable();

		if (conection) {
			GestorEjercicio gestorEjercicio = new GestorEjercicio();
			ret = gestorEjercicio.getInfo(idWorkout);
			launchBackupProcess(ret);

		} else {
			ret = backup.getExercises();
		}

		return ret;
	}

	public void launchBackupProcess(ArrayList<Ejercicio> ejercicios) throws IOException, InterruptedException {
		File tempEjerciciosFile = File.createTempFile("ejerciciosBackup", ".tmp");
		tempEjerciciosFile.deleteOnExit();

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tempEjerciciosFile))) {
			oos.writeObject(ejercicios);
		}

		ProcessBuilder processBuilder = new ProcessBuilder("java", "-cp", "target/classes",
				"gymApp.logica.backup.BackupProcess", tempEjerciciosFile.getAbsolutePath());

		processBuilder.inheritIO();
		Process process = processBuilder.start();

		int exitCode = process.waitFor();
		if (exitCode != 0) {
		}
	}

}