package gymApp.vista;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

import gymApp.bbdd.gestor.GestorUsuario;
import gymApp.bbdd.pojos.Usuario;
import gymApp.logica.ControladorPerfil;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelPerfil {
	private JPanel panel = null;
	public JTextField textFieldName;
	private JTextField textFieldSurname;
	private JTextField textFieldBirthdate;
	private JTextField textFieldPassword;
	private JTextField textFieldEmail;

	public PanelPerfil(ArrayList<JPanel> paneles) {
		panel = new JPanel();
		panel.setBounds(0, 0, 1499, 878);
		panel.setLayout(null);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Corbel", Font.BOLD, 40));
		btnSave.setBackground(new Color(113, 105, 105));
		btnSave.setForeground(new Color(251, 251, 251));
		btnSave.setBounds(1185, 727, 168, 56);
		panel.add(btnSave);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paneles.get(0).setVisible(false);
				paneles.get(1).setVisible(false);
				paneles.get(2).setVisible(true);
				paneles.get(3).setVisible(false);
				paneles.get(4).setVisible(false);
			}
		});
		btnHome.setFont(new Font("Corbel", Font.BOLD, 40));
		btnHome.setForeground(new Color(251, 251, 251));
		btnHome.setBackground(new Color(113, 105, 105));
		btnHome.setBounds(896, 727, 168, 56);
		panel.add(btnHome);

		textFieldEmail = new JTextField();
		textFieldEmail.setFont(new Font("Corbel", Font.PLAIN, 30));
		textFieldEmail.setForeground(Color.WHITE);
		textFieldEmail.setBackground(new Color(113, 105, 105));
		textFieldEmail.setBounds(1004, 442, 440, 49);
		panel.add(textFieldEmail);
		textFieldEmail.setColumns(10);

		textFieldPassword = new JTextField();
		textFieldPassword.setForeground(Color.WHITE);
		textFieldPassword.setFont(new Font("Corbel", Font.PLAIN, 30));
		textFieldPassword.setBackground(new Color(113, 105, 105));
		textFieldPassword.setBounds(1004, 535, 440, 49);
		panel.add(textFieldPassword);
		textFieldPassword.setColumns(10);

		textFieldBirthdate = new JTextField();
		textFieldBirthdate.setFont(new Font("Corbel", Font.PLAIN, 30));
		textFieldBirthdate.setForeground(Color.WHITE);
		textFieldBirthdate.setBackground(new Color(113, 105, 105));
		textFieldBirthdate.setBounds(1004, 351, 440, 49);
		panel.add(textFieldBirthdate);
		textFieldBirthdate.setColumns(10);

		textFieldSurname = new JTextField();
		textFieldSurname.setFont(new Font("Corbel", Font.PLAIN, 30));
		textFieldSurname.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldSurname.setForeground(Color.WHITE);
		textFieldSurname.setBackground(new Color(113, 105, 105));
		textFieldSurname.setBounds(1004, 261, 440, 49);
		panel.add(textFieldSurname);
		textFieldSurname.setColumns(10);

		textFieldName = new JTextField();
		textFieldName.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldName.setFont(new Font("Corbel", Font.PLAIN, 30));
		textFieldName.setForeground(Color.WHITE);
		textFieldName.setBackground(new Color(113, 105, 105));
		textFieldName.setBounds(1004, 171, 440, 49);
		panel.add(textFieldName);
		textFieldName.setColumns(10);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Corbel", Font.BOLD, 40));
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setForeground(new Color(251, 251, 251));
		lblEmail.setBounds(811, 455, 183, 49);
		panel.add(lblEmail);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Corbel", Font.BOLD, 40));
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setForeground(new Color(251, 251, 251));
		lblPassword.setBounds(811, 551, 183, 49);
		panel.add(lblPassword);

		JLabel lblBirthdate = new JLabel("Birthdate:");
		lblBirthdate.setFont(new Font("Corbel", Font.BOLD, 40));
		lblBirthdate.setForeground(new Color(251, 251, 251));
		lblBirthdate.setHorizontalAlignment(SwingConstants.LEFT);
		lblBirthdate.setBounds(811, 364, 183, 56);
		panel.add(lblBirthdate);

		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setForeground(new Color(251, 251, 251));
		lblSurname.setFont(new Font("Corbel", Font.BOLD, 40));
		lblSurname.setHorizontalAlignment(SwingConstants.LEFT);
		lblSurname.setBounds(811, 272, 183, 56);
		panel.add(lblSurname);

		JLabel lblName = new JLabel("Name: ");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setFont(new Font("Corbel", Font.BOLD, 40));
		lblName.setForeground(new Color(251, 251, 251));
		lblName.setBounds(811, 183, 183, 56);
		panel.add(lblName);

		JLabel fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(PanelLogin.class.getResource("/images/PERFILUSUARIO.png")));
		fondo.setBounds(0, 0, 1489, 867);
		panel.add(fondo);
	}

	public void loadUserData() {
		GestorUsuario gestorUsuario = new GestorUsuario();
		Usuario user = new Usuario();
		gestorUsuario.getAllData(textFieldName.setText(user.getName()), textFieldSurname.setText(user.getSurname()), textFieldBirthdate.setText(user.getBrithdate()), textFieldEmail.setText(user.getEmail()), textFieldPassword.setText(user.getPassword()));
		textFieldName.setText(user.getName);
	}
	
	public JPanel getPanel() {
		return panel;
	}
}
