package unhinged.objects.abilities;

import unhinged.game.Board;
import unhinged.objects.Monster;
import unhinged.objects.Player;

public class ConfuseRay extends AreaAbility{
	
	public ConfuseRay()
	{
		this.range = 99;
		this.maxCooldown = 10;
		this.radius = 1;
		name = "Confuse Ray";
	}

	@Override
	public boolean useAbility(Player self, Board board, int oldX, int oldY, int newX, int newY) {

		System.out.println(self.getName() + " uses " + this.name + "!");
		
		for(int x = newX - 1; x <= newX + 1; x++)
		{
			for(int y = newY - 1; y <= newY + 1; y++)
			{
				if(board.legalCoordinate(x, y) && board.gameboard[x][y] instanceof Player && !(board.gameboard[x][y] instanceof Monster))
				{
					System.out.println(((Player) board.gameboard[x][y]).getName() + " is hit by " + this.name + " and gains 10 insanity!");
					((Player) board.gameboard[x][y]).insanity += 10;
				}
			}
		}
		return true;
	}
}
