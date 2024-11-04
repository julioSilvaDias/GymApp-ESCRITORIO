package gymApp.vista;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import gymApp.bbdd.pojos.History;
import gymApp.logica.ControladorHistorico;

public class PanelHistorico {
	private JPanel panel = null;
	private JTable table;
	private String[] column = { "Completed Exercises", "date", "Expected Time", "Name Workout", "Total Time" };
	private Object[][] data = null;
	private DefaultTableModel model = new DefaultTableModel(data, column);

	public PanelHistorico(ArrayList<JPanel> paneles) {
		panel = new JPanel();
		panel.setBounds(0, 0, 1499, 878);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("Volver");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(684, 759, 155, 42);
		panel.add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(89, 179, 1314, 511);
		panel.add(scrollPane);

		table = new JTable(model);
		scrollPane.setViewportView(table);

		JLabel fondo = new JLabel("");
		fondo.setBounds(0, 0, 1489, 867);
		fondo.setIcon(new ImageIcon(PanelHistorico.class.getResource("/images/HISTORICO.png")));
		panel.add(fondo);
		
		getHistoric();
	}

	private void getHistoric() {
		ControladorHistorico historyControl = new ControladorHistorico();
		try {
			ArrayList<History> histories = historyControl.getHistoryList("001");
			
			for (History history : histories) {
				Object[] line = { history.getCompletedExercises(), history.getDate(), history.getExpectedTime(),
						history.getNameWorkour(), history.getTotalTime() };

				model.addRow(line);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JPanel getPanel() {
		return panel;
	}
}
