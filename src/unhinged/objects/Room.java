package unhinged.objects;

import unhinged.game.Board;

/**
 * A generic empty room, with no objects inside it.
 */
public class Room {
	int left = 0;
	int top = 0;
	int right = 0;
	int bottom = 0;
	
	public Room(Board board, int left, int top, int right, int bottom) throws IllegalArgumentException
	{
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
				
		for(int x = left; x <= right; x++)
		{
			for(int y = top; y <= bottom; y++)
			{
				if(board.legalCoordinate(x, y) && board.gameboard[x][y] == null)
				{
					if((y == bottom || y == top) || (x == left || x == right))
					{
						board.gameboard[x][y] = new Wall();
					}	
				}
				else
				{
					throw new IllegalArgumentException();
				}
			}
		}
	}
}
