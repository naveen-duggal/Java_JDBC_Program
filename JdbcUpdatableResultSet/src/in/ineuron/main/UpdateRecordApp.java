package in.ineuron.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import in.ineuron.util.JdbcUtil;

/**
 * @author nitin
 * @version JDK1.8 Company : www.ineuron.ai
 */
public class UpdateRecordApp {

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
					System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2)
							+ "\t" + resultSet.getString(3) + "\t" + resultSet.getInt(4));
				}

				//performing update operation
				resultSet.beforeFirst();
				while (resultSet.next()) {
					int originalSalary = resultSet.getInt(4);
					if (originalSalary < 5000) {
						int incrSalary = originalSalary + 1000;
						resultSet.updateInt(4, incrSalary);
						resultSet.updateRow();

					}
				}
				
				System.out.println();
				resultSet.beforeFirst();// taking the cursor to BFR
				System.out.println("Records after updation....");
				System.out.println("ID\tNAME\tADDRESS\tSALARY");
				while (resultSet.next()) {
					System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2)
							+ "\t" + resultSet.getString(3) + "\t" + resultSet.getInt(4));
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