package javacaleb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeDB {

    public static void main(String[] args) {
        try {
            Connection con = DbCon.getCon();

            // Insert example
            String insertSQL = "INSERT INTO Employee (FirstName, LastName, Email, Password) VALUES (?, ?, ?, ?)";
            PreparedStatement psInsert = con.prepareStatement(insertSQL);
            psInsert.setString(1, "Caleb");
            psInsert.setString(2, "Baron");
            psInsert.setString(3, "Caleb@example.com");
            psInsert.setString(4, "1234");
            int rowsInserted = psInsert.executeUpdate();
            System.out.println(rowsInserted > 0 ? "Insert successful" : "Insert failed");

            // Select example
            String selectSQL = "SELECT * FROM Employee";
            PreparedStatement psSelect = con.prepareStatement(selectSQL);
            ResultSet rs = psSelect.executeQuery();
            System.out.println("Employees:");
            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + ": " +
                    rs.getString("FirstName") + " " +
                    rs.getString("LastName") + ", Email: " +
                    rs.getString("Email")
                );
            }

            // Update example
            String updateSQL = "UPDATE Employee SET Password = ? WHERE Email = ?";
            PreparedStatement psUpdate = con.prepareStatement(updateSQL);
            psUpdate.setString(1, "1234");
            psUpdate.setString(2, "Caleb@example.com");
            int rowsUpdated = psUpdate.executeUpdate();
            System.out.println(rowsUpdated > 0 ? "Update successful" : "Update failed");

            // Delete example
            String deleteSQL = "DELETE FROM Employee WHERE Email = ?";
            PreparedStatement psDelete = con.prepareStatement(deleteSQL);
            psDelete.setString(1, "Caleb@example.com");
            int rowsDeleted = psDelete.executeUpdate();
            System.out.println(rowsDeleted > 0 ? "Delete successful" : "Delete failed");

            // Close resources
            rs.close();
            psInsert.close();
            psSelect.close();
            psUpdate.close();
            psDelete.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
