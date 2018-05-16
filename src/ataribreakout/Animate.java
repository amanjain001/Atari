package ataribreakout;
public class Animate implements Runnable {
	BlockBreakerPanel bbp;
	Animate(BlockBreakerPanel b)
	{
		bbp=b;
	}
	
	public void run()
	{
		try
		{
		while(true)
		{
			bbp.update();
			try {
				Thread.sleep(10);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				e.printStackTrace();
			}
		}
	}

	catch(ArrayIndexOutOfBoundsException e)
	{
		e.printStackTrace();
	}
	}

}
