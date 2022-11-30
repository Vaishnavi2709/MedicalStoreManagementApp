package Project.PresentationLayer;

import Project.BusinessLayer.Cart;
import Project.BusinessLayer.Models.Medicine;
import Project.Miscellaneous.ConsoleClean;

import java.util.Scanner;

public class BillWindow {
    private final Cart cart;
    private final ConsoleClean consoleClean;
    private final Scanner scanner;
    public BillWindow(Cart cart) {
        this.cart = cart;
        scanner = new Scanner(System.in);
        consoleClean = new ConsoleClean();
    }
    public void menu() {
        while (true) {
            consoleClean.consoleClean();
            System.out.println("""
                    BILLING WINDOW
                    1. View Bill
                    2. Pay and proceed
                    3. Exit""");
            switch (scanner.nextInt()) {
                case 1:
                    viewBillSection();
                    break;
                case 2:
                    payAndProceedSection();
                    return;
                case 3:
                    return;
                default:
                    System.out.println("Invalid input!!");
            }
        }
    }
    private void viewBillSection(){
        int totalPrice = 0;
        System.out.println("\t\t\t\t\t\t\t\t\t\tBILL");
        System.out.println("*********************************************************************************************");
        System.out.println("|Name\t\t|Quantity\t\t|Cost");
        System.out.println("_____________________________________________________________________________________________");
        for (Medicine medicine: cart.getCart()) {
            totalPrice += medicine.medicine_MRP * medicine.medicine_quantity_ordered;
            System.out.println(medicine.medicine_name + "\t\t\t" + medicine.medicine_quantity_ordered + "\t\t\t" + medicine.medicine_MRP * medicine.medicine_quantity_ordered);
        }
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Total Bill: " + totalPrice);
        System.out.println("---------------------------------------------------------------------------------------------");
    }
    private void payAndProceedSection() {
        if(cart.emptyCart()) {
            viewBillSection();
            return;
        }
        System.out.println("Something went wrong during billing...");
    }
}
