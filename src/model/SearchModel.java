package model;

public class SearchModel {
	private String type = "Book";
	
	public void setType(String type) {
		this.type = type;
	}
	
	
	public String[][] getSearchData() {
		String[][] data = { 
	            { "Kundan Kumar Jha", "4031", "CSE" }, 
	            { "Anand Jha", "6014", "IT" } 
	        }; 
		return data;
	}
	
	public String[] getColumnNames() {
	String[] columnNames = new String[3];
		switch (this.type) {
			case "Book":
				columnNames[0] = "bookId";
				columnNames[1] = "bookName";
				columnNames[2] = "ISBN";
				break;
		}
		return columnNames;
	}

}
