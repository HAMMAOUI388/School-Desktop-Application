package filiere;


import java.sql.*;
public class CRUDOperation {
    private static final String URL = "jdbc:mysql://localhost:3306/estbm";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    public static void insertdatafiliere(String nom, int  nmbr) {
		   try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			   String query = "INSERT INTO filiere_table (nom, nmbr) VALUES ( ?, ?)";
	            PreparedStatement preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setString(1,nom);
	            preparedStatement.setInt(2, nmbr);
	            preparedStatement.executeUpdate();
	            System.out.println("Données insérées avec succès.");
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }		
	}
  
    public static void updateData(String nom, int nmbr, int id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "UPDATE filiere_table SET nom=?, nmbr=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nom);
            preparedStatement.setInt(2, nmbr);
            preparedStatement.setInt(3, id);

            preparedStatement.executeUpdate();
            System.out.println("Données mises à jour avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteData(int id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "DELETE FROM filiere_table WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            System.out.println("Donnée supprimée avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

