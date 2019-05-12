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
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class LoginView extends JPanel {
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private LoginController loginController;
	/**
	 * Create the panel.
	 */
	public LoginView(StateModel state, UserModel user) {
		
		loginController = new LoginController(state, user);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{130, 35, 130, 83, 0};
		gridBagLayout.rowHeights = new int[]{29, 0, 0, 29, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setBounds(163, 11, 35, 16);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblUsername = new JLabel("Email");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.anchor = GridBagConstraints.EAST;
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 1;
		add(lblUsername, gbc_lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(28, 6, 130, 26);
		GridBagConstraints gbc_txtUsername = new GridBagConstraints();
		gbc_txtUsername.gridwidth = 2;
		gbc_txtUsername.anchor = GridBagConstraints.WEST;
		gbc_txtUsername.insets = new Insets(0, 0, 5, 5);
		gbc_txtUsername.gridx = 1;
		gbc_txtUsername.gridy = 1;
		add(txtUsername, gbc_txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 2;
		add(lblPassword, gbc_lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(163, 118, 130, 26);
		GridBagConstraints gbc_txtPassword = new GridBagConstraints();
		gbc_txtPassword.gridwidth = 2;
		gbc_txtPassword.anchor = GridBagConstraints.WEST;
		gbc_txtPassword.insets = new Insets(0, 0, 5, 5);
		gbc_txtPassword.gridx = 1;
		gbc_txtPassword.gridy = 2;
		add(txtPassword, gbc_txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnNewButton = new JButton("Log in");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginController.logIn(txtUsername.getText(), txtPassword.getText());
			}
		});
		btnNewButton.setBounds(46, 182, 83, 29);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 3;
		add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				state.setApplicationState(ApplicationState.Home);
			}
		});
		btnNewButton_1.setBounds(187, 206, 86, 29);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 3;
		add(btnNewButton_1, gbc_btnNewButton_1);
	}
}
