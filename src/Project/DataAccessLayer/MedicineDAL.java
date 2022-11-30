package Project.DataAccessLayer;

import Project.Miscellaneous.Constants;
import Project.Models.Medicine;

import java.util.ArrayList;

public class MedicineDAL {
    ArrayList<Medicine> medicines=new ArrayList<Medicine>();
    ArrayList<Medicine> medicinesBackup=new ArrayList<Medicine>();
    public MedicineDAL(){
        if( medicines.size()==0){
            medicines.add(new Medicine("Ibugesic Plus", Constants.TABLET, 200,2));
            medicines.add(new Medicine("Moxicip", Constants.EYE_DROP,165,210));
            medicines.add(new Medicine("Oxydyne", Constants.SYRUP, 145,85));
            medicines.add(new Medicine("Ofax", Constants.EAR_DROP, 180,60));
            medicines.add(new Medicine("Rabekind", Constants.TABLET, 190,9));
            medicines.add(new Medicine("OraGel", Constants.GEL_TUBE, 225,80));
            medicines.add(new Medicine("Corex", Constants.SYRUP, 250,96));
            medicines.add(new Medicine("Vicks Inhaler", Constants.INHALER, 145,85));
        }
    }
    public int getMedicine_serial_number(String medname){
        for(int i=0;i<medicines.size();i++){
            if(medicines.get(i).medicine_name.equals(medname)){
                return medicines.get(i).getMedicine_serial_number();
            }
        }
        return -77;
    }
    public ArrayList<Medicine> getAllMeds(){
        ArrayList<Medicine> results = medicines;
        return results;
    }
    public Medicine getMedicineByName(String medname){
        for(int i=0;i<medicines.size();i++){
            if(medicines.get(i).medicine_name.equals(medname)){
                return medicines.get(i);
            }
        }
        return null;
    }
    public boolean addNewMeds(String medName,String medicine_type,int medicine_quantity_available,int medicine_MRP){
        return medicines.add(new Medicine(medName,medicine_type,medicine_quantity_available,medicine_MRP));
    }
    public boolean removeMed(String medName){
        Medicine med= getMedicineByName(medName);
        return medicines.remove(med);
    }
    public boolean update(String medName,String newmedname,String newmedicine_type,int newmedicine_quantity_available,int newmedicine_MRP){
        try {
            Medicine med = getMedicineByName(medName);
            med.medicine_name = newmedname;
            med.medicine_type = newmedicine_type;
            med.medicine_quantity_available = newmedicine_quantity_available;
            med.medicine_MRP = newmedicine_MRP;
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    public boolean undoChanges(ArrayList<Medicine> cart) {
        if(cart.size() == 0){
            return true;
        }
        try {
            for (Medicine medicine : cart) {
                for (Medicine stockMedicine : medicines) {
                    if (medicine.medicine_name.equals(stockMedicine.medicine_name)) {
                        stockMedicine.medicine_quantity_available += medicine.medicine_quantity_available;
                        break;
                    }
                }
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public void setMedicinesBackup() {
        medicinesBackup = medicines;
    }
    public void revert() {
        medicines = medicinesBackup;
    }
}