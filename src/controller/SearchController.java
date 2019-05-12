package controller;

import query.SearchQuery;

public class SearchController {
	private SearchQuery query;
	
	public SearchController() {
		query = new SearchQuery();
	}
	
	public void searchObject(String searchQuery, String type) {
		query.searchObject(searchQuery, type);
	}

}
