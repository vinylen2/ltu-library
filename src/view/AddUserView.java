package view;

import javax.swing.JPanel;

import model.StateModel;
import model.UserModel;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;

import controller.UserController;
import library.DatabaseConnector;

import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JComponent;

public class AddUserView extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JLabel lblFirstName;
	private JLabel lblLastName;
	private JLabel lblSsn;
	private JLabel lblEmail;
	private JLabel lblStreetAddress;
	private JLabel lblPostalCode;
	private JLabel lblMobileNumber;
	private JLabel label;
	
	
	private JLabel lblRoll;
	private JComboBox comboBox;
	private UserController userController;

	/**
	 * Create the panel.
	 */
	
	
	public AddUserView(StateModel state, UserModel user) {
		
		userController = new UserController(user);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 193, 63, 0};
		gridBagLayout.rowHeights = new int[]{23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblFirstName = new JLabel("First name");
		GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
		gbc_lblFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirstName.anchor = GridBagConstraints.EAST;
		gbc_lblFirstName.gridx = 0;
		gbc_lblFirstName.gridy = 1;
		add(lblFirstName, gbc_lblFirstName);
		
		// textfield for firstname input
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		
		lblLastName = new JLabel("Last name");
		GridBagConstraints gbc_lblLastName = new GridBagConstraints();
		gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
		gbc_lblLastName.anchor = GridBagConstraints.EAST;
		gbc_lblLastName.gridx = 0;
		gbc_lblLastName.gridy = 2;
		add(lblLastName, gbc_lblLastName);
		
		// textfield for lastname input
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 2;
		add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		lblSsn = new JLabel("SSN");
		GridBagConstraints gbc_lblSsn = new GridBagConstraints();
		gbc_lblSsn.insets = new Insets(0, 0, 5, 5);
		gbc_lblSsn.anchor = GridBagConstraints.EAST;
		gbc_lblSsn.gridx = 0;
		gbc_lblSsn.gridy = 3;
		add(lblSsn, gbc_lblSsn);
		
		// textfield for SSN input
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 3;
		add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		lblEmail = new JLabel("E-mail");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 4;
		add(lblEmail, gbc_lblEmail);
		
		// textfield for E-mail input
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 4;
		add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);

		
		lblStreetAddress = new JLabel("Street address");
		GridBagConstraints gbc_lblStreetAddress = new GridBagConstraints();
		gbc_lblStreetAddress.insets = new Insets(0, 0, 5, 5);
		gbc_lblStreetAddress.anchor = GridBagConstraints.EAST;
		gbc_lblStreetAddress.gridx = 0;
		gbc_lblStreetAddress.gridy = 5;
		add(lblStreetAddress, gbc_lblStreetAddress);
		
		// textfield for Street address input
		
		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 1;
		gbc_textField_4.gridy = 5;
		add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
		
		lblPostalCode = new JLabel("Postal code");
		GridBagConstraints gbc_lblPostalCode = new GridBagConstraints();
		gbc_lblPostalCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblPostalCode.anchor = GridBagConstraints.EAST;
		gbc_lblPostalCode.gridx = 0;
		gbc_lblPostalCode.gridy = 6;
		add(lblPostalCode, gbc_lblPostalCode);
		
		// textfield for postal code input
		
		textField_5 = new JTextField();
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 5, 5);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 1;
		gbc_textField_5.gridy = 6;
		add(textField_5, gbc_textField_5);
		textField_5.setColumns(10);
		
		lblMobileNumber = new JLabel("Mobile number");
		GridBagConstraints gbc_lblMobileNumber = new GridBagConstraints();
		gbc_lblMobileNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblMobileNumber.anchor = GridBagConstraints.EAST;
		gbc_lblMobileNumber.gridx = 0;
		gbc_lblMobileNumber.gridy = 7;
		add(lblMobileNumber, gbc_lblMobileNumber);
		
		// textfield for mobile number input
		
		textField_6 = new JTextField();
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.insets = new Insets(0, 0, 5, 5);
		gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.gridx = 1;
		gbc_textField_6.gridy = 7;
		add(textField_6, gbc_textField_6);
		textField_6.setColumns(10);
		
		label = new JLabel("Password");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.gridx = 0;
		gbc_label.gridy = 8;
		add(label, gbc_label);
		
		// textfield for password input
		
		textField_7 = new JTextField();
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.insets = new Insets(0, 0, 5, 5);
		gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_7.gridx = 1;
		gbc_textField_7.gridy = 8;
		add(textField_7, gbc_textField_7);
		textField_7.setColumns(10);
		
			
			
		// roll to select the type of user
		
		lblRoll = new JLabel("Roll");
		GridBagConstraints gbc_lblRoll = new GridBagConstraints();
		gbc_lblRoll.insets = new Insets(0, 0, 5, 5);
		gbc_lblRoll.anchor = GridBagConstraints.EAST;
		gbc_lblRoll.gridx = 0;
		gbc_lblRoll.gridy = 9;
		add(lblRoll, gbc_lblRoll);
		
		JButton btnKnapp = new JButton("Add user");
		btnKnapp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			 String role = (String) comboBox.getSelectedItem();
			 String firstName = textField.getText();
			 String lastName = textField_1.getText();
			 String SSN = textField_2.getText();
			 String email = textField_3.getText();
			 String streetAdress = textField_4.getText();
			 String postalCode = textField_5.getText();
			 String mobileNumber = textField_6.getText();
			 String password = textField_7.getText();
			 
			 
			 String[] formInput = {role, firstName, lastName, SSN, email, streetAdress, postalCode, mobileNumber, password};
			 if (verifyForm(formInput)) {
				 userController.createUser(role, firstName, lastName, SSN, email, streetAdress, postalCode, mobileNumber, password);
			 } else {
				 System.out.println("not submitted");
			 }
				
			}
		});
		
		comboBox = new JComboBox();	
		comboBox.addItem("Borrower");
		comboBox.addItem("Librarian");
		comboBox.addItem("Admin");
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 9;
		add(comboBox, gbc_comboBox);
		GridBagConstraints gbc_btnKnapp = new GridBagConstraints();
		gbc_btnKnapp.insets = new Insets(0, 0, 5, 0);
		gbc_btnKnapp.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnKnapp.gridx = 2;
		gbc_btnKnapp.gridy = 9;
		add(btnKnapp, gbc_btnKnapp);
	}
	
	
	// function to verify the form
	public boolean verifyForm(String[] formInput) {
		boolean isCompleted = true;
		
		for (int i = 0; i < formInput.length; i++) {
			if (formInput[i].length() == 0) {
				isCompleted = false;
			}
		}
		return isCompleted;
		
	}

}
