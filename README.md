# QueryBooksDatabase

## Load Data
1. Download and Open MySQLWorkbench
2. Create a Connection. Give it any connection name and a password. You can leave the other default values
3. Connect to your new connection
4. Once connected, a new tab should open. At the top left of the toolbar, click the create new schema icon (It should be the fourth one from the left. It looks like a cylinder with a horizontal line and a plus icon)
5. In the new window opened in the middle of the screen, give the scheme the name Booksdatabase and click Apply
3. Go to Server > Data Import
4. Choose Import from Dump Project Folder and choose the path to the Booksdatabase folder in this repo
5. Click Load Folder Contents
6. Under the Select Database Objects to Import, choose BooksDatabase
7. Click Start Import
8. Once the Import is complete the BooksDatabase should have all the data needed under tables