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
			while (true)
			{
				
				try {
					interfaz.showData();
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		
	}
}
