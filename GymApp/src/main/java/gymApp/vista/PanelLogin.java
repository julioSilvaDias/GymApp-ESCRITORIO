package gymApp.vista;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

import gymApp.logica.ControladorLogin;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class PanelLogin {
	private JPanel panel = null;
	private JTextField textFieldUser;
	private JTextField textFieldPassword;

	public PanelLogin(ArrayList<JPanel> paneles) {
		panel = new JPanel();
		panel.setBounds(0, 0, 1499, 878);
		panel.setLayout(null);

		textFieldPassword = new JTextField();
		textFieldPassword.setFont(new Font("Corbel", Font.PLAIN, 20));
		textFieldPassword.setBackground(new Color(251, 251, 251));
		textFieldPassword.setBounds(675, 509, 253, 31);
		panel.add(textFieldPassword);
		textFieldPassword.setColumns(10);

		textFieldUser = new JTextField();
		textFieldUser.setFont(new Font("Corbel", Font.PLAIN, 20));
		textFieldUser.setBackground(new Color(251, 251, 251));
		textFieldUser.setBounds(675, 420, 253, 31);
		panel.add(textFieldUser);
		textFieldUser.setColumns(10);

		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				paneles.get(0).setVisible(false);
				paneles.get(1).setVisible(true);
				paneles.get(2).setVisible(false);
				paneles.get(3).setVisible(false);
				paneles.get(4).setVisible(false);
			}
		});
		btnRegister.setForeground(new Color(255, 255, 255));
		btnRegister.setFont(new Font("Corbel", Font.BOLD, 24));
		btnRegister.setBackground(new Color(113, 105, 105));
		btnRegister.setBounds(790, 725, 122, 44);
		panel.add(btnRegister);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ControladorLogin users = new ControladorLogin();

				String check = "";

				try {
					check = users.checkLogin(textFieldUser.getText(), textFieldPassword.getText());
				} catch (InterruptedException | ExecutionException | IOException e1) {
					JOptionPane.showMessageDialog(null, "Error al conectar a los datos del Usuario");

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Error General");
				}

				if ("Correct Login".equals(check)) {
					JOptionPane.showMessageDialog(null, "Correct Login");
					PanelPerfil.loadUserData(textFieldUser.getText());
					paneles.get(0).setVisible(false);
					paneles.get(1).setVisible(false);
					paneles.get(2).setVisible(true);
					paneles.get(3).setVisible(false);
					paneles.get(4).setVisible(false);
				}else if("Correct Login. Local user found".equals(check)) {
					JOptionPane.showMessageDialog(null, "Correct Login. Local user found");
					PanelPerfil.loadUserData(textFieldUser.getText());
					paneles.get(0).setVisible(false);
					paneles.get(1).setVisible(false);
					paneles.get(2).setVisible(true);
					paneles.get(3).setVisible(false);
					paneles.get(4).setVisible(false);
				} else if ("User does not exist".equals(check)) {
					JOptionPane.showMessageDialog(null, "Incorrect username or password");
				} else {
					JOptionPane.showMessageDialog(null, "Incorrect username or passwords");
				}

			}
		});
		btnLogin.setFont(new Font("Corbel", Font.BOLD, 25));
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBackground(new Color(113, 105, 105));
		btnLogin.setBounds(592, 725, 131, 44);
		panel.add(btnLogin);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setFont(new Font("Corbel", Font.BOLD, 25));
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBounds(555, 509, 122, 44);
		panel.add(lblPassword);

		JLabel lblUser = new JLabel("User:");
		lblUser.setHorizontalAlignment(SwingConstants.LEFT);
		lblUser.setFont(new Font("Corbel", Font.BOLD, 25));
		lblUser.setForeground(Color.WHITE);
		lblUser.setBounds(555, 420, 102, 31);
		panel.add(lblUser);

		JLabel fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(PanelLogin.class.getResource("/images/LOGIN.png")));
		fondo.setBounds(0, 0, 1489, 867);
		panel.add(fondo);
	}

	public JPanel getPanel() {
		return panel;
	}
}
