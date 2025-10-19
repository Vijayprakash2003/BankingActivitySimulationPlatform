package bank;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateStudentExample {

    public static void main(String[] args) {


    	String url = "jdbc:mysql://localhost:3306/banking_simulator";
		String user = "root";
		String password = "Vijay@2011"; 
		
        // SQL query to update student details
        String updateQuery = "UPDATE students SET name='Rahul Kumar', course='Data Science' WHERE id=1";

        try {
            // Step 1: Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish Connection
            Connection con = DriverManager.getConnection(url, user, password);

            // Step 3: Create a Statement object
            Statement stmt = con.createStatement();

            // Step 4: Execute Update Query
            int rowsAffected = stmt.executeUpdate(updateQuery);

            // Step 5: Confirm Update
            if (rowsAffected > 0) {
                System.out.println("✅ Student record updated successfully!");
            } else {
                System.out.println("⚠️ No record found with that ID.");
            }

            // Step 6: Close connection
            stmt.close();
            con.close();

        } catch (ClassNotFoundException e) {
            System.out.println("❌ JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Database error occurred!");
            e.printStackTrace();
        }
    }
}