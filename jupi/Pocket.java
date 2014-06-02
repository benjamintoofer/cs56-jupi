package jupi;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Pocket {
	
	private VectorDouble position;
	private double diameter = BilliardsConstants.POCKET_DIAMETER;
	public Ball[] balls;
	public int numBalls;
	
	public Pocket(double x,double y)
	{
		position = new VectorDouble(x,y);
		numBalls = 0;
		balls = new Ball[BilliardsConstants.MAX_BALLS];
		
	}
	public VectorDouble getPosition(){
		return position;
	}
	public void setPosition(double x, double y)
	{
		this.position.x = x;
		this.position.y = y;
	}
	public void setPosition(VectorDouble position)
	{
		this.position = position;
	}
	public double getDiameter()
	{
		return diameter;
	}
	public void draw(Graphics g)
	{
		g.setColor(Color.red);
		g.drawOval((int)(this.getPosition().x + BilliardsConstants.CUSHION_WIDTH+BilliardsConstants.FLOOR_WIDTH)*9, (int)(this.getPosition().y + BilliardsConstants.CUSHION_WIDTH+BilliardsConstants.FLOOR_WIDTH)*9, 10, 10);
	     
	}
	
	
}
