package model;

public class User {
	private static boolean isLoggedIn = true;
	
	public boolean getState() {
		return isLoggedIn;
	}
	
	public void logIn() {
		isLoggedIn = true;
		System.out.println("Logged in");
	}

	public void logOut() {
		isLoggedIn = false;
	}

}
