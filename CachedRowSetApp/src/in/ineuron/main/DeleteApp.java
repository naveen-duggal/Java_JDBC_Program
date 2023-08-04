package in.ineuron.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class DeleteApp {

	public static void main(String[] args) throws Exception {

		//Connection connection = DriverManager.getConnection("jdbc:mysql:///employee","root","Naveen@132");

		//Statement stmt = connection.createStatement();
		//ResultSet resultSet = stmt.executeQuery("select id,name,address,salary from employee");

		RowSetFactory rsf = RowSetProvider.newFactory();
		CachedRowSet crs = rsf.createCachedRowSet();// same as resultSet(connected),but it is scrollable and updatable.
		crs.setUrl("jdbc:mysql:///employee?relaxAutoCommit=false");
		crs.setUsername("root");
		crs.setPassword("Naveen@132");

		crs.setCommand("Select id,name,address,salary from employee");
		crs.execute();
		//crs.populate(resultSet);

		while (crs.next()) {
			int actualSalary = crs.getInt(4);
			if (actualSalary < 5000) {
				crs.deleteRow();
			}
		}
		crs.moveToCurrentRow();
		crs.acceptChanges();
		//crs.commit();

		System.out.println("Records deleted succesfully....");
		crs.close();

	}
}
