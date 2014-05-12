package jupi;

import java.awt.*;

/**
 * Constants used to define billiards game parameters. "Inches" means inches within the simulated world.
 * The size taken on screen is equal to inches * pixelsPerInch.
 */

public interface BilliardsConstants {
	int pixelsPerInch = 8;
	
    int inchPerFoot = 12;

    /**
     * {@code double} array containing the width and height, in inches, of the region which can contain balls.
     */
    double[] tableDimension = new double[]{inchPerFoot * 10, inchPerFoot * 5};

    /**
     * Width, in inches, of the felt-covered region surrounding the table. The balls bump into the cushions.
     */
    double cushionWidth = 2;

    /**
     * Width, in inches, of the non-felt-covered region surrounding the cushions.
     */
    double borderWidth = 3;

    /**
     * Radius, in inches, of the table border's corners.
     */
    double borderCorner = 5;

    /**
     * Width, in inches, of the empty space surrounding the table and cushions.
     */
    double floorWidth = 12;

    /**
     * Diameter, in inches, of a ball.
     */
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
     * Default colors of the table felt, including both play area and cushions.
     */
    Color felt = new Color(0x008800);

    /**
     * Default color of the table border, outside the cushions.
     */
    Color border = new Color(0x663300);

    /**
     * Default color of the floor surrounding the table.
     */
    Color floor = new Color(0xbb7700);

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

    /**
     * Default color for rendering table markings.
     */
    Color mark = Color.white;
}
