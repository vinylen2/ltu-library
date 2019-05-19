package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import javax.swing.table.DefaultTableModel;

import Common.Item;

public class LoanModel extends Observable{
	private String type = "Borrower";
	private HashMap<String, Item> pendingLoans = new HashMap<String, Item>(); 
	private HashMap<String, Item> currentLoans = new HashMap<String, Item>(); 
	private DefaultTableModel tableModel;
	
	public LoanModel() {
	}


	public void setType(String type) {
		this.type = type;		
		setChanged();
		notifyObservers();
	}
	
	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	public void addItemToPending(String id, Item item) {
		this.pendingLoans.put(id, item);
	}
	
	public void removeItemFromPending(String itemId) {
		this.pendingLoans.remove(itemId);
	}
	// rename to getPendingItem
	public Item getItem(String id) {
		return pendingLoans.get(id);
	}

	public HashMap<String, Item> getAllPendingLoans() {
		return this.pendingLoans;
	}

	public void addItemToCurrent(String id, Item item) {
		this.pendingLoans.put(id, item);
	}
	
	public void removeItemFromCurrent(String itemId) {
		this.pendingLoans.remove(itemId);
	}

	public Item getCurrentItem(String id) {
		return currentLoans.get(id);
	}
	
	public HashMap<String, Item> getAllCurrentLoans() {
		return this.pendingLoans;
	}

	public void setCurrentLoanResult(ArrayList<Item> currentLoanResult) {
		// clear hashmap because new result is incoming
		this.currentLoans.clear();

		for (Item item : currentLoanResult) {
			this.currentLoans.put(item.getObjectIdInString(), item);
		}
	}

	public void getCurrentLoansData(DefaultTableModel tableModel) {
		// modifies the private arrayList to array
		for (Item item : currentLoans.values()) {
			int ID = item.getObjectId();
			String name = item.getName();
			int SNorAge = item.getSNorAge();
			Object[] data = { ID, name, SNorAge };
			tableModel.addRow(data);
		}
	}
	
	
	public void getLoanData(DefaultTableModel tableModel) {
		// modifies the private arrayList to array
		for (Item item : pendingLoans.values()) {
			int ID = item.getObjectId();
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
