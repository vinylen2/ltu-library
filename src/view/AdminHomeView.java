package view;

import javax.swing.JPanel;

import model.StateModel;
import model.UserModel;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

public class AdminHomeView extends JPanel implements Observer {
	private JLabel userLabel;

	/**
	 * Create the panel.
	 */
	public AdminHomeView(StateModel state, UserModel user) {
		
		user.addObserver(this);
		this.userLabel = new JLabel(user.getName());
		add(this.userLabel);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		UserModel user = (UserModel)o;
		this.userLabel.setText(user.getName());
	}
	
}
