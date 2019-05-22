package view;

import javax.swing.JPanel;

import model.LoanModel;
import model.StateModel;
import model.StateModel.ApplicationState;
import model.UserModel;

import javax.swing.ButtonGroup;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;

import controller.ObjectController;
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
import javax.swing.JRadioButton;

public class AddObjectView extends JPanel {
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JLabel titleText;
	private JLabel numberField;
	private JLabel numberField2;
	private UserController userController;
	private JButton button;
	private JComboBox comboBox;
	private JLabel lblType;
	private String firstLabelText;
	private String secondLabelText;
	private String thirdLabelText;
	private ObjectController objectController;

	/**
	 * Create the panel.
	 */
	
	
	public AddObjectView(StateModel state, UserModel user, LoanModel loan) {
		userController = new UserController(user);
		objectController = new ObjectController();

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 193, 63, 0};
		gridBagLayout.rowHeights = new int[]{23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		button = new JButton("<");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				state.setApplicationState(ApplicationState.Admin);
			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 0);
		gbc_button.gridx = 2;
		gbc_button.gridy = 1;
		add(button, gbc_button);

		lblType = new JLabel("Type");
		GridBagConstraints gbc_lblType = new GridBagConstraints();
		gbc_lblType.insets = new Insets(0, 0, 5, 5);
		gbc_lblType.anchor = GridBagConstraints.EAST;
		gbc_lblType.gridx = 0;
		gbc_lblType.gridy = 4;
		add(lblType, gbc_lblType);
		
		comboBox = new JComboBox();
		comboBox.addItem("Article");
		comboBox.addItem("Book");
		comboBox.addItem("DVD");
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// update labels here
				switch((String) comboBox.getSelectedItem()) {
				case "Book":
					numberField.setText("ISBN: ");
					numberField2.setText("Pages: ");
					break;
				case "DVD":
					numberField.setText("Length: ");
					numberField2.setText("Age limit: ");
					break;
				case "Article":
					numberField.setText("ISSN: ");
					numberField2.setText("Edition: ");
					break;
				}
			}
		});
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 4;
		add(comboBox, gbc_comboBox);

		titleText = new JLabel("Title: ");
		GridBagConstraints gbc_titleText = new GridBagConstraints();
		gbc_titleText.insets = new Insets(0, 0, 5, 5);
		gbc_titleText.anchor = GridBagConstraints.EAST;
		gbc_titleText.gridx = 0;
		gbc_titleText.gridy = 5;
		add(titleText, gbc_titleText);

		numberField = new JLabel("Select type");
		GridBagConstraints gbc_numberField = new GridBagConstraints();
		gbc_numberField.insets = new Insets(0, 0, 5, 5);
		gbc_numberField.anchor = GridBagConstraints.EAST;
		gbc_numberField.gridx = 0;
		gbc_numberField.gridy = 6;
		add(numberField, gbc_numberField);

		numberField2 = new JLabel("Select type");
		GridBagConstraints gbc_numberField2 = new GridBagConstraints();
		gbc_numberField2.insets = new Insets(0, 0, 5, 5);
		gbc_numberField2.anchor = GridBagConstraints.EAST;
		gbc_numberField2.gridx = 0;
		gbc_numberField2.gridy = 7;
		add(numberField2, gbc_numberField2);

		
		
		// textfield for Title
		
		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 1;
		gbc_textField_4.gridy = 5;
		add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
		
		
		// textfield for ISSN/ISBN/length
		
		textField_5 = new JTextField();
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 5, 5);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 1;
		gbc_textField_5.gridy = 6;
		add(textField_5, gbc_textField_5);
		textField_5.setColumns(10);
		
		
		// textField for pages, edition, age limit
		
		textField_6 = new JTextField();
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.insets = new Insets(0, 0, 5, 5);
		gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.gridx = 1;
		gbc_textField_6.gridy = 7;
		add(textField_6, gbc_textField_6);
		textField_6.setColumns(10);

		
		JButton btnKnapp = new JButton("Add object");
		btnKnapp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String type = (String) comboBox.getSelectedItem();
			String title =  textField_4.getText();
			String ISBN = textField_5.getText();
			String pages = textField_6.getText();


			 String[] formInput = {type, title, ISBN, pages};
			 if (verifyForm(formInput)) {
				 // insert object
				 objectController.insertObject(type, title, ISBN, pages);
			 } else {
				 System.out.println("not submitted");
			 }
				
			}
		});
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
