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
	
	private int temperatura;
	
	private int movimiento;
	
	private int sonido;
	
	public Quark(Date xFecha, int xTemperatura, int xMovimiento, int xSonido)
	{
		fecha = xFecha;
		temperatura = xTemperatura;
		movimiento = xMovimiento;
		sonido = xSonido;
	}
	public Quark()
	{
		fecha = new Date();
		temperatura = (int) (Math.random()*100);
		movimiento =  (int) (Math.random()*100);
		sonido =  (int) (Math.random()*100);
	}
	public void random(Quark xquark)
	{

		temperatura = (int) (xquark.getTemperatura() +(Math.random()*5) - (Math.random()*5));
		movimiento = (int) (xquark.getMovimiento() + (Math.random()*5) - (Math.random()*5));
		sonido = (int) (xquark.getSonido() + (Math.random()*5) -  (Math.random()*5));
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
	
	public boolean movimientoAlto()
	{
		return movimiento > MOVIMIENTO_HIGH;
	}
	
	public boolean movimientoBajo()
	{
		return movimiento < MOVIMIENTO_LOW;
	}
	
	public boolean movimientoMedio()
	{
		return !movimientoAlto() && !movimientoBajo();
	}
	
	public boolean sonidoAlto()
	{
		return sonido > SONIDO_HIGH;
	}
	
	public boolean sonidoBajo()
	{
		return sonido < SONIDO_LOW;
	}
	
	public boolean sonidoMedio()
	{
		return !sonidoAlto() && !sonidoBajo();
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

	public void setTemperatura(int temperatura) 
	{
		this.temperatura = temperatura;
	}

	public long getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(int movimiento) 
	{
		this.movimiento = movimiento;
	}

	public long getSonido()
	{
		return sonido;
	}

	public void setSonido(int sonido) 
	{
		this.sonido = sonido;
	}
	public String toString()
	{
		String fechas = fecha.toString();
		return fechas + "\n"+temperatura+"         "+sonido+"            "+movimiento;
		
	}

}
