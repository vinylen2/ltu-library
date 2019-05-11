import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JFrame;

import model.StateModel.ApplicationState;
import model.StateModel;

import view.DynamicView;
import view.HomeView;
import view.LoginView;
import view.SearchObjectView;

public class Main {
	

	public static void main(String[] args) {
		StateModel state = new StateModel();

		HashMap <ApplicationState, JPanel> states = new HashMap <ApplicationState, JPanel>();
		states.put(ApplicationState.Home, new HomeView(state));
		states.put(ApplicationState.Login, new LoginView(state));
		states.put(ApplicationState.SearchObject, new SearchObjectView(state));
		JFrame frame = new JFrame();
		frame.setSize(500,500);
		frame.add(new DynamicView(state, states));
		frame.setVisible(true);
	}
}
