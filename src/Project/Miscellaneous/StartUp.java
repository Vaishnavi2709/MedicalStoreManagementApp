package Project.Miscellaneous;

import Project.BusinessLayer.MedicineBL;
import Project.BusinessLayer.UserBL;
import Project.DataAccessLayer.MedicineDAL;
import Project.DataAccessLayer.UserDAL;

public class StartUp {
    public static UserBL userBL;
    public static UserDAL userDAL;
    public static MedicineBL medicineBL;
    public static MedicineDAL medicineDAL;

    public StartUp() {
        if(userDAL == null || userBL == null || medicineDAL == null || medicineBL == null) {
            userDAL = new UserDAL();
            userBL = new UserBL(userDAL);
            medicineDAL = new MedicineDAL();
            medicineBL = new MedicineBL(medicineDAL);
        }
    }
}
