package unhinged.objects;

public class Mindflayer extends Monster{
	//CONSTRUCTOR
	public Mindflayer(){
		
		className = "Mindflayer";
		
		baseHealth = 11;
		baseAttack = 2;
		baseSpeed = 3;
		
		baseCD1 = 6;
		baseCD2 = 6;
		baseCD3 = 6;

		
		
		maxHealth = baseHealth;
		attack = baseAttack;
		speed = baseSpeed;
		
		currHealth = baseHealth;
		currAttack = baseAttack;
		currSpeed = baseSpeed;
	
		CD1 = 0;
		CD2 = 0;
		CD3 = 0;
		
		
		isVisible = true;
		this.setName("Player" + " (" + this.className + ") " + playerNumber);
	}
	
	//Class Abilities
	////////////////////////////////////////////////////////////
	// ABILITY 1, cooldown is 'CD1'
	void teleport(){
		
		//Involves the map
		//Allows transport to sqr within a range, neglecting 
		//un-passable terrain
		CD1 = baseCD1;
	}// END Heal
	
	////////////////////////////////////////////////////////////
	// ABILITY 2, cooldown is 'CD2'
	void confuseRay(){
	
		//Involves the map
		//At a range picks a point on map, in a 1-2 square radius
		//causes all to gain 15% insanity
		
		CD2 = baseCD2;
	}// END cure
	
	////////////////////////////////////////////////////////////
	// Ability 3, cooldown is 'CD3'
	void  mindcontrol(Player p){
		
		//Some how give the monster control of an adjacent players next turn
		// if this is too hard we can do something else
		
		CD3 = baseCD3;
	}//END Revive
	////////////////////////////////////////////////////////////

	@Override
	public void cleanUp(){ //RUNS AT END OF TURN, decrements CD's
		////////////////////////////////////////////////////////
		//Ability 1
		if(CD1>0){
			CD1 = CD1 - 1;
		}
		
		
		////////////////////////////////////////////////////////
		// Ability 2
		if(CD2>0){
			CD2 = CD2 - 1;
		}
		////////////////////////////////////////////////////////
		// Ability 3
		if(CD3>0){
			CD3 = CD3-1;
		}

		
	}// End CleanUp
}
