package controller;

import model.UserModel;
import view.LoginView;

public class LoginController {
	private UserModel model;
	private LoginView view;

	public LoginController(UserModel m, LoginView v) {
		model = m;
		view = v;
	}
	
	public void logIn(String username, String password) {
		System.out.println(username + password);
		
	}

	public void logOut(UserModel user) {
		user.logOut();
	}

}
