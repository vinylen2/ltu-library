package query;

import java.awt.List;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import library.DatabaseConnector;
import Common.User;
import model.UserModel;

public class UserQuery {
	private Connection connection;
	private static String getUserQuery = "SELECT * FROM USER u INNER JOIN Role r on r.roleId = u.roleId WHERE u.email = ? AND u.password = ?";
	private static String createUserQuery = "insert into user (roleId, firstname, lastname, SSN, email, streetAdress, postalCode, mobileNumber, password) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	public UserQuery() {
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
	public User logIn(String username, String password) {
		User user = new User();

		try (PreparedStatement logInUser = connection.prepareStatement(getUserQuery)){
			logInUser.setString(1, username);
			logInUser.setString(2, password);

			ResultSet rs = logInUser.executeQuery();

			if (rs.next()) {
				user = new User(
					true,
					rs.getInt("userId"),
					rs.getString("role"),
					rs.getString("firstname"),
					rs.getString("lastname"));
			} 
		}
		catch (SQLException sqlException) {
			System.out.print(sqlException.getMessage());
			sqlException.printStackTrace();
		}
		return user;
	}

	public int createUser(
		String role, 
		String firstName, 
		String lastName,
		String SSN,
		String email,
		String streetAdress,
		String postalCode,
		String mobileNumber,
		String password
		) {
		int roleIdValue = 0;
		int result = 0;
		try (PreparedStatement createUser = connection.prepareStatement(createUserQuery)){
			Connection connection = new DatabaseConnector().getConnection();
			 
			switch (role) {
			case "Admin":
				 roleIdValue = 1;
				 break;
			case "Librarian":
				 roleIdValue = 2;
				 break;
			case "Borrower":
				roleIdValue = 3;
				break;
			}

			
			
			createUser.setInt(1, roleIdValue);
			createUser.setString(2, firstName);
			createUser.setString(3, lastName);
			createUser.setString(4, SSN);
			createUser.setString(5, email);
			createUser.setString(6, streetAdress);
			createUser.setString(7, postalCode);
			createUser.setString(8, mobileNumber);
			createUser.setString(9, password);
			
			result = createUser.executeUpdate();
							
		}
		catch (SQLException sqlException) {
			System.out.print(sqlException.getMessage());
			sqlException.printStackTrace();
		} catch (IOException ioException) {
			System.out.print(ioException.getMessage());
		}
		return result;
	}
	

}
