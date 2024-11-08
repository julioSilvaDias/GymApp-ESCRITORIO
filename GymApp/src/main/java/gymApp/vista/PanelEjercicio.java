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

public class PanelEjercicio {
	private JPanel panel = null;
	private boolean workoutTimekeeper = false;
	private boolean exerciseTimekeeper = false;
	private JLabel lblExercise = null;
	private JLabel lblWorkout = null;
	private JLabel lblExerciseTime = null;
	private JLabel lblRest = null;
	private JLabel lblworkoutTimekeeper = null;
	private JLabel lblExerciseSet1 = null;
	private JLabel lblExerciseSet2 = null;
	private JLabel lblExerciseSet3 = null;
	private String nameExercise = null;
	private String descExercise = null;
	private String nameWorkout = null;
	private int rest = 0;
	private Timekeeper timekeeperWorkout = null;
	private Timekeeper timekeeperExercise = null;
	private Timekeeper timekeeperRest = null;

	public PanelEjercicio(ArrayList<JPanel> paneles) {
		panel = new JPanel();
		panel.setBounds(0, 0, 1499, 878);
		panel.setLayout(null);

		lblworkoutTimekeeper = new JLabel("TimeKeeper Workout: 00:00");
		lblworkoutTimekeeper.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255),
				new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
		lblworkoutTimekeeper.setFont(new Font("Corbel", Font.BOLD, 30));
		lblworkoutTimekeeper.setForeground(new Color(255, 255, 255));
		lblworkoutTimekeeper.setBounds(10, 110, 482, 101);
		panel.add(lblworkoutTimekeeper);

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

		lblExerciseSet1 = new JLabel();
		lblExerciseSet1.setForeground(Color.WHITE);
		lblExerciseSet1.setFont(new Font("Corbel", Font.BOLD, 30));
		lblExerciseSet1.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255),
				new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
		lblExerciseSet1.setBounds(502, 313, 482, 101);
		panel.add(lblExerciseSet1);

		lblExerciseSet2 = new JLabel();
		lblExerciseSet2.setForeground(Color.WHITE);
		lblExerciseSet2.setFont(new Font("Corbel", Font.BOLD, 30));
		lblExerciseSet2.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255),
				new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
		lblExerciseSet2.setBounds(502, 425, 482, 101);
		panel.add(lblExerciseSet2);

		lblExerciseSet3 = new JLabel();
		lblExerciseSet3.setForeground(Color.WHITE);
		lblExerciseSet3.setFont(new Font("Corbel", Font.BOLD, 30));
		lblExerciseSet3.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255),
				new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
		lblExerciseSet3.setBounds(502, 537, 482, 101);
		panel.add(lblExerciseSet3);

		JButton btnStart = new JButton();
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!workoutTimekeeper && !exerciseTimekeeper) {
					timekeeperWorkout.startTimekeeper();
					timekeeperExercise.startTimekeeper();
					timekeeperRest.startTimekeeper();
					timekeeperWorkout.start();
					timekeeperExercise.start();
					timekeeperRest.start();
					workoutTimekeeper = true;
					exerciseTimekeeper = true;
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

		try {
			if (idWorkout != null) {
				Ejercicio exercise = ControladorEjercicio.getInstance().getInfo(idWorkout);
				nameExercise = exercise.getNameExercise();
				descExercise = exercise.getDescription();
				rest = exercise.getRest();
				nameWorkout = exercise.getName();
				lblExercise.setText(nameExercise + " - " + descExercise);
				lblWorkout.setText(nameWorkout);
				lblRest.setText("Rest: " + String.valueOf(rest) + "s");
				timekeeperWorkout = new Timekeeper("TimeKeeper Workout", lblworkoutTimekeeper, false, 0);
				timekeeperExercise = new Timekeeper("Exercise Time", lblExerciseTime, false, 0);
				timekeeperRest = new Timekeeper("Rest: ", lblRest, true, rest);
				// Timekeeper timekeeperSet = new Timekeeper("", lblTimeKeeperWorkout);
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