package cour;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Gestioncour extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final String URL = "jdbc:mysql://localhost:3306/estbm";
	    private static final String USER = "root";
	    private static final String PASSWORD = "";
	    private JTextArea textArea;

	    public void createAndShowGUI() {
	        setTitle("Gestion des Cours");
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	        JPanel mainPanel = new JPanel(new BorderLayout());
	        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

	        JPanel formPanel = new JPanel(new GridLayout(10,2, 10, 10));
	        formPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

	        JLabel nomLabel = new JLabel("Nom De Cour :");
	        JTextField nomField = new JTextField(15);
	        JLabel semestre = new JLabel("Semestre  De Cour :");

	        String[] sem = {"S1","S2","S3","S4"};
	        JComboBox<String>  com =new JComboBox<>(sem);
	        
	        JLabel hr = new JLabel("Ajouter des Heures :");
	        JTextField hrtext = new JTextField();
	        
	        JLabel ens = new JLabel("Ajouter Un Responsable  :");
	        JTextField respo = new JTextField();	       

	        formPanel.add(nomLabel);
	        formPanel.add(nomField);
	       
	        formPanel.add(semestre);
	        formPanel.add(com);
	        formPanel.add(hr);
	        formPanel.add(hrtext);
	        formPanel.add(ens);
	        formPanel.add(respo);
	        
	       
	        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
	        JButton addButton = new JButton("Ajouter");
	        JButton displayButton = new JButton("Modifier ");
	        JButton deleteButton = new JButton("Supprimer");
	        JButton afficherButton = new JButton("Afficher");

	        buttonPanel.add(addButton);
	        buttonPanel.add(displayButton);
	        buttonPanel.add(deleteButton);
	        buttonPanel.add(afficherButton);

	        textArea = new JTextArea(60, 40);
	        textArea.setEditable(false);
	        JScrollPane scrollPane = new JScrollPane(textArea);
	        mainPanel.add(formPanel, BorderLayout.NORTH);
	        mainPanel.add(scrollPane, BorderLayout.CENTER);
	        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
	        add(mainPanel);
	        pack();
	        setLocationRelativeTo(null);
	        setVisible(true);
	  
        
        
       addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = nomField.getText();
               
                String heure = hrtext.getText();
                String resp = respo.getText();
               
               
                String semestre = (String) com.getSelectedItem();
               
                CRUDOperation.insertdatacour(nom, heure,resp,semestre);
                affichermsg();
            }
        });
      
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = nomField.getText();
                String heure = hrtext.getText();
                String resp = respo.getText();
                String semestre = (String) com.getSelectedItem();
                if (resp.isEmpty()) {
                    JOptionPane.showMessageDialog(Gestioncour.this, "Veuillez saisir un Enseignants responsable .");
                    return;  
                }
                int idmod;
                try {
                    idmod = Integer.parseInt(JOptionPane.showInputDialog("Entrez l'ID à modifier :"));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Gestioncour.this, "Veuillez saisir un nombre valide pour l'ID.");
                    return; 
                }

                CRUDOperation.updateData(nom, heure,resp,semestre,idmod);
                modifier();
            }
        });

       
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
                int idToDelete = Integer.parseInt(JOptionPane.showInputDialog("Entrez l'ID à supprimer :"));
                CRUDOperation.deleteData(idToDelete);
                supprimer();
            }
        });
        
       
        afficherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
              
                displayData();
            }
        });
        
        
    }

    private void displayData() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM cour_table");

            StringBuilder result = new StringBuilder();
            while (resultSet.next()) {
                result.append("ID: ").append(resultSet.getInt("id"))
                        .append(", Nom du cour: ").append(resultSet.getString("nom"))
                        .append(", Nombre Des heures : ").append(resultSet.getString("heure"))
                        .append(", Enseignant Responsable sur cette filiére  : ").append(resultSet.getString("respo"))
                        .append(", Le semestre au cours duquel ce cours sera étudié : ").append(resultSet.getString("semestre"))

                        .append("\n");
            }
            textArea.setText(result.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void affichermsg() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            @SuppressWarnings("unused")
			Statement statement = connection.createStatement();
           

            StringBuilder result = new StringBuilder();
            
                result.append("Cour a été ajouter avec succes \n");          
            textArea.setText(result.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void supprimer() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            @SuppressWarnings("unused")
			Statement statement = connection.createStatement();
           

            StringBuilder result = new StringBuilder();
            
                result.append("Cour a été supprimer avec succes \n");          
            textArea.setText(result.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void modifier() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            @SuppressWarnings("unused")
			Statement statement = connection.createStatement();
           

            StringBuilder result = new StringBuilder();
            
                result.append("Cour a été modifier avec succes \n");          
            textArea.setText(result.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
