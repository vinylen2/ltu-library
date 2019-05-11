package library;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.cj.jdbc.result.ResultSetMetaData;


public class DatabaseConnector {
	  private Connection connect = null;
	  
	  public void connect() throws SQLException, IOException {
		  System.out.println("running dbconnector");
		  Properties prop = new Properties();
		  FileInputStream ip = new FileInputStream("src/library/db.properties");
		  prop.load(ip);
		  String dbUsername = prop.getProperty("username");
		  String dbPassword = prop.getProperty("password");

		  connect = DriverManager.getConnection("jdbc:mysql://localhost/library?user=" + dbUsername + "&password=" + dbPassword + "&serverTimezone=UTC");

		  Statement statement = connect.createStatement();
		  //ResultSet resultSet = statement.executeQuery("SELECT * FROM User");
		  
		}
}
