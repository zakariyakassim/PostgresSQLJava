import java.util.UUID;

public abstract class Details {

    protected String userId = "";

    public String getUserId(){
        if (this.userId.equalsIgnoreCase("")){
            return UUID.randomUUID().toString();
        }
        return this.userId;

    }

    public abstract String getFirstName();
    public abstract String getLastName();
    public abstract String getUserName();
    public abstract String getPassword();


}
