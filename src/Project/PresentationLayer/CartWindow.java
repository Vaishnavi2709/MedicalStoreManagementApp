package Project.PresentationLayer;

import Project.BusinessLayer.Cart;
import Project.BusinessLayer.MedicineBL;
import Project.BusinessLayer.Models.Medicine;
import Project.Miscellaneous.ConsoleClean;
import Project.Models.ValidatedValue;

import java.util.Scanner;

public class CartWindow {
    private final MedicineBL medicineBL;
    private final Cart cart;
    private final Scanner scanner;
    private final ConsoleClean consoleClean;

    public CartWindow(MedicineBL medicineBL, Cart cart) {
        this.medicineBL = medicineBL;
        this.cart = cart;
        scanner = new Scanner(System.in);
        consoleClean = new ConsoleClean();
    }
    public void menu() {
        while(true) {
            consoleClean.consoleClean();
            System.out.println("Manage Cart:\n" +
                    "1. Add medicine in cart\n" +
                    "2. Remove medicine from cart\n" +
                    "3. exit cart" +
                    "\nenter your choice:");
            switch (scanner.nextInt()) {
                case 1:
                    addMedicineSection();
                    break;
                case 2:
                    removeMedicineSection();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("invalid input");
            }
        }
    }
    private void addMedicineSection() {
        scanner.nextLine();
        System.out.println("Please enter the medicine name:");
        String medicineName = scanner.nextLine();
        Medicine medicine = medicineBL.getMedByName(medicineName);

        if(medicine == null){
            System.out.println("Medicine does not exists...please check for spelling error or any other possible errors");
            return;
        }

        System.out.println("Please enter the medicine quantity:");
        medicine.medicine_quantity_ordered = scanner.nextInt();
        ValidatedValue result = medicineBL.quantityValidation(medicineName, medicine.medicine_quantity_ordered);
        if(result.isValid){
            cart.addToCart(medicineName, medicine.medicine_quantity_ordered);
            System.out.println("medicine added successfully to the cart");
        } else{
            System.out.println("sorry we only have " + result.currentAvailability);
        }
    }
    private void removeMedicineSection() {
        scanner.nextLine();
        System.out.println("Please enter the medicine name:");
        String medicineName = scanner.nextLine();
        if(!cart.isMedicineInCart(medicineName)){
            System.out.println("Medicine is not in the list. Please check the spelling and other possible errors.");
            return;
        }
        if(cart.removeFromCart(medicineName)) {
            System.out.println("Medicine removed successfully.");
            return;
        }
        System.out.println("Some Error Occurred. Try again later.");
    }
}
