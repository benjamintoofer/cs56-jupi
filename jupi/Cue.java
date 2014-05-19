package jupi;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Cue {
	
	private VectorDouble tipPosition;
	private double power;
	private double angle;
	
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
	public void setAngle(double angle)
	{
		this.angle = angle;
	}
	public double getAngle()
	{
		return angle;
	}
	public void setPower(double power)
	{
		this.power = power;
	}
}
