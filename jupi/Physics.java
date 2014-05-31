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
	public static void checkBallCollision(Ball ball1, Ball ball2)
	{
		//Check if balls collide
		double dx = ball2.getPosition().x - ball1.getPosition().x;
		double dy = ball2.getPosition().y - ball1.getPosition().y;
		double distance = Math.sqrt(dx*dx + dy*dy);
		double radiusDistance = ball1.getRadius() + ball2.getRadius();
		
		if(distance < radiusDistance)
		{
			double angle = Math.atan2(dy, dx);
			double sin = Math.sin(angle);
			double cos = Math.cos(angle);
			
			//Rotate ball1 and ball2 position
			VectorDouble pos1 = new VectorDouble(0,0);
			VectorDouble pos2 = rotate(dx,dy,sin,cos,true);
			
			//Rotate ball1 and ball2 velocity
			VectorDouble vel1 = rotate(ball1.getVelocity().x,ball1.getVelocity().y,sin,cos,true);
			VectorDouble vel2 = rotate(ball2.getVelocity().x,ball2.getVelocity().y,sin,cos,true);
			
			//Collision reaction
			double vxTotal = vel1.x - vel2.x;
			vel1.x = ((ball1.getMass() - ball2.getMass()) * vel1.x + 2 * ball2.getMass() * vel2.x)/(ball1.getMass() + ball2.getMass());
			vel2.x = vxTotal + vel1.x;
			
			//Update Position
				//Prevent overlap
			double absV = Math.abs(vel1.x) + Math.abs(vel2.x);
			double overlap = (ball1.getRadius() + ball2.getRadius() - Math.abs(pos1.x - pos2.x));
			
			pos1.x += vel1.x/absV * overlap;
			pos2.x += vel2.x/absV * overlap;
			
			//Rotate back
			VectorDouble finalPos1 = rotate(pos1.x,pos1.y,sin,cos,false);
			VectorDouble finalPos2 = rotate(pos2.x,pos2.y,sin,cos,false);
			
			//Adjust positions
			ball2.setPosition((ball1.getPosition().x+finalPos2.x), (ball1.getPosition().y+finalPos2.y));
			ball1.setPosition((ball1.getPosition().x+finalPos1.x),(ball1.getPosition().y+finalPos1.y));
			
			//Rotate velocities back
			VectorDouble finalVel1 = rotate(vel1.x,vel1.y,sin,cos,false);
			VectorDouble finalVel2 = rotate(vel2.x,vel2.y,sin,cos,false);
			
			//Adjust Velocities
			ball1.setVelocity(finalVel1);
			ball2.setVelocity(finalVel2);
			
			//System.out.println("Collided");
		}
	}//checkBallCollision
	public static void hitBall(Cue cue, Ball ball)
	{
		double newXVel = (cue.getPower()*.5) * Math.cos(cue.getAngle() - Math.toRadians(90));
		double newYVel = (cue.getPower()*.5) * Math.sin(cue.getAngle() - Math.toRadians(90));
		//System.out.println(newXVel+"  "+newYVel+"  "+cue.getAngle()*(180/Math.PI));
		ball.setVelocity(newXVel, newYVel);
	}
	public static VectorDouble rotate(double x,double y,double sin, double cos, boolean reverse)
	{
		VectorDouble result = new VectorDouble();
		
		if(reverse)
		{
			result.x = x * cos + y * sin;
			result.y = y * cos - x * sin;
		}else
		{
			result.x = x * cos - y * sin;
			result.y = y * cos + x * sin;
		}
		
		return result;
	}//rotate
	public static double checkPath(VectorDouble start,double dx,double dy)
	{
		double rightCushion  = BilliardsConstants.TABLE_DIMENSION[0];
		double leftCushion   = 0, 
			   topCushion    = 0;
		double bottomCushion = BilliardsConstants.TABLE_DIMENSION[1];
		
	}
	
}//Physics

