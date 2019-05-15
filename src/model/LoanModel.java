package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import javax.swing.table.DefaultTableModel;

import Common.Item;

public class LoanModel extends Observable{
	private String type = "Borrower";
	private HashMap<String, Item> pendingLoans = new HashMap<String, Item>(); 
	private DefaultTableModel tableModel = new DefaultTableModel();
	
	public LoanModel() {

	}

	public void setType(String type) {
		this.type = type;		
		setChanged();
		notifyObservers();
	}
	
	public void addItemToPending(String id, Item item) {
		this.pendingLoans.put(id, item);
	}
	
	public void removeItemFromPending(String itemId) {
		this.pendingLoans.remove(itemId);
	}
	
	public Item getItem(String id) {
		return pendingLoans.get(id);
	}
	
	public void getLoanData(DefaultTableModel tableModel) {
		// modifies the private arrayList to array
		for (Item item : pendingLoans.values()) {
			int ID = item.getId();
			String name = item.getName();
			int SNorAge = item.getSNorAge();
			Object[] data = { ID, name, SNorAge };
			tableModel.addRow(data);
		}
	}
	
	public String[] getColumnNames() {
	String[] columnNames = new String[3];
		switch (this.type) {
			case "Borrower":
				columnNames[0] = "Id";
				columnNames[1] = "Title";
				columnNames[2] = "Serial number";
				break;
			case "Article":
				columnNames[0] = "articleId";
				columnNames[1] = "articleName";
				columnNames[2] = "ISSN";
				break;
			case "DVD":
				columnNames[0] = "dvdId";
				columnNames[1] = "dvdName";
				columnNames[2] = "length";
				break;
		}
		return columnNames;
	}

}
