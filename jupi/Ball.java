package jupi;

import java.awt.*;

/**
 * Class defining a billiards ball.
 */

public class Ball {
    private Color color;
    private double radius;
    private Point initialPosition;
    private Point position;

    /**
     * Default constructor makes an odd-colored (blue) ball. Should not be used.
     */
    public Ball() {
        this(Color.blue);
    }

    public Ball(Color c) {
        color = c;
        radius = BilliardsConstants.ballDiameter / 2;
        position = initialPosition;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

}
