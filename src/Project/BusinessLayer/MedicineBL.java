package Project.BusinessLayer;

import Project.DataAccessLayer.MedicineDAL;
import Project.Miscellaneous.ModelConvertor;
import Project.Models.Medicine;
import Project.Models.ValidatedValue;

import java.util.ArrayList;

public class MedicineBL {
    MedicineDAL medicineDAL;
    public MedicineBL (MedicineDAL medRef) {
       medicineDAL=medRef;
    }
    //find medicine
    public Project.BusinessLayer.Models.Medicine getMedByName(String medicineName){
        Medicine medicine = medicineDAL.getMedicineByName(medicineName);
        if (medicine == null) {
            return null;
        }
        return ModelConvertor.ModelToBusinessModel(medicine);
    }
    public Medicine getDALMedByName(String medicineName){
        Medicine medicine = medicineDAL.getMedicineByName(medicineName);
        if (medicine == null) {
            return null;
        }
        return medicineDAL.getMedicineByName(medicineName);
    }
    //quantity validation
    public ValidatedValue quantityValidation(String medicineName, int medQuantity){
        int quantity_available = getMedByName(medicineName).medicine_quantity_ordered;
        return new ValidatedValue(quantity_available >= medQuantity, quantity_available);
    }
    public boolean addMed(String medicine_name,String medicine_type,int medicine_quantity_available,int medicine_MRP){
        return medicineDAL.addNewMeds(medicine_name,medicine_type,medicine_quantity_available,medicine_MRP);
    }
    public ArrayList<Medicine> getAllMeds(){
        return medicineDAL.getAllMeds();
    }

    public boolean updateMedicineQuantityByName(String medicineName, int quantity) {
        Medicine medicine = getDALMedByName(medicineName);
        return medicineDAL.update(medicine.medicine_name, medicine.medicine_name, medicine.medicine_type, medicine.medicine_quantity_available - quantity, medicine.medicine_MRP);
    }
    public boolean unCart(ArrayList<Project.BusinessLayer.Models.Medicine> cart) {
        ArrayList<Medicine> list = new ArrayList<Medicine>();
        for(Project.BusinessLayer.Models.Medicine medicine : cart){
            list.add(ModelConvertor.BusinessModelToModel(medicine));
        }
        medicineDAL.setMedicinesBackup();
        return medicineDAL.undoChanges(list);
    }
    public void undoUnCart() {
        medicineDAL.revert();
    }
}