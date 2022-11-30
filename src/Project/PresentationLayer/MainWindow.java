package Project.PresentationLayer;

import Project.BusinessLayer.MedicineBL;
import Project.BusinessLayer.UserBL;
import Project.Miscellaneous.ConsoleClean;
import Project.Models.User;

import java.util.Scanner;

public class MainWindow {
    Scanner scan = new Scanner(System.in);
    UserBL userBL;
    MedicineBL medicineBL;
    User user;
    UserWindow userWindow;
    ConsoleClean consoleClean;
    public MainWindow(UserBL userBL, MedicineBL medicineBL){
        this.userBL = userBL;
        this.medicineBL = medicineBL;
        consoleClean = new ConsoleClean();
    }

    public void menu(){
        while(true) {
            consoleClean.consoleClean();
            System.out.println("                    Main Menu");
            System.out.println("***************************************************************");
            System.out.println("                    Please Select");
            System.out.println("1.Login");
            System.out.println("2.Register");
            System.out.println("3.Exit");
            System.out.println(" please enter your selected operation number:");
            switch (scan.nextInt()) {
                case 1:
                    loginInput();
                    break;
                case 2:
                    regInput();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid Input. Try again.");
            }
        }
    }
    public void loginInput(){
        scan.nextLine();
        System.out.println("please enter the user name here:");
        String user_name=scan.nextLine();

        System.out.println("please enter the password here:");
        String password=scan.nextLine();

        if(!userBL.checkPasswordValidation(password)){
            System.out.println("INVALID PASSWORD. Please try again.");
            return;
        }
        user = userBL.login(user_name,password);
        if(user != null){
            System.out.println("Login Successful!");
            userWindow = new UserWindow(userBL, medicineBL, user);
            userWindow.userMenu();
        }
        System.out.println("Sorry, Something went wrong. Please try again later.");
    }

    public void regInput(){
        scan.nextLine();
        System.out.println("please enter the user name here:");
        String user_name=scan.nextLine();
        if(!userBL.checkUserNameValidation(user_name)){
            System.out.println("INVALID USERNAME. Please try again later.");
            return;
        }
        System.out.println("please enter the password here:");
        String password=scan.nextLine();
        if(!userBL.checkPasswordValidation(password)){
            System.out.println("INVALID PASSWORD. Please try again.");
            return;
        }
        user = userBL.registration(user_name,password);
        if(user != null){
            System.out.println("Registration Successful!");
            userWindow = new UserWindow(userBL, medicineBL, user);
            userWindow.userMenu();
            return;
        }
        System.out.println("Sorry, Something went wrong. Please try again later.");
    }
}
