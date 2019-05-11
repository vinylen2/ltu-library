package controller;

import model.User;

public class UserController {
	public void logOut(User user) {
		user.logOut();
		System.out.println("logOut");
	}
	public void logIn(User user) {
		user.logIn();
		System.out.println("logIn");
	}

}
