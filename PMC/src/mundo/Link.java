package mundo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Link 
{

private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306/crowdcontrol";
	
	private static final String USER = "pmc";
	
	private static final String PASS = "crowdcontrol";
	
	private static Connection conexion;

	private static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}
	public Link()
	{
		try 
		{
			conexion = DriverManager.getConnection(DB_URL, USER, PASS);
		}
		catch(Exception e){e.printStackTrace();}
	}

	
	public void cerrarConexion(Connection con)
	{
		try
		{
			con.close();
			con= null;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	

	public void getAll() throws Exception
	{
		PreparedStatement prep = null;
		String q ="SELECT * FROM temperatura";
		prep = conexion.prepareStatement(q);
		prep.executeQuery();
		
	}
}
