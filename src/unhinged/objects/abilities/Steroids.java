package unhinged.objects.abilities;

import unhinged.objects.Player;

public class Steroids extends Ability{
	
	public Steroids()
	{
		this.maxCooldown = 0;
		name = "Steroids";
	}

	@Override
	public boolean useAbility(Player self, Player other) {
		if(!self.equals(other))
		{
			System.out.println("Cannot target another player with self-buffs.");
			return false;
		}
		
		System.out.println(self.getName() + " uses " + this.name + ", boosting his attack by 1.");
		System.out.println(self.getName() + " feels rage build up inside him.");
		
		this.currCooldown = this.maxCooldown;
		self.insanity += 5;
		self.attack += 1;
		
		return true;
	}
}
