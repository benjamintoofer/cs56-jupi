package jupi;
import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

/**
 * Class defining the billiards table and its aggregation of billiards balls.
 */

public class CaromTable extends JPanel 
{
    private double[] dimTable, dimCushion, dimBorder, dimFloor;
    private double borderCorner, borderWidth, cushionWidth, floorWidth, radius, mass,gap,cueStickLength,pullDistance;
    private int ppi;//pixels per inch
    private Ball whiteball, redball, yellowball;
    private BallPath path;
    private Cue cueStick;
    private ArrayList<Ball> balls = new ArrayList<>();
    private Color felt, border, floor, edge, mark;
    int mouseX,mouseY;
    boolean mouseDown,showCue;

    /**
     * Default constructor draws all field values from BilliardConstants
     */

    public CaromTable() 
    {
        borderCorner   = BilliardsConstants.BORDER_CORNER;
        borderWidth    = BilliardsConstants.BORDER_WIDTH;
        cushionWidth   = BilliardsConstants.CUSHION_WIDTH;
        floorWidth     = BilliardsConstants.FLOOR_WIDTH;
        radius         = BilliardsConstants.BALL_DIAMETER / 2;
        mass		   = BilliardsConstants.BALL_MASS;
        gap			   = BilliardsConstants.GAP;
        cueStickLength = BilliardsConstants.CUE_LENGTH;
        pullDistance   = 0;
        mouseDown      = false;
        showCue 	   = false;

        dimTable = BilliardsConstants.TABLE_DIMENSION;
        dimCushion = new double[]{dimTable[0]   + 2*cushionWidth, dimTable[1]   + 2*cushionWidth};
        dimBorder  = new double[]{dimCushion[0] + 2*borderWidth , dimCushion[1] + 2*borderWidth};
        dimFloor   = new double[]{dimBorder[0]  + 2*floorWidth  , dimBorder[1]  + 2*floorWidth};
        
        cueStick = new Cue();
        whiteball  = new Ball(dimTable[0] / 3 , dimTable[1] /2  ,radius,mass, BilliardsConstants.WHITE);
        redball    = new Ball(dimTable[0] *2/3, dimTable[1] *2/5,radius,mass, BilliardsConstants.RED);
        yellowball = new Ball(dimTable[0] *2/3, dimTable[1] *3/5,radius,mass, BilliardsConstants.YELLOW);
        path = new BallPath(whiteball.getPosition(),0,30);
        
        balls.add(whiteball);
        balls.add(redball);
        balls.add(yellowball);
        
     
        
        whiteball.setVelocity(0,0);
        redball.setVelocity(0,0);
        yellowball.setVelocity(0,0);
        
        felt   = BilliardsConstants.FELT;
        border = BilliardsConstants.BORDER;
        floor  = BilliardsConstants.FLOOR;
        edge   = BilliardsConstants.EDGE;
        mark   = BilliardsConstants.MARK;
        
        this.addMouseListener(new MouseListener()
        {

			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {
				mouseDown = true;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				mouseDown = false;
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
        	
        });
        this.addMouseMotionListener(new MouseMotionListener()
        {
        	public void mouseMoved(MouseEvent e)
        	{
        		mouseX = e.getX();
        		mouseY = e.getY();
        	}
        	public void mouseDragged(MouseEvent e) {}
        });//MouseMotionListener
        
    }//CaromTable 


    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        int x, y, width, height;
        int cushionEdge = (int) ((floorWidth + borderWidth) * ppi);
        int tableEdge   = (int) ((floorWidth + borderWidth + cushionWidth) * ppi);

        /* draw a filled rectangle for the floor surrounding the table border */
        g.setColor(floor);
        g.fillRect(0, 0, (int)(dimFloor[0] * ppi), (int)(dimFloor[1] * ppi));

        /* draw a filled round rect for the rail border surrounding the cushions */
        g.setColor(border);
        g.fillRoundRect((int)(floorWidth * ppi)  , (int)(floorWidth * ppi),
                		(int)(dimBorder[0] * ppi), (int)(dimBorder[1] * ppi),
                		(int)(borderCorner * ppi), (int)(borderCorner * ppi));

        /* draw a filled rectangle for the play area plus the cushions */
        g.setColor(felt);
        g.fillRect(cushionEdge, cushionEdge,
                   (int)(dimCushion[0] * ppi),
                   (int)(dimCushion[1] * ppi));

        /* draw a rectangle outline for the play area */
        g.setColor(edge);
        g.drawRect(tableEdge, tableEdge,
                   (int)(dimTable[0] * ppi),
                   (int)(dimTable[1] * ppi));

        /* draw a line segment connecting the play field corners to each border corner */
        g.drawLine(cushionEdge, cushionEdge, tableEdge, tableEdge);
        g.drawLine(cushionEdge + (int)dimCushion[0] * ppi, cushionEdge,
                   tableEdge + (int)dimTable[0] * ppi, tableEdge);
        g.drawLine(cushionEdge, cushionEdge + (int)dimCushion[1] * ppi,
                   tableEdge, tableEdge + (int) dimTable[1] * ppi);
        g.drawLine(cushionEdge + (int)dimCushion[0] * ppi, cushionEdge + (int)dimCushion[1] * ppi,
                   tableEdge + (int)dimTable[0] * ppi, tableEdge + (int)dimTable[1] * ppi);

        /* draw filled circles to mark the edges */
        g.setColor(mark);
        int markRadius = (int)(cushionWidth / 2 * ppi); 
        int marks = 8;
        int sideMarks = 3;
        
        for (int i = 0; i <= marks; i++) 
        {   
        	//Top border markers:
        	g.fillOval((int)(cushionEdge + (cushionWidth/2 + i*dimTable[0]/marks )*ppi),        			   
        			   (int)(cushionEdge - markRadius - borderWidth/2),
        			   markRadius, markRadius);
            //Bottom border markers:                                 
            g.fillOval((int)(cushionEdge + (cushionWidth/2 + i*dimTable[0]/marks )*ppi),
            		   (int)(cushionEdge + (dimTable[1] + 2*cushionWidth)* ppi + borderWidth/2),                      
            		   markRadius, markRadius);
        }
        
        for (int i = 1; i <= sideMarks; i++) 
        {
        	//Left border markers:
        	g.fillOval((int)(cushionEdge - markRadius - borderWidth/2),        			   
     			       (int)(cushionEdge + (i*dimTable[1]/(sideMarks+1))*ppi),
     			       markRadius, markRadius);
        	//Right border markers:
        	g.fillOval((int)(cushionEdge + (2*cushionWidth + dimTable[0])*ppi + borderWidth/2),        			   
  			       	   (int)(cushionEdge + (i*dimTable[1]/(sideMarks+1))*ppi),
  			           markRadius, markRadius);        	
        }
        
        for (Ball b: balls) 
        {// draw a circle outline for each ball 
            g.setColor(edge);
            g.drawOval((int)((floorWidth + borderWidth + cushionWidth + b.getPosition().x-radius) * ppi),
                    (int)((floorWidth + borderWidth +cushionWidth + b.getPosition().y-radius) * ppi),
                    (int)(2*radius*ppi), (int)(2*radius*ppi));

            // draw a filled circle for each ball 
            g.setColor(b.getColor());
            g.fillOval((int)((floorWidth + borderWidth + cushionWidth + b.getPosition().x-radius) * ppi),
                    (int)((floorWidth + borderWidth +cushionWidth + b.getPosition().y-radius) * ppi),
                    (int)(2*radius*ppi), (int)(2*radius*ppi));          
        }
        
        /*
         * Drawing cue stick
         */
        if(showCue)
        {
        	
        	double dx = mouseX- ((floorWidth + borderWidth + cushionWidth+whiteball.getPosition().x) * ppi);
        	double dy = mouseY - ((floorWidth + borderWidth + cushionWidth+whiteball.getPosition().y) * ppi);
        
        	double angle = Math.atan2(dy, dx) - Math.toRadians(90);
        	cueStick.setAngle(angle);
        
        	cueStick.setPosition(( floorWidth + borderWidth + cushionWidth + whiteball.getPosition().x - BilliardsConstants.CUE_DIAMETER*.5 ) ,
				(pullDistance + floorWidth + borderWidth +cushionWidth + whiteball.getPosition().y - radius + gap));
        
        
        	int[] cuesX = new int[]{(int)(cueStick.getX()*ppi),
        					(int)((cueStick.getX() + BilliardsConstants.CUE_DIAMETER )*ppi),
        					(int)((cueStick.getX() +  1.5*BilliardsConstants.CUE_DIAMETER )*ppi),
        					(int)((cueStick.getX() -  BilliardsConstants.CUE_DIAMETER*.5 )*ppi)};
        	int[]cuesY = new int[]{(int)((cueStick.getY() + BilliardsConstants.CUE_DIAMETER*.5)*ppi),
        					(int)((cueStick.getY() + BilliardsConstants.CUE_DIAMETER*.5)*ppi),
        					(int)((cueStick.getY() + BilliardsConstants.CUE_DIAMETER*.5 + cueStickLength)*ppi),
        					(int)((cueStick.getY() + BilliardsConstants.CUE_DIAMETER*.5 + cueStickLength)*ppi)};
        	///
        	 path.updateProp(whiteball.getPosition(), angle);
        	 path.draw(g,cushionWidth,floorWidth,borderWidth);
        	///
             
        	AffineTransform transform = new AffineTransform();
        	transform.rotate(angle,(floorWidth + borderWidth + cushionWidth + whiteball.getPosition().x )*ppi,(floorWidth + borderWidth + cushionWidth + whiteball.getPosition().y  )*ppi);
        	Graphics2D g2d = (Graphics2D)g;
        	g2d.transform(transform);
			g2d.setColor(Color.BLUE);
			g2d.fillArc((int)(cueStick.getX()*ppi) , 
				   (int)(cueStick.getY()*ppi),
				   (int)(BilliardsConstants.CUE_DIAMETER*ppi) ,(int)(BilliardsConstants.CUE_DIAMETER*ppi) ,0 ,180);
		
			g2d.setColor(new Color(0xDBB84D));
			g2d.fillPolygon(cuesX, cuesY, 4);
        }
       
		
        
        
    }//paintComponent


