package in.ineuron.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import in.ineuron.util.JdbcUtil;

/**
 * @Naveen_Duggal...
 */
public class UpdateApp {

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		Scanner scanner = null;

		try {
			connection = JdbcUtil.getJdbcConnection();
			
			String sqlUpdateQuery = "update employee set salary = ? where id = ?";
			if (connection != null)
				pstmt = connection.prepareStatement(sqlUpdateQuery);

			if (pstmt != null) {
				scanner = new Scanner(System.in);

				System.out.print("Enter the id of the employee :: ");
				int sid = scanner.nextInt();

				System.out.print("Enter the salary of the employee :: ");
				int salary = scanner.nextInt();

				// use precompiled query to set the values
				pstmt.setInt(1, salary);
				pstmt.setInt(2, sid);

				// execute the query
				int rowCount = pstmt.executeUpdate();
				System.out.println("No of rows updated is :: " + rowCount);
			}
		} catch (IOException ie) {
			ie.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.cleanUp(connection, pstmt, null);
				scanner.close();
				System.out.println("Closing the resource...");
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}
}