package gymApp.logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import gymApp.bbdd.gestor.GestorHistorico;
import gymApp.bbdd.pojos.History;
import gymApp.logica.backup.Backup;

public class ControladorHistorico {

	public ArrayList<History> getHistoryList()
			throws FileNotFoundException, IOException, ExecutionException, InterruptedException, Exception {

		String id = ControllerInstance.getInstance().getIdUser();

		ArrayList<History> ret = new ArrayList<History>();
		GestorHistorico gestorHistorico = new GestorHistorico();

		Backup backup = new Backup();
		boolean conection = backup.isConnectionAvailable();

		if (conection) {
			ret = gestorHistorico.getHistory(id);
			launchBackupProcess(ret);
		} else {
			ret = backup.getHistories();
		}

		return ret;
	}

	public void launchBackupProcess(ArrayList<History> histories) throws IOException, InterruptedException {
		File tempHistoriesFile = File.createTempFile("historiesBackup", ".tmp");
		tempHistoriesFile.deleteOnExit();

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tempHistoriesFile))) {
			oos.writeObject(histories);
		}

		ProcessBuilder processBuilder = new ProcessBuilder("java", "-cp", "target/classes",
				"gymApp.logica.backup.BackupProcess", tempHistoriesFile.getAbsolutePath());

		processBuilder.inheritIO();
		Process process = processBuilder.start();

		int exitCode = process.waitFor();
		if (exitCode != 0) {
		}
	}
}
