package view;

import javax.swing.JPanel;

import model.StateModel;
import model.StateModel.ApplicationState;
import model.UserModel;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class AdminHomeView extends JPanel implements Observer {
	private JLabel userLabel;

	/**
	 * Create the panel.
	 */
	public AdminHomeView(StateModel state, UserModel user) {
		// add observable UserModel user
		user.addObserver(this);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{63, 117, 129, 112, 0};
		gridBagLayout.rowHeights = new int[]{29, 0, 0, 29, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		this.userLabel = new JLabel(user.getName());
		userLabel.setBounds(249, 11, 73, 16);
		GridBagConstraints gbc_userLabel = new GridBagConstraints();
		gbc_userLabel.anchor = GridBagConstraints.WEST;
		gbc_userLabel.insets = new Insets(0, 0, 5, 5);
		gbc_userLabel.gridx = 1;
		gbc_userLabel.gridy = 0;
		add(this.userLabel, gbc_userLabel);
		
		JButton btnAddObject = new JButton("Add object");
		btnAddObject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnAddObject = new GridBagConstraints();
		gbc_btnAddObject.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddObject.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnAddObject.gridx = 1;
		gbc_btnAddObject.gridy = 2;
		add(btnAddObject, gbc_btnAddObject);
		
		JButton btnSearchObject = new JButton("Search object");
		btnSearchObject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnSearchObject = new GridBagConstraints();
		gbc_btnSearchObject.insets = new Insets(0, 0, 5, 5);
		gbc_btnSearchObject.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnSearchObject.gridwidth = 2;
		gbc_btnSearchObject.gridx = 1;
		gbc_btnSearchObject.gridy = 3;
		add(btnSearchObject, gbc_btnSearchObject);
		
		JButton btnAddBorrower = new JButton("Add borrower");
		btnAddBorrower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				state.setApplicationState(ApplicationState.AddBorrower);
			}
		});
		GridBagConstraints gbc_btnAddBorrower = new GridBagConstraints();
		gbc_btnAddBorrower.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnAddBorrower.insets = new Insets(0, 0, 0, 5);
		gbc_btnAddBorrower.gridx = 1;
		gbc_btnAddBorrower.gridy = 4;
		add(btnAddBorrower, gbc_btnAddBorrower);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		UserModel user = (UserModel)o;
		this.userLabel.setText("Administrator - " + user.getName());
	}
}
