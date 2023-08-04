package in.ineuron.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import in.ineuron.util.JdbcUtil;
//JDBC4.X autoloading feature is enabled.
public class SelectApp {

	public static void main(String[] args) throws SQLException {

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try
		{
			connection = JdbcUtil.getJdbcConnection();
			
			if(connection!=null)
				statement=connection.createStatement();
			
			if(statement!=null)
			{
				resultSet=statement.executeQuery("select * from employee");
			}
			if(resultSet!=null)
			{
				System.out.println("ID\tNAME\tADDRESS\tSALARY");
			while(resultSet.next())
			{
				int id=resultSet.getInt(1);
				String name = resultSet.getString(2);
				String address=resultSet.getString(3);
				int salary = resultSet.getInt(4);
				
				System.out.println(id+"\t"+name+"\t"+address+"\t"+salary);
			}
			}
		}catch(SQLException ce)
		{
			ce.printStackTrace();
		}
		catch(Exception se)
		{
			se.printStackTrace();
		}
		finally
		{
			try
			{
				JdbcUtil.cleanUp(connection,statement,resultSet);
				System.out.println("close ethe resourse...");
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		
	}
}