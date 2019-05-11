package view;

import javax.swing.JPanel;

import model.StateModel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JTextField;

public class LoginView extends JPanel {
	private JTextField txtUsername;
	private JTextField txtPassword;
	/**
	 * Create the panel.
	 */
	public LoginView(StateModel state) {
		setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setText("Username");
		txtUsername.setBounds(140, 81, 130, 26);
		add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setBounds(204, 6, 35, 16);
		add(lblNewLabel);
		
		txtPassword = new JTextField();
		txtPassword.setText("Password");
		txtPassword.setBounds(140, 119, 130, 26);
		add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnNewButton = new JButton("Log in");
		btnNewButton.setBounds(214, 184, 117, 29);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setBounds(89, 184, 117, 29);
		add(btnNewButton_1);
	}
}
