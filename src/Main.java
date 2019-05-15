import java.util.HashMap;

import javax.swing.JPanel;

import javax.swing.JFrame;

import model.StateModel.ApplicationState;
import model.UserModel;
import model.LoanModel;
import model.StateModel;
import view.AddUserView;
import view.AddLoanView;

import view.AdminHomeView;
import view.BorrowerHomeView;
import view.DynamicView;
import view.HomeView;
import view.LoginView;
import view.SearchObjectView;

public class Main{

	private UserModel user;
	private StateModel state;
	private LoanModel loan;


	public Main() {
		user = new UserModel();
		state = new StateModel();
		loan = new LoanModel();
		

		HashMap <ApplicationState, JPanel> states = new HashMap <ApplicationState, JPanel>();
		states.put(ApplicationState.Home, new HomeView(state));
		states.put(ApplicationState.Login, new LoginView(state, user));
		states.put(ApplicationState.SearchObject, new SearchObjectView(state, user, loan));
		states.put(ApplicationState.Admin, new AdminHomeView(state, user));
		states.put(ApplicationState.User, new BorrowerHomeView(state, user));
		states.put(ApplicationState.AddBorrower, new AddUserView(state, user));
		states.put(ApplicationState.AddLoan, new AddLoanView(state, user, loan));

		JFrame frame = new JFrame("Library system");
		frame.setSize(500,500);
		frame.add(new DynamicView(state, states));
		frame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
