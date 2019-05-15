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

	/**
	 * Create the panel.
	 */
	public AddLoanView(StateModel state, UserModel user, LoanModel loan) {
		this.state = state;
		this.user = user;
		this.loan = loan;

		this.userController= new UserController(user);

		loan.addObserver(this);

		addTable(loan);
		loan.getLoanData(tableModel);
		
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
			System.out.println("when does this run?");
		}
		public void actionPerformed(ActionEvent e) {
			System.out.println("when does this run2?");
		}
	}

	public void createDefaultTableModel(LoanModel loan) {
		tableModel = new DefaultTableModel(loan.getColumnNames(),0) {
		  public boolean isCellEditable(int rowIndex, int mColIndex) {
			  return false;
			}
		};
	}
	
	public void addTable(LoanModel loan) {
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		setLayout(new GridLayout(0, 1, 0, 0));
		add(scrollPane);
		// creates table to show results

		createDefaultTableModel(loan);

		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 3;
		gbc_btnNewButton_1.gridy = 1;
		
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
