import java.sql.Connection;
import java.sql.DriverManager;

public class DataHandler {

   // private Connection connection = null;

    PSQLConnection psqlConnection;
    private String user_details_table = "user_details";

    User details;

    public DataHandler(){

        psqlConnection = new PSQLConnection("localhost", "5432","users", "postgres", "1234", new Completion() {
            public void onCompleted() {
                System.out.println("Connected");
               // connection = c;
            }

            public void onRefused(Exception exception) {
                System.out.println("Connection refused");
            }
        });

    }

    public void addUser(User user){

        String statement = "INSERT INTO "+this.user_details_table+" (user_id, first_name, last_name, user_name, password) "
                + "VALUES ('"+user.getUserId()+"', '"+user.getFirstName()+"', '"+user.getLastName()+"', '"+user.getUserName()+"', '"+user.getPassword()+"');";

        System.out.println(statement);

        psqlConnection.newStatement(statement, new Completion() {
            public void onCompleted() {
                System.out.println("Added new user.");
            }

            public void onRefused(Exception exception) {
                System.out.println("Refused to add a new user.");
            }
        });

    }

    public void login(String username, String password, final Completion completion){



        psqlConnection.getUser("SELECT *FROM user_details WHERE user_name='" + username + "' AND password='" + password + "';", new Completion() {
            @Override
            void onCompleted(User user) {
                super.onCompleted(user);
              completion.onCompleted(user);
            }

            @Override
            void onRefused(Exception exception) {
                super.onRefused(exception);

            }
        });

    }








}
