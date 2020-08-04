import java.util.Scanner;

import user.User;
import user.JdbcSelectTest;
public class MyClass {
    static void Create() {
        System.out.println("Create a account here");
        Scanner name = new Scanner(System.in);
        Scanner username = new Scanner(System.in);
        Scanner password = new Scanner(System.in);
        User new_user = new User();
        System.out.println("Enter name");
        new_user.Name = name.nextLine();
        System.out.println("Enter username ");
        new_user.Username = username.nextLine();
        System.out.println("Enter password");
        new_user.Password = password.nextLine();
        System.out.println(new_user.Name + " " + new_user.Username + " " + new_user.Password);
        JdbcSelectTest.insert(new_user);

    }
    static void Login() {
        System.out.println("Login here");
        Scanner name = new Scanner(System.in);
        Scanner username = new Scanner(System.in);
        Scanner password = new Scanner(System.in);
        User new_user = new User();
        System.out.println("Enter name");
        new_user.Name = name.nextLine();
        System.out.println("Enter username ");
        new_user.Username = username.nextLine();
        System.out.println("Enter password");
        new_user.Password = password.nextLine();
        System.out.println(new_user.Name + " " + new_user.Username + " " + new_user.Password);
        JdbcSelectTest.login(new_user);


    }


    public static void main(String[] args) {
        System.out.println("Hello Welcome to the Quiz\n to login press 1, to create account press 2");
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        int userChoice = myObj.nextInt();  // Read user input
        System.out.println("Userchoice" + userChoice);
        if (userChoice == 2) {
            Create();
        }
        else if (userChoice== 1){
            Login();
        }
        else
        {
            System.out.println("invalid choice!");
        }


    }
}
