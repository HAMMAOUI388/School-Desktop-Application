package note;


import javax.swing.*;
import java.awt.*;

public class UserInterface {

    private CrudOperations crudOperation;

    public UserInterface(CrudOperations crudOperation) {
        this.crudOperation = crudOperation;
        createAndShowUI();
    }

    private void createAndShowUI() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Note Management System");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(900, 300);
            frame.setLayout(new BorderLayout());

            JButton addNoteButton = new JButton("Add Note");
            addNoteButton.addActionListener(e -> showAddNoteDialog());

            JButton updateNoteButton = new JButton("Update Note");
            updateNoteButton.addActionListener(e -> showUpdateNoteDialog());

            JButton deleteNoteButton = new JButton("Delete Note");
            deleteNoteButton.addActionListener(e -> showDeleteNoteDialog());

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(addNoteButton);
            buttonPanel.add(updateNoteButton);
            buttonPanel.add(deleteNoteButton);

            frame.add(buttonPanel, BorderLayout.CENTER);

            frame.setVisible(true);
        });
    }

    private void showAddNoteDialog() {
        JTextField apogeeField = new JTextField();
        JTextField nomField = new JTextField();
        JTextField filiereField = new JTextField();
        JTextField courField = new JTextField();
        JTextField noteField = new JTextField();

        Object[] fields = {
                "Apogee:", apogeeField,
                "Nom:", nomField,
                "Filiere:", filiereField,
                "Cour:", courField,
                "Note:", noteField
        };

        int result = JOptionPane.showConfirmDialog(null, fields, "Add Note", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String apogee = apogeeField.getText();
                String nom = nomField.getText();
                String filiere = filiereField.getText();
                String cour = courField.getText();
                double note = Double.parseDouble(noteField.getText());

                crudOperation.createOrUpdateNote(apogee, nom, filiere, cour, note);

                JOptionPane.showMessageDialog(null, "Note added successfully!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error adding note. Please check your input.");
                e.printStackTrace();
            }
        }
    }

    private void showUpdateNoteDialog() {
        JTextField apogeeField = new JTextField();
        JTextField courField = new JTextField();
        JTextField noteField = new JTextField();

        Object[] fields = {
                "Apogee:", apogeeField,
                "Nom du cour:",courField,
                "New Note:", noteField

        };

        int result = JOptionPane.showConfirmDialog(null, fields, "Update Note", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String apogee = apogeeField.getText();
                String cour = courField.getText();
                double note = Double.parseDouble(noteField.getText());

                crudOperation.createOrUpdateNote(apogee, "", "", cour, note);

                JOptionPane.showMessageDialog(null, "Note updated successfully!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error updating note. Please check your input.");
                e.printStackTrace();
            }
        }
    }
    private void showDeleteNoteDialog() {
        JTextField apogeeField = new JTextField();
        JTextField nomField = new JTextField();
        JTextField courField = new JTextField();
    
        Object[] fields = {
            "Apogee:", apogeeField,
            "Nom du cour:", courField,
            "Nom de l'étudiant:", nomField
        };
    
        int result = JOptionPane.showConfirmDialog(null, fields, "Delete Note", JOptionPane.OK_CANCEL_OPTION);
    
        if (result == JOptionPane.OK_OPTION) {
            String apogee = apogeeField.getText();
            nomField.getText();
          
            crudOperation.deleteNote(apogee);
            JOptionPane.showMessageDialog(null, "Note deleted successfully!");
        }
    }
    
    
}
