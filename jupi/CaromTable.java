package jupi;

import javax.swing.*;
import java.awt.*;

/**
 * Class defining the billiards table and its aggregation of billiards balls.
 */

public class CaromTable extends JPanel {
    private Dimension dimTable, dimBorder, dimFloor;
    private int pixelsPerInch;
    private Ball cueball, redball, yellowball;
    private Color felt = BilliardsConstants.felt;
    private Color border = BilliardsConstants.border;
    private Color floor = BilliardsConstants.floor;

    public CaromTable() {
        dimTable = BilliardsConstants.tableDimension;
        dimBorder = new Dimension(dimTable.width + 2*(int)BilliardsConstants.cushionWidth,
                dimTable.height + 2*(int)BilliardsConstants.cushionWidth);
        dimFloor = new Dimension(dimBorder.width + 2*(int)BilliardsConstants.floorWidth,
                dimBorder.height + 2*(int)BilliardsConstants.floorWidth);
        cueball = new Ball(BilliardsConstants.white);
        redball = new Ball(BilliardsConstants.red);
        yellowball = new Ball(BilliardsConstants.yellow);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(floor);
        g.fillRect(0, 0, dimFloor.width * pixelsPerInch, dimFloor.height * pixelsPerInch);

        g.setColor(border);
        g.fillRoundRect((int) BilliardsConstants.floorWidth * pixelsPerInch,
                (int) BilliardsConstants.floorWidth * pixelsPerInch,
                dimBorder.width * pixelsPerInch, dimBorder.height * pixelsPerInch,
                (int) BilliardsConstants.borderCorner * pixelsPerInch,
                (int) BilliardsConstants.borderCorner * pixelsPerInch);

        g.setColor(felt);
        g.fillRect((int)(BilliardsConstants.floorWidth + BilliardsConstants.cushionWidth) * pixelsPerInch,
                (int)(BilliardsConstants.floorWidth + BilliardsConstants.cushionWidth) * pixelsPerInch,
                dimTable.width * pixelsPerInch, dimTable.height * pixelsPerInch);
    }

    public int getPixelsPerInch() {
        return pixelsPerInch;
    }

    public void setPixelsPerInch(int pixelsPerInch) {
        if (pixelsPerInch < 1)
            throw new IllegalArgumentException("Pixels per inch must be positive.");
        this.pixelsPerInch = pixelsPerInch;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(dimFloor.width * pixelsPerInch, dimFloor.height * pixelsPerInch);
    }
}
