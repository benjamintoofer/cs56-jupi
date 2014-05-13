package jupi;
import java.awt.Color;
import javax.swing.*;

/**
 * Singleton class that extends {@code JApplet}, for running as an applet,
 * and contains the {@code main} method, for running as an application.
 */

public class World extends JApplet {
    private static World world = null;
    //Hello
    public static void main(String[] args) {
        World applet = new World();
        JFrame frame = new JFrame();
        frame.add(applet);
        frame.setTitle("Jupi: Carom Billiards");
        frame.pack();
        frame.setMinimumSize(frame.getSize());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private World() {
        final CaromTable table = new CaromTable();
        table.setPixelsPerInch(BilliardsConstants.pixelsPerInch);
        this.add(table);
        
        //Game Thread
        Thread gameThread = new Thread(new Runnable(){
        	public void run()
        	{
        		try{
        			while(true)
        			{
        				table.update();
        				Thread.sleep(30);
        			}
        		}catch(InterruptedException ex ){}
        	}
        });
        gameThread.start();
    }

    public static synchronized World getInstance() {
        if (world == null)
            world = new World();
        return world;
    }
}
