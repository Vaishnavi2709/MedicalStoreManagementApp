package Project.PresentationLayer;

import Project.BusinessLayer.Cart;
import Project.BusinessLayer.MedicineBL;
import Project.BusinessLayer.UserBL;
import Project.Miscellaneous.ConsoleClean;
import Project.Models.User;

import java.util.Scanner;

public class UserWindow {
    Scanner scanner = new Scanner(System.in);
    final UserBL userBL;
    final MedicineBL medicineBL;
    final User user;
    final Cart cart;
    final ProfileWindow profileWindow;
    final MedicineWindow medicineWindow;
    final ConsoleClean consoleClean;
    public UserWindow(UserBL userBL, MedicineBL medicineBL, User user){
        this.userBL = userBL;
        this.medicineBL = medicineBL;
        this.user = user;
        consoleClean = new ConsoleClean();
        cart = new Cart(medicineBL);
        profileWindow = new ProfileWindow(userBL, user);
        medicineWindow = new MedicineWindow(medicineBL, cart);
    }
    public void userMenu(){
        while(true) {
            consoleClean.consoleClean();
            System.out.println("                    User Menu:");
            System.out.println("1. Update profile");
            System.out.println("2. Medicine manager");
            System.out.println("3. LogOut");

            System.out.println("Enter your choice here please:");
            switch (scanner.nextInt()) {
                case 1:
                    profileWindow.menu();
                    break;
                case 2:
                    medicineWindow.menu();
                    break;
                case 3:
                    logoutSection();
                    return;
                default:
                    System.out.println("Please try again later!");
            }
        }
    }
    private void logoutSection(){
        if(cart.getCart().size() != 0) {
            medicineBL.unCart(cart.getCart());
        }
    }
}
