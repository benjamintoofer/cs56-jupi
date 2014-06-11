package jupi;
import javax.swing.*;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

/**
 * Class defining the billiards table and its aggregation of billiards balls.
 */

public class JupiTable extends JPanel 
{
	 /**Score needed to end game (i.e. winning score)*/        
    private int endScore = 1;
   
	//Sound Effects
	protected AudioClip cueHittingSound       = Applet.newAudioClip(this.getClass().getResource("click_sound.wav"));
	protected AudioClip ballCollisionSound    = Applet.newAudioClip(this.getClass().getResource("ball_collision_sound.wav"));	
	protected AudioClip cushionCollisionSound = Applet.newAudioClip(this.getClass().getResource("thud_sound.wav"));
	protected AudioClip inPocketSound         = Applet.newAudioClip(this.getClass().getResource("wooden_thud_sound.wav"));	
	protected AudioClip gameWinnerSound 	  = Applet.newAudioClip(this.getClass().getResource("Cheering_Sound.wav"));	
	protected AudioClip gameExitSound 	      = Applet.newAudioClip(this.getClass().getResource("Ta_Da_Sound.wav"));
	
    private double[] dimTable, dimCushion, dimBorder, dimFloor;
    private double borderCorner, borderWidth, cushionWidth, floorWidth, radius, mass,gap,cueStickLength,pullDistance;
    private int ppi;//pixels per inch
    private Ball whiteball, redball, yellowball,blueball,orangeball,greenball,blackball,purpleball;
    private BallPath path;
    private Cue cueStick;
    //***************
    private Score firstPlayerScore,secondPlayerScore,currentPlayerScore;
    
    //***************
    private Pocket pocketOne,pocketTwo,pocketThree,pocketFour;
    private ArrayList<Pocket> pockets = new ArrayList<>();
    private ArrayList<Ball> balls = new ArrayList<>();
    private Color felt, border, floor, edge, mark,cueTip;
    private int mouseX,mouseY;
    private boolean mouseDown,showCue,turnBegin;

    /**
     * Default constructor draws all field values from BilliardConstants
     */

