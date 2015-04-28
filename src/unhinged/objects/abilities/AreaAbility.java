package unhinged.objects.abilities;

import unhinged.game.Board;
import unhinged.objects.Player;

public abstract class AreaAbility extends Ability{

	int radius;
	
	@Override
	public boolean useAbility(Player self, Player other) {
		System.out.println("This method should not be being called. Please report this error immediately.");
		return false;
	}
	
	public abstract boolean useAbility(Player self, Board board, int oldX, int oldY, int newX, int newY);

}
