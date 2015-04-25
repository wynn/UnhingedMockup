package unhinged.objects.abilities;

import unhinged.objects.Player;

public abstract class Ability {
	
	public int maxCooldown;
	public int currCooldown = 0;
	public String name;
	public int range = 1;
	
	public boolean canUseAbility()
	{
		return currCooldown == 0;
	}
	
	public abstract boolean useAbility(Player self, Player other);
	
	public void updateCooldown(Player p)
	{
		if(currCooldown > 0) currCooldown--;
	}
}