    public JupiTable() 
    {
    	
    	this.setLayout(null);
    	ppi			   = BilliardsConstants.PIXELS_PER_INCH;
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
        purpleball = new Ball(dimTable[0] *2/3,  dimTable[1]/2,   radius,mass, BilliardsConstants.PURPLE);
        blueball   = new Ball(dimTable[0] *15/24, dimTable[1] *7/15,radius,mass, Color.BLUE);
        orangeball = new Ball(dimTable[0] *17/24, dimTable[1] *7/15,radius,mass, Color.ORANGE);
        greenball = new Ball(dimTable[0] *15/24, dimTable[1] *8/15,radius,mass, Color.GREEN);
        blackball = new Ball(dimTable[0] *17/24, dimTable[1] *8/15,radius,mass, Color.BLACK);
        path = new BallPath(whiteball.getPosition(),0,30);
        //******
       
        firstPlayerScore = new Score((dimTable[0]* 2/7)*ppi,(dimTable[1]* 1/15)*ppi,"Player1",this,Color.red);
        secondPlayerScore = new Score((dimTable[0]* 5/7)*ppi,(dimTable[1]* 1/15)*ppi,"Player2",this,Color.blue);
        currentPlayerScore = firstPlayerScore;
       //*******
        //this.add(firstPlayerLabel);
        balls.add(whiteball);
        balls.add(redball);
        balls.add(yellowball);
        balls.add(purpleball);
        balls.add(blueball);
        balls.add(orangeball);
        balls.add(greenball);
        balls.add(blackball);
       
        //******************
        pocketOne = new Pocket(dimTable[0]/10 + 2.7,dimTable[1]/5 + 2.7);
        pocketTwo = new Pocket(dimTable[0]/10 + 2.7,dimTable[1]+floorWidth+cushionWidth+2.7);
        pocketThree = new Pocket(dimTable[0]+floorWidth+cushionWidth+2.7,dimTable[1]/5+2.7);
        pocketFour = new Pocket(dimTable[0]+floorWidth+cushionWidth+2.7,dimTable[1]+floorWidth+cushionWidth+2.7);
        
        pockets.add(pocketOne);
        pockets.add(pocketTwo);
        pockets.add(pocketThree);
        pockets.add(pocketFour);
        //******************
        
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

        /* draw top left corner pocket*/
        g.setColor(felt);
        int extraSpace = 1;
        int fx1[] = {(int)(floorWidth + borderWidth )*ppi,(int)(floorWidth + borderWidth+cushionWidth)*ppi,(int)(floorWidth + borderWidth+cushionWidth+(pocketOne.getDiameter()/1.5)+extraSpace)*ppi,(int)(floorWidth + borderWidth+cushionWidth)*ppi};
        int fy1[] = {(int)(floorWidth + borderWidth+cushionWidth+1)*ppi,(int)(floorWidth + borderWidth-extraSpace)*ppi,(int)(floorWidth + borderWidth+cushionWidth)*ppi,(int)(floorWidth + borderWidth+cushionWidth+(pocketOne.getDiameter()/1.5)+extraSpace)*ppi};
        g.fillPolygon(fx1,fy1,4);
        g.setColor(edge);
        g.drawLine(fx1[0], fy1[0], fx1[3], fy1[3]);
        g.drawLine(fx1[1], fy1[1], fx1[2], fy1[2]);
        
        /* draw bottom left corner pocket*/
        g.setColor(felt);
        int fx2[] = {(int)(floorWidth + borderWidth)*ppi,(int)(floorWidth + borderWidth+cushionWidth)*ppi,(int)(floorWidth + borderWidth+cushionWidth+(pocketOne.getDiameter()/1.5)+extraSpace)*ppi,(int)(floorWidth + borderWidth+cushionWidth + extraSpace)*ppi};
        int fy2[] = {(int)(floorWidth + borderWidth+cushionWidth+dimTable[1] - extraSpace)*ppi,(int)(floorWidth + borderWidth+dimTable[1]-extraSpace)*ppi,(int)(floorWidth + borderWidth+cushionWidth+dimTable[1])*ppi,(int)(floorWidth + borderWidth+cushionWidth+(pocketOne.getDiameter()/1.5)+dimTable[1])*ppi};
        g.fillPolygon(fx2,fy2,4);
        g.setColor(edge);
        g.drawLine(fx2[0], fy2[0], fx2[1], fy2[1]);
        g.drawLine(fx2[2], fy2[2], fx2[3], fy2[3]);
        
        /* draw top right corner pocket*/
        g.setColor(felt);
        int fx3[] = {(int)(floorWidth + borderWidth+dimTable[0]-extraSpace)*ppi,(int)(floorWidth + borderWidth+cushionWidth+dimTable[0]-extraSpace)*ppi,(int)(floorWidth + borderWidth+cushionWidth+(pocketOne.getDiameter()/1.5)+dimTable[0])*ppi,(int)(floorWidth + borderWidth+cushionWidth+dimTable[0])*ppi};
        int fy3[] = {(int)(floorWidth + borderWidth+cushionWidth)*ppi,(int)(floorWidth + borderWidth)*ppi,(int)(floorWidth + borderWidth+cushionWidth+extraSpace)*ppi,(int)(floorWidth + borderWidth+cushionWidth+(pocketOne.getDiameter()/1.5)+extraSpace)*ppi};
        g.fillPolygon(fx3,fy3,4);
        g.setColor(edge);
        g.drawLine(fx3[0], fy3[0], fx3[1], fy3[1]);
        g.drawLine(fx3[2], fy3[2], fx3[3], fy3[3]);
        
        /* draw bottom right corner pocket*/
        g.setColor(felt);
        int fx4[] = {(int)(floorWidth + borderWidth+dimTable[0]-extraSpace)*ppi,(int)(floorWidth + borderWidth+cushionWidth+dimTable[0])*ppi,(int)(floorWidth + borderWidth+cushionWidth+(pocketOne.getDiameter()/1.5)+dimTable[0])*ppi,(int)(floorWidth + borderWidth+cushionWidth+dimTable[0]-extraSpace)*ppi};
        int fy4[] = {(int)(floorWidth + borderWidth+cushionWidth+dimTable[1])*ppi,(int)(floorWidth + borderWidth+dimTable[1]-extraSpace)*ppi,(int)(floorWidth + borderWidth+cushionWidth+dimTable[1]-extraSpace)*ppi,(int)(floorWidth + borderWidth+cushionWidth+(pocketOne.getDiameter()/1.5)+dimTable[1])*ppi};
        g.fillPolygon(fx4,fy4,4);
        g.setColor(edge);
        g.drawLine(fx4[0], fy4[0], fx4[3], fy4[3]);
        g.drawLine(fx4[1], fy4[1], fx4[2], fy4[2]);
        
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
        	if(b.isVisible())
        	{
        		g.setColor(edge);
            g.drawOval((int)((floorWidth + borderWidth + cushionWidth + b.getPosition().x-radius) * ppi),
                    (int)((floorWidth + borderWidth + cushionWidth + b.getPosition().y-radius) * ppi),
                    (int)(2*radius*ppi), (int)(2*radius*ppi));

            // draw a filled circle for each ball 
            g.setColor(b.getColor());
            g.fillOval((int)((floorWidth + borderWidth + cushionWidth + b.getPosition().x-radius) * ppi),
                    (int)((floorWidth + borderWidth +cushionWidth + b.getPosition().y-radius) * ppi),
                    (int)(2*radius*ppi), (int)(2*radius*ppi));   
        	}
                   
        }
        /*
         * Pockets
         */
        //***************************************
      
