package unhinged.objects.abilities;

import unhinged.objects.Player;

public class Sprint extends Ability{
	
	int baseTime = 4;
	int currTime = 0;
	
	public Sprint()
	{
		this.maxCooldown = 6;
		name = "Sprint";
	}

	@Override
	public boolean useAbility(Player self, Player other) {
		if(!self.equals(other))
		{
			System.out.println("Cannot target another player with self-buffs.");
			return false;
		}
		
		System.out.println(self.getName() + " uses " + this.name + ", boosting his speed by 1 for the next 3 turns.");
		
		this.currCooldown = this.maxCooldown;
		this.currTime = baseTime;
		self.speed += 1;
		
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
				p.speed = p.baseSpeed;
			}
			else
			{
				System.out.println(currTime + " turns left of " + this.name + ".");
			}
		}
	}
}
