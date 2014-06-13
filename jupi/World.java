package jupi;
import javax.swing.*;

public class World extends JApplet 
{
    public final CaromTable table;
    
    public World()    
    {
        int pixelsPerInch = BilliardsConstants.PIXELS_PER_INCH;
        table = new CaromTable();
        table.setPixelsPerInch(pixelsPerInch);
        this.add(table);
    }
}