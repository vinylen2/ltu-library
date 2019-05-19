package view;

import javax.swing.JPanel;

import model.LoanModel;
import model.SearchModel;
import model.StateModel;
import model.StateModel.ApplicationState;
import model.UserModel;

import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controller.LoanController;
import controller.ObjectController;
import controller.SearchController;
import controller.UserController;

import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTable;
import java.awt.ScrollPane;
import java.awt.GridLayout;

public class AddLoanView extends JPanel implements Observer{
	private final Action action = new SwingAction();
	private JTable table;
	private JScrollPane scrollPane;
	private LoanModel loan;
	private LoanController loanController;
	private DefaultTableModel tableModel;
	private UserModel user;
	private StateModel state;
	private UserController userController;
	private ButtonGroup bgroup;
	private ObjectController objectController = new ObjectController();
	private JTable table_1;
	private JTextField SSNField;
	private boolean isSSNFieldRendered = false;

	/**
	 * Create the panel.
	 */
	public AddLoanView(StateModel state, UserModel user, LoanModel loan) {
		this.state = state;
		this.user = user;
		this.loan = loan;

		this.userController = new UserController(user);
		this.loanController = new LoanController(user, loan);

		loan.addObserver(this);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JButton button = new JButton("<");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(user.getRole()) {
				case "Admin": 
					state.setApplicationState(ApplicationState.Admin);
					break;
				case "User": 
					state.setApplicationState(ApplicationState.User);
					break;
				default:
					state.setApplicationState(ApplicationState.Home);
					break;
				}
			}
		});
		
		JButton btnNewButton_1 = new JButton("Låna");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loanController.loanBooks();
				switch (user.getRole()) {
				case "Admin": 
					loanController.loanBooksForUser(SSNField.getText());
					break;
				default:
					loanController.loanBooks();
					break;
				}
				((DefaultTableModel)table_1.getModel()).setRowCount(0);
			}
		});
		
		JButton btnNewButton_2 = new JButton("Ta bort");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// gets object ID from selection in jtable
				int iid = (int) table_1.getValueAt(table_1.getSelectedRow(), 0);
				String id = Integer.toString(iid);

				// deletes item from loanModel
				loanController.removeItemFromPending(id);

				// deletes item from tablemodel
				((DefaultTableModel)table_1.getModel()).removeRow(table_1.getSelectedRow());
			}
		});

		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 1;
		gbc_btnNewButton_2.gridy = 0;
		add(btnNewButton_2, gbc_btnNewButton_2);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 0;
		add(btnNewButton_1, gbc_btnNewButton_1);
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 0);
		gbc_button.gridx = 4;
		gbc_button.gridy = 0;
		add(button, gbc_button);

		JButton btnNewButton = new JButton("Uppdatera vy");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loan.getLoanData(tableModel);
				checkRole();
			}
		});

		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 0;
		add(btnNewButton, gbc_btnNewButton);
		
		addTable(loan);
		loan.getLoanData(tableModel);
	}
	
	public void checkRole() {
		if (user.getRole().equals("Admin") && !isSSNFieldRendered) {
			JLabel lblSsn = new JLabel("SSN:");
			GridBagConstraints gbc_lblSsn = new GridBagConstraints();
			gbc_lblSsn.insets = new Insets(0, 0, 5, 5);
			gbc_lblSsn.anchor = GridBagConstraints.EAST;
			gbc_lblSsn.gridx = 2;
			gbc_lblSsn.gridy = 1;
			add(lblSsn, gbc_lblSsn);
			
			SSNField = new JTextField();
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.insets = new Insets(0, 0, 5, 5);
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = 3;
			gbc_textField.gridy = 1;
			add(SSNField, gbc_textField);
			SSNField.setColumns(10);
			
			isSSNFieldRendered = true;
		}
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}

	
	public void addTable(LoanModel loan) {
		createDefaultTableModel();
		

		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 5;
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 2;
		add(scrollPane_1, gbc_scrollPane_1);
		
		table_1 = new JTable(tableModel);
		scrollPane_1.setViewportView(table_1);
		
	}
	public void createDefaultTableModel() {
		tableModel = new DefaultTableModel(loan.getColumnNames(), 0) {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
	}
	
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("ran this");
		// TODO Auto-generated method stub
		// checks if user is an admin to display add object button


		this.remove(scrollPane);
		this.addTable(loan);
		this.revalidate();
		this.repaint();
		
	}
}
