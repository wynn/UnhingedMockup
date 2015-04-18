package unhinged.objects;

public class Portal extends Entity implements Interactable{

	private String symbol = "[]";
	private String name = "Portal";
	public Portal other = null;
	private int x, y;
	public int destinationX, destinationY;
	
	/**
	 * 
	 * @param x The x position of the portal.
	 * @param y The y position of the portal.
	 * @param destinationX The x position of the space that players will be teleported to when the portal is used.
	 * @param destinationY The x position of the space that players will be teleported to when the portal is used.
	 * destinationX and Y should be next to the portal, although there is no check for that
	 */
	public Portal(int x, int y, int destinationX, int destinationY)
	{
		this.x = x;
		this.y = y;
		this.destinationX = destinationX;
		this.destinationY = destinationY;
		this.setAllied(true);
		this.setSymbol(this.symbol); 
		this.setName(this.name); 
	}
	
	/**
	 * 
	 * @param other The portal this portal leads to.
	 * @param x The x position of the portal.
	 * @param y The y position of the portal.
	 * @param destinationX The x position of the space that players will be teleported to when the portal is used.
	 * @param destinationY The x position of the space that players will be teleported to when the portal is used.
	 * destinationX and Y should be next to the portal, although there is no check for that
	 */
	public Portal(Portal other, int x, int y, int destinationX, int destinationY)
	{
		this.x = x;
		this.y = y;
		this.destinationX = destinationX;
		this.destinationY = destinationY;
		this.setAllied(true);
		this.setSymbol(this.symbol); 
		this.setName(this.name); 
		this.other = other;
	}
	
	public void setDestinationPortal(Portal p)
	{
		this.other = p;
	}
	
	@Override
	public boolean interact(Actor a) {
		if(other != null)
		{
			System.out.println(a.getName() + " is using " + this.getName() + ".");	
			return true;
		}
		
		System.out.println("The portal leads nowhere...");	
		return false;
	}
}