         g.setColor(Color.BLACK);
 		 g.fillOval((int)(pocketOne.getPosition().x)*ppi,(int)( pocketOne.getPosition().y)*ppi,(int) pocketOne.getDiameter()*ppi,(int) pocketOne.getDiameter()*ppi);
 		 g.fillOval((int)(pocketTwo.getPosition().x)*ppi,(int)( pocketTwo.getPosition().y)*ppi,(int) pocketTwo.getDiameter()*ppi,(int) pocketTwo.getDiameter()*ppi);
 		 g.fillOval((int)(pocketThree.getPosition().x)*ppi,(int)( pocketThree.getPosition().y)*ppi,(int) pocketThree.getDiameter()*ppi,(int) pocketThree.getDiameter()*ppi);
 		 g.fillOval((int)(pocketFour.getPosition().x)*ppi,(int)( pocketFour.getPosition().y)*ppi,(int) pocketFour.getDiameter()*ppi,(int) pocketFour.getDiameter()*ppi);
 	
 		//***************************************
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
        	
        	
        	///Drawing Cue balls path
        	path.updateProp(whiteball.getPosition(), angle);
        	path.draw(g,cushionWidth,floorWidth,borderWidth);
        	///
             
        	AffineTransform transform = new AffineTransform();
        	AffineTransform oldTransform;
        	transform.rotate(angle,(floorWidth + borderWidth + cushionWidth + whiteball.getPosition().x )*ppi,(floorWidth + borderWidth + cushionWidth + whiteball.getPosition().y  )*ppi);
        	Graphics2D g2d = (Graphics2D)g;
        	oldTransform = g2d.getTransform();
        	
        	g2d.transform(transform);
			g2d.setColor(cueTip);
			g2d.fillArc((int)(cueStick.getX()*ppi) , 
				   (int)(cueStick.getY()*ppi),
				   (int)(BilliardsConstants.CUE_DIAMETER*ppi) ,(int)(BilliardsConstants.CUE_DIAMETER*ppi) ,0 ,180);
		
			g2d.setColor(new Color(0xDBB84D));
			g2d.fillPolygon(cuesX, cuesY, 4);
			g2d.setTransform(oldTransform);

        }//Cue
       
        
        
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
    	//Mouse down event to hit ball
		if(mouseDown && pullDistance < BilliardsConstants.MAX_PULL && showCue)
		{
			pullDistance += BilliardsConstants.PULL_RATE;
			cueStick.setPower(pullDistance);
			
		}else if(!mouseDown && pullDistance > 0)
		{
			pullDistance -= 3; 
		}
		if(pullDistance < 0)
		{
			Physics.hitBall(cueStick,whiteball);
			cueHittingSound.play();
			pullDistance = 0;			
		}
		//Check if balls are at rest
		if(whiteball.getVelocity().x ==0 && whiteball.getVelocity().y == 0
				&& yellowball.getVelocity().x == 0 && yellowball.getVelocity().y  == 0
				&& redball.getVelocity().x == 0 && redball.getVelocity().y == 0
				&& blueball.getVelocity().x == 0 && blueball.getVelocity().y == 0
				&& blackball.getVelocity().x == 0 && blackball.getVelocity().y == 0
				&& purpleball.getVelocity().x == 0 && purpleball.getVelocity().y == 0
				&& orangeball.getVelocity().x == 0 && orangeball.getVelocity().y == 0)
		{
			if(currentPlayerScore.isScoreChanged())
			{//check if game over
				if (currentPlayerScore.getScore() == endScore)
				{   
					gameWinnerSound.play();
					JOptionPane.showConfirmDialog(this, "Congratulations! You won the game!", "Game Over",
												  JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE); 
					gameExitSound.play();    				
					try {    					
						  Thread.sleep(1500L);// let sound finish
						}    					
					catch (Exception e) {} 
					
					System.exit(0);   	
				}//if endScore			
			}//if score changed
			
			if(!currentPlayerScore.isScoreChanged() && turnBegin)
			{
				if(currentPlayerScore == firstPlayerScore)
				{
					currentPlayerScore = secondPlayerScore;
					cueTip = Color.blue;
				}else{
					currentPlayerScore = firstPlayerScore;
					cueTip = Color.red;
				}
				turnBegin = false;
			}else{
				currentPlayerScore.setScoreChanged(false);
			}
			showCue = true;
			
		}else
		{
			showCue = false;
			turnBegin = true;
		}
		
    	//Update each ball
    	for(int i = 0; i < balls.size(); i++)
    	{
    		
    		
    		if (Physics.checkCusionCollision(balls.get(i), this))
    		{//had cusion collision
    			cushionCollisionSound.play();
    		}
    		
    		for(int j = i + 1; j < balls.size(); j++)
    		{
    			if (Physics.checkBallCollision(balls.get(i), balls.get(j)))
    			{//had a collision
    				ballCollisionSound.play();
    			}
    		}
    		balls.get(i).update();
    		
    		for(Pocket p:pockets)
    		{
    			
    			boolean ballIn = Physics.checkInPocket(balls.get(i),p);
    			if(ballIn)
    			{
    				inPocketSound.play();
    				currentPlayerScore.increment();
    				currentPlayerScore.setScoreChanged(true);
    				balls.remove(i);
    				break;
    			}
    		}
    	}
    	
    	repaint();
    }
}
