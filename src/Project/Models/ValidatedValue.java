package Project.Models;

public class ValidatedValue {
    public boolean isValid;
    public int currentAvailability;
    public ValidatedValue(boolean isValid, int currentAvailability) {
        this.isValid = isValid;
        this.currentAvailability = currentAvailability;
    }
}
