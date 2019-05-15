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
import model.LoanModel;
import model.UserModel;
import query.UserQuery;

public class LoanController {
	private UserModel user;
	private LoanModel loan;
	private UserQuery userQuery = new UserQuery();

	public LoanController(UserModel user, LoanModel loan) {
		this.user = user;
		this.loan = loan;
	}
	
	public LoanController() {
		
	}
	
	public void addItemToPending(String id, Item item) {
		loan.addItemToPending(id, item);
	}
	
	public void removeItemFromPending(String id) {
		loan.removeItemFromPending(id);
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
