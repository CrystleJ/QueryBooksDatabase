import java.sql.*;
import java.util.*;

public class CreateTables {

   public static void main(String[] args) {
	   Statement stmt = null;
	   try{

	   	System.out.println("Would you like to delete all the data? (y/n) ");
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();

		if(input.equals("y") || input.equals("Y") ) {
			// Open a connection
		    JDBC.connect();
		    stmt = JDBC.connection.createStatement();

		    // Drop Tables if they exist
		    String dropAuthors = "DROP TABLE IF EXISTS Authors ";
		  	stmt.executeUpdate(dropAuthors);
            

		  	String dropAuthorISBN = "DROP TABLE IF EXISTS authorISBN ";
		  	stmt.executeUpdate(dropAuthorISBN);

		  	String dropTitles = "DROP TABLE IF EXISTS Titles ";
		  	stmt.executeUpdate(dropTitles);

		  	String dropPublishers = "DROP TABLE IF EXISTS Publishers ";
		  	stmt.executeUpdate(dropPublishers);

		  	System.out.println("All the data has been deleted. \nCreating new tables...");

		    // Create Tables
		    stmt.executeUpdate("CREATE TABLE Authors " + "(authorID INTEGER NOT NULL AUTO_INCREMENT, " +
                    " firstName CHAR(20), " + " lastName CHAR(20), " + " PRIMARY KEY (authorID))");

		    System.out.println("\tAuthors table complete");

            stmt.executeUpdate("CREATE TABLE authorISBN " + "(authorID INTEGER REFERENCES Authors(authorID), " +
		            " isbn CHAR(10) REFERENCES Titles(isbn))");
		    
            System.out.println("\tAuthorISBN table complete");

		    stmt.executeUpdate("CREATE TABLE Titles " + "(isbn CHAR(13) NOT NULL, " + " title VARCHAR(255), " +
		        " editionNumber INTEGER, " + " year CHAR(4), " + 
                " publisherID INTEGER REFERENCES Publishers(publisherID), " + " price DECIMAL(8,2), " +
		        " PRIMARY KEY (isbn))");

		    System.out.println("\tTitles table complete");

		    stmt.executeUpdate("CREATE TABLE Publishers " + "(publisherID INTEGER NOT NULL AUTO_INCREMENT, " +
		        " publisherName CHAR(100), " + " PRIMARY KEY (publisherID))");

		    System.out.println("\tPublishers table complete");

            System.out.println("Inserting test data into all the tables");

            //Populate all the tables with sample data
            updateAuthors(stmt);
			updateAuthorIsbn(stmt);
			updateTitles(stmt);
			updatePublishers(stmt);

			System.out.println("\tTest data sucessfully inserted!");
		}

	   } catch(SQLException se) {
	      // Handle errors for JDBC
	      se.printStackTrace();
	   } finally {
	      // Finally block, used to close resources
	   	  if(stmt != null) {
	   	  	JDBC.close();
	   	  }
	   }
	}

	private static void updateAuthors(Statement stmt) {
        // Data
		String firstNames[] = {"David", "Mary", "Thomas", "Margaret", "Leo", "Herman", "James", 
                "Carlos", "J.R.R", "Philip", "Joseph", "Louisa", "Kathryn", "George", "Toni"};
		String lastNames[] = { "Mitchell", "Karr", "Pyncho", "Atwood", "Tolstoy", "Melville", "Joyce",
                "Zafon", "Tolkien", "Pullman", "Heller", "Alcott", "Stockett", "Orwell", "Morrison"};

        // Reset auto increment
		try {
			stmt.executeUpdate("ALTER TABLE Authors AUTO_INCREMENT = 1");
		} catch (SQLException e) {
			System.out.println("Failed to update AUTO_INCREMENT for the authors table.");
			e.printStackTrace();
			return;
		}

        // Insert data to table
        String dataSQLScript = "INSERT INTO Authors (firstName, lastName) VALUES";
		for (int i = 0; i < firstNames.length-1; i++) {
			dataSQLScript = dataSQLScript + "('" + firstNames[i]
					+ "', '" + lastNames[i] + "'), ";
		}
        dataSQLScript = dataSQLScript + "('" + firstNames[firstNames.length-1]
				+ "', '" + lastNames[lastNames.length-1] + "');";

        try {
			stmt.executeUpdate(dataSQLScript);
		} catch (SQLException e) {
			System.out.println("Failed to insert test data into the authors table.");
			e.printStackTrace();
			return;
		}

		System.out.println("\tAuthors table updated");
	}

