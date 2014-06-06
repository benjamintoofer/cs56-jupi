package jupi;
import java.awt.Color;
import javax.swing.*;

/**
 * Singleton class that extends {@code JApplet}, for running as an applet,
 * and contains the {@code main} method, for running as an application.
 */

public class World extends JApplet 
{
    private static World world = null;
   
    public static void main(String[] args) 
    {        
    	World applet = World.getInstance();
        JFrame frame = new JFrame();
        frame.add(applet);
        frame.setTitle("Jupi: Carom Billiards");
        frame.pack();
        //frame.setMinimumSize(frame.getSize());
        frame.setSize((int)(BilliardsConstants.TOTAL_FLOOR_DIMENSION[0] * BilliardsConstants.PIXELS_PER_INCH), 
        		      (int)(BilliardsConstants.TOTAL_FLOOR_DIMENSION[1] * BilliardsConstants.PIXELS_PER_INCH)); //testing----------
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
        //frame.setResizable(false);//testing---------------
        
        frame.setVisible(true);
    }//main


   // private World() {
     //   final CaromTable table = new CaromTable();
       // table.setPixelsPerInch(BilliardsConstants.pixelsPerInch);

    //private World()
    //made public so it can also run as an Applet  
    public World()    
    {
    	// prevent more than one world from being constructed:
    	if (world != null)
    		throw new RuntimeException();
        int pixelsPerInch = BilliardsConstants.PIXELS_PER_INCH;
        //final CaromTable table = new CaromTable();
        final JupiTable table = new JupiTable();
        table.setPixelsPerInch(pixelsPerInch);
        this.add(table);
        
        //Game Thread
        Thread gameThread = new Thread(new Runnable()
        {
        	public void run()
        	{
        		try
        		{
        			while(true)
        			{
        				table.update();        				
        				Thread.sleep(BilliardsConstants.TIME_SLICE);
        			}
        		}
        		catch(InterruptedException ex )
        		{        			
        		}
        	}
        });
        gameThread.start();

    }//World()


    public static synchronized World getInstance() 
    {
        if (world == null)
            world = new World();
        return world;
    }
    
}//World
