package unhinged.objects.abilities;

import unhinged.game.Board;
import unhinged.objects.Player;

public class Teleport extends AreaAbility{
	
	public Teleport()
	{
		this.range = 99;
		this.maxCooldown = 5;
		name = "Teleport";
	}

	@Override
	public boolean useAbility(Player self, Board board, int oldX, int oldY, int newX, int newY) {
		if(board.gameboard[newX][newY] != null)
		{
			System.out.println("Cannot teleport there.");
			return false;
		}
			
		else
		{
			System.out.println(self.getName() + " uses " + this.name + "!");
			board.gameboard[newX][newY] = board.gameboard[oldX][oldY];
			board.gameboard[oldX][oldY] = null;
			this.currCooldown = this.maxCooldown;
			return true;
		}
	}
}
