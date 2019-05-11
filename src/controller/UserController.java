package controller;

import model.User;

public class UserController {
	public void logOut(User user) {
		user.logOut();
	}

	public void logIn(User user) {
		user.logIn();
	}

}
