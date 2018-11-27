import java.util.*;
import java.sql.*;

public class JDBC {

	public static Connection connection = null;

    public static void connect() throws SQLException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("MySQL JDBC Driver Registered!");
		} catch (ClassNotFoundException e) {
			System.out.println("MySQL JDBC Driver not found");
			e.printStackTrace();
			throw new SQLException();
		}

		String url = null, username = null, password = null;

		if(url == null || username == null || password == null) {
			Scanner scan = new Scanner(System.in);

			System.out.println("Answer the following questions to connect to your database: ");
			System.out.print("\tEnter your database url: ");
			url = scan.next();

			System.out.print("\n\tEnter your database username: ");
			username = scan.next();

			System.out.print("\n\tEnter your database password: ");
			password = scan.next();
		}

		System.out.println("\nTrying to connect to database ... ");
		connection = DriverManager.getConnection(url, username, password);

		if(connection == null) {
			throw new SQLException();
		} else {
			System.out.println("Successfully connected");
		}
    }

    public static void close() {
    	try {
    		if(connection != null) {
    			connection.close();
				System.out.println("Closing connection");
    		}
		} catch (SQLException e) {
			System.out.println("Failed to close connection!");
		}
    }
    
}