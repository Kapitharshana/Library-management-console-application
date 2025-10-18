package library_management_with_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbconnection {
	
	public static final String url="jdbc:mysql://localhost:3306/library_management";
	public static final String username="root";
	public static final String password = "Kapi_*314";
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

}
