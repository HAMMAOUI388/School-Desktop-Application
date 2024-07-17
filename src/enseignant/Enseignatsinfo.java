package enseignant;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Enseignatsinfo extends JFrame {


	private static final long serialVersionUID = 1L;
	private static final String URL = "jdbc:mysql://localhost:3306/estbm";
	    private static final String USER = "root";
	    private static final String PASSWORD = "";
	    private JTextArea textArea;

	    public void createAndShowGUI() {
	        setTitle("Gestion des Enseignant");
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	        JPanel mainPanel = new JPanel(new BorderLayout());
	        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

	        JPanel formPanel = new JPanel(new GridLayout(10,2, 10, 10));
	        formPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

	        JLabel nomLabel = new JLabel("Nom:");
	        JTextField nomField = new JTextField(15);
	        JLabel prenomLabel = new JLabel("Prénom:");
	        JTextField prenomField = new JTextField(15);
	        JLabel sexe = new JLabel("Sexe:");
	        JRadioButton btnfemenin = new JRadioButton("Féminin ");
	        JRadioButton btnmasculin = new JRadioButton("Masculin ");
	        ButtonGroup btngroup = new ButtonGroup();
	        btngroup.add(btnmasculin);
	        btngroup.add(btnfemenin);
	        JLabel adresseLabel = new JLabel("Adresse:");
	        JTextField adresseField = new JTextField(15);
	        JLabel ageLabel = new JLabel("Âge:");
	        JTextField ageField = new JTextField(5);
	       
	        JLabel filier = new JLabel("Domaine d'enseignants : ");
	        String[] fil = {"Informatique","Physique","Algebre","Analyse","Biologie"}; 
	        JComboBox<String> com = new JComboBox<> (fil);
	        JLabel datenais = new JLabel("Date de debut de travail : ");
	        JTextField dateneiField = new JTextField("jj-mm-aaaa");
	      
	        JLabel Status = new JLabel("Status : ");
	        JCheckBox pa = new JCheckBox("PA");
	        JCheckBox ph = new JCheckBox("PH");
	        JCheckBox pes = new JCheckBox("PES");

	        
	        
	        JLabel role = new JLabel("Role : ");
	        JCheckBox chefdep = new JCheckBox("Chef de Departement  ");
	        JCheckBox cheffil = new JCheckBox("Chef de filiére ");

	        formPanel.add(nomLabel);
	        formPanel.add(nomField);
	        formPanel.add(prenomLabel);
	        formPanel.add(prenomField);
	        
	        JPanel sexPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	        sexPanel.add(sexe);
	        sexPanel.add(btnmasculin);
	        sexPanel.add(btnfemenin);
	        formPanel.add(sexPanel);
	        
	        
	        formPanel.add(new JLabel("   "));

	        formPanel.add(adresseLabel);
	        formPanel.add(adresseField);
	        formPanel.add(ageLabel);
	        formPanel.add(ageField);
	        
	        formPanel.add(datenais);
	        formPanel.add(dateneiField);
	        formPanel.add(filier);
	        formPanel.add(com);
	        
	        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	        statusPanel.add(Status);
	        statusPanel.add(pa);
	        statusPanel.add(ph);
	        statusPanel.add(pes);

	        formPanel.add(statusPanel);
	        formPanel.add(new JLabel(""));
	        
	        JPanel rolepanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	        rolepanel.add(role);
	        rolepanel.add(chefdep);
	        rolepanel.add(cheffil);
	        formPanel.add(rolepanel);
	        
	        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
	        JButton addButton = new JButton("Inscrire");
	        JButton displayButton = new JButton("Mettre à jour");
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
        
	        afficherButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	
	              
	                displayData();
	            }
	        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 String nom = nomField.getText();
                 String prenom = prenomField.getText();
                 String adresse = adresseField.getText();
                 int age = Integer.parseInt(ageField.getText());
                 String datenais = dateneiField.getText();
                 String sexe = "";
                 if (btnmasculin.isSelected()) {
                     sexe = "Masculin";
                 } else if (btnfemenin.isSelected()) {
                     sexe = "Féminin";
                 }
                 String demaine = (String) com.getSelectedItem();
                 String status = "";
                 if (pa.isSelected()) {
                     status = "PA";
                 } else if (ph.isSelected()) {
                     status = "PH";
                 } else if (pes.isSelected()) {
                     status = "PES";
                 }

                 String role = "  ";
                 if (chefdep.isSelected()) {
                     role = " Chef de departement ";
                 } else if (cheffil.isSelected()) {
                     role = " Chef de filière ";
                 }

                 CRUDOperation.insertdataenseignants(nom, prenom, adresse, age, datenais, sexe, status, role, demaine);
                 affichermsg();

            }
        });
      
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = nomField.getText();
                String prenom = prenomField.getText();
                String adresse = adresseField.getText();
                int age = Integer.parseInt(ageField.getText());
                String datenais = dateneiField.getText();
                
                String sexe = "";
                if (btnmasculin.isSelected()) {
                    sexe = "Masculin";
                } else if (btnfemenin.isSelected()) {
                    sexe = "Féminin";
                }
                String demaine = (String) com.getSelectedItem();
                String status = "";
                if (pa.isSelected()) {
                    status = "PA";
                } else if (ph.isSelected()) {
                    status = "PH";
                } else if (pes.isSelected()) {
                    status = "PES";
                }

                String role = "  ";
                if (chefdep.isSelected()) {
                    role = " Chef de departement ";
                } else if (cheffil.isSelected()) {
                    role = " Chef de filière ";
                }
                int idToUpdate = Integer.parseInt(JOptionPane.showInputDialog("Entrez l'ID à mettre à jour :"));
                CRUDOperation.updateData(nom, prenom, adresse, age,datenais,sexe,status,role,demaine,idToUpdate);
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
        
    }

    private void displayData() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM enseignants_table");

            StringBuilder result = new StringBuilder();
            while (resultSet.next()) {
                result.append("ID: ").append(resultSet.getInt("id"))
                        .append(", Nom: ").append(resultSet.getString("nom"))
                        .append(", Prénom: ").append(resultSet.getString("prenom"))
                        .append(", Sexe : ").append(resultSet.getString("sexe"))

                        .append(", Adresse: ").append(resultSet.getString("adresse"))
                        .append(", Âge: ").append(resultSet.getInt("age"))
                        .append(", Date debut de travail : ").append(resultSet.getString("datenais"))
                        .append(", Domaine de Travail : ").append(resultSet.getString("domaine"))
                        .append(", Status : ").append(resultSet.getString("status"))
                        .append(", Role : ").append(resultSet.getString("role"))

                        
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
            
                result.append("Enseignant a été ajouter avec succes \n");          
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
            
                result.append("Enseignant a été supprimer avec succes \n");          
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
            
                result.append("Enseignant a été modifier avec succes \n");          
            textArea.setText(result.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
