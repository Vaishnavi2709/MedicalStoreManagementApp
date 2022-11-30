package Project.DataAccessLayer;

import Project.Models.User;
import java.util.ArrayList;

public class UserDAL {
    static ArrayList<User> users=new ArrayList<User>();

    public UserDAL(){
        if( users.size()==0){
            users.add(new User("Vaibhav Infonian","1'mmean3.14"));
            users.add(new User("Vaibhav Infonian2","1'mmean3.1444"));
            users.add(new User("Vaibhav Infonian3","1'mmean3.144444"));
        }
    }

    public int getUserId(String userName) {
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).user_name.equals(userName)) {
                return users.get(i).getUserId();
            }
        }
        return -99;
    }

    public User getUserByCredentials(String user_name,String password){
        for(int i=0; i<users.size();i++){
            if(users.get(i).user_name.equals(user_name)&& users.get(i).password.equals(password)){
                // if in this block then the current object contains the correct data
                return users.get(i);
            }
        }
        return null;
    }
    public int getUserIndex(String userName, String password){
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).user_name.equals(userName) && users.get(i).password.equals(password)){
                return i;
            }
        }
        return -99;
    }
    public ArrayList<User> getAllUsers(){
        ArrayList<User> results = users;
        return results;
    }

    public User addUser(String user_name,String password){
        users.add(new User(user_name,password));
        if(getUserIndex(user_name, password) != -99) {
            return users.get(getUserIndex(user_name, password));
        }
        return null;
    }

    public boolean remove(String user_name,String password){
        User user=getUserByCredentials(user_name,password);
        return users.remove(user);
    }

    public boolean update(String user_name,String password, String newName,String newPassword){
        try {
            User user = getUserByCredentials(user_name, password);
            user.user_name = newName;
            user.password = newPassword;
            return true;
        }catch (Exception e){
            return false;
        }
    }
}