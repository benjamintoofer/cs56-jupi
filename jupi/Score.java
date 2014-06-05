package jupi;

/**Keeps track of player's score and whether score has changed in last round*/

public class Score 
{
	private int score;
	private boolean updated;
	
	public Score()
	{
		score = 0;
		updated = false;		
	}
	
	public void increment()
	{		
			score++;	
			updated = true;
	}	
	
	public void setScoreChanged(boolean isChanged)
	{
		updated = isChanged;
	}
	
	boolean isScoreChanged()
	{
		return updated;
	}	
	
	public void setScore(int s)
	{
		score = s;
	}
	
	int getScore()
	{
		return score;
	}
	
}//Class Score
