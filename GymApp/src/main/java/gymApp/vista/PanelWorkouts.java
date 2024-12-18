package gymApp.vista;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import gymApp.bbdd.pojos.Ejercicio;
import gymApp.bbdd.pojos.Workout;
import gymApp.logica.ControladorEjercicio;
import gymApp.logica.ControladorWorkouts;
import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelWorkouts {
	private JPanel panel = null;
	private ControladorWorkouts controladorWorkouts = new ControladorWorkouts();
	private ArrayList<Workout> workouts;
	private JPanel labelPanel = null;
	private JLabel infoWorkout = null;
	private JLabel videoThumbnail = null;
	private JTable tableExercises;
	private String[] columns = { "Name" };
	private Object[][] data;
	private DefaultTableModel model = new DefaultTableModel(data, columns);
	private ArrayList<Ejercicio> exercises;
	private PanelHistorico panelHistorico;


	public PanelWorkouts(ArrayList<JPanel> paneles, PanelHistorico panelHistorico,  PanelEjercicio panelEjercicio) {
		this.panelHistorico = panelHistorico;

		try {
			
			ArrayList<Workout> workouts = null;
			panel = new JPanel();
			panel.setBounds(0, 0, 1499, 878);
			panel.setOpaque(false);
			panel.setLayout(null);

			JScrollPane scrollPaneExercises = new JScrollPane();
			scrollPaneExercises.setBounds(970, 118, 519, 750);
			scrollPaneExercises.setBorder(null);
			scrollPaneExercises.setOpaque(false);
			scrollPaneExercises.getViewport().setOpaque(false);

			labelPanel = new JPanel();
			labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
			labelPanel.setOpaque(false);
			labelPanel.setBounds(0, 0, panel.getWidth(), panel.getHeight());

			JScrollPane scrollPaneWorkout = new JScrollPane(labelPanel);
			scrollPaneWorkout.setBounds(21, 118, 432, 760);

			scrollPaneWorkout.setBorder(null);
			scrollPaneWorkout.setOpaque(false);
			scrollPaneWorkout.getViewport().setOpaque(false);
			
			JButton btnNewButton = new JButton("Start");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					panelEjercicio.refresPanelExercise(0);
					paneles.get(0).setVisible(false);
					paneles.get(1).setVisible(false);
					paneles.get(2).setVisible(false);
					paneles.get(3).setVisible(true);
					paneles.get(4).setVisible(false);
					paneles.get(5).setVisible(false);
				}
			});
			btnNewButton.setBounds(647, 786, 138, 31);
			panel.add(btnNewButton);

			JButton btnHistorico = new JButton("History");

			btnHistorico.setBounds(1201, 43, 89, 23);
			panel.add(btnHistorico);

			JLabel botonPerfil = new JLabel("");
			botonPerfil.setBounds(1384, 11, 91, 87);
			panel.add(botonPerfil);
			scrollPaneWorkout.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPaneWorkout.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

			panel.add(scrollPaneWorkout);
			panel.add(scrollPaneExercises);

			tableExercises = new JTable(model);
			scrollPaneExercises.setViewportView(tableExercises);

			videoThumbnail = new JLabel();
			videoThumbnail.setBounds(463, 414, 497, 320);
			panel.add(videoThumbnail);

			infoWorkout = new JLabel("");
			infoWorkout.setFont(new Font("Arial", Font.PLAIN, 20));
			infoWorkout.setForeground(new Color(255, 255, 255));
			infoWorkout.setBounds(521, 142, 402, 203);
			panel.add(infoWorkout);

			JLabel backgroundLabel = new JLabel();
			backgroundLabel.setBounds(0, 0, 1499, 878);
			backgroundLabel.setIcon(new ImageIcon(PanelLogin.class.getResource("/images/WORKOUTS.png")));
			panel.add(backgroundLabel);

			workouts = controladorWorkouts.getAllWorkouts();

			for (Workout workout : workouts) {
				JLabel label = new JLabel();
				label.setIcon(new ImageIcon(PanelLogin.class.getResource("/images/BACKLBL.png")));
				label.setText(workout.getName());
				label.setFont(new Font("Arial", Font.PLAIN, 40));
				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setVerticalAlignment(SwingConstants.CENTER);
				label.setForeground(Color.WHITE);

				label.setHorizontalTextPosition(SwingConstants.CENTER);
				label.setVerticalTextPosition(SwingConstants.CENTER);
				label.setPreferredSize(new Dimension(400, 100));
				label.setMaximumSize(new Dimension(400, 100));
				label.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

				addClickListener(label, workout);

				labelPanel.add(label);
				labelPanel.add(Box.createVerticalStrut(10));
			}
			panel.setComponentZOrder(backgroundLabel, panel.getComponentCount() - 1);

			panel.revalidate();
			panel.repaint();

			botonPerfil.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					paneles.get(0).setVisible(false);
					paneles.get(1).setVisible(false);
					paneles.get(2).setVisible(false);
					paneles.get(3).setVisible(false);
					paneles.get(4).setVisible(true);
				}
			});

			btnHistorico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
			        for (JPanel p : paneles) {
			            p.setVisible(false);
			        }

			        paneles.get(5).setVisible(true);
			        panelHistorico.refreshHistoric();
				}
			});

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,
					"ERROR!!! " + "\n" + "No se ha encontrado el acceso a la base de datos");
			e.printStackTrace();

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "ERROR!!! " + "\n" + "Fallo al leer la base de datos");
			e.printStackTrace();

		} catch (ExecutionException e) {
			JOptionPane.showMessageDialog(null, "ERROR!!! " + "\n" + "El programa fue interrompido");
			e.printStackTrace();

		} catch (InterruptedException e) {
			JOptionPane.showMessageDialog(null, "ERROR!!! " + "\n" + "El programa fue interrompido");
			e.printStackTrace();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR!!! " + "\n" + "Error generico");
			e.printStackTrace();
		}
	}

	private void addClickListener(JLabel label, Workout workout) {
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showInfoWorkout(workout);
				showVideoWorkout(workout);
				showExercisesTable(workout.getId());
				ControladorEjercicio.getInstance().setId(workout.getId());
			}
		});
	}

	private void showExercisesTable(String id) {
		try {
			exercises = controladorWorkouts.getExercisesById(id);
			model.setRowCount(0);

			for (Ejercicio ejercicio : exercises) {
				Object[] vector = { ejercicio.getNameExercise() };
				model.addRow(vector);
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,
					"ERROR!!! " + "\n" + "No se ha encontrado el acceso a la base de datos");
			e.printStackTrace();

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "ERROR!!! " + "\n" + "Fallo al leer la base de datos");
			e.printStackTrace();

		} catch (ExecutionException e) {
			JOptionPane.showMessageDialog(null, "ERROR!!! " + "\n" + "El programa fue interrompido");
			e.printStackTrace();

		} catch (InterruptedException e) {
			JOptionPane.showMessageDialog(null, "ERROR!!! " + "\n" + "El programa fue interrompido");
			e.printStackTrace();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR!!! " + "\n" + "Error generico");
			e.printStackTrace();
		}

	}

	private void showVideoWorkout(Workout workout) {
		try {
			videoThumbnail.setIcon(null);

			String videoUrl = workout.getVideo();
			String videoId = extractVideoId(videoUrl);

			String thumbnailUrl = "https://img.youtube.com/vi/" + videoId + "/hqdefault.jpg";
			ImageIcon thumbnailIcon = new ImageIcon(new URL(thumbnailUrl));

			videoThumbnail.setIcon(thumbnailIcon);

			videoThumbnail.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						Desktop.getDesktop().browse(new URI(videoUrl));
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});

			panel.revalidate();
			panel.repaint();
		} catch (Exception e) {

		}
	}

	private String extractVideoId(String videoUrl) {
		String[] parts = videoUrl.split("v=");
		return parts[1].split("&")[0];
	}

	private void showInfoWorkout(Workout workout) {
		String nivel = "";
		infoWorkout.setText("");

		if (workout.getNivel() == 0) {
			nivel = "Aprendiz";
		} else if (workout.getNivel() == 1) {
			nivel = "Iniciante";
		} else if (workout.getNivel() == 2) {
			nivel = "Avazando";
		} else {
			nivel = "Ultimo nivel";
		}
		infoWorkout.setText("<html>" + "Name: " + workout.getName() + "<br>" + "Numero de Ejercicios: "
				+ workout.getExercises() + "<br>" + "nivel: " + nivel + "</html>");
	}

	public JPanel getPanel() {
		return panel;
	}
}
