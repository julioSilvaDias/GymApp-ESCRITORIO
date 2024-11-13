package gymApp.vista;

import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import gymApp.bbdd.pojos.Usuario;
import gymApp.logica.ControladorRegistro;

public class PanelRegistro {
	private JPanel panel = null;
	private JTextField textFieldName;
	private JTextField textFieldSurname;
	private JTextField textFieldEmail;
	private JTextField textFieldBirthdate;
	private JTextField textFieldPass;
	private JButton btnRegister;

	public PanelRegistro(ArrayList<JPanel> paneles) {
		panel = new JPanel();
		panel.setBounds(0, 0, 1499, 878);
		panel.setLayout(null);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Corbel", Font.BOLD, 25));
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setBounds(477, 350, 76, 29);
		panel.add(lblName);

		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldName.setBounds(477, 390, 204, 29);
		panel.add(textFieldName);
		textFieldName.setColumns(10);

		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setForeground(Color.WHITE);
		lblSurname.setFont(new Font("Corbel", Font.BOLD, 25));
		lblSurname.setBounds(800, 350, 114, 29);
		panel.add(lblSurname);

		textFieldSurname = new JTextField();
		textFieldSurname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldSurname.setColumns(10);
		textFieldSurname.setBounds(800, 390, 204, 29);
		panel.add(textFieldSurname);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Corbel", Font.BOLD, 25));
		lblEmail.setBounds(477, 447, 76, 29);
		panel.add(lblEmail);

		textFieldEmail = new JTextField();
		textFieldEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(477, 487, 527, 29);
		panel.add(textFieldEmail);

		JLabel lblBirthdate = new JLabel("Birthdate");
		lblBirthdate.setForeground(Color.WHITE);
		lblBirthdate.setFont(new Font("Corbel", Font.BOLD, 25));
		lblBirthdate.setBounds(477, 552, 114, 29);
		panel.add(lblBirthdate);

		textFieldBirthdate = new JTextField();
		textFieldBirthdate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldBirthdate.setColumns(10);
		textFieldBirthdate.setBounds(477, 592, 204, 29);
		panel.add(textFieldBirthdate);

		JLabel lblPass = new JLabel("Password");
		lblPass.setForeground(Color.WHITE);
		lblPass.setFont(new Font("Corbel", Font.BOLD, 25));
		lblPass.setBounds(800, 552, 114, 29);
		panel.add(lblPass);

		textFieldPass = new JTextField();
		textFieldPass.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldPass.setColumns(10);
		textFieldPass.setBounds(800, 592, 204, 29);
		panel.add(textFieldPass);

		textFieldName.getDocument().addDocumentListener(validate());
		textFieldSurname.getDocument().addDocumentListener(validate());
		textFieldEmail.getDocument().addDocumentListener(validate());
		textFieldBirthdate.getDocument().addDocumentListener(validate());
		textFieldPass.getDocument().addDocumentListener(validate());

		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paneles.get(0).setVisible(true);
				paneles.get(1).setVisible(false);
				paneles.get(2).setVisible(false);
				paneles.get(3).setVisible(false);
				paneles.get(4).setVisible(false);
				paneles.get(5).setVisible(false);
			}
		});
		btnNewButton.setBounds(477, 764, 114, 47);
		panel.add(btnNewButton);

		btnRegister = new JButton("Register");
		btnRegister.setEnabled(false);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Usuario userExists = new ControladorRegistro().existsUser(textFieldName.getText());
					if (userExists.getName() != null) {
						JOptionPane.showMessageDialog(null, "User already exists", "Information",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						new ControladorRegistro().addUser(textFieldName.getText(), textFieldSurname.getText(),
								textFieldEmail.getText(), textFieldBirthdate.getText(), textFieldPass.getText());
						JOptionPane.showMessageDialog(null, "You have registered!", "Information",
								JOptionPane.INFORMATION_MESSAGE);
						paneles.get(0).setVisible(true);
						paneles.get(1).setVisible(false);
						paneles.get(2).setVisible(false);
						paneles.get(3).setVisible(false);
						paneles.get(4).setVisible(false);
						paneles.get(5).setVisible(false);
						textFieldName.setText("");
						textFieldSurname.setText("");
						textFieldEmail.setText("");
						textFieldBirthdate.setText("");
						textFieldPass.setText("");
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Error database", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnRegister.setBounds(890, 764, 114, 47);
		panel.add(btnRegister);

		JLabel fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(PanelLogin.class.getResource("/images/REGISTRO.png")));
		fondo.setBounds(0, 0, 1499, 878);
		panel.add(fondo);
	}

	private DocumentListener validate() {
		return new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				enableButton();
			}

			public void removeUpdate(DocumentEvent e) {
				enableButton();
			}

			public void changedUpdate(DocumentEvent e) {
				enableButton();
			}
		};
	}

	protected void enableButton() {
		boolean habilitar = false;
		if (!textFieldName.getText().isEmpty() && !textFieldSurname.getText().isEmpty()
				&& !textFieldEmail.getText().isEmpty() && !textFieldBirthdate.getText().isEmpty()
				&& !textFieldPass.getText().isEmpty() && validateEmail(textFieldEmail.getText())
				&& validateBirthdate(textFieldBirthdate.getText())) {
			habilitar = true;
		}else {
			habilitar = false;
		}

		btnRegister.setEnabled(habilitar);
	}

	private boolean validateEmail(String email) {
		return Pattern.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$", email);
	}

	private boolean validateBirthdate(String birthDate) {
		if (birthDate.matches("\\d{2}-\\d{2}-\\d{4}")) {
			try {
				SimpleDateFormat simpleFormat = new SimpleDateFormat("dd-MM-yyyy");
				simpleFormat.setLenient(false);
				simpleFormat.parse(birthDate);
				return true;
			} catch (ParseException e) {
				return false;
			}
		} else {
			return false;
		}
	}

	public JPanel getPanel() {
		return panel;
	}
}
