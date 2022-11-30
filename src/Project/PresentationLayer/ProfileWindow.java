package Project.PresentationLayer;

import Project.BusinessLayer.UserBL;
import Project.Miscellaneous.ConsoleClean;
import Project.Models.User;

import java.util.Scanner;
public class ProfileWindow {
    Scanner scanner = new Scanner(System.in);
    private final UserBL userBL;
    private final User user;
    private final ConsoleClean consoleClean;
    public ProfileWindow(UserBL userBL, User user) {
        this.userBL = userBL;
        this.user = user;
        consoleClean = new ConsoleClean();
    }
    public void menu() {
        while (true) {
            consoleClean.consoleClean();
            System.out.println("\t\t\t\t\t\tPROFILE MENU");
            System.out.println("1 for changing username");
            System.out.println("2 for changing password");
            System.out.println("3 for exiting");
            System.out.println("enter your choice:");
            switch (scanner.nextInt()) {
                case 1:
                    changeUserNameSection();
                    break;
                case 2:
                    changePasswordSection();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Please try again later!");
            }
        }
    }
    private void changeUserNameSection() {
        scanner.nextLine();
        System.out.println("please enter a new valid user-name:");
        String userName = scanner.nextLine();
        if (userBL.checkUserNameValidation(userName)) {
            if(userBL.updateUserName(user.user_name, user.password, userName)) {
                System.out.println("User Name has been updated.");
                return;
            }
            System.out.println("Something went wrong! Please try again later");
            return;
        }
        System.out.println("Invalid Credential!!!");
    }
    private void changePasswordSection() {
        scanner.nextLine();
        System.out.println("please enter a new valid password:");
        String password = scanner.nextLine();
        if (userBL.checkPasswordValidation(password)) {
            if(userBL.updatePassword(user.user_name, user.password, password)) {
                System.out.println("Password has been updated.");
                return;
            }
            System.out.println("Something went wrong! Please try again later");
            return;
        }
        System.out.println("Invalid Credential!!!");
    }
}

