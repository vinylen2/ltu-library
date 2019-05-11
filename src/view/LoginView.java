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
	/**
	 * Create the panel.
	 */
	public LoginView(StateModel state) {
		
		JLabel lblNewLabel = new JLabel("New label");
		add(lblNewLabel);
		System.out.println("Login");
	}
}
