import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import controller.LoginController;

import javax.swing.JFrame;

import model.StateModel.ApplicationState;
import model.UserModel;
import model.StateModel;
import view.AdminHomeView;
import view.BorrowerHomeView;
import view.DynamicView;
import view.HomeView;
import view.LoginView;
import view.SearchObjectView;

public class Main{

	private UserModel user;
	private StateModel state;
	private LoginController loginController;


	public Main() {
		user = new UserModel();
		state = new StateModel();
		loginController = new LoginController(state, user);
		

		HashMap <ApplicationState, JPanel> states = new HashMap <ApplicationState, JPanel>();
		states.put(ApplicationState.Home, new HomeView(state));
		states.put(ApplicationState.Login, new LoginView(state, user));
		states.put(ApplicationState.SearchObject, new SearchObjectView(state));
		states.put(ApplicationState.Admin, new AdminHomeView(state, user));
		states.put(ApplicationState.Borrower, new BorrowerHomeView(state, user));

		JFrame frame = new JFrame();
		frame.setSize(500,500);
		frame.add(new DynamicView(state, states));
		frame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
