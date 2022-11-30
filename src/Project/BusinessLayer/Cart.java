package Project.BusinessLayer;

import Project.BusinessLayer.Models.Medicine;

import java.util.ArrayList;

public class Cart {
    private ArrayList<Medicine> cartList=new ArrayList<>();
    private final MedicineBL medicineBL;
    public Cart(MedicineBL medicineBL) {
        this.medicineBL = medicineBL;
    }
    public boolean addToCart(String medicineName, int quantity) {
        Medicine medicine = medicineBL.getMedByName(medicineName);
        if (medicine == null) {
            return false;
        }
        if(medicineBL.updateMedicineQuantityByName(medicineName, quantity)) {
            cartList.add(new Medicine(medicineName, quantity, medicine.medicine_MRP));
            return true;
        }
        return false;
    }
    public boolean removeFromCart(String medicineName) {
        Medicine medObj = null;
        for(Medicine medicine: cartList){
            if(medicine.medicine_name.equals(medicineName)){
                medObj = medicine;
                break;
            }
        }
        if(medObj != null){
            medicineBL.updateMedicineQuantityByName(medicineName, -1 * medObj.medicine_quantity_ordered);
            cartList.remove(medObj);
            return true;
        }
        return false;
    }
    public boolean isMedicineInCart(String medicineName) {
        for (Medicine medicine: cartList) {
            if (medicine.medicine_name.equals(medicineName)){
                return true;
            }
        }
        return false;
    }
    public ArrayList<Medicine> getCart() {
        return cartList;
    }
    public Medicine getMedicineFromCart(String medicineName) {
        for (Medicine medicine: cartList){
            if(medicine.medicine_name.equals(medicineName)){
                return medicine;
            }
        }
        return null;
    }
    public boolean emptyCart() {
        try {
            cartList = new ArrayList<Medicine>();
            return true;
        }catch (Exception e){
            return false;
        }
    }
}