package jupi;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class MainMenu extends JPanel implements ActionListener {
	
	private JButton playJupiButton,playCaromButton;
	private String chosenGame;
	private boolean isPicked;
	
	public MainMenu()
	{
		
		this.setLayout(null);
		
		isPicked = false;
		
		playJupiButton = new JButton("Play Jupi");
		playJupiButton.setBounds((int)BilliardsConstants.TOTAL_FLOOR_DIMENSION[0] *3/7 * BilliardsConstants.PIXELS_PER_INCH,
								 (int)BilliardsConstants.TOTAL_FLOOR_DIMENSION[1] *5/7 * BilliardsConstants.PIXELS_PER_INCH ,
									100, 70);
		playJupiButton.addActionListener(this);
		
		playCaromButton = new JButton("Play Carom");
		playCaromButton.setBounds((int)BilliardsConstants.TOTAL_FLOOR_DIMENSION[0] *4/7 * BilliardsConstants.PIXELS_PER_INCH,
				 				  (int)BilliardsConstants.TOTAL_FLOOR_DIMENSION[1] *5/7 * BilliardsConstants.PIXELS_PER_INCH ,
				 				    100, 70);
		playCaromButton.addActionListener(this);
		
		this.add(playJupiButton);
		this.add(playCaromButton);
	}
	public boolean getIsPicked()
	{
		return isPicked;
	}
	public String getGame()
	{
		return chosenGame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == playCaromButton)
		{
			chosenGame = "Carom";
			isPicked = true;
			
		}else
		{
			chosenGame = "Jupi";
			isPicked = true;
		}
		
	}
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		setBackground(BilliardsConstants.FELT);
	}

    
}