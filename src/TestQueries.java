import java.sql.*;
import java.util.*;

public class TestQueries {
   
   public static void main(String[] args) {
	   Statement stmt = null;
	   try{

	      // Open a connection
	      JDBC.connect();
	      stmt = JDBC.connection.createStatement();
	      
	      // Query 1: Select all authors
			selectAuthors(stmt);

			// TODO: Query 2
			// TODO: Query 3
			// TODO: Query 4
			// TODO: Query 5
			// TODO: Query 6
			// TODO: Query 7
			// TODO: Query 8

	   } catch(SQLException se) {
	      //Handle errors for JDBC
	      se.printStackTrace();
	   } finally {
	      //finally block used to close resources
	      JDBC.close();
	   }
	}

	public static void selectAuthors(Statement stmt) throws SQLException {
		String query = "SELECT * FROM authors ORDER BY lastName , firstName "; 
		
		System.out.println("All authors ordered alphabetically by last name and first name:");
	   
		ResultSet result = stmt.executeQuery(query);
	   while (result.next()) {
	      System.out.println(result.getInt("authorID") + "\t\t" + result.getString("firstName") + "\t\t" + result.getString("lastName"));
	   }
	}
}