    public int getPixelsPerInch() 
    {
        return ppi;
    }

    public void setPixelsPerInch(int pixelsPerInch) 
    {
        if (pixelsPerInch < 1)
            throw new IllegalArgumentException("Pixels per inch must be positive.");
        this.ppi = pixelsPerInch;
    }
    
    //Get Table Dimensions
    public double getTableWidth()
    {
    	return dimTable[0];
    }
    public double getTableHeight()
    {
    	return dimTable[1];
    }

    @Override
    public Dimension getPreferredSize() 
    {
        return new Dimension((int)(dimFloor[0] * ppi), (int)(dimFloor[1] * ppi));
    }

    public void update()
    {
    	
    	
    	for(int i = 0; i < balls.size(); i++)
    	{
    		//Mouse down even to hit ball
    		if(mouseDown && pullDistance < BilliardsConstants.MAX_PULL && showCue)
    		{
    			pullDistance += BilliardsConstants.PULL_RATE;
    			cueStick.setPower(pullDistance);
    			
    		}else if(!mouseDown && pullDistance > 0)
    		{
    			pullDistance -= 1; 
    		}
    		if(pullDistance < 0)
    		{
    			Physics.hitBall(cueStick,whiteball);
    			pullDistance = 0;
    			System.out.println("hit");
    		}
    		
    		//Check if balls are at rest
    		if(whiteball.getVelocity().x ==0 && whiteball.getVelocity().y == 0
    				&& yellowball.getVelocity().x == 0 && yellowball.getVelocity().y  == 0
    				&& redball.getVelocity().x == 0&& redball.getVelocity().y == 0)
    		{
    			showCue = true;
    		}else
    		{
    			showCue = false;
    		}
    		
    		Physics.checkCusionCollision(balls.get(i), this);
    		
    		for(int j = i + 1; j < balls.size(); j++)
    		{
    			Physics.checkBallCollision(balls.get(i), balls.get(j));
    		}
    		balls.get(i).update();
    	}
    	
    	repaint();
    }
}