package jupi;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;

import javax.swing.*;


public class Physics 
{		
	public static boolean checkCusionCollision(Ball ball,CaromTable table)
	{
		double xPos          = ball.getPosition().x;
		double yPos          = ball.getPosition().y;
		double radius        = ball.getRadius();
		double rightCushion  = table.getTableWidth();
		double leftCushion   = 0, 
			   topCushion    = 0;
		double bottomCushion = table.getTableHeight();
				
		boolean isCusionHit = false;
		
		//Collision with left wall
		if(xPos - radius < leftCushion && (ball.getVelocity().x < 0))
		{
			ball.setPosition(radius, yPos);
			ball.reflect(true, false);
			isCusionHit = true;
		}
		
		//Collision with right wall
		if(xPos + radius > rightCushion && (ball.getVelocity().x > 0))
		{
			ball.setPosition(rightCushion - radius, yPos);
			ball.reflect(true, false);
			isCusionHit = true;
		}

		//Collision with bottom wall
		if(yPos + radius > bottomCushion && (ball.getVelocity().y > 0))
		{	
			ball.setPosition(xPos, bottomCushion - radius);
			ball.reflect(false, true);
			isCusionHit = true;
		}
		
		//Collision with top wall
		if(yPos - radius < topCushion && ball.getVelocity().y < 0 )
		{
			ball.setPosition(xPos,radius);
			ball.reflect(false, true);
			isCusionHit = true;
		}	
			
		return isCusionHit;
	}//checkCusionCollision
	
	public static void checkCusionCollision(Ball ball,JupiTable table)
	{
		double xPos          = ball.getPosition().x;
		double yPos          = ball.getPosition().y;
		double radius        = ball.getRadius();
		double rightCushion  = table.getTableWidth();
		double leftCushion   = 0, 
			   topCushion    = 0;
		double bottomCushion = table.getTableHeight();
		double pocketGap = 2;
		
		
		//Collision with left wall
		if(xPos - radius < leftCushion && (ball.getVelocity().x < 0) && yPos > topCushion + pocketGap && yPos < bottomCushion - pocketGap)
		{
			ball.setPosition(radius, yPos);
			ball.reflect(true, false);			
		}
		
		//Collision with right wall
		if(xPos + radius > rightCushion && (ball.getVelocity().x > 0) && yPos > topCushion + pocketGap && yPos < bottomCushion - pocketGap)
		{
			ball.setPosition(rightCushion - radius, yPos);
			ball.reflect(true, false);
		}

		//Collision with bottom wall
		if(yPos + radius > bottomCushion && (ball.getVelocity().y > 0) && xPos > leftCushion + pocketGap && xPos < rightCushion - pocketGap)
		{	
			ball.setPosition(xPos, bottomCushion - radius);
			ball.reflect(false, true);
		}
		
		//Collision with top wall
		if(yPos - radius < topCushion && ball.getVelocity().y < 0 && xPos > leftCushion + pocketGap && xPos < rightCushion - pocketGap)
		{
			ball.setPosition(xPos,radius);
			ball.reflect(false, true);
		}	
		
	}//checkCusionCollision
	
	
	public static boolean checkBallCollision(Ball ball1, Ball ball2)
	{
		//Check if balls collide
		double dx = ball2.getPosition().x - ball1.getPosition().x;
		double dy = ball2.getPosition().y - ball1.getPosition().y;
		double distance = Math.sqrt(dx*dx + dy*dy);
		double radiusDistance = ball1.getRadius() + ball2.getRadius();
		
		boolean isCollision = false;
		
		if(distance <= radiusDistance) 
		{//collision has occurred
			isCollision = true;
			
			double angle = Math.atan2(dy, dx);
			double sin = Math.sin(angle);
			double cos = Math.cos(angle);
			
			ball1.setPosition(ball2.getPosition().x - dx, ball2.getPosition().y - dy);
			
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
		}//if
		
		return isCollision;
	}//checkBallCollision
	
	public static void hitBall(Cue cue, Ball ball)
	{
		//double powerMulti = 0.35;
		double powerMulti = 0.3;
		double newXVel = (cue.getPower()*powerMulti) * Math.cos(cue.getAngle() - Math.toRadians(90));
		double newYVel = (cue.getPower()*powerMulti) * Math.sin(cue.getAngle() - Math.toRadians(90));
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
	public static double checkXPath(double x)
	{
		double rightCushion  = BilliardsConstants.TABLE_DIMENSION[0];
		double leftCushion   = 0;
		
		
		if(x > rightCushion)
		{
			
			return 2*(rightCushion - x);
		}else if(x < leftCushion)
		{
			return 2*(leftCushion - x); 
		}else{
			return 0;
		}
		
	}
	public static double checkYPath(double y)
	{
		
		double topCushion    = 0;
		double bottomCushion = BilliardsConstants.TABLE_DIMENSION[1];
		
		
		if(y > bottomCushion)
		{
			
			return 2*(bottomCushion - y);
		}else if(y < topCushion)
		{
			return 2*(topCushion - y); 
		}else{
			return 0;
		}
		
	}
	public static boolean checkInPocket(Ball b,Pocket p)
	{
		double dx = b.getPosition().x + (BilliardsConstants.BORDER_WIDTH+BilliardsConstants.CUSHION_WIDTH+BilliardsConstants.FLOOR_WIDTH)  - (p.getPosition().x + p.getDiameter()/2) ;
		double dy = b.getPosition().y + (BilliardsConstants.BORDER_WIDTH+BilliardsConstants.CUSHION_WIDTH+BilliardsConstants.FLOOR_WIDTH) - (p.getPosition().y + p.getDiameter()/2);
		double distance = Math.sqrt(dx*dx + dy*dy);
		//System.out.println(p.getDiameter()/2);
		if(distance < (p.getDiameter()/2))
		{
			if(b.getColor() != BilliardsConstants.WHITE && b.isVisible())
			{
				System.out.print("removed-------->>>>>");
				p.balls[p.numBalls++] = b;
				b.setVisible(false);
				b.setVelocity(0, 0);
				return true;
			}else{
				System.out.println("white ball in");
				b.reset();
				return false;
			}
			
		}
		return false;
		
	}
}//Physics

