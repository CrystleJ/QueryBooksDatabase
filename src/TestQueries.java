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
	      				       "VALUES ('16', 'Will', 'Shakespeare')"; 
	   stmt.executeUpdate(query4Author);
	   System.out.println("Added Author 'Will Shakespeare'");

	   String query = "SELECT * FROM authors"; 
	      
	   ResultSet rs1 = stmt.executeQuery(query);
	         
	   while (rs1.next()) {
	      int id = rs1.getInt("authorID");
	      String firstName = rs1.getString("firstName");
	      String lastName = rs1.getString("lastName");
	      if(firstName.length() < 8) {
	      	System.out.println(id + "\t\t" + firstName + "\t\t" + lastName);
			} else {
				System.out.println(id + "\t\t" + firstName + "\t" + lastName);
			}
	   }
	}

	/* Edit/Update the existing information about an author */
	public static void query5(Statement stmt) throws SQLException {
		
		String query = "SELECT * FROM authors"; 

		// Retrieve and print the original Value
		ResultSet orginal_result = stmt.executeQuery(query);

		int id = 0;
		String fName = null, lName = null;
		while(orginal_result.next()){
			id = orginal_result.getInt("authorID");
			if(id == 16){
				fName = orginal_result.getString("firstName");
				lName = orginal_result.getString("lastName");
			}
		}

		System.out.println("Author's name at id#"+id+" changed from: ");
		System.out.println("\tFROM:" + fName + " " + lName);

		// Update Author's information
		String queryAuthor = "UPDATE authors SET firstName = 'William' WHERE authorID= 16";
		stmt.executeUpdate(queryAuthor);
		
		// Prints the Updated Value
		ResultSet updated_result = stmt.executeQuery(query);
		id = 0;
		String updatedFName = null, updatedLName = null;
		while(updated_result.next()){
			id = updated_result.getInt("authorID");
			if(id == 16){
				updatedFName = updated_result.getString("firstName");
				updatedLName = updated_result.getString("lastName");
			}
		}
		System.out.println("\tTO:" + updatedFName + " " + updatedLName);

		// Printing the updated author table
		updated_result = stmt.executeQuery(query);
		System.out.println("\n*****************Printing Updated Table*****************\n");
		System.out.println("Authors table:");		
		while (updated_result.next()) {
			id = updated_result.getInt("authorID");
			fName = updated_result.getString("firstName");
			lName = updated_result.getString("lastName");
			if(fName.length() < 8) {
	      	System.out.println(id + "\t\t" + fName + "\t\t" + lName);
			} else {
				System.out.println(id + "\t\t" + fName + "\t" + lName);
			}
		}
	}

	/* Add a new title for an author */
	public static void query6(Statement stmt) throws SQLException {
		// Create new publisher for new book
		String queryPublisher = "INSERT INTO Publishers (publisherName)" +
	      				       "VALUES ('Grand Central Publishing')"; 
		stmt.executeUpdate(queryPublisher);
		System.out.println("Added the publisher: 'Grand Central Publishing' ");
		
		// Adding a new book
		String queryTitle = "INSERT INTO Titles (isbn, title, editionNumber, year, publisherID, price)" +
									"VALUES ('0446310786', 'To Kill a Mockingbird', '1', '1988', (SELECT publisherID FROM Publishers WHERE publisherName = 'Grand Central Publishing'), '7.30')"; 
		stmt.executeUpdate(queryTitle);
		System.out.println("Added the book 'To Kill a Mockingbird' ");

		// Link the new title to the author
		String queryAuthorISBN = "INSERT INTO authorISBN (authorID, isbn)" +
									"VALUES ((SELECT authorID FROM Authors WHERE firstName = 'Harper' AND lastName = 'Lee'), '0446310786')"; 
		stmt.executeUpdate(queryAuthorISBN);
		System.out.println("Updated authorISBN in regards to the newly added book");

		// Print modified tables
		System.out.println("*****************Printing Updated Table*****************\n");

		queryPublisher = "SELECT * FROM Publishers"; 
		System.out.println("Updated publishers:");
		ResultSet resultPub = stmt.executeQuery(queryPublisher);
		while (resultPub.next()) {
			int id = resultPub.getInt("publisherID");
			String pName = resultPub.getString("publisherName");
			System.out.println(id + "\t" + pName);
		}

		queryTitle = "SELECT * FROM Titles"; 
		System.out.println("\nUpdated titles table:");
		ResultSet resultTitle = stmt.executeQuery(queryTitle);
		while (resultTitle.next()) {
			String isbn = resultTitle.getString("isbn");
			String title = resultTitle.getString("title");
			int ed = resultTitle.getInt("editionNumber");
			String year = resultTitle.getString("year");
			int pID = resultTitle.getInt("publisherID");
			float price = resultTitle.getFloat("price");

			System.out.print(isbn + "\t" + title); 
			if(title.length() < 8) {
	      	System.out.print("\t\t\t\t\t");
			} else  if(title.length() < 16) {
	      	System.out.print("\t\t\t\t");
			} else  if(title.length() < 24) {
	      	System.out.print("\t\t\t");
			} else  if(title.length() < 32) {
	      	System.out.print("\t\t");
			} else {
				System.out.print("\t");
			}
			System.out.print(ed + "\t" + year + "\t" + pID + "\t" + price+"\n");
		}

		queryAuthorISBN = "SELECT * FROM authorISBN";
		System.out.println("\nUpdated authorISBN table:"); 
		ResultSet resultAuthorISBN = stmt.executeQuery(queryAuthorISBN);
		while (resultAuthorISBN.next()) {
			int id = resultAuthorISBN.getInt("authorID");
			String isbn = resultAuthorISBN.getString("isbn");
			System.out.println(id + "\t" + isbn);
		}
	}

	/* Add a new publisher */
	public static void query7(Statement stmt) throws SQLException {
		String queryPublisher = "INSERT INTO Publishers(publisherName)" + "VALUES('Forge Books')";
		stmt.executeUpdate(queryPublisher);
		System.out.println("Added publisher 'Forge Books'\n");

		// Print updated table
		System.out.println("*****************Printing Updated Table*****************\n");
		System.out.println("Publishers table:");

		String query = "SELECT * FROM publishers"; 
		ResultSet result = stmt.executeQuery(query);
		while (result.next()) {
			int id = result.getInt("publisherID");
			String pName = result.getString("publisherName");
			System.out.println(id + "\t" + pName);
		}
	}

	/* Edit/Update the existing information about a publisher */
	public static void query8(Statement stmt) throws SQLException {
		
		String query = "SELECT * FROM publishers"; 

		// Retrieve and print the original Value
		ResultSet orginal_result = stmt.executeQuery(query);

		int id = 0;
		String pName = null;
		boolean end = false;
		while(orginal_result.next() && !end){
			id = orginal_result.getInt("publisherID");
			if(id == 8){
				pName = orginal_result.getString("publisherName");
				end = true;
			}
		}

		System.out.println("Publisher's name at id#"+id+" changed from: ");
		System.out.println("\tFROM:" + pName);

		// Update Publisher's information
		String queryPublisher = "UPDATE publishers SET publisherName='Yanny Leaf' WHERE publisherID=8";
		stmt.executeUpdate(queryPublisher);
		
		// Prints the Updated Value
		ResultSet updated_result = stmt.executeQuery(query);
		id = 0;
		String updatedPName = null;
		while(updated_result.next()){
			id = updated_result.getInt("publisherID");
			if(id == 8){
				updatedPName = updated_result.getString("publisherName");
			}
		}
		System.out.println("\tTO:" + updatedPName);

		// Printing the updated publishers table
		updated_result = stmt.executeQuery(query);
		System.out.println("\n*****************Printing Updated Table*****************\n");
		System.out.println("Publishers table:");		
		while (updated_result.next()) {
			id = updated_result.getInt("publisherID");
			pName = updated_result.getString("publisherName");
			
			System.out.println(id + "\t" + pName);
		}

		// Reset the Information
		String resetQuery = "UPDATE publishers SET publisherName='Laurel Leaf' WHERE publisherID = 8";
		stmt.executeUpdate(resetQuery);
	}

}
