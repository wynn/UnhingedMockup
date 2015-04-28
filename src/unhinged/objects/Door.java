package unhinged.objects;

public class Door extends Entity implements Interactable{
	private String symbol = "||";
	private String name = "Door";
	public int frontX, frontY, backX, backY;

	/**
	 * 
	 * @param frontX The x position of the tile in front of the door.
	 * @param frontY The y position of the tile in front of the door.
	 * @param backX The x position of the tile behind the door.
	 * @param backY The y position of the tile behind the door.
	 * frontX, frontY, backX, and backY should be within 1 tile of the door.
	 */
	public Door(int frontX, int frontY, int backX, int backY)
	{
		this.frontX = frontX;
		this.frontY = frontY;
		this.backX = backX;
		this.backY = backY;
		this.setAllied(true);
		this.setSymbol(this.symbol); 
		this.setName(this.name); 
	}
	
	@Override
	public boolean interact(Player a) {
		System.out.println(a.getName() + " is opening the " + this.getName() + ".");	
		return true;
	}
}
