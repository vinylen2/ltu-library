package controller;


import Common.User;
import model.StateModel;
import model.StateModel.ApplicationState;
import model.UserModel;
import query.UserQuery;
import view.AdminHomeView;
import view.LoginView;

public class LoginController{
	private UserModel user;
	private User newUser;
	private LoginView view;
	private UserQuery query;
	private StateModel state;
	private UserController userController;
	private AdminHomeView adminView;

	public LoginController(StateModel state, UserModel user) {
		this.user = user;
		this.state = state;
		query = new UserQuery();
		userController = new UserController(user);
	}
	
	public void logIn(String username, String password) {
		newUser = query.logIn(username, password);
		if (newUser.isLoggedIn) {
			userController.updateUser(newUser);
			ApplicationState stateEnum = ApplicationState.valueOf(newUser.role);
			state.setApplicationState(stateEnum);
			adminView.revalidate();
		}
	}

	public void logOut(UserModel user) {
		//fix this to controller
	}


}
