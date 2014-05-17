package jupi;
import java.awt.*;
import javax.swing.*;


public class Physics 
{	
	private static int  ppi = BilliardsConstants.PIXELS_PER_INCH;	
	
	public static void checkCusionCollision(Ball ball,CaromTable table)
	{
		double xPos          = ball.getPosition().x;
		double yPos          = ball.getPosition().y;
		double radius        = ball.getRadius();
		double rightCushion  = table.getTableWidth();
		double leftCushion   = 0, 
			   topCushion    = 0;
		double bottomCushion = table.getTableHeight();
		
		
		//Collision with left wall
		if(xPos - radius < leftCushion && (ball.getVelocity().x < 0))
		{
			ball.setPosition(radius, yPos);
			ball.reflect(true, false);			
		}
		
		//Collision with right wall
		if(xPos + radius > rightCushion && (ball.getVelocity().x > 0))
		{
			ball.setPosition(rightCushion - radius, yPos);
			ball.reflect(true, false);
		}

		//Collision with bottom wall
		if(yPos + radius > bottomCushion && (ball.getVelocity().y > 0))
		{
			ball.setPosition(xPos, bottomCushion - radius);
			ball.reflect(false, true);
		}
		
		//Collision with top wall
		if(yPos - radius < topCushion && ball.getVelocity().y < 0)
		{
			ball.setPosition(xPos,radius);
			ball.reflect(false, true);
		}	
		
	}//checkCusionCollision
	
}//Physics

