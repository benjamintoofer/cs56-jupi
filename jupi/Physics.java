package jupi;


import java.awt.*;

import javax.swing.*;


public class Physics {
	
	public static void checkCusionCollision(Ball ball,CaromTable table)
	{
		//Collision with left wall
		if((ball.getPositionInPixels().x - ball.getRadiusInPixels()) - BilliardsConstants.cushionWidth*8 < 0 && (ball.getVelocity().x < 0))
		{
			ball.reflect(true, false);
		}
		//Collision with right wall
		//System.out.println(ball.getPosition().x +"  "+table.getTableWidth());
		if((ball.getPositionInPixels().x + (ball.getRadiusInPixels()) > table.getTableWidth()+ BilliardsConstants.cushionWidth*8) && (ball.getVelocity().x > 0))
		{
			ball.reflect(true, false);
		}
		//Collision with bottom wall
		System.out.println((table.getTableHeight() - 415));
		if((ball.getPosition().y + ball.getRadius() > (table.getTableHeight() - 415)) && (ball.getVelocity().y > 0))
		{
			ball.reflect(false, true);
		}
	}
}
