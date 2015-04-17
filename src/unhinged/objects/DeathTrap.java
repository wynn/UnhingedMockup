package unhinged.objects;

public class DeathTrap extends Trap{
	
	private String symbol = "  ";
	private String name = "Death Trap";
	
	public DeathTrap(){
		this.setAllied(false);
		this.setSymbol(this.symbol); 
		this.setName(this.name); 
	}
	
	@Override
	public boolean interact(Actor a) {
		System.out.println(a.getName() + " walked onto a " + this.getName() + " and has died!");
		a.health = 0;
		return true;
	}
}
