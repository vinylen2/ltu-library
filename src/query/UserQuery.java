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
	private static String getUserQuery = "SELECT * FROM USER u INNER JOIN Role r on r.roleId = u.roleId WHERE u.email = ? AND u.password = ?";

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

}
