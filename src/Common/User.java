package Common;

public class User {
	public boolean isLoggedIn = false;
	public int userId = 0;
	public String role = "";
	public String firstname = "Hej";
	public String lastname = "";

	public User(boolean isLoggedIn, int userId, String role, String firstname, String lastname) {
		this.isLoggedIn = isLoggedIn;
		this.userId = userId;
		this.role = role;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	public User() {
		
	}

}
