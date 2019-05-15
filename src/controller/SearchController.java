package controller;

import model.SearchModel;
import query.ObjectQuery;

public class SearchController {
	private ObjectQuery query;
	private SearchModel model;
	
	public SearchController(SearchModel searchModel) {
		query = new ObjectQuery();
		model = searchModel;
	}
	
	public void searchObject(SearchModel searchModel, String searchQuery, String type) {
		searchModel.setSearchResult(query.searchForObjects(searchQuery, type));
	}
	
}
