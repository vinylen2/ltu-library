package view;

import javax.swing.JPanel;

import model.SearchModel;
import model.StateModel;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controller.SearchController;

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

public class SearchObjectView extends JPanel implements Observer{
	private JTextField textField;
	private final Action action = new SwingAction();
	private JTable table;
	private JScrollPane scrollPane;
	private SearchModel searchModel;
	private DefaultTableModel tableModel;

	/**
	 * Create the panel.
	 */
	public SearchObjectView(StateModel state) {
		
		searchModel = new SearchModel();
		SearchController searchController = new SearchController(searchModel);

		searchModel.addObserver(this);
		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{202, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{16, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel label = new JLabel("Search object");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.anchor = GridBagConstraints.NORTHWEST;
		gbc_label.gridx = 1;
		gbc_label.gridy = 0;
		add(label, gbc_label);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Article");
		rdbtnNewRadioButton.setActionCommand("Article");
		GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
		gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton.gridx = 1;
		gbc_rdbtnNewRadioButton.gridy = 2;
		add(rdbtnNewRadioButton, gbc_rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Book");
		rdbtnNewRadioButton_1.setSelected(true);
		rdbtnNewRadioButton_1.setActionCommand("Book");
		GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton_1.gridx = 2;
		gbc_rdbtnNewRadioButton_1.gridy = 2;
		add(rdbtnNewRadioButton_1, gbc_rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("DVD");
		rdbtnNewRadioButton_2.setActionCommand("DVD");
		GridBagConstraints gbc_rdbtnNewRadioButton_2 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_2.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnNewRadioButton_2.gridx = 3;
		gbc_rdbtnNewRadioButton_2.gridy = 2;
		add(rdbtnNewRadioButton_2, gbc_rdbtnNewRadioButton_2);

        ButtonGroup bgroup = new ButtonGroup();
        bgroup.add(rdbtnNewRadioButton);
        bgroup.add(rdbtnNewRadioButton_1);
        bgroup.add(rdbtnNewRadioButton_2);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 1;
		add(textField, gbc_textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String type = bgroup.getSelection().getActionCommand();

			searchModel.setType(type);

			searchController.searchObject(searchModel, textField.getText(), type);
			searchModel.getSearchData(tableModel);
			}
		});
		
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 1;
		add(btnNewButton, gbc_btnNewButton);
		
		addTable(searchModel);
		
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}

	public void createDefaultTableModel(SearchModel searchModel) {
		tableModel = new DefaultTableModel(searchModel.getColumnNames(),0);
	}
	
	public void addTable(SearchModel searchModel) {

		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		add(scrollPane, gbc_scrollPane);
		
		
		createDefaultTableModel(searchModel);
		// creates table to show results
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		this.remove(scrollPane);
		this.addTable(searchModel);
		this.revalidate();
		this.repaint();
		
	}
}
