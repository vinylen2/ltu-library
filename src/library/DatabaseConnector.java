package library;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

import com.mysql.cj.jdbc.result.ResultSetMetaData;


public class DatabaseConnector {
	  private Connection connect = null;
	  

	  public DatabaseConnector() throws SQLException, IOException {
		  connect();
	  }

	  public Connection getConnection() {
		  return connect;
	  }

	  private void connect() throws SQLException, IOException {
		  Properties prop = new Properties();
		  FileInputStream ip = new FileInputStream("src/library/db.properties");
		  prop.load(ip);

		  String dbUsername = prop.getProperty("username");
		  String dbPassword = prop.getProperty("password");

		  connect = DriverManager.getConnection("jdbc:mysql://localhost/library?user=" + dbUsername + "&password=" + dbPassword + "&serverTimezone=UTC");
		}
}
