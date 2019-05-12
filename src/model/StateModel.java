package model;
import java.util.Observable;

public class StateModel extends Observable {
	private ApplicationState currentState = ApplicationState.Home;
	
	public enum ApplicationState {
		Home,
		Login,
		SearchObject,
		Admin,
		User,
		AddBorrower,
		AddObject, 
		AddLoan,
		
	}
	
	public ApplicationState getCurrentState() {
		return currentState;
	}
	
	public void setApplicationState(ApplicationState state) {
		currentState = state;
		setChanged();

		// notifies DynamicView about change in state
		notifyObservers();
	}
}
