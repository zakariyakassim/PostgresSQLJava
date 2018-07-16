import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    DataHandler dataHandler;

    Scanner input = new Scanner(System.in);

    public void run(){

        dataHandler = new DataHandler();

        System.out.println("Select an option: \n L for login \n S for Sign Up \n Q for Quit ");

        String option = input.next();

        if (option.equalsIgnoreCase("s")) {
            this.SignUp();
            System.out.println();
        }
        if (option.equalsIgnoreCase("l")) {
            this.login();
            System.out.println();
        }


    }

    public void SignUp(){
        System.out.println("First Name:");
        String name = input.next();
        System.out.println();

        System.out.println("Last Name:");
        String lastName = input.next();
        System.out.println();

        System.out.println("User Name:");
        String userName = input.next();
        System.out.println();

        System.out.println("Password:");
        String password = input.next();
        System.out.println();

        System.out.println("Repeat Password:");
        String repeatPassword = input.next();
        System.out.println();

        if (this.checkIfPasswordMatch(password, repeatPassword)){
            dataHandler.addUser(new User(name,lastName,userName,password));
        }



    }

    public Boolean checkIfPasswordMatch(String password1, String password2){

        return password1.equalsIgnoreCase(password2);
    }

    public void login(){

        System.out.println("User Name:");
        final String userName = input.next();
        System.out.println();

        System.out.println("Password:");
        String password = input.next();
        System.out.println();

        dataHandler.login(userName, password, new Completion() {
            @Override
            void onCompleted(User user) {
                super.onCompleted(user);

                System.out.println("length: " +  user.getUserId().length());

                if (user.getUserId().length() > 0) {

                    System.out.println("User found!");
                    System.out.println();
                    System.out.println("User ID: " + user.getUserId());
                    System.out.println("First Name: " + user.getFirstName());
                    System.out.println("Last Name: " + user.getLastName());
                }else if (user.getUserId().length() == 0) {
                    System.out.println("User not found, Try again.");
                }



            }

            @Override
            void onRefused(Exception exception) {
                super.onRefused(exception);

            }
        });


    }



    public static void main(String[] args) {


       new Main().run();





    }
}
