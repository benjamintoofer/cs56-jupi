package jupi;
import java.awt.*;

/**
 * Constants used to define billiards game parameters. "Inches" means inches within the simulated world.
 * The size taken on screen is equal to inches * pixelsPerInch.
 */


public interface BilliardsConstants 
{
	int PIXELS_PER_INCH= 8;
    int INCHES_PER_FOOT = 12;    

    /**
     * {@code double} array containing the width and height, in inches, of the region which can contain balls.
     */
    double[] TABLE_DIMENSION = new double[]{INCHES_PER_FOOT * 10, INCHES_PER_FOOT * 5};

    /**
     * Width, in inches, of the felt-covered region surrounding the table. The balls bump into the cushions.
     */
    double CUSHION_WIDTH = 2;

    /**
     * Width, in inches, of the non-felt-covered region surrounding the cushions.
     */
    double BORDER_WIDTH = 3;

    /**
     * Radius, in inches, of the table border's corners.
     */
    double BORDER_CORNER = 5;

    /**
     * Width, in inches, of the empty space surrounding the table and cushions.
     */
    double FLOOR_WIDTH = 12;

    /**
     * Ball diameter, in inches.
     */
    double BALL_DIAMETER = 2.375;
    
    /**
     * Ball Mass, in grams.
     */
    double BALL_MASS = 210;
    

    /**
     * Simulated time, in milliseconds, between updating the world state.
     */
    int TIME_SLICE = 5;

    /**
     * The number by which ball speeds are multiplied every timeslice to yield speed on next timeslice.
     * Should be within the interval [0, 1).
     */
    double ROLLING_FRICTION = 0.98;

    /**
     * The speed below which a ball will be stopped on the next timeslice. Units?
     * Should be > 0.
     */
    double MINIMUM_SPEED = 0.01;

    /**
     * Default colors of the table felt, including both play area and cushions.
     * Green
     */
    Color FELT = new Color(0x008800);

    /**
     * Default color of the table border, outside the cushions.
     * Dark Brown
     */
    Color BORDER = new Color(0x663300);

    /**
     * Default color of the floor surrounding the table.
     * light Brown
     */
    Color FLOOR = new Color(0xbb7700); 

    /**
     * Default color of the white ball.
     */
    Color WHITE = new Color(0xffffcc);

    /**
     * Default color of the red ball.
     */
    Color RED = new Color(0xaa0000);

    /**
     * Default color of the yellow ball.
     */
    Color YELLOW = new Color(0xcccc00);

    /**
     * Default color for rendering edges of objects.
     */
    Color EDGE = Color.black;

    /**
     * Default color for rendering table markings.
     */
    Color MARK = Color.white;
}
