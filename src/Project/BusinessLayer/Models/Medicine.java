package Project.BusinessLayer.Models;

public class Medicine {
    public String medicine_name;
    public int medicine_quantity_ordered;
    public int medicine_MRP;
    public Medicine(String medicine_name, int medicine_quantity_available, int medicine_MRP){
        this.medicine_name=medicine_name;
        this.medicine_quantity_ordered = medicine_quantity_available;
        this.medicine_MRP=medicine_MRP;
    }
}