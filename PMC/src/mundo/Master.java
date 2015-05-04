package mundo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Master {

	
private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	
	private static final String DB_URL = "jdbc:mysql://169.254.96.47/crowdcontrol";
	
	private static final String USER = "pmc";
	
	private static final String PASS = "crowdcontrol";
	
	private static Connection conexion;


	
	public static void establecerConexion(String url , String user , String pass)
	{
		try {
		    Class.forName(JDBC_DRIVER);
		} 
		catch (ClassNotFoundException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} 
		try 
		{
			conexion = DriverManager.getConnection(url, user, pass);
			System.out.println("Conectado");
		}
			catch(Exception e)
		
			{
				e.printStackTrace();
			}
	}
	
	public static void cerrarConexion(Connection con)
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

	public ArrayList<Quark> getAll() throws Exception
	{
		ArrayList<Quark> datos = new ArrayList<Quark>();
		establecerConexion(DB_URL, USER, PASS);
		PreparedStatement prep = null;
		String q ="SELECT * FROM TEMPERATURA";
		prep = conexion.prepareStatement(q);
		ResultSet rs = prep.executeQuery();
		Quark quark = new  Quark(null, 0, 0, 0);
		while(rs.next())
		{
			int temp = rs.getInt("valor");
			Timestamp t = rs.getTimestamp("tiempoRegistro");
			quark.setFecha(t);
			quark.setTemperatura(temp);
			datos.add(quark);
		}
		String q1 ="SELECT * FROM SONIDO";
		prep = conexion.prepareStatement(q1);
		ResultSet rs1 = prep.executeQuery();
		int i=0;
		while(rs1.next()&&i<datos.size())
		{
			int temp = rs1.getInt("valor");
			Quark quark1 = datos.get(i);
			quark1.setSonido(temp);
			i++;
		}
		String q2 ="SELECT * FROM MOVIMIENTO";
		prep = conexion.prepareStatement(q2);
		ResultSet rs2 = prep.executeQuery();
		int j=0;
		while(rs2.next()&&j<datos.size())
		{
			int temp = rs2.getInt("valor");
			Quark quark2 = datos.get(j);
		
			quark2.setMovimiento(temp);
			j++;
		}
		cerrarConexion(conexion);
		return datos;
	}
	
	public Hadron darHistorial(Date fechaInicial, Date fechaFinal)  throws Exception
	{
		//Se debe conectar a la base de datos, 
		//pedir los datos que esten entre las fechas indicadas
		//y retornar un Hadron con la informacion solicitada
		Hadron v= new Hadron();
		ArrayList<Quark> datos = new ArrayList<Quark>();
		establecerConexion(DB_URL, USER, PASS);
		PreparedStatement prep = null;
		String q ="SELECT * FROM TEMPERATURA WHERE tiempoRegistro BETWEEN "+fechaInicial +" AND "+fechaFinal;
		prep = conexion.prepareStatement(q);
		ResultSet rs = prep.executeQuery();
		Quark quark = new  Quark(null, 0, 0, 0);
		while(rs.next())
		{
			int temp = rs.getInt("valor");
			Timestamp t = rs.getTimestamp("tiempoRegistro");
						
			quark.setFecha(t);
			quark.setTemperatura(temp);
			datos.add(quark);
		}
		String q1 ="SELECT * FROM SONIDO WHERE tiempoRegistro BETWEEN "+fechaInicial +" AND "+fechaFinal;
		prep = conexion.prepareStatement(q1);
		ResultSet rs1 = prep.executeQuery();
		int i=0;
		while(rs1.next()&&i<datos.size())
		{
			int temp = rs1.getInt("valor");
			Quark quark1 = datos.get(i);
			quark1.setSonido(temp);
			i++;
		}
		String q2 ="SELECT * FROM MOVIMIENTO WHERE tiempoRegistro BETWEEN "+fechaInicial +" AND "+fechaFinal;
		prep = conexion.prepareStatement(q2);
		ResultSet rs2 = prep.executeQuery();
		int j=0;
		while(rs2.next()&&j<datos.size())
		{
			int temp = rs2.getInt("valor");
			Quark quark2 = datos.get(j);
		
			quark2.setMovimiento(temp);
			j++;
		}
		cerrarConexion(conexion);
		
		return v;
	
		
	}
}

