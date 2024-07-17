package exercise;


public class Main {
    public static void main(String[] args) {
        CreateDatabaseAndTabl.createTable(); 
        UserInterface userInterface = new UserInterface();
            userInterface.createAndShowGUI();
    }
}

