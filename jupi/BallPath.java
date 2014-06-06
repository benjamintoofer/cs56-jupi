package jupi;

import java.awt.Color;
import java.awt.Graphics;

public class BallPath 
{
	private double angle,dx,dy,distance;
	private VectorDouble startPoint,endPoint;
	private int ppi = BilliardsConstants.PIXELS_PER_INCH;	
	
	public BallPath(VectorDouble ballPosition,double angle,double distance)
	{
		this.angle = angle;
		this.distance = distance;
		this.dx = distance * Math.cos(angle);
		this.dy = distance * Math.sin(angle);
		this.startPoint = ballPosition;
		this.endPoint = new VectorDouble();		
	}
	
	public void updateProp(VectorDouble ballPosition,double angle)
	{
		this.angle = angle;
		this.dx = -distance * Math.cos(angle + Math.toRadians(90));
		this.dy = -distance * Math.sin(angle + Math.toRadians(90));
		this.startPoint = ballPosition;
	}	
	
	
	public void draw(Graphics g, double cushion, double floor, double border)
	{
		double totalWidth = cushion+floor+border;
		draw(g, totalWidth * ppi , totalWidth * ppi);
	}
	
	
	//public void draw(Graphics g,double cushion,double floor,double border)
	public void draw(Graphics g,  double innerTableEdgeX, double innerTableEdgeY)
	{		
		double firstPath,secondPath;
		//double totalWidth = cushion+floor+border;		
		
		g.setColor(Color.RED);		
		
		this.dx = -distance * Math.cos(angle + Math.toRadians(90));
		this.dy = -distance * Math.sin(angle + Math.toRadians(90));
		for(int i = 0; i < distance; i++)
		{			
			double y =  (dy * i/18);
			double x =  (dx * i/18);
			double overlapX = Physics.checkXPath((startPoint.x + x));
			double overlapY = Physics.checkYPath((startPoint.y + y));			
			
			//g.fillOval((int)((startPoint.x + totalWidth+ x + overlapX)*ppi) - 4,(int)((startPoint.y +totalWidth+ y + overlapY)*ppi) - 4, 8, 8);
			g.fillOval((int)((startPoint.x + innerTableEdgeX/ppi + x + overlapX)*ppi) - 4,
					   (int)((startPoint.y +innerTableEdgeY/ppi + y + overlapY)*ppi) - 4, 8, 8);
		}
	}
	
}
