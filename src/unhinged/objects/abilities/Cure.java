package unhinged.objects.abilities;

import unhinged.objects.Player;

public class Cure extends Ability{

	public Cure()
	{
		this.maxCooldown = 6;
		name = "Cure";
	}
	
	@Override
	public boolean useAbility(Player self, Player other) {
		
		System.out.println(self.getName() + " uses " + this.name + " on " + other.getName() + ".");
		System.out.println(other.getName() + "'s insanity has been reduced by 5.");
		
		this.currCooldown = this.maxCooldown;
		other.insanity -= 5;
		
		if(other.insanity < 0) other.insanity = 0;
		
		return true;
	}
}
