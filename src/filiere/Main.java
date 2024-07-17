package filiere;


public class Main {
    public static void main(String[] args) {
        CreateDatabaseAndTabl.createTablefiliere(); 
        FiliereInt filiereinterface = new FiliereInt();
        filiereinterface.createAndShowGUI();
    }
}
