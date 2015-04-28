package unhinged.objects;

import unhinged.objects.abilities.*;

public class Thief extends Player {

	public Thief() {

		className = "Thief";

		currHealth = maxHealth = 5;
		currAttack = baseAttack = 2;
		currSpeed = baseSpeed = 2;

		riftStones = 0;

		usedPortal = false;
		isVisible = true;
		
		this.abilities.add(new Sprint());
		this.abilities.add(new Hide());
		
		this.setName("Player" + " (" + this.className + ") " + playerNumber);
	}
}
