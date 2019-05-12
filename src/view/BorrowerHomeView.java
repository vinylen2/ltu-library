package view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.StateModel;
import model.StateModel.ApplicationState;
import model.UserModel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BorrowerHomeView extends JPanel implements Observer {
	private JLabel userLabel;

	/**
	 * Create the panel.
	 */
	public BorrowerHomeView(StateModel state, UserModel user) {
		user.addObserver(this);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{162, 65, 55, 0};
		gridBagLayout.rowHeights = new int[]{23, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		this.userLabel = new JLabel(user.getName());
		GridBagConstraints gbc_userLabel = new GridBagConstraints();
		gbc_userLabel.insets = new Insets(0, 0, 5, 0);
		gbc_userLabel.anchor = GridBagConstraints.WEST;
		gbc_userLabel.gridx = 2;
		gbc_userLabel.gridy = 0;
		add(this.userLabel, gbc_userLabel);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				state.setApplicationState(ApplicationState.SearchObject);
				
			}
		});
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
		gbc_btnSearch.gridx = 0;
		gbc_btnSearch.gridy = 4;
		add(btnSearch, gbc_btnSearch);
		
		JButton btnLoans = new JButton("Loans");
		btnLoans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnLoans = new GridBagConstraints();
		gbc_btnLoans.insets = new Insets(0, 0, 5, 5);
		gbc_btnLoans.gridx = 0;
		gbc_btnLoans.gridy = 5;
		add(btnLoans, gbc_btnLoans);
		
		JButton btnAddLoans = new JButton("Add loans");
		btnAddLoans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				state.setApplicationState(ApplicationState.AddLoan);
			}
		});
		GridBagConstraints gbc_btnAddLoans = new GridBagConstraints();
		gbc_btnAddLoans.insets = new Insets(0, 0, 0, 5);
		gbc_btnAddLoans.gridx = 0;
		gbc_btnAddLoans.gridy = 6;
		add(btnAddLoans, gbc_btnAddLoans);

	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		UserModel user = (UserModel)o;
		this.userLabel.setText("Borrower - " + user.getName());
		
	}

}
