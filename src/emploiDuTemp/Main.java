package emploiDuTemp;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Connection databaseConnection = CreateDatabaseAndTables.createDatabaseConnection();
            CrudOperations crudOperations = new CrudOperations(databaseConnection);
            UserInterface userInterface = new UserInterface(crudOperations);

            ((UserInterface) userInterface).setConsultEmploiDuTempsListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String filiere = ((UserInterface) userInterface).getFiliere();
                    String semester = ((UserInterface) userInterface).getSemester();

                    String emploiDuTemps = crudOperations.fetchEmploiDuTemps(filiere, semester);
                    userInterface.showEmploiDuTempsDialog(emploiDuTemps);
                }
            });
        });
    }
}