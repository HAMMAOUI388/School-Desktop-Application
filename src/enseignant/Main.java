package enseignant;


public class Main {
    public static void main(String[] args) {
        CreateDatabaseAndTabl.createTableEnseignants(); 
        Enseignatsinfo userInterface = new Enseignatsinfo();
            userInterface.createAndShowGUI();
    }
}
