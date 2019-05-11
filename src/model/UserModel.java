package model;

public class UserModel {
	private static boolean isLoggedIn = false;
	private static String role = "";
	
	public boolean getState() {
		return isLoggedIn;
	}
	
	public void logIn() {
		isLoggedIn = true;
	}

	public void logOut() {
		isLoggedIn = false;
	}

	public String getRole() {
		return role;
	};
}
