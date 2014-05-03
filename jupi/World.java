package jupi;
import javax.swing.*;

/**
 * Singleton class that extends {@code JApplet}, for running as an applet,
 * and contains the {@code main} method, for running as an application.
 */

public class World extends JApplet {
    private static World world = null;
    private int pixelsPerInch;

    public static void main(String[] args) {
        World applet = new World();
        JFrame frame = new JFrame();
        frame.add(applet);
        frame.setTitle("Jupi: Carom Billiards");
        frame.pack();
        frame.setMinimumSize(frame.getSize());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    private World() {
        pixelsPerInch = 4;
        CaromTable table = new CaromTable();
        table.setPixelsPerInch(pixelsPerInch);
        this.add(table);
    }

    public static synchronized World getInstance() {
        if (world == null)
            world = new World();
        return world;
    }
}
