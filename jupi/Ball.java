package jupi;

import java.awt.*;

/**
 * Class defining a billiards ball.
 */

public class Ball {
    private Color color;
    private double[] initialPosition, position;

    /**
     * Default constructor makes an odd-colored (blue) ball at position 0,0.
     * Should not be used.
     */
    public Ball() {
        this(0, 0, Color.blue);
    }

    public Ball(double x, double y, Color c) {
        initialPosition = new double[]{x, y};
        position = new double[]{x, y};
        color = c;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double[] getPosition() {
        return position;
    }

    public void setPosition(double[] position) {
        this.position = position;
    }

}
