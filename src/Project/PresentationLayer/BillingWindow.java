package Project.PresentationLayer;

import Project.BusinessLayer.Cart;
import Project.BusinessLayer.MedicineBL;
import Project.BusinessLayer.Models.Medicine;
import Project.Miscellaneous.ConsoleClean;

import java.util.Scanner;

public class BillingWindow {
    private final MedicineBL medicineBL;
    private final Scanner scanner;
    private final Cart cart;
    private final ConsoleClean consoleClean;
    private final CartWindow cartWindow;
    private final BillWindow billWindow;
    public BillingWindow(MedicineBL medicineBL, Cart cart) {
        this.medicineBL = medicineBL;
        scanner = new Scanner(System.in);
        this.cart = cart;
        consoleClean = new ConsoleClean();
        cartWindow = new CartWindow(medicineBL, cart);
        billWindow = new BillWindow(cart);
    }
    public void menu(){
        while(true) {
            consoleClean.consoleClean();
            System.out.println(
                    """
                            Billing Section:
                            1. View Cart
                            2. Manage Cart
                            3. Manage bill
                            4. Discard Cart
                            5. Exit Cart
                            enter your choice:""");
            switch (scanner.nextInt()) {
                case 1:
                    viewCartSection();
                    break;
                case 2:
                    cartWindow.menu();
                    break;
                case 3:
                    billWindow.menu();
                    break;
                case 4:
                    discardCartSection();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid Input! Please try again later.");
            }
        }
    }
    private void viewCartSection() {
        System.out.println("\t\t\t\t\t\t\t\t\t\tCart");
        System.out.println("*********************************************************************************************");
        System.out.println("|Name\t\t|Quantity\t\t|Cost");
        System.out.println("_____________________________________________________________________________________________");
        for (Medicine medicine: cart.getCart()) {
            System.out.println(medicine.medicine_name + "\t\t\t" + medicine.medicine_quantity_ordered + "\t\t\t" + medicine.medicine_MRP * medicine.medicine_quantity_ordered);
        }
        System.out.println("---------------------------------------------------------------------------------------------");
    }
    private void discardCartSection(){
        if(medicineBL.unCart(cart.getCart())){
            if (cart.emptyCart()){
                System.out.println("cart emptied...");
                return;
            }
            System.out.println("sorry could not empty cart for the moment. try again later.");
            medicineBL.undoUnCart();
            return;
        }
        System.out.println("something went wrong. please try again later.");
    }
}
