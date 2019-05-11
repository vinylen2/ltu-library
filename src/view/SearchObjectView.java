package view;

import javax.swing.JPanel;
import model.StateModel;
import javax.swing.JLabel;

public class SearchObjectView extends JPanel {

	/**
	 * Create the panel.
	 */
	public SearchObjectView(StateModel state) {
		
		JLabel label = new JLabel("Search object");
		add(label);

	}

}
