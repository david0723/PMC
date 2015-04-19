package mundo;

import java.util.Date;

public class Quark 
{
	public static final long TEMPERATURA_LOW = 0;
	public static final long TEMPERATURA_MID = 0;
	public static final long TEMPERATURA_HIGH = 0;
	
	public static final long MOVIMIENTO_LOW = 0;
	public static final long MOVIMIENTO_MID = 0;
	public static final long MOVIMIENTO_HIGH = 0;
	
	public static final long SONIDO_LOW = 0;
	public static final long SONIDO_MID = 0;
	public static final long SONIDO_HIGH = 0;
	
	private Date fecha;
	
	private long temperatura;
	
	private long movimiento;
	
	private long sonido;
	
	public Quark(Date xFecha, long xTemperatura, long xMovimiento, long xSonido)
	{
		fecha = xFecha;
		temperatura = xTemperatura;
		movimiento = xMovimiento;
		sonido = xSonido;
	}
	public boolean temperaturaAlta()
	{
		return temperatura > TEMPERATURA_HIGH;
	}
	
	public boolean temperaturaBaja()
	{
		return temperatura < TEMPERATURA_LOW;
	}
	
	public boolean temperaturaMedia()
	{
		return !temperaturaAlta() && !temperaturaBaja();
	}

	public Date getFecha() 
	{
		return fecha;
	}

	public void setFecha(Date fecha) 
	{
		this.fecha = fecha;
	}

	public long getTemperatura() 
	{
		return temperatura;
	}

	public void setTemperatura(long temperatura) 
	{
		this.temperatura = temperatura;
	}

	public long getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(long movimiento) 
	{
		this.movimiento = movimiento;
	}

	public long getSonido()
	{
		return sonido;
	}

	public void setSonido(long sonido) 
	{
		this.sonido = sonido;
	}
	

}
