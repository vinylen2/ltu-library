package controller;


import Common.User;
import model.StateModel;
import model.StateModel.ApplicationState;
import model.UserModel;
import query.UserQuery;
import view.LoginView;

public class LoginController{
	private UserModel user;
	private User newUser;
	private LoginView view;
	private UserQuery query;
	private StateModel state;
	private UserController userController;

	public LoginController(StateModel state, UserModel user) {
		this.user = user;
		this.state = state;
		query = new UserQuery();
		userController = new UserController(user);
	}
	
	public void logIn(String username, String password) {
		newUser = query.logIn(username, password);
		if (newUser.isLoggedIn) {
			user.setUser(newUser);

			// changes applicationstate from newUsers role
			ApplicationState stateEnum = ApplicationState.valueOf(newUser.role);
			state.setApplicationState(stateEnum);
		}
	}

	public void logOut(UserModel user) {
		//fix this to controller
	}


}
