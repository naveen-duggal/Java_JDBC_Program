package in.ineuron.main;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class JdbcRowSetApp {

	public static void main(String[] args) throws Exception {

		RowSetFactory rsf = RowSetProvider.newFactory();
		JdbcRowSet jrs = rsf.createJdbcRowSet();// same as resultSet(connected),but it is scrollable and updatable.

		// setting url,username,password
		jrs.setUrl("jdbc:mysql:///employee");
		jrs.setUsername("root");
		jrs.setPassword("Naveen@132");

		// setting a command for execution
		jrs.setCommand("select id,name,address,salary from employee");
		jrs.execute();

		System.out.println("Retreiveing the records in forward direction...");
		System.out.println("ID\tNAME\tADDRESS\tSALARY");
		while (jrs.next()) {
			System.out
					.println(jrs.getInt(1) + "\t" + jrs.getString(2) + "\t" + jrs.getString(3) + "\t" + jrs.getInt(4));
		}
		System.out.println();

		System.out.println("Retreiveing the records in backward direction...");
		System.out.println("ID\tNAME\tADDRESS\tSALARY");
		while (jrs.previous()) {
			System.out
					.println(jrs.getInt(1) + "\t" + jrs.getString(2) + "\t" + jrs.getString(3) + "\t" + jrs.getInt(4));
		}

		System.out.println();

		// accessing the record randomly
		jrs.absolute(4);
		System.out.println(jrs.getInt(1) + "\t" + jrs.getString(2) + "\t" + jrs.getString(3) + "\t" + jrs.getInt(4));

		jrs.relative(-2);
		System.out.println(jrs.getInt(1) + "\t" + jrs.getString(2) + "\t" + jrs.getString(3) + "\t" + jrs.getInt(4));

	}
}
