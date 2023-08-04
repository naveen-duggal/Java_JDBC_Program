package in.ineuron.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import in.ineuron.util.JdbcUtil;

/**
 * NAVEEN_DUGGAL... 
 * */
public class InsertRecordApp {

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
				System.out.println("Records before insertion....");
				System.out.println("ID\tNAME\tADDRESS\tSALARY");
				while (resultSet.next()) {
					System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3)
							+ "\t" + resultSet.getInt(4));
				}

				resultSet.moveToInsertRow();
			
				resultSet.updateInt(1, 11);
				resultSet.updateString(2, "nitin");
				resultSet.updateString(3,"Gurugram");
				resultSet.updateInt(4,7500);
				resultSet.insertRow();

				System.out.println();
				resultSet.beforeFirst();// taking the cursor to BFR
				System.out.println("Records after insertion....");
				System.out.println("ID\tNAME\tADDRESS\tSALARY");
				while (resultSet.next()) {
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