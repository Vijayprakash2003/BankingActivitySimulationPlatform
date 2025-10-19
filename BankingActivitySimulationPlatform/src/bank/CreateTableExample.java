package bank;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableExample {

    public static void main(String[] args) {

    	String url = "jdbc:mysql://localhost:3306/banking_simulator";
		String user = "root";
		String password = "Vijay@2011";            // your MySQL password

        // SQL command to create a table
        String createTableSQL = "CREATE TABLE students_dummy (" +
                                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                                "name VARCHAR(50) NOT NULL, " +
                                "age INT NOT NULL, " +
                                "course VARCHAR(50))";

        try {
            // Step 1: Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish connection
            Connection con = DriverManager.getConnection(url, user, password);

            // Step 3: Create a statement object
            Statement stmt = con.createStatement();

            // Step 4: Execute the SQL statement
            stmt.executeUpdate(createTableSQL);
            System.out.println("✅ Table 'students' created successfully!");

            // Step 5: Close connection
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