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
import query.ObjectQuery;
import query.UserQuery;
import view.AddLoanView;

public class LoanController {
	private UserModel user;
	private LoanModel loan;
	private ObjectQuery objectQuery = new ObjectQuery();
	private UserQuery userQuery = new UserQuery();

	public LoanController(UserModel user, LoanModel loan) {
		this.user = user;
		this.loan = loan;
	}
	
	// has to use objectId, not bookId. Change this!
	public void addItemToPending(String id, Item item) {
		loan.addItemToPending(id, item);
	}
	
	public void removeItemFromPending(String id) {
		loan.removeItemFromPending(id);
	}
	
	public void loanBooks() {
		for (Item item : loan.getAllPendingLoans().values()) {
			int id = item.getObjectId();
			objectQuery.insertLoan(id, user.getId());
		}
	}
}
