package view;

import javax.swing.JPanel;

import model.StateModel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class LoginView extends JPanel {

	/**
	 * Create the panel.
	 */
	public LoginView(StateModel state) {
		
		JLabel lblLogin = new JLabel("Login");
		add(lblLogin);
		
	}
}
