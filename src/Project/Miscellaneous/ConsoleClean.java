package Project.Miscellaneous;

public class ConsoleClean {
    public void consoleClean(){
        //To clean the console screen whenever needed
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
