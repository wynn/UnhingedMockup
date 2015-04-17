package unhinged.objects;


public class Player extends Actor {
	
	public static int playerNumber = 1;
	private String symbol = "P";
	
	public Player(){
		this.setAllied(true);
		this.setName("Player " + playerNumber++);
		this.setSymbol(this.symbol + playerNumber); 
	}

	@Override
	public boolean canMove(int oldX, int oldY, int newX, int newY) {
		
		int deltaX = Math.abs(newX-oldX);
		int deltaY = Math.abs(newY-oldY);
		
		//Players can only move one space at a time
		if(deltaX > 1 || deltaY > 1)
		{
			return false;
		}
		
		return true;
	}
}
