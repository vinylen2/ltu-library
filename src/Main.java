import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JFrame;

import model.StateModel.ApplicationState;
import model.UserModel;
import model.StateModel;
import view.AdminHomeView;
import view.DynamicView;
import view.HomeView;
import view.LoginView;
import view.SearchObjectView;

public class Main {

	public static void main(String[] args) {
		StateModel state = new StateModel();
		UserModel user = new UserModel();


		HashMap <ApplicationState, JPanel> states = new HashMap <ApplicationState, JPanel>();
		states.put(ApplicationState.Home, new HomeView(state));
		states.put(ApplicationState.Login, new LoginView(state, user));
		states.put(ApplicationState.SearchObject, new SearchObjectView(state));
		states.put(ApplicationState.AdminHome, new AdminHomeView(state));
		JFrame frame = new JFrame();
		frame.setSize(500,500);
		frame.add(new DynamicView(state, states));
		frame.setVisible(true);
	}
}
