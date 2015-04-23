package mundo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Master {

	
private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306/ccpmc";
	
	private static final String USER = "crowdcontrol";
	
	private static final String PASS = "pmccc";
	
	private static Connection conexion;

	private static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}
	
	public void establecerConexion(String url , String user , String pass)
	{
		try 
		{
			conexion = DriverManager.getConnection(url, user, pass);
		}
			catch(Exception e)
		
			{
				e.printStackTrace();
			}
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
		establecerConexion(DB_URL, USER, PASS);
		PreparedStatement prep = null;
		String q ="SELECT * FROM temperatura";
		prep = conexion.prepareStatement(q);
		prep.executeQuery();
		
	}
}
