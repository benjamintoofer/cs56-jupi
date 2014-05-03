package jupi;

import java.awt.*;

/**
 * Constants used to define billiards game parameters
 */

public interface BilliardsConstants {
    int inchPerFoot = 12;

    /**
     * {@code Dimension} containing the width and height, in inches, of the region which can contain balls.
     */
    Dimension tableDimension = new Dimension(inchPerFoot * 10, inchPerFoot * 5);

    /**
     * Width, in inches, of the raised region surrounding the table. The balls bump into the cushions.
     */
    double cushionWidth = 3;

    /**
     * Radius, in inches, of the table border's corners.
     */
    double borderCorner = 5;

    /**
     * Width, in inches, of the empty space surrounding the table and cushions.
     */
    double floorWidth = inchPerFoot * 2;
    double ballDiameter = 2.375;

    /**
     * Simulated time, in milliseconds, between updating the world state.
     */
    int timeslice = 5;

    /**
     * The number by which ball speeds are multiplied every timeslice to yield speed on next timeslice.
     * Should be within the interval [0, 1).
     */
    double rollingFriction = 0.98;

    /**
     * The speed below which a ball will be stopped on the next timeslice. Units?
     * Should be > 0.
     */
    double minimumSpeed = 0.01;

    /**
     * Default colors of the table felt.
     */
    Color felt = new Color(0x008800);

    /**
     * Default color of the table border (the cushions).
     */
    Color border = new Color(0x004400);

    /**
     * Default color of the floor surrounding the table.
     */
    Color floor = new Color(0x663300);

    /**
     * Default color of the white ball.
     */
    Color white = new Color(0xffffcc);

    /**
     * Default color of the red ball.
     */
    Color red = new Color(0xaa0000);

    /**
     * Default color of the yellow ball.
     */
    Color yellow = new Color(0xcccc00);

    /**
     * Default color for rendering edges of objects.
     */
    Color edge = Color.black;
}
