package gymApp.vista;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import gymApp.bbdd.pojos.History;
import gymApp.logica.ControladorHistorico;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelHistorico {
	private JPanel panel = null;
	private JTable table;
	private String[] column = { "Completed Exercises", "Date", "Expected Time", "Name Workout", "Total Time" };
	private DefaultTableModel model = new DefaultTableModel(null, column);

	public PanelHistorico(ArrayList<JPanel> paneles) {
		panel = new JPanel();
		panel.setBounds(0, 0, 1499, 878);
		panel.setLayout(null);

		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVolver.setBounds(684, 759, 155, 42);
		panel.add(btnVolver);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(89, 179, 1314, 511);
		panel.add(scrollPane);

		table = new JTable(model);
		scrollPane.setViewportView(table);

		JLabel fondo = new JLabel("");
		fondo.setBounds(0, 0, 1489, 867);
		fondo.setIcon(new ImageIcon(PanelHistorico.class.getResource("/images/HISTORICO.png")));
		panel.add(fondo);
		
		

		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (JPanel p : paneles)
					p.setVisible(false);
				paneles.get(2).setVisible(true);
			}
		});
	}

	public void refreshHistoric() {
	    getHistoric();
	}

	private void getHistoric() {
		ControladorHistorico historyControl = new ControladorHistorico();
		try {
			ArrayList<History> histories = historyControl.getHistoryList();
			model.setRowCount(0);

			for (History history : histories) {
				Object[] line = { history.getCompletedExercises(), history.getDate(), history.getExpectedTime(),
						history.getNameWorkour(), history.getTotalTime() };
				model.addRow(line);
			}

			table.revalidate();
			table.repaint();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JPanel getPanel() {
		return panel;
	}
}
