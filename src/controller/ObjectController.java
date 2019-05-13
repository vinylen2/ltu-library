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
	public void insertObject(int id, String type) {
		query.insertObject(id, type);
	}
}
