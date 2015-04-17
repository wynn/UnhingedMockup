package unhinged.objects;

public class Wall extends Entity{
	
	private String symbol = "W";
	
	public Wall()
	{
		this.setAllied(false);
		this.setSymbol(this.symbol + this.symbol); 
	}
}
