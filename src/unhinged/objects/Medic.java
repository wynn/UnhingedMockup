package unhinged.objects;

public class Medic extends Player {

	// CONSTRUCTOR
	public Medic() {
		
		className = "Medic";
		
		baseHealth = 6;
		baseSanity = 0;

		baseAttack = 1;
		baseSpeed = 3;

		baseCD1 = 6;
		baseCD2 = 6;
		baseCD3 = 6;

		baseTimeout1 = 4;
		baseTimeout2 = 4;
		baseTimeout3 = 4;

		health = baseHealth;
		sanity = baseSanity;

		attack = baseAttack;
		speed = baseSpeed;

		currHealth = baseHealth;
		currSanity = baseSanity;

		currAttack = baseAttack;
		currSpeed = baseSpeed;

		CD1 = 0;
		CD2 = 0;
		CD3 = 0;

		Timeout1 = 0;
		Timeout2 = 0;
		Timeout3 = 0;

		riftStones = 0;

		usedPortal = false;
		isVisible = true;
		
		this.setName("Player" + " (" + this.className + ") " + playerNumber);
	}

	// Class Abilities
	// //////////////////////////////////////////////////////////
	// ABILITY 1, cooldown is 'CD1'
	void heal(Player p) {

		if (p.currHealth == 0) {

			// Do nothing, a dead player cannot be healed, they must
			// be revived

		}

		if (p.currHealth > 0) {

			p.currHealth = p.currHealth + 3;

			if (p.currHealth > p.health) {
				p.currHealth = p.health;
			}

		}

		CD1 = baseCD1;

		// NOTE: for now no matter what, the ability is put on CD after use

	}// END Heal

	// //////////////////////////////////////////////////////////
	// ABILITY 2, cooldown is 'CD2'
	void cure(Player p) {
		p.sanity = p.sanity - 5;

		if (p.sanity < 0) {
			p.sanity = 0;
		}

		CD2 = baseCD2;
	}// END cure

	// //////////////////////////////////////////////////////////
	// Ability 3, cooldown is 'CD3'
	void revive(Player p) {

		if (p.currHealth == 0) {

			p.currHealth = 1;
			// player revives with 1 HP

		}

		if (p.currHealth > 0) {

			// DO nothing, revive does not work on living players
		}

		CD3 = baseCD3;

	}// END Revive
		// //////////////////////////////////////////////////////////

	@Override
	public void cleanUp() { // RUNS AT END OF TURN, decrements CD's
		// //////////////////////////////////////////////////////
		// Ability 1
		if (CD1 > 0) {
			CD1 = CD1 - 1;
		}

		// //////////////////////////////////////////////////////
		// Ability 2
		if (CD2 > 0) {
			CD2 = CD2 - 1;
		}
		// //////////////////////////////////////////////////////
		// Ability 3
		if (CD3 > 0) {
			CD3 = CD3 - 1;
		}

	}// End CleanUp

}
