package unhinged.objects;

import unhinged.objects.abilities.*;

public class Mindflayer extends Monster{
	//CONSTRUCTOR
	public Mindflayer(){
		
		className = "Mindflayer";
		
		currHealth = maxHealth = 11;
		currAttack = baseAttack = 2;
		currSpeed = baseSpeed = 2;
		
		this.abilities.add(new Teleport());
		this.abilities.add(new ConfuseRay());
		
		isVisible = true;
		this.setName("Player" + " (" + this.className + ") " + playerNumber);
	}
	
	/*/Class Abilities
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

		
	}/*/ 
}
