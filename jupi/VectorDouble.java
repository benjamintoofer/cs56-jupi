package jupi;

import java.awt.geom.Point2D;

public class VectorDouble extends Point2D{
	
	public double x,y;
	private double magnitude;
	
	public VectorDouble(double x, double y)
	{
		this.x = x;
		this.y = y;
		this.magnitude = Math.sqrt(x*x + y*y);
	}
	public VectorDouble()
	{
		this(0,0);
	}
	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
		
	}
	public double getMagnitude()
	{
		return magnitude;
	}

}
