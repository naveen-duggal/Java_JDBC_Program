package in.ineuron.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import in.ineuron.util.JdbcUtil;

/**
 *@Naveen_Duggal... 
 **/
public class SensitiveNatureApp {

	public static void main(String[] args) {

		// Resources used
		Connection connection = null;
		Statement stmt = null;
		ResultSet resultSet = null;

		try {
			connection = JdbcUtil.getJdbcConnection();

			if (connection != null)
				// Expecting ResultSet to be SCROLLABLE AND UPDATABLE
				stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			String sqlQuery = "select Id,Name,Address,salary from employee";

			if (stmt != null)
				resultSet = stmt.executeQuery(sqlQuery);

			if (resultSet != null) {
				System.out.println("Records before updation....");
				System.out.println("ID\tNAME\tADDRESS\tSALARY");
				while (resultSet.next()) {
					System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3)
							+ "\t" + resultSet.getInt(4));
				}
				System.out.println();

				System.out.println("Application is in pausing state, please update database...");
				System.in.read();
				System.out.println("Records after updation...");

				resultSet.beforeFirst();// takes the cursor to BFR

				System.out.println("ID\tNAME\tADDRESS\tSALARY");
				while (resultSet.next()) {
					resultSet.refreshRow();//To get the updated value
					System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3)
							+ "\t" + resultSet.getInt(4));
				}

			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				JdbcUtil.cleanUp(connection, stmt, resultSet);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}