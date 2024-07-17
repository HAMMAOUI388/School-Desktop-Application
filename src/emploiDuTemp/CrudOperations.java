package emploiDuTemp;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



class CrudOperations {

    private Connection connection;

    public CrudOperations(Connection connection) {
        this.connection = connection;
    }

    public void createOrUpdateSchedule(String day, String time, String classroom, String course) {
        try {
            // Check if the schedule for the given day and time already exists
            String checkSql = "SELECT * FROM Schedule WHERE Day = ? AND Time = ?";
            try (PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {
                checkStatement.setString(1, day);
                checkStatement.setString(2, time);
                if (checkStatement.executeQuery().next()) {
                    // Update the existing schedule
                    String updateSql = "UPDATE Schedule SET Classroom = ?, Course = ? WHERE Day = ? AND Time = ?";
                    try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
                        updateStatement.setString(1, classroom);
                        updateStatement.setString(2, course);
                        updateStatement.setString(3, day);
                        updateStatement.setString(4, time);
                        updateStatement.executeUpdate();
                    }
                } else {
                    // Create a new schedule
                    String insertSql = "INSERT INTO Schedule (Day, Time, Classroom, Course) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
                        insertStatement.setString(1, day);
                        insertStatement.setString(2, time);
                        insertStatement.setString(3, classroom);
                        insertStatement.setString(4, course);
                        insertStatement.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSchedule(String day, String time) {
        try {
            // Delete the schedule for the specified day and time
            String deleteSql = "DELETE FROM Schedule WHERE Day = ? AND Time = ?";
            try (PreparedStatement deleteStatement = connection.prepareStatement(deleteSql)) {
                deleteStatement.setString(1, day);
                deleteStatement.setString(2, time);
                deleteStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String fetchSchedule(String filiere, String semester) {
        try {
            // Assuming you have a method to fetch the schedule based on filiere and semester
            // This is a placeholder, replace it with the actual logic to fetch the schedule
            String selectSql = "SELECT * FROM Schedule WHERE /* Your condition based on filiere and semester */";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectSql)) {
                // Process the result set and return the schedule details
                // For example, you can format the details as a string and return it
                StringBuilder scheduleDetails = new StringBuilder();
                while (resultSet.next()) {
                    scheduleDetails.append("Day: ").append(resultSet.getString("Day")).append(", ");
                    scheduleDetails.append("Time: ").append(resultSet.getString("Time")).append(", ");
                    scheduleDetails.append("Classroom: ").append(resultSet.getString("Classroom")).append(", ");
                    scheduleDetails.append("Course: ").append(resultSet.getString("Course")).append("\n");
                }
                return scheduleDetails.toString();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error fetching schedule";
        }
    }

    public String fetchEmploiDuTemps(String filiere, String semester) {
        return null;
    }
}