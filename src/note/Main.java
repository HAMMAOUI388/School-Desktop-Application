package note;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
                Connection connection = createDatabaseConnection();

        CreateDatabaseAndTables.createDatabaseAndTable(connection);

        CrudOperations crudOperations = new CrudOperations(connection);

        new UserInterface(crudOperations);
    }

    private static Connection createDatabaseConnection() {
                String url = "jdbc:mysql://localhost:3306/";
        String dbName = "ESTBM_Exam_Management";
        String driver = "com.mysql.cj.jdbc.Driver";
        String userName = "root";
        String password = "";

        try {
            Class.forName(driver);
            return DriverManager.getConnection(url + dbName, userName, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