	private static void updateAuthorIsbn(Statement stmt) {
        //Data
		String isbn[] = {"0812994711", "0143035746", "0143039946", "0385490818", "1400079985", "0679783275",
				"9780679722", "0143126393", "0345339703", "0440238137", "0679437223", "0375756728", "0425232204",
				"0452284236", "1400033423", "0452277507", "1400033411", "0307276767"};

        // Insert data to table
        String sqlScript =  "INSERT INTO authorISBN (authorID, isbn) Values";
		for (int i = 1; i < isbn.length; i++) {
			sqlScript = sqlScript + "(" + i + ", '" + isbn[i - 1] + "'), ";
		}
        sqlScript = sqlScript + "(" + isbn.length + ", '" + isbn[isbn.length - 1] + "');";

        try {
			stmt.executeUpdate(sqlScript);
		} catch (SQLException e) {
			System.out.println("Failed to insert test data into the authorsIsbn table.");
			e.printStackTrace();
			return;
		}

		System.out.println("\tAuthorIsbn table updated");
	}

	private static void updateTitles(Statement stmt) {
        // Data
		String isbn[] = {"0812994711", "0143035746", "0143039946", "0385490818", "1400079985", "0679783275",
				"9780679722", "0143126393", "0345339703", "0440238137", "0679437223", "0375756728", "0425232204",
				"0452284236", "1400033423", "0452277507", "1400033411", "0307276767"};
		String title[] = { "Cloud Atlas", "The Liars'' Club: A Memoir", "Gravity''s Rainbow", "The Handmaid''s Tale",
				"War and Peace", "Moby Dick", "Ulysses", "The Shadow of the Wind","The Fellowship of the Ring", 
                "The Golden Compass: His Dark Materials", "Catch-22", "Little Women", "The Help", "1984", 
                "Song of Solomon", "Animal Farm", "Beloved", "A Mercy"};
		int editionNumber[] = {3, 2, 4, 1, 6, 7, 5, 2, 6, 3, 3, 5, 1, 4, 2, 5, 3, 1};
		String year[] = {"2003", "2005", "2006", "1998", "2008", "2018", "2016", "2014", "2003", "2003", "1995",
				"2004", "2011", "2003", "1998", "2003", "1987", "2008"};
		int publisherID[] = { 1, 2, 3, 4, 5, 6, 7, 3, 8, 9, 10, 1, 11, 11, 10, 11, 12, 13, 14, 15};
		double price[] = {15.63, 8.15, 14.95, 14.95, 13.59, 12.11, 16.99, 22, 10.95, 7.19, 17.68, 9.59, 8.21, 11.55,
				8.29, 13.49, 16.19, 23.18};

        // Insert data in to table
        String sqlScript = "INSERT INTO Titles (isbn, title, editionNumber, year, publisherID, price) VALUES";
        int i;
		for (i = 0; i < isbn.length; i++) {
            sqlScript = sqlScript + "('" + isbn[i] + "', '" + title[i] + "', " + editionNumber[i] + ", '" + year[i] + "', "
					+ publisherID[i] + ", " + price[i] + ")";
            if(i == isbn.length-1) {
                sqlScript = sqlScript + ";";
            } else { sqlScript = sqlScript + ", "; }	    
		}

        try {
			stmt.executeUpdate(sqlScript);
		} catch (SQLException e) {
			System.out.println("Failed to insert test data into the titles table.");
			e.printStackTrace();
			return;
		}

		System.out.println("\tTitles table updated");
	}

	private static void updatePublishers(Statement stmt) {
        // Data
		String publishers[] = { "Modern Library", "Penguin Books", "Penguin Classics", "Anchor", "Vintage", 
            "CreateSpace Independent Publishing Platform", "Digireads.com", "Laurel Leaf", "Everyman''s Library", 
            "Berkley", "Collectible First Editions", "Bloomsbury Academy", "Houghton Milfflin Harcourt", "Chelsea House Pub"};

        // Reset auto increment
		try {
			stmt.executeUpdate("ALTER TABLE Publishers AUTO_INCREMENT = 1");
		} catch (SQLException e) {
			System.out.println("Failed to update AUTO_INCREMENT for the authors table.");
			e.printStackTrace();
			return;
		}

        // Insert data into table
        String sqlScript = "INSERT INTO Publishers (publisherName) VALUES";
		for (int i = 0; i < publishers.length - 1; i++) {
			sqlScript = sqlScript + "('" + publishers[i] + "'), ";
		}
        sqlScript =  sqlScript + "('" + publishers[publishers.length - 1] + "');";

        try {
			stmt.executeUpdate(sqlScript);
		} catch (SQLException e) {
			System.out.println("Failed to insert test data into the publishers table.");
			e.printStackTrace();
			return;
		}

		System.out.println("\tPublishers table updated");
	}
}
