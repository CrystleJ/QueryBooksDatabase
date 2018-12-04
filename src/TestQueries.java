import java.sql.*;
import java.util.*;

public class TestQueries {
   
   public static void main(String[] args) {
	   Statement stmt = null;
	   try{

	      // Open a connection
	      JDBC.connect();
	      stmt = JDBC.connection.createStatement();
	      
	      // Initializing queries
			query1(stmt);
			query2(stmt);
			query3(stmt);
			query4(stmt);
			
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

	public static void query1(Statement stmt) throws SQLException {
		String query = "SELECT * FROM authors ORDER BY lastName , firstName "; 
		
		System.out.println("All authors ordered alphabetically by last name and first name:");
	   
		ResultSet result = stmt.executeQuery(query);
	   while (result.next()) {
			int id = result.getInt("authorID");
			String fName = result.getString("firstName");
			String lName = result.getString("lastName");
			if(fName.length() < 8) {
	      	System.out.println(id + "\t\t" + fName + "\t\t" + lName);
			} else {
				System.out.println(id + "\t\t" + fName + "\t" + lName);
			}
	   }
	}

	public static void query2(Statement stmt) throws SQLException {
        
		String query2Author = "SELECT * FROM publishers";

      System.out.println("Show all publishers");

      ResultSet rs1 = stmt.executeQuery(query2Author);

      while (rs1.next()) {
         int id = rs1.getInt("publisherID");
         String pubName = rs1.getString("publisherName");
         System.out.println(id + "\t" + pubName);
      }
    }

	public static void query3(Statement stmt) throws SQLException {
	   String query3_name = "SELECT publisherID, publisherName FROM publishers WHERE publisherID=3";
          
      ResultSet rs1 = stmt.executeQuery(query3_name);
      String name = null;
      while(rs1.next()){
         int id = rs1.getInt("publisherID");
         name = rs1.getString("publisherName");
      }
      
		System.out.println("List of books by the publisher " + name + ":");
      
		String query3_books = "SELECT isbn, title, year, publisherID FROM titles WHERE publisherID=3 ORDER BY title";
      ResultSet rs2 = stmt.executeQuery(query3_books);
           
      while(rs2.next()){
         int id = rs2.getInt("publisherID");
         String isbn = rs2.getString("isbn");
         String title = rs2.getString("title");
         String year = rs2.getString("year");

         System.out.println(id + " - " + title + " - " + year + " - " + isbn);
      }
	}

	public static void query4(Statement stmt) throws SQLException {
	   
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
	}
}

