package controller;

import model.UserModel;

public class UserController {
	private UserModel model;
	private UserView view;
	public UserController(UserModel m, UserView v) {
		model = m;
		view = v;
		
	}

}
