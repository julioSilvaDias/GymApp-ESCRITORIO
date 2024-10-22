package gymApp.vista;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class PanelRegistro {
	private JPanel panel = null;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	public PanelRegistro(ArrayList<JPanel> paneles) {
		panel = new JPanel();
		panel.setBounds(0, 0, 1499, 878);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.setFont(new Font("Corbel", Font.BOLD, 25));
		btnNewButton.setBounds(509, 766, 126, 49);
		panel.add(btnNewButton);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setFont(new Font("Corbel", Font.BOLD, 25));
		btnRegister.setBounds(832, 766, 126, 49);
		panel.add(btnRegister);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Corbel", Font.BOLD, 25));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(509, 383, 86, 28);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Corbel", Font.PLAIN, 20));
		textField.setBounds(509, 422, 180, 28);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Surname");
		lblNewLabel_1.setFont(new Font("Corbel", Font.BOLD, 25));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(802, 383, 126, 28);
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Corbel", Font.PLAIN, 20));
		textField_1.setColumns(10);
		textField_1.setBounds(802, 422, 180, 28);
		panel.add(textField_1);
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setFont(new Font("Corbel", Font.BOLD, 25));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(509, 461, 86, 28);
		panel.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Corbel", Font.PLAIN, 24));
		textField_2.setColumns(10);
		textField_2.setBounds(509, 500, 473, 28);
		panel.add(textField_2);
		
		JLabel lblNewLabel_3 = new JLabel("Birthdate");
		lblNewLabel_3.setFont(new Font("Corbel", Font.BOLD, 26));
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(509, 539, 126, 28);
		panel.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Corbel", Font.PLAIN, 20));
		textField_3.setColumns(10);
		textField_3.setBounds(509, 578, 180, 28);
		panel.add(textField_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Password");
		lblNewLabel_3_1.setFont(new Font("Corbel", Font.BOLD, 25));
		lblNewLabel_3_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_3_1.setBounds(802, 539, 126, 28);
		panel.add(lblNewLabel_3_1);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Corbel", Font.PLAIN, 20));
		textField_4.setColumns(10);
		textField_4.setBounds(802, 578, 180, 28);
		panel.add(textField_4);
		
		JLabel fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(PanelLogin.class.getResource("/images/REGISTRO.png")));
		fondo.setBounds(0, 0, 1499, 878);
		panel.add(fondo);
	}

	public JPanel getPanel() {
		return panel;
	}
}
