package bank;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLTest {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/banking_simulator";
		String user = "root";
		String password = "Vijay@2011";
		
		try(Connection conn = DriverManager.getConnection(url, user, password)){
			System.out.println("Connection to Mysql!");
		} catch(SQLException e)	{
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub  

	}

}
