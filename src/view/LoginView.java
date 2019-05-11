package view;

import javax.swing.JPanel;
import javax.swing.JPasswordField;

import model.StateModel;
import model.StateModel.ApplicationState;
import model.UserModel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JTextField;

import controller.LoginController;

public class LoginView extends JPanel {
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private LoginController loginController;
	/**
	 * Create the panel.
	 */
	public LoginView(StateModel state, UserModel user) {
		
		loginController = new LoginController(state, user);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(28, 6, 130, 26);
		add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setBounds(163, 11, 35, 16);
		add(lblNewLabel);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(163, 118, 130, 26);
		add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnNewButton = new JButton("Log in");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginController.logIn(txtUsername.getText(), txtPassword.getText());
			}
		});
		btnNewButton.setBounds(46, 182, 83, 29);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				state.setApplicationState(ApplicationState.Home);
			}
		});
		btnNewButton_1.setBounds(187, 206, 86, 29);
		add(btnNewButton_1);
	}
}
