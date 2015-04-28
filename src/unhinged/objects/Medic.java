package unhinged.objects;

import unhinged.objects.abilities.*;

public class Medic extends Player {

	// CONSTRUCTOR
	public Medic() {
		
		className = "Medic";
		
		currHealth = maxHealth = 4;
		currAttack = baseAttack = 1;
		currSpeed = baseSpeed = 1;

		riftStones = 0;

		usedPortal = false;
		isVisible = true;
		
		this.abilities.add(new Cure());
		this.abilities.add(new Heal());
		
		this.setName("Player" + " (" + this.className + ") " + playerNumber);
	}

	/*// Class Abilities
	// //////////////////////////////////////////////////////////
	// ABILITY 1, cooldown is 'CD1'
	void heal(Player p) {

		if (p.currHealth == 0) {

			// Do nothing, a dead player cannot be healed, they must
			// be revived

		}

		if (p.currHealth > 0) {

			p.currHealth = p.currHealth + 3;

			if (p.currHealth > p.maxHealth) {
				p.currHealth = p.maxHealth;
			}

		}

		CD1 = baseCD1;

		// NOTE: for now no matter what, the ability is put on CD after use

	}// END Heal

	// //////////////////////////////////////////////////////////
	// ABILITY 2, cooldown is 'CD2'
	void cure(Player p) {
		p.insanity = p.insanity - 5;

		if (p.insanity < 0) {
			p.insanity = 0;
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

	}*/
}
