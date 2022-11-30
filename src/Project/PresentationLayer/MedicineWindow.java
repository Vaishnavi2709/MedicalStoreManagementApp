package Project.PresentationLayer;

import Project.BusinessLayer.Cart;
import Project.BusinessLayer.MedicineBL;
import Project.Miscellaneous.ConsoleClean;
import Project.Miscellaneous.Constants;

import java.util.ArrayList;
import java.util.Scanner;

public class MedicineWindow {
    Scanner scanner = new Scanner(System.in);
    private final MedicineBL medicineBL;
    private final BillingWindow billingWindow;
    private final ConsoleClean consoleClean;
    public MedicineWindow(MedicineBL medicineBL, Cart cart){
        this.medicineBL = medicineBL;
        billingWindow = new BillingWindow(medicineBL, cart);
        consoleClean = new ConsoleClean();
    }
    public void menu(){
        while (true) {
            consoleClean.consoleClean();
            System.out.println("Medicine Manager");
            System.out.println("1.Add a medicine");
            System.out.println("2.View all medicine");
            System.out.println("3.Billing Section");
            System.out.println("4.Exit");
            switch (scanner.nextInt()) {
                case 1:
                    addMedSec();
                    break;
                case 2:
                    viewAllMedicines();
                    break;
                case 3:
                    billingWindow.menu();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Something went wrong. Please try again!!");
            }
        }
    }
    /*
    * getMedicine(String medName) --> medicine
    * Validation(String medName, int quantity) --> {true, availableValue}
    * */

    private void addMedSec(){
        scanner.nextLine();

        System.out.println("Please enter the medicine name:");
        String medicineName = scanner.nextLine();
        if(medicineBL.getMedByName(medicineName) != null) {
            System.out.println("medicine already exists....");
            return;
        }
        String type = typeSelector();
        while(type.equals("failed")){
            System.out.println(type);
            type = typeSelector();
        }
        System.out.println("Please enter the medicine quantity:");
        int medicine_quantity_ordered = scanner.nextInt();

        System.out.println("Please enter the M.R.P.:");
        int medicineMRP = scanner.nextInt();

        if (medicineBL.addMed(medicineName, type, medicine_quantity_ordered, medicineMRP)) {
            System.out.println("Medicine added!");
            return;
        }
        System.out.println("Sorry Something went wrong! please try again later.");
    }
    private void printSubSet(String type, ArrayList<Project.Models.Medicine> medicineList) {
        boolean flag = false;
        for(Project.Models.Medicine medicine: medicineList){
            if(medicine.medicine_type.equals(type)) {
                String string = "";
                if(type.equals(Constants.SYRUP)){
                    string = "    ";
                }
                flag = true;
                System.out.println(medicine.getMedicine_serial_number() + "\t\t\t" + medicine.medicine_name + "\t\t\t\t\t\t" + medicine.medicine_type + string + "\t\t\t" + medicine.medicine_quantity_available + "\t\t\t\t\t\t\t" + medicine.medicine_MRP);
            }
        }
        if(!flag) {
            System.out.println("\t\t\t\t\t\t\t\t!!NOTHING TO SHOW FOR CATEGORY " + type.toUpperCase() +"!!");
        }
        System.out.println("---------------------------------------------------------------------------------------------");
    }
    private void viewAllMedicines(){
        scanner.nextLine();
        String[] Types = new String[]{Constants.SYRUP, Constants.TABLET, Constants.EAR_DROP, Constants.EYE_DROP, Constants.INHALER, Constants.GEL_TUBE};
        System.out.println("*********************************************************************************************");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\tMEDICINES");
        System.out.println("|Serial Number\t|Medicine Name\t\t\t|Medicine Type\t\t|Quality Available\t\t|M.R.P.");
        System.out.println("---------------------------------------------------------------------------------------------");
        for(String type: Types){
            printSubSet(type, medicineBL.getAllMeds());
        }
        System.out.println("*********************************************************************************************");
    }
    private String typeSelector() {
        int i = 0;
        String[] Types = new String[]{Constants.SYRUP, Constants.TABLET, Constants.EAR_DROP, Constants.EYE_DROP, Constants.INHALER, Constants.GEL_TUBE};
        for(String type: Types){
            System.out.println(++i + ". " + type);
        }
        System.out.println("Enter your choice:");
        switch (scanner.nextInt()){
            case 1:
                return Types[0];
            case 2:
                return Types[1];
            case 3:
                return Types[2];
            case 4:
                return Types[3];
            case 5:
                return Types[4];
            case 6:
                return Types[5];
            default:
                System.out.println("Invalid Input");
                return "failed";
        }
    }
}