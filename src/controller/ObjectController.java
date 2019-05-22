package controller;

import query.ObjectQuery;

public class ObjectController {
	private ObjectQuery query;

	// Constructor to create controller and ObjectQuery
	// ObjectQuery used for connection to DB
	public ObjectController() {
		query = new ObjectQuery();
	}
	// performs insertObject function from ObjectQuery
	public void insertObject(String type, String title, String ISBN, String pages) {
		query.insertObject(type, title, ISBN, pages);
	}
}
