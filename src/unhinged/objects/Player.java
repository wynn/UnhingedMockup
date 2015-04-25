package unhinged.objects;

import java.util.ArrayList;

import unhinged.objects.abilities.Ability;


public class Player extends Actor {
	
	public static int playerNumber = 0;
	private String symbol = "P";
	protected String className = "Player";
	
	public int baseHealth;
	public int baseSanity;
	public int baseAttack;
	public int baseSpeed;
	
	// Variable values used for Things in game
	public int maxHealth = 1;
	public int insanity;

	public int attack;
	public int speed;
	
	public int currHealth = 1;
	public int currSanity;

	public int currAttack;
	public int currSpeed;

	public boolean usedPortal;
	
	public boolean isVisible;
	
	//Ability Cooldowns
	public int baseCD1;
	public int baseCD2;
	public int baseCD3;

	public int CD1;
	public int CD2;
	public int CD3;

	//Character Inventory
	public String[] items = new String[5];
	public int riftStones;
	
	public ArrayList<Ability> abilities = new ArrayList<Ability>();
	
	public Player(){
		playerNumber++;
		this.setAllied(true);
		this.setName("Player" + " (" + this.className + ") " + playerNumber);
		this.setSymbol(this.symbol + playerNumber); 
	}

	@Override
	public boolean canMove(int oldX, int oldY, int newX, int newY) {
		
		int deltaX = Math.abs(newX-oldX);
		int deltaY = Math.abs(newY-oldY);
		
		//Players can only move one space at a time
		if(deltaX > currSpeed || deltaY > currSpeed)
		{
			return false;
		}
		
		return true;
	}
	
	//RUNS AT END OF TURN, decrements cooldowns
	public void cleanUp(){ 
		for(Ability a: abilities)
		{
			a.updateCooldown(this);
		}
	}
}
