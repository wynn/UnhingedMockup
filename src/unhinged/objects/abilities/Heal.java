package unhinged.objects.abilities;

import unhinged.objects.Player;

public class Heal extends Ability{

	public Heal()
	{
		this.maxCooldown = 6;
		name = "Heal";
	}
	
	@Override
	public boolean useAbility(Player self, Player other) {
		
		//Dead players are removed from the gameboard, so this check isn't used
		/*if (other.currHealth == 0) {
			// Do nothing, a dead player cannot be healed, they must be revived
			System.out.println("Cannot use cure on dead players");
		}*/
		
		if(other.currHealth == other.maxHealth)
		{
			System.out.println("Cannot use cure on players at full health.");
			return false;
		}

		else {
			System.out.println(self.getName() + " uses " + this.name + " on " + other.getName() + ".");
			System.out.println(other.getName() + " recovers 3 health!");
			other.currHealth = other.currHealth + 3;

			if (other.currHealth > other.maxHealth) {
				other.currHealth = other.maxHealth;
			}
			this.currCooldown = this.maxCooldown;
		}
		return false;
	}
}
