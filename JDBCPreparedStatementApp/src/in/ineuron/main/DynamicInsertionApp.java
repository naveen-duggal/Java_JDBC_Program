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
public class DynamicInsertionApp {

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		Scanner scanner = null;

		try {
			connection = JdbcUtil.getJdbcConnection();
			
			String sqlInsertQuery = "insert into employee(`id`,`name`,`address`,`salary`)values(?,?,?,?)";
			if (connection != null)
				pstmt = connection.prepareStatement(sqlInsertQuery);

			if (pstmt != null) {

				// use precompiled query to set the values
				scanner = new Scanner(System.in);
				System.out.println("Enter the id of Employee");
				int id=scanner.nextInt();
				System.out.println("Enter the name of Employee");
				String name = scanner.next();
				System.out.println("Enter the address of Employee");
				String address = scanner.next();
				System.out.println("Enter the salary of Employee");
				int salary = scanner.nextInt();
				
				pstmt.setInt(1,id);
				pstmt.setString(2,name);
				pstmt.setString(3,address);
				pstmt.setInt(4, salary);

				System.out.println(sqlInsertQuery);
				
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
				System.out.println("Closing the resource...");
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}
}