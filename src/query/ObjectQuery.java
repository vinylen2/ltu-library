package query;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.mysql.cj.util.StringUtils;
import com.mysql.cj.xdevapi.Statement;

import Common.Item;
import library.DatabaseConnector;

public class ObjectQuery {
	private Connection connection;

	// SQL for searchObject
	//private static String getBookQuery = "SELECT * FROM Book WHERE bookName LIKE ?";
	private static String getBookQuery = "SELECT * FROM Book b JOIN Object o ON o.bookId = b.bookId WHERE bookName LIKE ? AND o.isLoaned = false;";
	private static String getArticleQuery = "SELECT * FROM Article a JOIN Object o ON o.articleId = a.articleId WHERE articleName LIKE ? AND o.isLoaned = false";
	private static String getDVDQuery = "SELECT * FROM DVD d JOIN Object o ON o.dvdId = d.dvdId WHERE title LIKE ? AND o.isLoaned = false";
	
	// SQL for gettingCurrentLoans
	private static String getCurrentBookLoans = "SELECT * FROM Loans l JOIN Object o ON l.objectId = o.objectId JOIN Book b ON o.bookId = b.bookId WHERE userId = ? AND isReturned = 0";
	private static String getCurrentDVDLoans = "SELECT * FROM Loans l JOIN Object o ON l.objectId = o.objectId JOIN DVD d ON o.dvdId = d.dvdId WHERE userId = ? AND isReturned = 0";
	private static String getCurrentArticleLoans = "SELECT * FROM Loans l JOIN Object o ON l.objectId = o.objectId  JOIN Article a ON o.articleId = a.articleId WHERE userId = ? AND isReturned = 0";
	
	// SQL for insert new Object depending on type
	private static String insertBookQuery = "INSERT INTO Book (bookName, ISBN, pages) VALUES (?, ?, ?)";
	private static String insertDVDQuery = "INSERT INTO DVD (title, length, ageLimit) VALUES (?, ?, ?)";
	private static String insertArticleQuery = "INSERT INTO Article (articleName, ISSN, edition) VALUES (?, ?, ?)";
	
	private static String insertNewObject = "INSERT INTO Object (bookId, dvdId, articleId) VALUES (?, ?, ?);";

	private static String insertLoanQuery = "INSERT INTO Loans (userId, objectId, loanDate, returnDate, isReturned) VALUES (?, ?, ?, ?, ?) ";
	
	// SQL for updating Object isLoaned 
	private static String updateObjectLoanStatus = "UPDATE Object SET isLoaned = ? WHERE objectId = ?";
	private static String updateLoanLoanStatus = "UPDATE Loans SET isReturned = ? WHERE objectId = ?";
	
