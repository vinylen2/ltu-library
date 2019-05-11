package view;

import javax.swing.JPanel;

import model.StateModel;
import model.UserModel;
import javax.swing.JLabel;

public class AdminHomeView extends JPanel {

	/**
	 * Create the panel.
	 */
	public AdminHomeView(StateModel state, UserModel user) {
		System.out.print("user name is" + user.getName());
	}
	
}
