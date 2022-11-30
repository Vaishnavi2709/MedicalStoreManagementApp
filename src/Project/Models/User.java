package Project.Models;

public class User {
    public String user_name;
    static int userIdCreator=1;
    private int userId;
    public String password;

    public User(String user_name, String password){
        this.user_name=user_name;
        this.password=password;
        this.userId=userIdCreator++;
    }

    public int getUserId() {
        return userId;
    }
}