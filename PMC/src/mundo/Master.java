package mundo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

public class Master {

	
private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	
	private static final String DB_URL = "jdbc:mysql://http://192.168.0.4/crowdcontrol";
	
	private static final String USER = "pmc";
	
	private static final String PASS = "crowdcontrol";
	
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
		ResultSet rs = prep.executeQuery();
		Quark quark = new  Quark(null, 0, 0, 0);
		while(rs.next())
		{
			long temp = rs.getLong("TEMPERATURA");
			Timestamp t = rs.getTimestamp("TIMESTAMP");
			int tiempo = t.getDate();
			quark.setFecha(new Date(tiempo));
			quark.setTemperatura(temp);
		}
		
	}
}


////Si detecta mucho ruido, se prende el led rojo
//if( sensor > 345 || sensor < 338 )
//{
//  digitalWrite(ledRojo, HIGH);
//  digitalWrite(ledVerde, LOW);
//}
//
////Si no detecta ruido, se prende el led verde
//else
//{
//  digitalWrite(ledRojo, LOW);
//  digitalWrite(ledVerde, HIGH);
//}









//if( move > 50 )
//{
//  contadorPersonas ++;
//  digitalWrite(yellow, HIGH);
//  
//}
//else
//{
//  digitalWrite(yellow, LOW);
//}
//if (mod == 1)
//{
//  digitalWrite(red, LOW);
//  digitalWrite(green, HIGH);
//}
//contadorSegs ++;
//
//Serial.println("Paso:");
//Serial.println(contadorPersonas/2, DEC);
//Serial.println("Segs:");
//Serial.println(contadorSegs/10, DEC);
//
//if( contadorSegs/(10) == 8 )
//{
//  mod = 2; 
//  contadorSegs = 0;
//  if( contadorPersonas/2 >= 5 )
//  {
//    digitalWrite(red, HIGH);
//    digitalWrite(green, LOW);
//  }
//  else
//  {
//    digitalWrite(red, LOW);
//    digitalWrite(green, HIGH);
//  }
//  contadorPersonas = 0;
//}