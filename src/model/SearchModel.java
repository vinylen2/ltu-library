package model;

import java.util.ArrayList;
import java.util.Observable;

import javax.swing.table.DefaultTableModel;

import Common.Item;

public class SearchModel extends Observable{
	private String type = "Book";
	private ArrayList<Item> searchItems = new ArrayList<Item>();
	private DefaultTableModel tableModel = new DefaultTableModel();
	
	public void setType(String type) {
		this.type = type;		
		setChanged();
		notifyObservers();

	}
	
	
	public void setSearchResult(ArrayList<Item> searchResult) {
		this.searchItems = searchResult;
	}
	
	public void getSearchData(DefaultTableModel tableModel) {
		// modifies the private arrayList to array

		for (int i = 0; i < searchItems.size(); i++) {
			int ID = searchItems.get(i).getId();
			String name = searchItems.get(i).getName();
			int SNorAge = searchItems.get(i).getSNorAge();
			

			Object[] data = { ID, name, SNorAge };
			tableModel.addRow(data);
		}

	}
	
	public String[] getColumnNames() {
	String[] columnNames = new String[3];
		switch (this.type) {
			case "Book":
				columnNames[0] = "bookId";
				columnNames[1] = "bookName";
				columnNames[2] = "ISBN";
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
