package jupi;
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
        frame.setMinimumSize(frame.getSize());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }//main

    //private World()
    //made public so it can also run as an Applet  
    public World()    
    {
    	// prevent more than one world from being constructed:
    	if (world != null)
    		throw new RuntimeException();
        int pixelsPerInch = 8;
        CaromTable table = new CaromTable();
        table.setPixelsPerInch(pixelsPerInch);
        this.add(table);
    }//World()

    public static synchronized World getInstance() 
    {
        if (world == null)
            world = new World();
        return world;
    }
    
}//World