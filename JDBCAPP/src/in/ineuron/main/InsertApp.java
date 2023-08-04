package in.ineuron.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//JDBC4.X autoloading feature is enabled.
public class InsertApp {

	public static void main(String[] args) throws SQLException {

		// Step2. Establish the Connection
		String url = "jdbc:mysql:///employee";
		String user = "root";
		String password = "Naveen@132";
		Connection connection = DriverManager.getConnection(url, user, password);
		System.out.println("connection object created...");

		// Step3. Create statement Object and send the Query
		Statement statement = connection.createStatement();
		System.out.println("statement object created...");

		// Step4. Execute the Query and Process the resultSet
		String sqlInsertQuery = "insert into employee(`id`,`name`,`address`,`salary`)values(10,'Rohan','Gurugram',24000)";
		int rowAffected = statement.executeUpdate(sqlInsertQuery);
		System.out.println("No of rows affected is :: " + rowAffected);

		// Step6. Close the resources
		statement.close();
		connection.close();
		System.out.println("closing the resources...");

	}
}