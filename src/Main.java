import Project.Miscellaneous.StartUp;
import Project.PresentationLayer.MainWindow;

public class Main {
    public static void main(String[] args) {
        new StartUp();
        new MainWindow(StartUp.userBL, StartUp.medicineBL).menu();
    }
}