package user;
import java.util.Scanner;
import java.sql.*;
import user.User;
public class Quiz {
    public static void format(User usr) {
        System.out.println("Choose a format here  ");
        System.out.println("The topics are : 1. Addition 2. Subtraction 3. Multiplication 4. fibonnaci seequence  ");
        Scanner num = new Scanner(System.in);
        System.out.println("how many questions do you want from Addition ");
        int add = num.nextInt();
        System.out.println("how many questions do you want from Subtraction ");
        int sub = num.nextInt();
        System.out.println("how many questions do you want from Multiplication");
        int multi = num.nextInt();
        System.out.println("how many questions do you want from Fibonacci Sequence");
        int fib = num.nextInt();
        generate_questions(add,sub,multi,fib);
        

    }
    public static void generate_questions(int add,int sub,int multi,int fib){
        System.out.println("your quiz starts now");
        while (add--){

            query_question("addition",id);
        }
        while (sub--){

            query_question("subtraction",id);
        }
        while (multi--){

            query_question("multiplication",id);
        }
        while (fib--){

            query_question("fibonacci",id);
        }
    }
    public static int query_question(String db_name,int id){
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
            String strSelect = "select question,choice1,choice2,choice3,choice4,answer from "+db_name+" where id=" id;
            System.out.println("The SQL statement is: " + strSelect + "\n"); // Echo For debugging

            ResultSet rset = stmt.executeQuery(strSelect);

            // Step 4: Process the ResultSet by scrolling the cursor forward via next().
            //  For each row, retrieve the contents of the cells with getXxx(columnName).
            System.out.println("The records selected are:");
            int rowCount = 0;
            while (rset.next()) {   // Move the cursor to the next row, return false if no more row
                String question = rset.getString("question");
                String choice1 = rset.getString("choice1");
                String choice2 = rset.getString("choice2");
                String choice3 = rset.getString("choice3");
                String choice4 = rset.getString("choice4");
                String answer= rset.getString("answer");
                System.out.println("question: "+ question+"\n 1."+choice1+"\n2."+choice2+"\n3."+choice3+"\n4."+choice4);
                Scanner ob = new Scanner(system.in);
                System.out.println("Enter answer:");
                String ans= nextLine(ob);
                if(ans== answer)
                {
                    System.out.println("correct answer!");
                    return 1;
                }
                else
                {
                    System.out.println("Wrong");
                    return 0;
                }


                ++rowCount;
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)
    }


}