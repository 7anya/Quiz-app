package user;

import java.sql.*;  // Using 'Connection', 'Statement' and 'ResultSet' classes in java.sql package

import user.User;

public class JdbcSelectTest {   // Save as "JdbcSelectTest.java" (JDK 7 and above)
    public static void login(User usr) {
        try (
                // Step 1: Allocate a database 'Connection' object
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                        "root", "Orchid");   // For MySQL only
                // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"

                // Step 2: Allocate a 'Statement' object in the Connection
                Statement stmt = conn.createStatement();
        ) {
            // Step 3: Execute a SQL SELECT query. The query result is returned in a 'ResultSet' object.
            String strSelect = "select name, username, password from users where name=\""+usr.Name + "\" AND "+"password=\""+usr.Password+"\"";
            System.out.println("The SQL statement is: " + strSelect + "\n"); // Echo For debugging

            ResultSet rset = stmt.executeQuery(strSelect);

            // Step 4: Process the ResultSet by scrolling the cursor forward via next().
            //  For each row, retrieve the contents of the cells with getXxx(columnName).
            System.out.println("The records selected are:");
            int rowCount = 0;
            while (rset.next()) {   // Move the cursor to the next row, return false if no more row
                String title = rset.getString("username");
                String price = rset.getString("password");
                String qty = rset.getString("name");
                System.out.println(title + ", " + price + ", " + qty);
                ++rowCount;
            }
            if(rowCount==1){
                System.out.println("logged in successfully");

            }
            else{
                System.out.println("invalid user");

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)
    }

    public static void insert(User new_user) {
        try (
                // Step 1: Allocate a database 'Connection' object
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                        "root", "Orchid"); // for MySQL only

                // Step 2: Allocate a 'Statement' object in the Connection
                Statement stmt = conn.createStatement();
        ) {
            // INSERT a record
            String sqlInsert = "insert into users values (\""+new_user.Username+"\",\""+new_user.Password+"\",\""+new_user.Name +"\")";
            System.out.println("The SQL statement is: " + sqlInsert + "\n");  // Echo for debugging
            int countInserted = stmt.executeUpdate(sqlInsert);
            System.out.println(countInserted + " records inserted.\n");

            // Issue a SELECT to check the changes
            String strSelect = "select * from users";
            System.out.println("The SQL statement is: " + strSelect + "\n");  // Echo For debugging
            ResultSet rset = stmt.executeQuery(strSelect);
            while (rset.next()) {   // Move the cursor to the next row
                System.out.println(rset.getString("username") + ", "
                        + rset.getString("password") + ", "
                        + rset.getString("name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }  // Step 5: Close conn and stmt - Done automatically by try-with-resources
    }

}