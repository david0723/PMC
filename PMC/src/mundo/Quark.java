package mundo;

import java.util.Date;

public class Quark 
{
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
