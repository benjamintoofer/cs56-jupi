package jupi;
import java.awt.*;

import javax.swing.*;

/**
 * Class defining a billiards ball.
 */

public class Ball
{
    private Color color;
    private VectorDouble position,velocity;
    private double mass, radius;
    private boolean visible;
    private final  VectorDouble startPosition;
    private boolean currentBall;//used to track which ball is hit by cue
    private boolean isHit;

    /**
     * Default constructor makes an odd-colored (blue) ball at position 0,0.
     * Should not be used.
     */
    public Ball() 
    {
        this(0, 0, 20, BilliardsConstants.BALL_MASS, Color.blue);    	
    }

    public Ball(double x, double y, double radius, double mass, Color c) 
    {        
        position    = new VectorDouble(x,y);
        startPosition = new VectorDouble(x,y);
        velocity    = new VectorDouble(0,0);
        this.radius = radius;
        this.mass   = mass;
        color       = c;
        visible = true;
        currentBall = false; 
        isHit = false;
    }

    public Ball(double x, double y, Color c) 
    {   
    	this(x,y, BilliardsConstants.BALL_DIAMETER/2, BilliardsConstants.BALL_MASS, c);
    }
    
    
    public Color getColor() 
    {
        return color;
    }

    public void setColor(Color color) 
    {
        this.color = color;
    }


    public VectorDouble getPosition() 
    {
        return position;
    }

    public void setPosition(VectorDouble position) 
    {
        this.position = position;
    }
    
    public void setPosition(double x, double y) 
    {
        this.setPosition(new VectorDouble(x,y));
    }
    
    public VectorDouble getVelocity()
    {
    	return velocity;
    }
    
    public boolean isVisible()
    {
    	return visible;
    }
    
    public void setVisible(boolean isVisible)
    {
    	this.visible = isVisible; 
    }
    
    public void setVelocity(VectorDouble velocity)
    {
    	this.velocity = velocity;
    }
    
    public void setVelocity(double xVel,double yVel)
    {
    	this.setVelocity(new VectorDouble(xVel,yVel));
    }
    
    public void reflect(boolean x, boolean y)
    {
    	if(x)
    	{    		
    		this.setVelocity((this.getVelocity().getX()*-1), this.getVelocity().getY());
    	}
    	if(y)
    	{   	
    		this.setVelocity((this.getVelocity().getX()), (this.getVelocity().getY() * -1));    			
    	}
    }
        
          
    public double getRadius()
    {
    	return radius;
    }

    public double getMass()
    {
    	return mass;
    }
    public void reset()
    {
    	double tempX = startPosition.x;
    	double tempY = startPosition.y;
    	this.setPosition(tempX,tempY);
    	this.setVelocity(0, 0);
    }
    
    //Update balls properties
    public void update()
    {        	
    	velocity.x *= BilliardsConstants.ROLLING_FRICTION;
    	velocity.y *= BilliardsConstants.ROLLING_FRICTION;
    	if(Math.abs(velocity.x) < BilliardsConstants.MINIMUM_SPEED)
    	{
    		velocity.x = 0;
    	}
    	if(Math.abs(velocity.y) < BilliardsConstants.MINIMUM_SPEED)
    	{
    		velocity.y = 0;
    	}
    	position.x += velocity.x;
    	position.y += velocity.y;    	
    }  
    
    public void setCurrentBall(boolean isCurrentBall)
    {
    	currentBall = isCurrentBall;
    }
    
    public boolean isCurrentBall()
    {
    	return currentBall;
    }
    public void setIsHit(boolean isHit)
    {
    	this.isHit  = isHit;
    }
    public boolean isHit()
    {
    	return this.isHit;
    }

}//Ball
