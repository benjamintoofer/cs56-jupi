package jupi;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Class defining the billiards table and its aggregation of billiards balls.
 */

public class CaromTable extends JPanel {
    private double[] dimTable, dimCushion, dimBorder, dimFloor;
    private double borderCorner, borderWidth, cushionWidth, floorWidth, radius;
    private int ppi;
    private Ball cueball, redball, yellowball;
    private ArrayList<Ball> balls = new ArrayList<>();
    private Color felt, border, floor, edge, mark;

    /**
     * Default constructor draws all field values from BilliardConstants
     */
    public CaromTable() {
        borderCorner = BilliardsConstants.borderCorner;
        borderWidth = BilliardsConstants.borderWidth;
        cushionWidth = BilliardsConstants.cushionWidth;
        floorWidth = BilliardsConstants.floorWidth;
        radius = BilliardsConstants.ballDiameter / 2;

        dimTable = BilliardsConstants.tableDimension;
        dimCushion = new double[]{dimTable[0] + 2*cushionWidth, dimTable[1] + 2*cushionWidth};
        dimBorder = new double[]{dimCushion[0] + 2*borderWidth, dimCushion[1] + 2*borderWidth};
        dimFloor = new double[]{dimBorder[0] + 2*floorWidth, dimBorder[1] + 2*floorWidth};
/*
        cueball = new Ball(dimTable[0] / 3, dimTable[1] / 2, BilliardsConstants.white);
        redball = new Ball(dimTable[0] * 2 / 3, dimTable[1] * 2 / 5, BilliardsConstants.red);
        yellowball = new Ball(dimTable[0] * 2 / 3, dimTable[1] * 3 / 5, BilliardsConstants.yellow);
        
        balls.add(cueball);
        balls.add(redball);
        balls.add(yellowball);
*/
        felt = BilliardsConstants.felt;
        border = BilliardsConstants.border;
        floor = BilliardsConstants.floor;
        edge = BilliardsConstants.edge;
        mark = BilliardsConstants.mark;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cushionEdge = (int) ((floorWidth + borderWidth) * ppi);
        int tableEdge = (int) ((floorWidth + borderWidth + cushionWidth) * ppi);

        /* draw a filled rectangle for the floor surrounding the table border */
        g.setColor(floor);
        g.fillRect(0, 0, (int)(dimFloor[0] * ppi), (int)(dimFloor[1] * ppi));

        /* draw a filled round rect for the rail border surrounding the cushions */
        g.setColor(border);
        g.fillRoundRect((int)(floorWidth * ppi), (int)(floorWidth * ppi),
                (int)(dimBorder[0] * ppi), (int)(dimBorder[1] * ppi),
                (int)borderCorner * ppi, (int) borderCorner * ppi);

        /* draw a filled rectangle for the play area plus the cushions */
        g.setColor(felt);
        g.fillRect(cushionEdge, cushionEdge,
                (int)(dimCushion[0] * ppi), (int)(dimCushion[1] * ppi));

        /* draw a rectangle outline for the play area */
        g.setColor(edge);
        g.drawRect(tableEdge, tableEdge,
                (int)(dimTable[0] * ppi), (int)(dimTable[1] * ppi));

        /* draw a line segment connecting the play field corners to each border corner */
        g.drawLine(cushionEdge, cushionEdge, tableEdge, tableEdge);
        g.drawLine(cushionEdge + (int)dimCushion[0] * ppi, cushionEdge,
                tableEdge + (int)dimTable[0] * ppi, tableEdge);
        g.drawLine(cushionEdge, cushionEdge + (int)dimCushion[1] * ppi,
                tableEdge, tableEdge + (int) dimTable[1] * ppi);
        g.drawLine(cushionEdge + (int)dimCushion[0] * ppi, cushionEdge + (int)dimCushion[1] * ppi,
                tableEdge + (int)dimTable[0] * ppi, tableEdge + (int)dimTable[1] * ppi);

        g.setColor(mark);
        int markRadius = (int)(cushionWidth / 2 * ppi);
        for (int i = 0; i <= 9; i++) {
            g.fillOval((int) (floorWidth + borderWidth + cushionWidth + i*dimTable[0]/9 - cushionWidth/2)*ppi,
                    (int)(cushionEdge - borderWidth/2 - markRadius),
                    markRadius, markRadius);
        }
/*
        for (Ball b: balls) {
            // draw a circle outline for each ball 
            g.setColor(edge);
            g.drawOval((int)((floorWidth + cushionWidth + b.getPosition()[0]-radius) * ppi),
                    (int)((floorWidth + cushionWidth + b.getPosition()[1]-radius) * ppi),
                    (int)(2*radius*ppi), (int)(2*radius*ppi));
            // draw a filled circle for each ball 
            g.setColor(b.getColor());
            g.fillOval((int)((floorWidth + cushionWidth + b.getPosition()[0]-radius) * ppi),
                    (int)((floorWidth + cushionWidth + b.getPosition()[1]-radius) * ppi),
                    (int)(2*radius*ppi), (int)(2*radius*ppi));
        }
*/    
    }

    public int getPixelsPerInch() {
        return ppi;
    }

    public void setPixelsPerInch(int pixelsPerInch) {
        if (pixelsPerInch < 1)
            throw new IllegalArgumentException("Pixels per inch must be positive.");
        this.ppi = pixelsPerInch;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension((int)(dimFloor[0] * ppi), (int)(dimFloor[1] * ppi));
    }
}
