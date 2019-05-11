package view;

import java.util.HashMap;
import java.util.Observable;

import javax.swing.JPanel;
import java.util.Observer;

import model.StateModel;
import model.StateModel.ApplicationState;

public class DynamicView extends JPanel implements Observer {
	private HashMap<ApplicationState, JPanel> states;

	public DynamicView(StateModel model, HashMap<ApplicationState, JPanel> states) {
		this.states = states;
		model.addObserver(this);
		
		update(model, null);
	}
	
	// runs when controllers notifies StateModel about changes in state
	@Override
	public void update(Observable o, Object arg) {
		System.out.println(o);
		// TODO Auto-generated method stub
		// update gui here
		this.removeAll();
		this.add(states.get(((StateModel)o).getCurrentState()));
		this.revalidate();
		this.repaint();
	}
	
	
}