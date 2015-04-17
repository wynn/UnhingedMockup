package unhinged.objects;

public abstract class Actor extends Entity{
	
	public int health = 10;
	
	public abstract boolean canMove(int oldX, int oldY, int newX, int newY);

}
