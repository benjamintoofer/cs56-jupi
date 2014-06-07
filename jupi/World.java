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
    private final JupiTable jupiTable;
    private final CaromTable caromTable;
    private MainMenu menu;
   
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
       
        menu = new MainMenu();
        //final CaromTable table = new CaromTable();
        jupiTable = new JupiTable();
        caromTable = new CaromTable();
        //table.setPixelsPerInch(pixelsPerInch);
        
        
        this.add(menu);
        
        
        //Game Thread
        Thread gameThread = new Thread(new Runnable()
        {
        	public void run()
        	{System.out.println("here");
        		try
        		{
        			while(true)
        			{
        				if(menu.getGame() == "Carom")
        		        {
        					if(menu.isVisible())
        					{
        						menu.setVisible(false);
        						world.add(caromTable);
        					}
        					
        					  caromTable.update();
        					  
        		        	
        		        }else if(menu.getGame() == "Jupi"){
        		        	
        		        	if(menu.isVisible())
        					{
        						menu.setVisible(false);
        						world.add(jupiTable);
        					}
        		        	  jupiTable.update(); 
        		        }
        			  
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
