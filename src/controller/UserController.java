package controller;

import java.awt.List;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import library.DatabaseConnector;
import Common.Item;
import Common.User;
import library.DatabaseConnector;
import model.UserModel;
import query.UserQuery;

public class UserController {
	private UserModel user;
	private UserQuery userQuery = new UserQuery();

	public UserController(UserModel user) {
		this.user = user;
	}
	
	public UserController() {
		
	}
	
	public void createUser(
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
			int result = userQuery.createUser(
				role, 
				firstName, 
				lastName,
				SSN,
				email,
				streetAdress,
				postalCode,
				mobileNumber,
				password
			);
			System.out.println(result);
	}
}