	// SQL for getting SSN from UserId
	private static String getUserIdFromSSNQuery = "SELECT UserId FROM User WHERE SSN = ?";

	
	
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
				searchResult.add(new Item(rs.getInt("objectId"), rs.getString(2), rs.getInt(3), type));
			}
		}
		catch (SQLException sqlException) {
			System.out.print(sqlException.getMessage());
			sqlException.printStackTrace();
		}
		return searchResult;
	}
	
	// function to insert new Object from bookId/dvdId/articleId depending on type
	public void insertObject(String type, String title, String ISBN, String pages) {
		int rowsAffected;
		String query;
		switch (type) {
		case "Book":
			query = insertBookQuery;
			break;
		case "Article":
			query = insertArticleQuery;
			break;
		case "DVD":
			query = insertDVDQuery;
			break;
		default:
			query = "";
			break;
		}

		try (PreparedStatement insertObject = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)){

			// Sets data to prepared Statement
			insertObject.setString(1, title);
			insertObject.setString(2, ISBN);
			insertObject.setString(3, pages);
			
			// inserting data gives us following SQL
			// INSERT INTO Object (bookId, dvdId, articleId) VALUES (bookId, dvdId, articleId);

			// execute query above
			insertObject.executeUpdate();
			ResultSet rs = insertObject.getGeneratedKeys();
			

			java.math.BigDecimal id;

			while (rs.next()) {
				  id = rs.getBigDecimal(1);     

				  // inset object in objectTable
				  insertObjectFromNew(id.intValue(), type);
			}
		}
		catch (SQLException sqlException) {
			System.out.print(sqlException.getMessage());
			sqlException.printStackTrace();
		}

	}

	
	public void insertObjectFromNew(int id, String type) {
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
		// variable to return depending on successful insertion or not
		try (PreparedStatement insertObject = connection.prepareStatement(insertNewObject)){
			// Sets data to prepared Statement
			insertObject.setString(1, bookId);
			insertObject.setString(2, dvdId);
			insertObject.setString(3, articleId);
			
			// inserting data gives us following SQL
			// INSERT INTO Object (bookId, dvdId, articleId) VALUES (bookId, dvdId, articleId);

			// execute query above
			insertObject.executeUpdate();
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
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		String strDate = dateFormat.format(cal.getTime());  
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String strReturnDate = dateFormat.format(cal.getTime());  

		System.out.println(userId);
		try (PreparedStatement insertObject = connection.prepareStatement(insertLoanQuery)){

			// Sets data to prepared Statement
			insertObject.setInt(1, userId);
			insertObject.setInt(2, objectId);
			insertObject.setString(3, strDate);
			insertObject.setString(4, strReturnDate);
			insertObject.setInt(5, 0);
			
			// inserting data gives us following SQL
			// INSERT INTO Object (bookId, dvdId, articleId) VALUES (bookId, dvdId, articleId);

			// execute query above
			int rs = insertObject.executeUpdate();

			// variable to return depending on successful insertion or not
			updateLoanStatus(objectId, 1);
		}
		
		catch (SQLException sqlException) {
			System.out.print(sqlException.getMessage());
			sqlException.printStackTrace();
		}
	}
	
	public void updateLoanStatus(int objectId, int loanStatus) {
		// loanStatus is 1 for loaned and 0 for returned
		
		try (PreparedStatement updateObject = connection.prepareStatement(updateObjectLoanStatus)) {
			updateObject.setInt(1, loanStatus);
			updateObject.setInt(2, objectId);

			int rs = updateObject.executeUpdate();
		}
		catch (SQLException sqlException) {
			System.out.print(sqlException.getMessage());
			sqlException.printStackTrace();
		}
	}
	
	public void updateLoanStatus2(int objectId) {

		try (PreparedStatement updateObject = connection.prepareStatement(updateLoanLoanStatus)) {
			updateObject.setInt(1, 1);
			updateObject.setInt(2, objectId);

			int rs = updateObject.executeUpdate();
		}
		catch (SQLException sqlException) {
			System.out.print(sqlException.getMessage());
			sqlException.printStackTrace();
		}
	}
	
	
	public int getUserIdFromSSN(String SSN) {
		int userId = 0;
		try (PreparedStatement getUserId = connection.prepareStatement(getUserIdFromSSNQuery)) {
			// executes prepared statement
			getUserId.setString(1, SSN);

			ResultSet rs = getUserId.executeQuery();

			while (rs.next()) {
				userId = rs.getInt("UserId");
			}
		}
		
		catch (SQLException sqlException) {
			System.out.print(sqlException.getMessage());
			sqlException.printStackTrace();
			
		}
		return userId;
	}

	public ArrayList<Item> getCurrentLoans(int userId) {
		ArrayList<Item> currentLoanResult = new ArrayList<Item>();

		// prepares a SQL-statement
		try (PreparedStatement getObjects = connection.prepareStatement(getCurrentBookLoans)){
			// executes prepared statement
			getObjects.setInt(1, userId);
			ResultSet rs = getObjects.executeQuery();

			while (rs.next()) {
				//currentLoanResult.add(new Item(rs.getInt("objectId"), rs.getString(2), rs.getInt(3))));
				currentLoanResult.add(new Item(rs.getInt("objectId"), rs.getString("bookName"), rs.getInt("ISBN"), "Book", rs.getString("returnDate")));
			}
		}
		catch (SQLException sqlException) {
			System.out.print(sqlException.getMessage());
			sqlException.printStackTrace();
		}

		try (PreparedStatement getObjects = connection.prepareStatement(getCurrentArticleLoans)){
			// executes prepared statement
			getObjects.setInt(1, userId);
			ResultSet rs = getObjects.executeQuery();

			while (rs.next()) {
				//currentLoanResult.add(new Item(rs.getInt("objectId"), rs.getString(2), rs.getInt(3))));
				currentLoanResult.add(new Item(rs.getInt("objectId"), rs.getString("articleName"), rs.getInt("ISSN"), "Article", rs.getString("returnDate")));
			}
		}
		catch (SQLException sqlException) {
			System.out.print(sqlException.getMessage());
			sqlException.printStackTrace();
		}

		try (PreparedStatement getObjects = connection.prepareStatement(getCurrentDVDLoans)){
			// executes prepared statement
			getObjects.setInt(1, userId);
			ResultSet rs = getObjects.executeQuery();

			while (rs.next()) {
				//currentLoanResult.add(new Item(rs.getInt("objectId"), rs.getString(2), rs.getInt(3))));
				currentLoanResult.add(new Item(rs.getInt("objectId"), rs.getString("title"), rs.getInt("ageLimit"), "DVD", rs.getString("returnDate")));
			}
		}
		catch (SQLException sqlException) {
			System.out.print(sqlException.getMessage());
			sqlException.printStackTrace();
		}
		
		
		return currentLoanResult;
	}
}
