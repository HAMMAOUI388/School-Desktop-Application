package enseignant;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabaseAndTabl {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String DB_NAME = "estbm";

    public static void createTableEnseignants() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            statement.executeUpdate("USE " + DB_NAME);

            String createTableQuery = "CREATE TABLE IF NOT EXISTS enseignants_table ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "nom VARCHAR(50) NOT NULL,"
                    + "prenom VARCHAR(50) NOT NULL,"
                    + "adresse VARCHAR(100) NOT NULL,"
                    + "age INT NOT NULL,"
                    + "datenais VARCHAR(20) NOT NULL,"
                    + "sexe VARCHAR(20) NOT NULL,"
                    + "status VARCHAR(50) NOT NULL,"
                    + "role VARCHAR(50) NOT NULL,"
                    + "domaine VARCHAR(50) NOT NULL"
                    + ")";
            statement.executeUpdate(createTableQuery);
            
            System.out.println("Base de données et table créées avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        createTableEnseignants();
    }
}
