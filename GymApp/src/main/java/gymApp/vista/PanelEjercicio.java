package gymApp.vista;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

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
import gymApp.logica.Timekeeper;
import gymApp.bbdd.pojos.Ejercicio;

import javax.swing.JButton;
import javax.swing.JFrame;

public class PanelEjercicio {
	private JPanel panel = null;
	private boolean stop = false;
	private boolean pause = false;
	private JLabel lblExercise;
	private JLabel lblWorkout;
	private JLabel lblExerciseTime;
	private JLabel lblRest;

	public PanelEjercicio(ArrayList<JPanel> paneles) {
		panel = new JPanel();
		panel.setBounds(0, 0, 1499, 878);
		panel.setLayout(null);

		JLabel lblTimeKeeperWorkout = new JLabel("TimeKeeper Workout: 00:00");
		lblTimeKeeperWorkout.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255),
				new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
		lblTimeKeeperWorkout.setFont(new Font("Corbel", Font.BOLD, 30));
		lblTimeKeeperWorkout.setForeground(new Color(255, 255, 255));
		lblTimeKeeperWorkout.setBounds(10, 110, 482, 101);
		panel.add(lblTimeKeeperWorkout);
		
		lblExercise = new JLabel();
		lblExercise.setForeground(Color.WHITE);
		lblExercise.setFont(new Font("Corbel", Font.BOLD, 30));
		lblExercise.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255),
				new Color(255, 255, 255), new Color(255, 255, 255)));
		lblExercise.setBounds(502, 110, 482, 101);
		panel.add(lblExercise);

		lblWorkout = new JLabel();
		lblWorkout.setForeground(Color.WHITE);
		lblWorkout.setFont(new Font("Corbel", Font.BOLD, 30));
		lblWorkout.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255),
				new Color(255, 255, 255), new Color(255, 255, 255)));
		lblWorkout.setBounds(994, 110, 482, 101);
		panel.add(lblWorkout);

		lblExerciseTime = new JLabel();
		lblExerciseTime.setForeground(Color.WHITE);
		lblExerciseTime.setFont(new Font("Corbel", Font.BOLD, 20));
		lblExerciseTime.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255),
				new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
		lblExerciseTime.setBounds(10, 236, 250, 50);
		panel.add(lblExerciseTime);

		lblRest = new JLabel();
		lblRest.setForeground(Color.WHITE);
		lblRest.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRest.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255),
				new Color(255, 255, 255), new Color(255, 255, 255)));
		lblRest.setBounds(10, 297, 250, 50);
		panel.add(lblRest);

		Timekeeper timekeeperWorkout = new Timekeeper("TimeKeeper Workout", lblTimeKeeperWorkout);
		Timekeeper timekeeperExercise = new Timekeeper("Exercise Time", lblExerciseTime);
		//Timekeeper timekeeperRest = new Timekeeper("Rest: ", lblRest);
		//Timekeeper timekeeperSet = new Timekeeper("", lblTimeKeeperWorkout);
		
		JButton btnStart = new JButton("");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!stop) {
					timekeeperWorkout.start();
					timekeeperExercise.start();
					pause = true;
				} else if (pause){
					timekeeperWorkout.running();
					timekeeperExercise.running();
					pause = false;
				} else {
					timekeeperWorkout.pause();
					timekeeperExercise.pause();
				}
			}
		});
		btnStart.setBackground(new Color(0, 128, 64));
		btnStart.setBounds(718, 765, 50, 50);
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
		btnExit.setBackground(new Color(255, 0, 0));
		btnExit.setBounds(1335, 790, 136, 25);
		panel.add(btnExit);

		JLabel fondo = new JLabel("");
		fondo.setBackground(new Color(240, 240, 240));
		fondo.setIcon(new ImageIcon(PanelEjercicio.class.getResource("/images/EJERCICIO.png")));
		fondo.setBounds(0, 0, 1499, 867);
		panel.add(fondo);
	}
	
	public void refresPanelExercise() {
		String idWorkout = ControladorEjercicio.getInstance().getId();
		String nameExercise = null;
		String descExercise = null;
		String nameWorkout = null;
		int rest = 0;

		try {
			if (idWorkout != null) {
				Ejercicio exercise = ControladorEjercicio.getInstance().getInfo(idWorkout);
				descExercise = exercise.getDescription();
				rest = exercise.getRest();
				nameWorkout = exercise.getName();
			}
		} catch (InterruptedException e) {
			JOptionPane.showMessageDialog(null, "Error loading exercise", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (ExecutionException e) {
			JOptionPane.showMessageDialog(null, "Error loading exercise", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error loading exercise", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public JPanel getPanel() {
		return panel;
	}
}
