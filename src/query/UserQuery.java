package query;

import java.awt.List;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Common.User;
import library.DatabaseConnector;
import model.UserModel;

public class UserQuery {
	private Connection connection;

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

		try (PreparedStatement selectAllUsers = connection.prepareStatement(
				"SELECT * FROM User WHERE email = ? AND password = ?")){
			selectAllUsers.setString(1, username);
			selectAllUsers.setString(2, password);

			ResultSet rs = selectAllUsers.executeQuery();

			if (rs.next()) {
				user = new User(
					true,
					rs.getInt("userId"),
					"Admin",
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

}
