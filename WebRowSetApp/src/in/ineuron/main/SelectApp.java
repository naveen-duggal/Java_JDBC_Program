package in.ineuron.main;

import java.io.FileWriter;

import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.WebRowSet;

public class SelectApp {

	public static void main(String[] args) throws Exception {

		RowSetFactory rsf = RowSetProvider.newFactory();
		WebRowSet wrs = rsf.createWebRowSet();// same as resultSet(connected),but it is scrollable and updatable.

		// setting url,username,password and getting connection object..
		wrs.setUrl("jdbc:mysql:///employee");
		wrs.setUsername("root");
		wrs.setPassword("Naveen@132");

		// setting a command for execution
		wrs.setCommand("select id,name,address,salary from employee");
		wrs.execute();

		FileWriter fw = new FileWriter("std.xml");
		wrs.writeXml(fw);
		System.out.println("Employee Data transferred to std.xml file...");
		fw.close();

	}
}
