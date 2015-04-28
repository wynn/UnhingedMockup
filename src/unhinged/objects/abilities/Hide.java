package unhinged.objects.abilities;

import unhinged.objects.Player;

public class Hide extends Ability{

	int baseTime = 2;
	int currTime = 0;
	
	public Hide()
	{
		this.maxCooldown = 4;
		name = "Hide";
	}
	
	@Override
	public boolean useAbility(Player self, Player other) {
		if(!self.equals(other))
		{
			System.out.println("Cannot target another player with self-buffs.");
			return false;
		}
		
		System.out.println(self.getName() + " uses " + this.name + ", turning invisible until the end of his next turn.");
		
		this.currCooldown = this.maxCooldown;
		this.currTime = baseTime;
		self.isVisible = false;
		
		return true;
	}
	
	@Override
	public void updateCooldown(Player p)
	{
		if(currCooldown > 0) currCooldown--;
		
		if(currTime > 0)
		{
			currTime--;
			if(currTime == 0)
			{
				System.out.println(this.name + " has expired.");
				p.isVisible = true;
			}
			else
			{
				System.out.println(currTime + " turns left of " + this.name + ".");
			}
		}
	}
}
