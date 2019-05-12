package view;

import javax.swing.JPanel;

import model.StateModel;
import model.UserModel;

import javax.swing.JButton;

public class AddBorrowerView extends JPanel {

	/**
	 * Create the panel.
	 */
	public AddBorrowerView(StateModel state, UserModel user) {
		
		JButton btnKnapp = new JButton("Knapp");
		add(btnKnapp);

	}

}
