package controller;

import model.SearchModel;
import query.SearchQuery;

public class SearchController {
	private SearchQuery query;
	private SearchModel model;
	
	public SearchController(SearchModel searchModel) {
		query = new SearchQuery();
		model = searchModel;
	}
	
	public void searchObject(SearchModel searchModel, String searchQuery, String type) {
		searchModel.setSearchResult(query.searchForObjects(searchQuery, type));
	}

}
