import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PSQLConnection {

    private Connection c = null;

    private String defaultUser = "";

    private String defaultPassword = "";

    private String defaultDatabase = "";

    private Statement stmt = null;

    public PSQLConnection(String host, String port, String database, String user, String pass, Completion completion){

        this.connectToDatabase(host,port,database,user,pass,completion);

    }

    public PSQLConnection(String host, String port, String database, String user, Completion completion){

        this.connectToDatabase(host,port,database,user,this.defaultPassword,completion);


    }

    public PSQLConnection(String host, String port, String database, Completion completion){
        this.connectToDatabase(host,port,database,this.defaultUser,this.defaultPassword,completion);

    }

    public PSQLConnection(String host, String port, Completion completion){

        this.connectToDatabase(host,port,this.defaultDatabase,this.defaultUser,this.defaultPassword,completion);
    }


    private void connectToDatabase(String host, String port, String database, String user, String pass, Completion completion){

        try {

            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://"+host+":"+port+"/"+database,
                            user, pass);


            if (completion != null) {
                completion.onCompleted();
            }


        } catch (Exception e) {
            // e.printStackTrace();
            // System.err.println(e.getClass().getName()+": "+e.getMessage());
            // System.exit(0);


            if (completion != null) {
                completion.onRefused(e);
            }

        }
        //  System.out.println("Opened database successfully");

    }



    public void newStatement(String statement, Completion completion){
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
        /*String sql = "CREATE TABLE COMPANY " +
                "(ID INT PRIMARY KEY     NOT NULL," +
                " NAME           TEXT    NOT NULL, " +
                " AGE            INT     NOT NULL, " +
                " ADDRESS        CHAR(50), " +
                " SALARY         REAL)"; */
        stmt.executeUpdate(statement);
        stmt.close();
        c.commit();
        c.close();
        completion.onCompleted();
    } catch ( Exception e ) {
       // System.err.println( e.getClass().getName()+": "+ e.getMessage() );
       // System.exit(0);
            completion.onRefused(e);
    }
    }


    public void getUser(String statement, Completion completion){

        try {

        stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery( statement);



        while ( rs.next() ) {

            String  userId = rs.getString(Constants.user_id);
            String  firstName = rs.getString(Constants.first_name);
            String  lastName = rs.getString(Constants.last_name);

            User user = new User(userId,firstName,lastName,"","");

            completion.onCompleted(user);



        }
        rs.close();
        stmt.close();
        c.close();
    } catch ( Exception e ) {
       // System.err.println( e.getClass().getName()+": "+ e.getMessage() );
       // System.exit(0);
            completion.onRefused(e);
    }

    }


}
