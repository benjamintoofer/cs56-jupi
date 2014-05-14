package jupi;
import java.awt.*;
import javax.swing.*;

public class Physics 
{	
	public static void checkCusionCollision(Ball ball,CaromTable table)
	{
		//Collision with left wall		
		if((ball.getPosition().getX() - ball.getRadius() < 0) && (ball.getVelocity().getX() < 0))
		{
			ball.reflect(true, false);
		}
		
	}//checkCusionCollision
	
}//Physics
