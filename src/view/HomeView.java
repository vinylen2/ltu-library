package view;

import javax.swing.JPanel;

import library.DatabaseConnector;
import model.StateModel;
import model.StateModel.ApplicationState;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class HomeView extends JPanel {

	/**
	 * Create the panel.
	 */
	public HomeView(StateModel state) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{73, 67, 83, 143, 0};
		gridBagLayout.rowHeights = new int[]{29, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton btnLogin = new JButton("Log in");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				state.setApplicationState(ApplicationState.Login);
			}
		});
		
		JLabel lblLogin = new JLabel("Homepage");
		GridBagConstraints gbc_lblLogin = new GridBagConstraints();
		gbc_lblLogin.anchor = GridBagConstraints.WEST;
		gbc_lblLogin.insets = new Insets(0, 0, 5, 5);
		gbc_lblLogin.gridx = 1;
		gbc_lblLogin.gridy = 0;
		add(lblLogin, gbc_lblLogin);
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnLogin.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogin.gridx = 1;
		gbc_btnLogin.gridy = 1;
		add(btnLogin, gbc_btnLogin);
		
		JButton btnNewButton_1 = new JButton("Search for book");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				state.setApplicationState(ApplicationState.SearchObject);
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 2;
		add(btnNewButton_1, gbc_btnNewButton_1);
	}
}
