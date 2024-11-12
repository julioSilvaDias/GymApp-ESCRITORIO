package gymApp.vista;

import java.util.ArrayList;
import java.util.List;
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

public class PanelEjercicio {
	private JPanel panel = null;
	private JLabel lblExercise = null;
	private JLabel lblWorkout = null;
	private JLabel lblExerciseTime = null;
	private JLabel lblRest = null;
	private JLabel lblWorkoutTimekeeper = null;
	private JLabel lblExerciseSet1 = null;
	private JLabel lblExerciseSet2 = null;
	private JLabel lblExerciseSet3 = null;
	private JLabel lblCountDown = null;
	private Timekeeper timekeeperWorkout = null;
	private Timekeeper timekeeperExercise = null;
	private Timekeeper timekeeperRest = null;
	private Timekeeper timekeeperSet1 = null;
	private Timekeeper timekeeperSet2 = null;
	private Timekeeper timekeeperSet3 = null;
	private Timekeeper timekeeperCountDown = null;
	private List<Ejercicio> exercises = null;
	private int rest = 0;

	public PanelEjercicio(ArrayList<JPanel> paneles) {
		panel = new JPanel();
		panel.setBounds(0, 0, 1499, 878);
		panel.setLayout(null);

		lblWorkoutTimekeeper = new JLabel("TimeKeeper Workout: 00:00");
		lblWorkoutTimekeeper.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255),
				new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
		lblWorkoutTimekeeper.setFont(new Font("Corbel", Font.BOLD, 30));
		lblWorkoutTimekeeper.setForeground(new Color(255, 255, 255));
		lblWorkoutTimekeeper.setBounds(10, 110, 482, 101);
		panel.add(lblWorkoutTimekeeper);

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

		lblExerciseTime = new JLabel("Exercise time: 00:00");
		lblExerciseTime.setForeground(Color.WHITE);
		lblExerciseTime.setFont(new Font("Corbel", Font.BOLD, 25));
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

		lblCountDown = new JLabel();
		lblCountDown.setForeground(Color.WHITE);
		lblCountDown.setFont(new Font("Corbel", Font.BOLD, 30));
		lblCountDown.setBounds(502, 236, 482, 101);
		panel.add(lblCountDown);

		lblExerciseSet1 = new JLabel();
		lblExerciseSet1.setForeground(Color.WHITE);
		lblExerciseSet1.setFont(new Font("Corbel", Font.BOLD, 30));
		lblExerciseSet1.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255),
				new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
		lblExerciseSet1.setBounds(502, 374, 482, 101);
		panel.add(lblExerciseSet1);

		lblExerciseSet2 = new JLabel();
		lblExerciseSet2.setForeground(Color.WHITE);
		lblExerciseSet2.setFont(new Font("Corbel", Font.BOLD, 30));
		lblExerciseSet2.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255),
				new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
		lblExerciseSet2.setBounds(502, 486, 482, 101);
		panel.add(lblExerciseSet2);

		lblExerciseSet3 = new JLabel();
		lblExerciseSet3.setForeground(Color.WHITE);
		lblExerciseSet3.setFont(new Font("Corbel", Font.BOLD, 30));
		lblExerciseSet3.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255),
				new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
		lblExerciseSet3.setBounds(502, 598, 482, 101);
		panel.add(lblExerciseSet3);

		JButton btnStart = new JButton();
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!timekeeperWorkout.start && !timekeeperExercise.start) {
					timekeeperWorkout.startTimekeeper();
					timekeeperExercise.startTimekeeper();
					timekeeperWorkout.start();
					timekeeperExercise.start();
					btnStart.setBackground(new Color(255, 255, 0));
				} else if (timekeeperWorkout.paused) {
					timekeeperWorkout.resumeTimekeeper();
					timekeeperExercise.resumeTimekeeper();
					btnStart.setBackground(new Color(255, 255, 0));
				} else {
					timekeeperWorkout.pauseTimekeeper();
					timekeeperExercise.pauseTimekeeper();
					btnStart.setBackground(new Color(0, 128, 0));
				}
			}

		});
		btnStart.setBackground(new Color(0, 128, 0));
		btnStart.setBounds(718, 765, 50, 50);
		panel.add(btnStart);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showWorkoutSummary();
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

	public void refresPanelExercise(int numExercise) {
		lblWorkoutTimekeeper.setText("TimeKeeper Workout: 00:00");
		lblExerciseTime.setText("Exercise time: 00:00");

		String idWorkout = ControladorEjercicio.getInstance().getId();
		try {
			if (idWorkout != null) {
				exercises = ControladorEjercicio.getInstance().getInfo(idWorkout);
				Ejercicio exercise = exercises.get(numExercise);
				if (exercise != null) {
					String nameExercise = exercise.getNameExercise();
					String descExercise = exercise.getDescription();
					rest = exercise.getRest();
					int[] sets = exercise.getSets();
					String nameWorkout = exercise.getName();

					lblExercise.setText(nameExercise + " - " + descExercise);
					lblWorkout.setText(nameWorkout);
					lblRest.setText("Rest: " + rest + "s");
					for (int i = 0; i < sets.length; i++) {
						int set = sets[i];
						if (i == 0) {
							lblExerciseSet1.setText("Set " + (i + 1) + " " + formatTime(set));
						} else if (i == 1) {
							formatTime(set);
							lblExerciseSet2.setText("Set " + (i + 1) + " " + formatTime(set));
						} else {
							formatTime(set);
							lblExerciseSet3.setText("Set " + (i + 1) + " " + formatTime(set));
						}
					}

					timekeeperWorkout = new Timekeeper("TimeKeeper Workout", lblWorkoutTimekeeper, false, 0);
					timekeeperExercise = new Timekeeper("Exercise Time", lblExerciseTime, false, 0);
				} else {
					JOptionPane.showMessageDialog(null, "there are no more exercises", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "There are no exercises to do", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (InterruptedException e) {
			JOptionPane.showMessageDialog(null, "Exercise loading has been interrupted", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (ExecutionException e) {
			JOptionPane.showMessageDialog(null, "Error loading exercise", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error generic", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void showWorkoutSummary() {
		int totalExercises = 10;
		int completedExercises = 8;
		int totalTime = 4500;

		double completionPercentage = ((double) completedExercises / totalExercises) * 100;

		String motivationalMessage = "Excellent work! Keep it up!";

		String workoutSummary = String.format(
				"<html><body><h2>Resumen de tu Workout</h2><br>" + "Ejercicios completados: %d/%d<br>"
						+ "Tiempo total: %s minutos<br>" + "Porcentaje completado: %.2f%%<br><br>"
						+ "<i>%s</i></body></html>",
				completedExercises, totalExercises, formatTime(totalTime), completionPercentage, motivationalMessage);

		// Mostrar el resumen usando JOptionPane
		JOptionPane.showMessageDialog(null, workoutSummary, "Workout Summary", JOptionPane.CLOSED_OPTION);
	}

	private String formatTime(int totalSeconds) {
		int minutes = totalSeconds / 60;
		int seconds = totalSeconds % 60;
		return String.format("%02d:%02d", minutes, seconds);
	}

	public JPanel getPanel() {
		return panel;
	}
}