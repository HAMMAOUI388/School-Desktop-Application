package enseignant;


import java.sql.*;
public class CRUDOperation {
    private static final String URL = "jdbc:mysql://localhost:3306/estbm";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    public static void insertdataenseignants(String nom, String prenom, String adresse, int age, String datenais,String sexe, String status, String role, String demaine) {
		   try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			   String query = "INSERT INTO enseignants_table (nom, prenom, adresse, age, datenais, sexe, status, role, domaine) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	            PreparedStatement preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setString(1, nom);
	            preparedStatement.setString(2, prenom);
	            preparedStatement.setString(3, adresse);
	            preparedStatement.setInt(4, age);
	            preparedStatement.setString(5, datenais);
	            preparedStatement.setString(6, sexe);
	            preparedStatement.setString(7, status);
	            preparedStatement.setString(8, role);
	            preparedStatement.setString(9, demaine);
	            preparedStatement.executeUpdate();
	            System.out.println("Données insérées avec succès.");
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }		
	}
  
    public static void updateData( String nom, String prenom, String adresse, int age, String datenais, String sexe, String status, String role, String domaine,int id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "UPDATE enseignants_table SET nom=?, prenom=?, adresse=?, age=?, datenais=?, sexe=?, status=?, role=?, domaine=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, adresse);
            preparedStatement.setInt(4, age);
            preparedStatement.setString(5, datenais);
            preparedStatement.setString(6, sexe);
            preparedStatement.setString(7, status);
            preparedStatement.setString(8, role);
            preparedStatement.setString(9, domaine);
            preparedStatement.setInt(10, id);
            

            preparedStatement.executeUpdate();
            System.out.println("Données mises à jour avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteData(int id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "DELETE FROM enseignants_table WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Donnée supprimée avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
}

