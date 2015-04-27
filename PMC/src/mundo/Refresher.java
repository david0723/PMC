package mundo;

import Graficas.Interfaz;

public class Refresher implements Runnable
{

	private Interfaz interfaz;
	public Refresher(Interfaz interfaz)
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
				Thread.sleep(250);
			}
			
		} 
		catch (InterruptedException e) {e.printStackTrace();}
	}
}
