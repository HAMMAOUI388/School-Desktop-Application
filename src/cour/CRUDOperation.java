package cour;


import java.sql.*;
public class CRUDOperation {
    private static final String URL = "jdbc:mysql://localhost:3306/estbm";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    public static void insertdatacour(String nom, String heure, String respo,String semestre) {
		   try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			   String query = "INSERT INTO cour_table (nom, heure,respo,semestre) VALUES ( ?, ?,?,?)";
	            PreparedStatement preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setString(1, nom);
	            preparedStatement.setString(2, heure);
	            preparedStatement.setString(3, respo);
	            preparedStatement.setString(4, semestre);
	            preparedStatement.executeUpdate();
	            System.out.println("Données insérées avec succès.");
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }		
	}
  
    public static void updateData(String nom, String heure, String respo,String semestre,int id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "UPDATE cour_table SET nom=?, heure=?,respo=?,semestre=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, heure);
            preparedStatement.setString(3, respo);
            preparedStatement.setString(4, semestre);
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
            System.out.println("Données mises à jour avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteData(int id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "DELETE FROM cour_table WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            System.out.println("Donnée supprimée avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
}

