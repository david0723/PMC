package mundo;

import Graficas.Interfaz;
import Graficas.InterfazPMC;

public class Looper implements Runnable
{

	private InterfazPMC interfaz;
	public Looper(InterfazPMC interfaz)
	{
		this.interfaz = interfaz;
	}
	
	@Override
	public void run() 
	{
		try 
		{
			while (true)
			{
				interfaz.showData();
				Thread.sleep(500);
			}
			
		} 
		catch (InterruptedException e) {e.printStackTrace();}
	}
}
