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
			System.out.println("----------------Start Query 1: Arranging authors in alphabetical order----------------\n");
			query1(stmt);
			System.out.println("--------------------------------------End Query 1-------------------------------------\n\n");

			System.out.println("-------------------------Start Query 2: Select all publishers-------------------------\n");
			query2(stmt);
			System.out.println("--------------------------------------End Query 2-------------------------------------\n\n");

			System.out.println("------------Start Query 3: Get all books published by a specific publisher-------------\n");
			query3(stmt);
			System.out.println("--------------------------------------End Query 3-------------------------------------\n\n");

			System.out.println("----------------------------Start Query 4: Add a new author----------------------------\n");
			query4(stmt);
			System.out.println("--------------------------------------End Query 4-------------------------------------\n\n");

			System.out.println("------------Start Query 5: Edit/Update existing information about an author------------\n");
			query5(stmt);
			System.out.println("--------------------------------------End Query 5-------------------------------------\n\n");

			System.out.println("----------------------Start Query 6: Add a new title for an author---------------------\n");
			query6(stmt);
			System.out.println("--------------------------------------End Query 6-------------------------------------\n\n");

			System.out.println("---------------------------Start Query 7: Add new publisher----------------------------\n");
			query7(stmt);
			System.out.println("--------------------------------------End Query 7-------------------------------------\n\n");

			System.out.println("-----------Start Query 8: Edit/Update  existing information about a publisher----------\n");
			query8(stmt);
			System.out.println("--------------------------------------End Query 8-------------------------------------");

	   } catch(SQLException se) {
	      //Handle errors for JDBC
	      se.printStackTrace();
	   } finally {
	      //finally block used to close resources
	      JDBC.close();
	   }
	}

	/* Select all the authors from the author's table and arrange them in alphabetical order */
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

	/* Select all publishers from the publisher table */
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

	/* Select a specific publisher and list all books published by that publisher. 
	 * Include the title, yearand ISBN number. 
	 * Order the information alphabetically by title
	 */
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

	/* Adds a new author */
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
	
	/* Edit/Update the existing information about an author */
	public static void query5(Statement stmt) throws SQLException {
		// Execute a query
		stmt = JDBC.connection.createStatement();

		String query = "SELECT * FROM authors"; 

		// Prints the original Value
		ResultSet rs1 = stmt.executeQuery(query);
		System.out.println("Author's name changed from : ");
		while(rs1.next()){
			int id = rs1.getInt("authorID");
			if(id == 16){
				String firstName = rs1.getString("firstName");
				String lastName = rs1.getString("lastName");
				System.out.println(id + "\t" + firstName + "\t" + lastName);
			}
		}

		// Update Author's information
		String query5Author = "UPDATE authors SET firstName = 'NotWilliam' WHERE authorID= 16";
		stmt.executeUpdate(query5Author);
		
		// Prints the Updated Value
		ResultSet rs2 = stmt.executeQuery(query);
		while(rs2.next()){
			int id = rs2.getInt("authorID");
			if(id == 16){
				System.out.println(" to ");
				
				String updatedFName = rs2.getString("firstName");
				String updatedLName = rs2.getString("lastName");
				System.out.println(id + "\t" + updatedFName + "\t" + updatedLName);
				
			}
		}
		
		System.out.println();
		// Prints the Update Author table
		rs2 = stmt.executeQuery(query);
		System.out.println("----***-----");
		System.out.println("Show all Authors");
		System.out.println();
		while (rs2.next()) {
			int id = rs2.getInt("authorID");
			String firstName = rs2.getString("firstName");
			String lastName = rs2.getString("lastName");
			System.out.println(id + "\t" + firstName + "\t" + lastName);
		}
		
		String query5AuthorToOriginal = "UPDATE authors SET firstName = 'William' WHERE authorID= 16";
		stmt.executeUpdate(query5AuthorToOriginal);
	}

	/* Add a new title for an author */
	public static void query6(Statement stmt) throws SQLException {
		// Create new publisher for new title
		String query6Publisher = "INSERT INTO Publishers (publisherName)" +
	      				       "VALUES ('Crown')"; 
		stmt.executeUpdate(query6Publisher);
		System.out.println("Added Publisher 'Crown' ");

		
		// Add new title
		String query6Title = "INSERT INTO Titles (isbn, title, editionNumber, year, publisherID, price)" +
									"VALUES ('0553448122', 'Artemis: A Novel', '1', '2017', (SELECT publisherID FROM Publishers WHERE publisherName = 'Crown'), '16.20')"; 
		stmt.executeUpdate(query6Title);
		System.out.println("Added title 'Artemis: A Novel' ");


		// Link new title to author (In this case Andy Weir)
		String query6authorISBN = "INSERT INTO authorISBN (authorID, isbn)" +
									"VALUES ((SELECT authorID FROM Authors WHERE firstName = 'Andy' AND lastName = 'Weir'), '0553448122')"; 
		stmt.executeUpdate(query6authorISBN);
		System.out.println("Added authorISBN relation");

		// Print modified tables
		String exampleQuery2 = "SELECT * FROM Titles"; 
		System.out.println("Titles:");
		ResultSet rs2 = stmt.executeQuery(exampleQuery2);
		while (rs2.next()) {
			String isbn = rs2.getString("isbn");
			String title = rs2.getString("title");
			int edition = rs2.getInt("editionNumber");
			String year = rs2.getString("year");
			int pubID = rs2.getInt("publisherID");
			float price = rs2.getFloat("price");
			System.out.println(isbn + "\t" + title + "\t" + edition + "\t" + year + "\t" + pubID + "\t" + price);
		}

		String exampleQuery3 = "SELECT * FROM Publishers"; 
		System.out.println("Publishers:");
		ResultSet rs3 = stmt.executeQuery(exampleQuery3);
		while (rs3.next()) {
			int id = rs3.getInt("publisherID");
			String name = rs3.getString("publisherName");
			System.out.println(id + "\t" + name);
		}

		String exampleQuery4 = "SELECT * FROM authorISBN";
		System.out.println("authorISBN:"); 
		ResultSet rs4 = stmt.executeQuery(exampleQuery4);
		while (rs4.next()) {
			int id = rs4.getInt("authorID");
			String isbn = rs4.getString("isbn");
			System.out.println(id + "\t" + isbn);
		}
	}

	/* Add a new publisher */
	public static void query7(Statement stmt) throws SQLException {
		String query4Publisher = "INSERT INTO Publishers(publisherName)" + "VALUES('Random House')";
			
		stmt.executeUpdate(query4Publisher);

		String query = "SELECT * FROM publishers"; 

		
		System.out.println("Added publisher 'Random House':");
		
		ResultSet rs1 = stmt.executeQuery(query);
		
		
		while (rs1.next()) {
			int id = rs1.getInt("publisherID");
			String publisherName = rs1.getString("publisherName");
			System.out.println(id + "\t" + publisherName);
		}
	}

	/* Edit/Update the existing information about a publisher */
	public static void query8(Statement stmt) throws SQLException {
		// Display all current information to see original listing
		String query8View = "SELECT * FROM publishers;";

		System.out.println("Show all publishers Here!");

		ResultSet rs1 = stmt.executeQuery(query8View);

		while (rs1.next()) {
			int id = rs1.getInt("publisherID");
			String pubName = rs1.getString("publisherName");
			System.out.println(id + "\t" + pubName);
		}
		
		String query8Publisher = "UPDATE publishers SET publisherName='Lupin' WHERE publisherID = 1";

		// executeUpdate to manipulate data information for update command
		stmt.executeUpdate(query8Publisher);
		System.out.println("Command being executed: " + query8Publisher);
		ResultSet rs2 = stmt.executeQuery(query8View);
		while (rs2.next()) {
			int id = rs2.getInt("publisherID");
			String pubName = rs2.getString("publisherName");
			System.out.println(id + "\t" + pubName);
		}
		
		// Reset the Information
		String resetQuery = "UPDATE publishers SET publisherName='Penguin' WHERE publisherID = 1";
		stmt.executeUpdate(resetQuery);
	}

}
