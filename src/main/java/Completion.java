import java.sql.Connection;

public abstract class Completion {


    void onCompleted(User user) {
        System.out.println("connected");
    }

    void onCompleted() {
        System.out.println("connected");
    }

    void onRefused(Exception exception) {
        System.out.println("Refused");
    }


}
