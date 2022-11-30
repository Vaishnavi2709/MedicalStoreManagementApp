package Project.Miscellaneous;

import Project.Models.Medicine;

public class ModelConvertor {

    public static Project.BusinessLayer.Models.Medicine ModelToBusinessModel(Medicine medicine){
        return new Project.BusinessLayer.Models.Medicine(medicine.medicine_name, medicine.medicine_quantity_available, medicine.medicine_MRP);
    }
    public static Medicine BusinessModelToModel(Project.BusinessLayer.Models.Medicine medicine) {
        return new Medicine(medicine.medicine_name, "", medicine.medicine_quantity_ordered, medicine.medicine_MRP);
    }
}
