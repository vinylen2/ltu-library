package controller;

import Common.User;
import model.UserModel;

public class UserController {
	private UserModel user;
	public UserController(UserModel user) {
		this.user = user;
	}
	
	public void updateUser(User newUser) {
		user.setUser(newUser);
	}

}
