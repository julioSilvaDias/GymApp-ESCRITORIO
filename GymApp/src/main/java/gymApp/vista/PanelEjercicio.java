package gymApp.vista;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.BevelBorder;

import gymApp.logica.ControladorEjercicio;
import gymApp.bbdd.pojos.Ejercicio;

import javax.swing.JButton;

public class PanelEjercicio {
	private JPanel panel=null;
	public PanelEjercicio(ArrayList<JPanel> paneles) {
		panel = new JPanel();
		panel.setBounds(0, 0, 1499, 878);
		panel.setLayout(null);
		
		String nameExercise = null;
		String descExercise = null;
		String nameWorkout = null;
		int rest = 0;
		try {
			Ejercicio exercise = new ControladorEjercicio().getInfo();
			nameExercise = exercise.getName();
			descExercise = exercise.getDescription();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error loading exercise", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
		JLabel lblTimeKeeperWorkout = new JLabel("Cronómetro Workout: ");
		lblTimeKeeperWorkout.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
		lblTimeKeeperWorkout.setFont(new Font("Corbel", Font.BOLD, 30));
		lblTimeKeeperWorkout.setForeground(new Color(255, 255, 255));
		lblTimeKeeperWorkout.setBounds(10, 110, 482, 101);
		panel.add(lblTimeKeeperWorkout);
		
		JLabel lblExercise = new JLabel(nameExercise + "-" + descExercise);
		lblExercise.setForeground(Color.WHITE);
		lblExercise.setFont(new Font("Corbel", Font.BOLD, 30));
		lblExercise.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
		lblExercise.setBounds(502, 110, 482, 101);
		panel.add(lblExercise);
		
		JLabel lblWorkout = new JLabel("");
		lblWorkout.setForeground(Color.WHITE);
		lblWorkout.setFont(new Font("Corbel", Font.BOLD, 30));
		lblWorkout.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
		lblWorkout.setBounds(994, 110, 482, 101);
		panel.add(lblWorkout);
		
		JLabel lblExerciseTime = new JLabel("Tiempo Ejercicio: ");
		lblExerciseTime.setForeground(Color.WHITE);
		lblExerciseTime.setFont(new Font("Corbel", Font.BOLD, 20));
		lblExerciseTime.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
		lblExerciseTime.setBounds(10, 236, 250, 50);
		panel.add(lblExerciseTime);
		
		JLabel lblRest = new JLabel("Descanso: ");
		lblRest.setForeground(Color.WHITE);
		lblRest.setFont(new Font("Corbel", Font.BOLD, 20));
		lblRest.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
		lblRest.setBounds(10, 297, 250, 50);
		panel.add(lblRest);
		
		JButton btnStart = new JButton("");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnStart.setBorderPainted(false);
		btnStart.setBackground(new Color(0, 128, 64));
		btnStart.setBounds(718, 790, 50, 50);
		panel.add(btnStart);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paneles.get(0).setVisible(false);
				paneles.get(1).setVisible(false);
				paneles.get(2).setVisible(true);
				paneles.get(3).setVisible(false);
				paneles.get(4).setVisible(false);
				paneles.get(5).setVisible(false);
			}
		});
		btnExit.setForeground(new Color(255, 255, 255));
		btnExit.setFont(new Font("Corbel", Font.BOLD, 15));
		btnExit.setBorderPainted(false);
		btnExit.setBackground(new Color(255, 0, 0));
		btnExit.setBounds(1340, 830, 136, 25);
		panel.add(btnExit);
		
		JLabel fondo = new JLabel("");
		fondo.setBackground(new Color(240, 240, 240));
		fondo.setIcon(new ImageIcon(PanelEjercicio.class.getResource("/images/EJERCICIO.png")));
		fondo.setBounds(10, 0, 1489, 867);
		panel.add(fondo);
	}
	
	public JPanel getPanel() {
		return panel;
	}
}
