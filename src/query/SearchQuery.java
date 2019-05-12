package query;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Common.Item;
import library.DatabaseConnector;

public class SearchQuery {
	private Connection connection;

	// SQL for searchObject
	private static String getBookQuery = "SELECT * FROM Book WHERE bookName LIKE ?";
	private static String getArticleQuery = "SELECT * FROM Article WHERE articleName LIKE ?";
	private static String getDVDQuery = "SELECT * FROM DVD WHERE title LIKE ?";
	
	public SearchQuery() {
		try {
			connection = new DatabaseConnector().getConnection();
		}
		catch (SQLException sqlException) {
			System.out.print(sqlException.getMessage());
			sqlException.printStackTrace();
			System.exit(1);;
		} catch (IOException ioException) {
			System.out.print(ioException.getMessage());
		}
	}
	
	public ArrayList<Item> searchForObjects(String stringQuery, String type) {
		String query;
		ArrayList<Item> searchResult = new ArrayList<Item>();

		switch (type) {
		case "Book":
			query = getBookQuery;
			break;
		case "Article":
			query = getArticleQuery;
			break;
		case "DVD":
			query = getDVDQuery;
			break;
		default:
			query = "";
			break;
		}

		// prepares a SQL-statement
		try (PreparedStatement getObjects = connection.prepareStatement(query)){

			// executes prepared statement
			getObjects.setString(1, "%" + stringQuery + "%");

			ResultSet rs = getObjects.executeQuery();

			while (rs.next()) {
				searchResult.add(new Item(rs.getInt(1), rs.getString(2), rs.getInt(3)));
			}
		}
		catch (SQLException sqlException) {
			System.out.print(sqlException.getMessage());
			sqlException.printStackTrace();
		}
		return searchResult;
		
	}

}
