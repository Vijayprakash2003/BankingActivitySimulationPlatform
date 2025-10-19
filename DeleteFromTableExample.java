package bank;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteFromTableExample {

    public static void main(String[] args) {

    	String url = "jdbc:mysql://localhost:3306/banking_simulator";
		String user = "root";
		String password = "Vijay@2011"; 
		
        // SQL command to delete a record (delete student with id = 1)
        String deleteSQL = "DELETE FROM students WHERE id = 1";

        try {
            // Step 1: Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish connection
            Connection con = DriverManager.getConnection(url, user, password);

            // Step 3: Create a statement object
            Statement stmt = con.createStatement();

            // Step 4: Execute delete query
            int rowsDeleted = stmt.executeUpdate(deleteSQL);

            if (rowsDeleted > 0) {
                System.out.println("✅ Record deleted successfully!");
            } else {
                System.out.println("⚠️ No record found with the given condition.");
            }

            // Step 5: Close resources
            stmt.close();
            con.close();

        } catch (ClassNotFoundException e) {
            System.out.println("❌ JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Database error!");
            e.printStackTrace();
        }
    }
}