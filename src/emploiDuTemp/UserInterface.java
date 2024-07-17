package emploiDuTemp;



import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileOutputStream;
import java.io.IOException;

class UserInterface {

    private CrudOperations crudOperation;

    private JComboBox<String> filiereComboBox;

    public UserInterface(CrudOperations crudOperation) {
        this.crudOperation = crudOperation;
        createAndShowUI();
    }

    public void setConsultEmploiDuTempsListener(ActionListener listener) {
        // Set ActionListener for the button
    }

    public String getFiliere() {
        // Retrieve selected filiere from the combo box
        return (String) filiereComboBox.getSelectedItem();
    }

    public String getSemester() {
        // Retrieve selected semester from user input
        return showSemesterDialog();
    }

    public void showEmploiDuTempsDialog(String emploiDuTemps) {
        try {
            // Use JFileChooser to let the user choose the save location
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Emploi du Temps");
            fileChooser.setSelectedFile(new java.io.File("EmploiDuTemps.pdf"));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF files", "pdf");
            fileChooser.setFileFilter(filter);
    
            int userSelection = fileChooser.showSaveDialog(null);
    
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                java.io.File fileToSave = fileChooser.getSelectedFile();
    
                // Check if the file already exists
                if (fileToSave.exists()) {
                    int overwriteConfirmation = JOptionPane.showConfirmDialog(
                            null,
                            "File already exists. Do you want to overwrite it?",
                            "File Exists",
                            JOptionPane.YES_NO_OPTION
                    );
    
                    if (overwriteConfirmation != JOptionPane.YES_OPTION) {
                        return; // User chose not to overwrite
                    }
                }
    
                try (FileOutputStream fos = new FileOutputStream(fileToSave)) {
                    fos.write(emploiDuTemps.getBytes());
                    JOptionPane.showMessageDialog(null, "Emploi du Temps saved successfully.");
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error saving Emploi du Temps.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error downloading Emploi du Temps.");
        }
    }
    

    private void createAndShowUI() {
        JFrame frame = new JFrame("Emploi du Temps");

        String[] filieres = {"GI", "GP", "ARI", "AA", "AI", "GO", "GEEE", "MLT"};
        filiereComboBox = new JComboBox<>(filieres);

        JButton consultButton = new JButton("Consult Emploi du Temps");

        consultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filiere = getFiliere();
                String semester = getSemester();

                if (semester != null && !semester.isEmpty()) {
                    String emploiDuTemps = crudOperation.fetchEmploiDuTemps(filiere, semester);
                    showEmploiDuTempsDialog(emploiDuTemps);
                }
            }
        });
        JLabel B = new JLabel();
        
        JPanel panl = new JPanel();
        panl.add(B);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Filiere:"));
        panel.add(filiereComboBox);
        panel.add(consultButton);
        JPanel p1= new JPanel();
        p1.setLayout(new BorderLayout());  
   

        p1.add(panel,BorderLayout.CENTER);
        
        frame.getContentPane().add(p1);
        
        frame.setSize(900, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
    private String showSemesterDialog() {
        String[] options = {"S1", "S2", "S3", "S4"};
        return (String) JOptionPane.showInputDialog(null, "Select Semester:", "Choose Semester",
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
    }
}
