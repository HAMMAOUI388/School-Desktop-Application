package examen;



import javax.swing.*;
import java.awt.*;

import java.sql.Date;


public class UserInterface {

    private CrudOperations crudOperation;

    public UserInterface(CrudOperations crudOperation) {
        this.crudOperation = crudOperation;
        createAndShowUI();
    }

    private void createAndShowUI() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Exam Management System");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(900, 300);
            frame.setLayout(new BorderLayout());

            JButton addButton = new JButton("Add Exam");
            addButton.addActionListener(e -> showAddExamDialog());

            JButton updateButton = new JButton("Update Exam");
            updateButton.addActionListener(e -> showUpdateExamDialog());

            JButton deleteButton = new JButton("Delete Exam");
            deleteButton.addActionListener(e -> showDeleteExamDialog());

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(addButton);
            buttonPanel.add(updateButton);
            buttonPanel.add(deleteButton);

            frame.add(buttonPanel, BorderLayout.CENTER);

            frame.setVisible(true);
        });
    }

    private void showAddExamDialog() {
        JTextField courseField = new JTextField();
        JTextField examDateField = new JTextField("yyyy-MM-dd");
        JTextField locationField = new JTextField();

        Object[] fields = {
                "Course:", courseField,
                "Exam Date:", examDateField,
                "Location:", locationField
        };

        int result = JOptionPane.showConfirmDialog(null, fields, "Add Exam", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            // Parse examDateField as a Date. This is a simplified example; you might need to handle parsing more robustly.
            // You may also consider using a date picker or a formatted text field for better user input.
            Date examDate = Date.valueOf(examDateField.getText());

            crudOperation.createExam(courseField.getText(), examDate, locationField.getText());
            JOptionPane.showMessageDialog(null, "Exam added successfully!");
        }
    }


    private void showUpdateExamDialog() {
        JTextField examIdField = new JTextField();
        JTextField newDateField = new JTextField();
        JTextField newLocationField = new JTextField();

        Object[] fields = {
                "Exam ID:", examIdField,
                "New Exam Date (yyyy-mm-dd):", newDateField,
                "New Location:", newLocationField
        };

        int result = JOptionPane.showConfirmDialog(null, fields, "Update Exam", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int examId = Integer.parseInt(examIdField.getText());
                Date newDate = Date.valueOf(newDateField.getText()); // Assuming the date is entered in yyyy-mm-dd format
                String newLocation = newLocationField.getText();
                
                crudOperation.updateExam(examId, newDate, newLocation);
                
                JOptionPane.showMessageDialog(null, "Exam updated successfully!");
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Error updating exam. Please check your input.");
                e.printStackTrace();
            }
        }
    }




    private void showDeleteExamDialog() {
        JTextField examIdField = new JTextField();

        Object[] fields = {
                "Exam ID:", examIdField
        };

        int result = JOptionPane.showConfirmDialog(null, fields, "Delete Exam", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            crudOperation.deleteExam(Integer.parseInt(examIdField.getText()));
            JOptionPane.showMessageDialog(null, "Exam deleted successfully!");
        }
    }
}

