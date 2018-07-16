public class User extends Details {


    private String firstName;
    private String lastName;
    private String userName;
    private String password;

    public User(String firstName, String lastName, String userName, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;

    }

    public User(String userId, String firstName, String lastName, String userName, String password){
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }
}
