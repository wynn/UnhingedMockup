package unhinged.objects;

import unhinged.game.Board;

/**
 * An empty room with portals.
 */
public class PortalRoom extends Room{

	public Portal topLeft, bottomLeft, topRight, bottomRight = null;
	
	/**
	 * @param leftTop Create a portal in the top left corner of the room if true, 
	 * with the destination 1 tile south of the portal location.
	 * 
	 * @param leftBottom Create a portal in the bottom left corner of the room if true, 
	 * with the destination 1 tile north of the portal location.
	 * 
	 * @param rightTop Create a portal in the top right corner of the room if true, 
	 * with the destination 1 tile south of the portal location.
	 * 
	 * @param rightBottom Create a portal in the bottom right corner of the room if true, 
	 * with the destination 1 tile north of the portal location.
	 */
	public PortalRoom(Board board, int left, int top, int right, int bottom,
			boolean leftTop, boolean leftBottom, boolean rightTop, boolean rightBottom)
			throws IllegalArgumentException {
		super(board, left, top, right, bottom);
	
		if(leftTop){
			topLeft = new Portal(left+1, top+1, left+1, top+2);
			board.gameboard[left+1][top+1] = topLeft;
		}
		
		if(leftBottom){
			bottomLeft = new Portal(left+1, bottom-1, left+1, bottom-2);
			board.gameboard[left+1][bottom-1] = bottomLeft;
		}
		
		if(rightTop){
			topRight = new Portal(right-1, top+1, right-1, top+2);
			board.gameboard[right-1][top+1] = topRight;
		}
		
		if(rightBottom){
			bottomRight = new Portal(right-1, bottom-1, right-1, bottom-2);
			board.gameboard[right-1][bottom-1] = bottomRight;
		}
	}
}
