package in.ineuron.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//JDBC4.X autoloading feature is enabled.
public class SelectApp {

	public static void main(String[] args) throws  SQLException {
		
		//step1. Load and register the driver
		
		// Step2. Establish the Connection
		String url = "jdbc:mysql:///employee";
		String user = "root";
		String password = "Naveen@132";
		Connection connection = DriverManager.getConnection(url, user, password);
		System.out.println("CONNECTION object created...");

		// Step3. Create statement Object and send the Query
		Statement statement = connection.createStatement();
		System.out.println("STATEMENT object created...");

		// Step4. Execute the Query and Process the resultSet
		String sqlSelectQuery = "select id,name,address,salary from employee";
		ResultSet resultSet = statement.executeQuery(sqlSelectQuery);
		System.out.println("RESULTSET object created...");
		System.out.println("SID\tSNAME\tADDRESS\tSALARY");
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String address = resultSet.getString("address");
			int salary = resultSet.getInt("salary");
			System.out.println(id + "\t" + name + "\t" + address + "\t" + salary);
		}

		// Step6. Close the resources
		resultSet.close();
		statement.close();
		connection.close();
		System.out.println("Closing the resources...");

	}
}