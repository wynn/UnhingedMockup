package unhinged.objects;

public abstract class Entity {
	
	// firstMove, is true if piece has not been moved
	// executeMove will set this to false if the piece has been moved
	public boolean firstMove = true;
		
	private String symbol;
	private String name;
	private boolean allied = true;
	
	//all non-player and non-item entities will always be enemies
	//all players and items will be allied entities
	public boolean isAllied(){
		return this.allied;
	}
	
	public void setAllied(boolean isAllied){
		this.allied = isAllied;
	}
	
	public void setSymbol(String s)
	{
		this.symbol = s;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String drawPiece()
	{
		return this.symbol;
	}
}
