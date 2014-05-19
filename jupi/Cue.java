package jupi;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Cue {
	
	private VectorDouble tipPosition;
	private double power;
	
	public Cue()
	{
		this(0,0,0);
	}
	public Cue(double xPos,double yPos,double power)
	{
		tipPosition = new VectorDouble(xPos,yPos);
		this.power = power;
	}
	public double getX()
	{
		return tipPosition.x;
	}
	public double getY()
	{
		return tipPosition.y;
	}
	public void setPosition(VectorDouble position)
	{
		tipPosition = position;
	}
	public void setPosition(double xPos,double yPos)
	{
		tipPosition.x = xPos;
		tipPosition.y = yPos;
	}
	public double getPower()
	{
		return power;
	}
	public void draw(Graphics g,Ball ball)
	{
		Graphics2D g2d = (Graphics2D)g;
		g2d.rotate(Math.toRadians(30));
		g.setColor(Color.BLUE);
		g.fillArc((int)ball.getPosition().x * BilliardsConstants.PIXELS_PER_INCH, (int)ball.getPosition().y* BilliardsConstants.PIXELS_PER_INCH, 20 ,10 ,0 ,180);
		
		
	}
}
