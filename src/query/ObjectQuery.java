package query;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Common.Item;
import library.DatabaseConnector;

public class ObjectQuery {
	private Connection connection;

	// SQL for searchObject
	private static String getBookQuery = "SELECT * FROM Book WHERE bookName LIKE ?";
	private static String getArticleQuery = "SELECT * FROM Article WHERE articleName LIKE ?";
	private static String getDVDQuery = "SELECT * FROM DVD WHERE title LIKE ?";
	
	// SQL for insert new Object depending on type
	private static String insertObjectQuery = "INSERT INTO Object (bookId, dvdId, articleId) VALUES (?, ?, ?) ";
	
	// Constructor to create connection to DB via Class DatabaseConnector (containing props to local DB)
	public ObjectQuery() {
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
	
	// function to insert new Object from bookId/dvdId/articleId depending on type
	public int insertObject(int id, String type) {
		// variable to return if insert is completed or not 
		// 0 = failed
		// 1 = completed
		int rowsAffected = 0;

		// Strings for prepared statement
		String bookId = null;
		String dvdId = null;
		String articleId = null;
		
		// set Id for insertion depending on type
		switch (type) {
		case "Book":
			bookId = Integer.toString(id);
			break;
		case "Article":
			articleId = Integer.toString(id);
			break;
		case "DVD":
			dvdId = Integer.toString(id);
			break;
		}

		try (PreparedStatement insertObject = connection.prepareStatement(insertObjectQuery)){

			// Sets data to prepared Statement
			insertObject.setString(1, bookId);
			insertObject.setString(2, dvdId);
			insertObject.setString(3, articleId);
			
			// inserting data gives us following SQL
			// INSERT INTO Object (bookId, dvdId, articleId) VALUES (bookId, dvdId, articleId);

			// execute query above
			int rs = insertObject.executeUpdate();

			// variable to return depending on successful insertion or not
			rowsAffected = rs;

		}
		catch (SQLException sqlException) {
			System.out.print(sqlException.getMessage());
			sqlException.printStackTrace();
		}

		return rowsAffected;
	}

}
