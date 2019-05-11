package view;

import javax.swing.JPanel;

import library.DatabaseConnector;
import model.StateModel;
import model.StateModel.ApplicationState;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeView extends JPanel {

	/**
	 * Create the panel.
	 */
	public HomeView(StateModel state) {
		
		JButton btnLogin = new JButton("Log in");
		btnLogin.setBounds(68, 58, 83, 29);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				state.setApplicationState(ApplicationState.Login);
			}
		});
		add(btnLogin);

		JLabel lblLogin = new JLabel("Homepage");
		lblLogin.setBounds(178, 6, 67, 16);
		add(lblLogin);
		
		JButton btnNewButton_1 = new JButton("Search for book");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				state.setApplicationState(ApplicationState.SearchObject);
			}
		});
		btnNewButton_1.setBounds(163, 58, 117, 29);
		add(btnNewButton_1);
	}
}
