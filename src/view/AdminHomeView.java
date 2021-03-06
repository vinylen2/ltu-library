package view;

import javax.swing.JPanel;

import model.StateModel;
import model.StateModel.ApplicationState;
import model.UserModel;
import controller.LoginController;

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
	private LoginController loginController;

	/**
	 * Create the panel.
	 */
	public AdminHomeView(StateModel state, UserModel user) {
		// add observable UserModel user
		user.addObserver(this);
		loginController = new LoginController(state, user);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{63, 117, 129, 112, 0};
		gridBagLayout.rowHeights = new int[]{29, 0, 0, 29, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
				state.setApplicationState(ApplicationState.AddObject);
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
				state.setApplicationState(ApplicationState.SearchObject);
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
		gbc_btnAddBorrower.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddBorrower.gridx = 1;
		gbc_btnAddBorrower.gridy = 4;
		add(btnAddBorrower, gbc_btnAddBorrower);
		
		JButton btnAddLoan = new JButton("Add loan");
		GridBagConstraints gbc_btnAddLoan = new GridBagConstraints();
		gbc_btnAddLoan.anchor = GridBagConstraints.WEST;
		gbc_btnAddLoan.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddLoan.gridx = 1;
		gbc_btnAddLoan.gridy = 5;
		add(btnAddLoan, gbc_btnAddLoan);
		
		JButton btnNewButton = new JButton("Return loans");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				state.setApplicationState(ApplicationState.ReturnLoan);
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 6;
		add(btnNewButton, gbc_btnNewButton);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginController.logOut();
				state.setApplicationState(ApplicationState.Home);
			}
		});

		GridBagConstraints gbc_btnLogout = new GridBagConstraints();
		gbc_btnLogout.anchor = GridBagConstraints.WEST;
		gbc_btnLogout.insets = new Insets(0, 0, 0, 5);
		gbc_btnLogout.gridx = 1;
		gbc_btnLogout.gridy = 7;
		add(btnLogout, gbc_btnLogout);
		btnAddLoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				state.setApplicationState(ApplicationState.AddLoan);
			}
		});
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		UserModel user = (UserModel)o;
		this.userLabel.setText("Administrator - " + user.getName());
	}
}
