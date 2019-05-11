package view;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeView extends JPanel {

	/**
	 * Create the panel.
	 */
	public HomeView() {
		

		JButton btnNewButton = new JButton("Knapp");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(btnNewButton);

	}

}
