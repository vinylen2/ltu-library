package model;

import java.util.Observable;

import Common.User;

public class UserModel extends Observable {
	private User user;

	public UserModel(boolean isLoggedIn, int userId, String role, String firstname, String lastname) {
		user = new User(
			isLoggedIn,
			userId,
			role,
			firstname,
			lastname
			);
	}
	public UserModel() {
		user = new User();
	}
	
	public void setUser(User newUser) {
		this.user = newUser;
		setChanged();
		// notifies DynamicView about change in state
		notifyObservers();
	}
	
	// byt till isLoggedIn, skickar vidare intern variabel
	public boolean hasLoggedIn() {
		return user.isLoggedIn;
	}
	
	public String getName() {
		return user.firstname + user.lastname;
	}
	
	public String getRole() {
		return user.role;
	}
}
