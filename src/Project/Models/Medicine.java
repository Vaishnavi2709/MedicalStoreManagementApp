package Project.Models;

public class Medicine {
    public String medicine_name;
    //medicine_type(syrup, tablet, eye drop, ear drop, etc)
    public String medicine_type;

    static int medicine_number=1000;
    private int medicine_serial_number;
    public int medicine_quantity_available;
    public int medicine_MRP;

    public Medicine (String medicine_name,String medicine_type,int medicine_quantity_available,int medicine_MRP){
        this.medicine_name=medicine_name;
        this.medicine_type=medicine_type;
        this.medicine_quantity_available=medicine_quantity_available;
        this.medicine_MRP=medicine_MRP;
        this.medicine_serial_number=medicine_number++;
    }

    public int getMedicine_serial_number(){
        return medicine_serial_number;
    }
}