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
	//private static String getBookQuery = "SELECT * FROM Book WHERE bookName LIKE ?";
	private static String getBookQuery = "SELECT * FROM Book b JOIN Object o on o.bookId = b.bookId WHERE b.bookName LIKE ? AND o.isLoaned = false;";
	private static String getArticleQuery = "SELECT * FROM Article WHERE articleName LIKE ?";
	private static String getDVDQuery = "SELECT * FROM DVD WHERE title LIKE ?";
	
	// SQL for insert new Object depending on type
	private static String insertObjectQuery = "INSERT INTO Object (bookId, dvdId, articleId) VALUES (?, ?, ?) ";
	private static String insertLoanQuery = "INSERT INTO Loans (userId, objectId, loanDate, returnDate, isReturned) VALUES (?, ?, ?, ?, ?) ";
	
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
				searchResult.add(new Item(rs.getInt("objectId"), rs.getString(2), rs.getInt(3), type, rs.getInt("bookId")));
			}
		}
		catch (SQLException sqlException) {
			System.out.print(sqlException.getMessage());
			sqlException.printStackTrace();
		}
		return searchResult;
		
	}
	
	// function to insert new Object from bookId/dvdId/articleId depending on type
	public void insertObject(int id, String type) {
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
	}

	public void insertLoan(int objectId, int userId) {
		// variable to return if insert is completed or not 
		// 0 = failed
		// 1 = completed


		try (PreparedStatement insertObject = connection.prepareStatement(insertLoanQuery)){

			// Sets data to prepared Statement
			insertObject.setInt(1, userId);
			insertObject.setInt(2, objectId);
			insertObject.setString(3, "20190517");
			insertObject.setString(4, "20190517");
			insertObject.setInt(5, 0);
			
			// inserting data gives us following SQL
			// INSERT INTO Object (bookId, dvdId, articleId) VALUES (bookId, dvdId, articleId);

			// execute query above
			int rs = insertObject.executeUpdate();

			// variable to return depending on successful insertion or not

		}
		catch (SQLException sqlException) {
			System.out.print(sqlException.getMessage());
			sqlException.printStackTrace();
		}
	}

}
