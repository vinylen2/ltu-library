package view;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class AddObjectView extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public AddObjectView() {
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(10);
		
		JButton btnAddObject = new JButton("Add object");
		add(btnAddObject);

	}

}
