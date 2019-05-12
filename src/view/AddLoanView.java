package view;

import java.util.Observer;

import javax.swing.JPanel;

import model.StateModel;
import model.UserModel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;

public class AddLoanView extends JPanel {

	/**
	 * Create the panel.
	 */
	public AddLoanView(StateModel state, UserModel user) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{180, 89, 0};
		gridBagLayout.rowHeights = new int[]{23, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblAddLoan = new JLabel("Add loan");
		GridBagConstraints gbc_lblAddLoan = new GridBagConstraints();
		gbc_lblAddLoan.insets = new Insets(0, 0, 5, 0);
		gbc_lblAddLoan.gridx = 1;
		gbc_lblAddLoan.gridy = 0;
		add(lblAddLoan, gbc_lblAddLoan);
		
		JButton btnNewButton = new JButton("New button");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 4;
		add(btnNewButton, gbc_btnNewButton);
		
		

	}

}
