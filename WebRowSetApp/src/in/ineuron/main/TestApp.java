package in.ineuron.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class TestApp {
	public static void main(String[] args) throws Exception {

		Connection connection = DriverManager.getConnection("jdbc:mysql:///employee", "root", "Naveen@132");
		Statement stmt = connection.createStatement();
		ResultSet resultSet = stmt.executeQuery("select id,name,address,salary from employee");
		
		RowSetFactory rsf = RowSetProvider.newFactory();
		CachedRowSet crs = rsf.createCachedRowSet();//disconnected rowset
		crs.populate(resultSet);
		
		connection.close();//Operation not allowed after ResultSet closed
		System.out.println("ID\tNAME\tADDRESS\tSALARY");
		while (crs.next()) {
			System.out.println(crs.getInt(1) + "\t" + crs.getString(2) + "\t" + crs.getString(3) + "\t"
					+ "\t" + crs.getInt(4));
		}

	}
}
