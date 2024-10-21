package gymApp;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gymApp.vista.PanelEjercicio;
import gymApp.vista.PanelHistorico;
import gymApp.vista.PanelLogin;
import gymApp.vista.PanelPerfil;
import gymApp.vista.PanelRegistro;
import gymApp.vista.PanelWorkouts;

public class GymApp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;

	private ArrayList<JPanel> paneles = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new GymApp().frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GymApp() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1499, 878);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		paneles = new ArrayList<JPanel>();

		PanelLogin panelLogin = new PanelLogin(paneles);
		JPanel panel1 = panelLogin.getPanel();
		panel1.setVisible(false);

		paneles.add(panel1);
		frame.getContentPane().add(panel1);

		PanelRegistro panelRegistro = new PanelRegistro(paneles);
		JPanel panel2 = panelRegistro.getPanel();
		panel2.setVisible(false);

		paneles.add(panel2);
		frame.getContentPane().add(panel2);

		PanelWorkouts panelWorkout = new PanelWorkouts(paneles);
		JPanel panel3 = panelWorkout.getPanel();
		panel3.setVisible(true);

		paneles.add(panel3);
		frame.getContentPane().add(panel3);

		PanelEjercicio panelEjercicio = new PanelEjercicio(paneles);
		JPanel panel4 = panelEjercicio.getPanel();
		panel4.setVisible(false);

		paneles.add(panel4);
		frame.getContentPane().add(panel4);

		PanelPerfil panelPerfil = new PanelPerfil(paneles);
		JPanel panel5 = panelPerfil.getPanel();
		panel5.setVisible(false);

		paneles.add(panel5);
		frame.getContentPane().add(panel5);

		PanelHistorico panelHistorico = new PanelHistorico(paneles);
		JPanel panel6 = panelHistorico.getPanel();
		panel6.setVisible(false);

		paneles.add(panel6);
		frame.getContentPane().add(panel6);

	}

}
