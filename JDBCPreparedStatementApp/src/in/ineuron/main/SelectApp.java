package in.ineuron.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import in.ineuron.util.JdbcUtil;

/**
 * @Naveen_Duggal...
 */
public class SelectApp {

	public static void main(String[] args) {
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		Scanner scanner = null;
		ResultSet resultSet = null;
		int id = 0;

		try {
			connection = JdbcUtil.getJdbcConnection();

			String sqlSelectQuery = "select id,name,address,salary from employee where id =?";
			if (connection != null)
				pstmt = connection.prepareStatement(sqlSelectQuery);

			if (pstmt != null) {

				scanner = new Scanner(System.in);

				System.out.print("Enter the id of the employee :: ");
				id = scanner.nextInt();

				// use precompiled query to set the values
				pstmt.setInt(1, id);

				// execute the query
				resultSet = pstmt.executeQuery();
			}
			if (resultSet != null) {

				if (resultSet.next()) {
					System.out.println("ID\tNAME\tADDRESS\tSalary");
					System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3)
							+ "\t" + resultSet.getInt(4));
				} else {
					System.out.println("Record not available for the give id :: " + id);
				}
			}

		} catch (IOException ie) {
			ie.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.cleanUp(connection, pstmt, resultSet);
				System.out.println("Closing the resource...");
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}
}