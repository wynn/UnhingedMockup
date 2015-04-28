package unhinged.objects;

import unhinged.objects.abilities.*;

public class Fighter extends Player{




	//CONSTRUCTOR
	public Fighter(){
		className = "Fighter";
		
		currHealth = maxHealth = 7;
		currAttack = baseAttack = 2;
		currSpeed = baseSpeed = 1;
		
		riftStones = 0;
		
		usedPortal = false;
		isVisible = true;
		
		this.abilities.add(new Steroids());
		this.abilities.add(new Sprint());
		
		this.setName("Player" + " (" + this.className + ") " + playerNumber);
	}
	
	
	/*/Class Abilities
	// ABILITY 1, cooldown is 'CD1'
	void burstOfStrength(){
		currAttack = currAttack + 1;
		CD1 = baseCD1; //actually 5 since cleanup runs at end of turn
		Timeout1 = baseTimeout1; //actually 3 since cleanup
	}
	
	void strengthReset(){
		currAttack = attack;
	}
	
	// ABILITY 2, cooldown is 'CD2'
	void burstOfSpeed(){
		currSpeed = currSpeed + 1;
		CD2 = baseCD2; //actually 5 since cleanup runs at end of turn
		Timeout2 = baseTimeout2; //actually 3 since cleanup
	}
	
	void speedReset(){
		currSpeed = speed;
	}*/
} // End Class Fighter
