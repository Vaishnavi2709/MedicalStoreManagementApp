package Project.BusinessLayer;

import Project.DataAccessLayer.UserDAL;
import Project.Models.User;

public class UserBL {
    private final UserDAL userDAL;
    public UserBL(UserDAL userDAL){
        this.userDAL=userDAL;
    }

    public boolean checkUserNameValidation(String userName){

        for (int i = 0; i < userName.length(); i++){
            if(
                    !((userName.charAt(i) >= 'a' && userName.charAt(i) <= 'z') ||
                            (userName.charAt(i) >= 'A' && userName.charAt(i) <= 'Z') ||
                            (userName.charAt(i) >= '0' && userName.charAt(i) <= '9'))
            ){
                return false;
            }
        }

        for( User item : userDAL.getAllUsers()){
            if(item.user_name.equals(userName)){
                return false;
            }
        }
        return true;
    }

    public boolean checkPasswordValidation(String password){
        boolean isUpper = false;
        boolean isLower = false;
        boolean isDigit = false;
        boolean isSpace = false;
        boolean isLength;
        boolean isSpl = false;

        for(int i=0; i<password.length();i++){
            if(password.charAt(i)>='A'&& password.charAt(i)<='Z'){
                isUpper = true;
            }
            else if (password.charAt(i)>='a' && password.charAt(i)<='z') {
                isLower = true;
            }
            else if (password.charAt(i)>='0' && password.charAt(i)<='9') {
                isDigit = true;
            }
            else if (password.charAt(i) ==' ') {
                isSpace = true;
            }
            else{
                isSpl = true;
            }
        }
        isLength = password.length()>=8&& password.length()<=16;
        return isLower && isUpper && isDigit && isLength && isSpl && !isSpace;
    }

    public User login(String username, String password){
        return userDAL.getUserByCredentials(username, password);
    }

    public User registration(String username, String password){
        if(checkUserNameValidation(username) && checkPasswordValidation(password)){
            return userDAL.addUser(username, password);
        }
        return null;
    }
    public boolean updateUserName(String userName, String password, String newName) {
        return userDAL.update(userName, password, newName, password);
    }
    public boolean updatePassword(String userName, String password, String newPassword) {
        return userDAL.update(userName, password, userName, newPassword);
    }
}
