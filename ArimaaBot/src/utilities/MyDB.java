package utilities;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class MyDB {
	
	private static final String MYSQL_USERNAME = "arimaa";
	private static final String MYSQL_PASSWORD = "arimaa";
	private static final String MYSQL_DATABASE_SERVER = "arimaa-navv.csqhtrngores.us-west-1.rds.amazonaws.com";
	private static final String MYSQL_DATABASE_NAME = "Arimaa";
	
	private static Connection con;
	private static Statement stmt;
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://" + MYSQL_DATABASE_SERVER + "/" + MYSQL_DATABASE_NAME;
			con = DriverManager.getConnection(url, MYSQL_USERNAME, MYSQL_PASSWORD);
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Update the MySQL constants to correct values!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Add the MySQL jar file to your build path!");
		}
	}
	
	public static void close() {
		try {
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void executeUpdate(String query)
	{
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ResultSet executeQuery(String query)
	{
		try {
			return stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<String> getAllTableNames() 
	{
		ArrayList<String> tables = new ArrayList<String>();
		try {
			DatabaseMetaData md = con.getMetaData();
		    ResultSet rs = md.getTables(null, null, "%", null);
		    while (rs.next()) {
		    	tables.add(rs.getString(3));
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tables;
	}

}
