package unhinged.objects;

public abstract class Monster extends Player{
	
	private String symbol = "M";
	
	public Monster()
	{
		this.allied = false;
		this.setSymbol(this.symbol + playerNumber); 
	}
}
