package unhinged.objects;

import unhinged.objects.abilities.*;

public class Fighter extends Player{




	//CONSTRUCTOR
	public Fighter(){
		className = "Fighter";
		
		baseHealth = 4;
		baseSanity = 0;

		baseAttack = 2;
		baseSpeed = 3;
		
		baseCD1 = 6;
		baseCD2 = 6;
		
		maxHealth = baseHealth;
		insanity = baseSanity;
		
		attack = baseAttack;
		speed = baseSpeed;
		
		currHealth = baseHealth;
		currSanity = baseSanity;

		currAttack = baseAttack;
		currSpeed = baseSpeed;
	
		CD1 = 0;
		CD2 = 0;
		
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
