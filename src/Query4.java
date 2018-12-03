
import java.sql.*;
import java.util.*;

public class Query4 {
   
   public static void main(String[] args) {
	   Statement stmt = null;
	   try{

	      // Open a connection
	      JDBC.connect();
	      
	      // Execute a query
	      stmt = JDBC.connection.createStatement();

	      // Create new author
	      String query4Author = "INSERT INTO Authors (authorID, firstName, lastName)" +
	      				       "VALUES ('16', 'William', 'Shakespeare')"; 
	      stmt.executeUpdate(query4Author);
	      System.out.println("Added Author 'William Shakespeare'");

	      String query = "SELECT * FROM authors"; 
	      
	      ResultSet rs1 = stmt.executeQuery(query);
	      
	      
	      while (rs1.next()) {
	          	int id = rs1.getInt("authorID");
	            String firstName = rs1.getString("firstName");
	            String lastName = rs1.getString("lastName");
	            System.out.println(id + "\t" + firstName + "\t" + lastName);
	          }

	      

	   } catch(SQLException se) {
	      //Handle errors for JDBC
	      se.printStackTrace();
	   } finally {
	      //finally block used to close resources
	      JDBC.close();
	   }
	}
}