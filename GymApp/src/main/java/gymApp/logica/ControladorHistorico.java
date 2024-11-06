package gymApp.logica;

import java.io.FileNotFoundException;
import java.io.IOException;
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
		
		if(conection) {
			ret = gestorHistorico.getHistory(id);
			backup.saveHistories(ret);
		}else {
			ret = backup.getHistories();
		}
		
		
		
		return ret;
	}
}
