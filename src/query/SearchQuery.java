package query;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import library.DatabaseConnector;

public class SearchQuery {
	private Connection connection;

	// SQL for searchObject
	private static String getBookQuery = "SELECT * FROM Book WHERE bookName LIKE ?";
	private static String getArticleQuery = "SELECT * FROM Article WHERE articleName LIKE ?";
	
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
	
	public void searchObject(String stringQuery, String type) {
		String query;
		if (type.equals("Book")) {
			query = getBookQuery;
		} else {
			query = getArticleQuery;
		}
		// prepares a SQL-statement
		try (PreparedStatement getObjects = connection.prepareStatement(query)){
			// executes prepared statement
			getObjects.setString(1, "%" + stringQuery + "%");

			ResultSet rs = getObjects.executeQuery();

			while (rs.next()) {
				//System.out.println(rs.getString(1));
				//System.out.println(rs.getString(2));
			}
		}
		catch (SQLException sqlException) {
			System.out.print(sqlException.getMessage());
			sqlException.printStackTrace();
		}
		
	}

}
