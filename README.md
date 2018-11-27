# QueryBooksDatabase

## Setup
1. Download and Open MySQLWorkbench
2. Create a Connection. Give it any connection name and password. You can change the other default values or leave it as is
3. Connect to your new connection
4. Once connected, a new tab should open. At the top left of the toolbar, click the Create New Schema icon (It should be the fourth one from the left. It looks like a cylinder with a horizontal line and a plus sign)
5. In the new window opened in the middle of the screen, give the schema the name Booksdatabase and click Apply. Click Apply again and click close. The schema should have been created. If you don't see it just refresh the Schema tab.
6. Install mysql connector on your device. Mac users can copy the jar file from this repo into /Library/Java/Extensions (Terminal command: sudo cp -i mysql-connector-java-5.1.47-bin.jar /Library/Java/Extensions/)

## Running the Code
1. Compile all the java files and run CreateTables.java (This should create the necessary table with some test data)
2. Enter your database url, username and password. The database url should be jdbc:mysql://< your connection name >:< your connection port number >/Booksdatabase?autoReconnect=true&useSSL=false. The username and password will be your connection username and password.
3. Run TestQueries.java and repeat Step 2 (It should run a query to list all the authors in alphabetical order)