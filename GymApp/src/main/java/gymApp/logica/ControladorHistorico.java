package gymApp.logica;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import gymApp.bbdd.gestor.GestorHistorico;
import gymApp.bbdd.pojos.History;
import gymApp.logica.backup.Backup;

public class ControladorHistorico {
	public ArrayList<History> getHistoryList(String id)
			throws FileNotFoundException, IOException, ExecutionException, InterruptedException, Exception {
		
		ArrayList<History> ret = new ArrayList<History>();
		GestorHistorico gestorHistorico = new GestorHistorico();
		ret = gestorHistorico.getHistory(id);
		
		Backup backup = new Backup();
		backup.saveHistories(ret);
		
		return ret;
	}
}
