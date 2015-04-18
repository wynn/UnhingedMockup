package unhinged.game;

import java.util.ArrayList;

import unhinged.objects.DeathTrap;
import unhinged.objects.Player;
import unhinged.objects.Entity;
import unhinged.objects.Portal;
import unhinged.objects.Room;

/**
 *
 */
public class Board {
	
	public Entity[][] gameboard;
	public static int length = 20;
	public static int width = 20;
	public ArrayList<Player> players = new ArrayList<Player>();

	public Board(){
		gameboard = new Entity[length][width];
		initializeBoard();
	}
	
	public void initializeBoard(){
		
		int left = 0, top = 0, right = 5, bottom = 5;
		if(legalCoordinates(left, top, right, bottom))
		{
			Room r = new Room(this, left, top, right, bottom);
		}
		
		left = 10;
		top = 10;
		right = 15;
		bottom = 15;
		
		if(legalCoordinates(left, top, right, bottom))
		{
			Room r = new Room(this, left, top, right, bottom);
		}
		
		Portal po = new Portal(4, 4, 3, 4);
		
		
		Portal por = new Portal(po, 11, 11, 12, 11);
		po.setDestinationPortal(por);
		gameboard[11][11] = por;	
		gameboard[4][4] = po;
		
		//chessboard[3][3] = new Player(false);
		gameboard[4][8] = new DeathTrap();
		
		Player p = new Player();
		players.add(p);
		
		gameboard[3][4] = p;
		
		Player p1 = new Player();
		players.add(p1);
		
		gameboard[4][9] = p1;
		
		Player p2 = new Player();
		players.add(p2);
		
		gameboard[4][3] = p2;
	}
	
	//The game is over when all players have no health remaining (or are insane)
	public boolean gameIsOver()
	{
		for(Player p: players)
		{
			if(p.health > 0) return false;
		}
		
		return true;
	}
	
	public static boolean legalCoordinates(int oldX, int oldY, int newX, int newY){
		return oldX >= 0 && oldX < width && oldY >= 0 && oldY < length &&
				newX >= 0 && newX < width && newY >= 0 && newY < length;
	}
	
	public static boolean legalCoordinate(int x, int y){
		return x >= 0 && x < width && y >= 0 && y < length;
	}
	
	//1 and a are the lowest value char codes in the set of chars used for file/rank
	public static int fileToCoordinate(String fileAndRank){
		return Character.getNumericValue(fileAndRank.toLowerCase().charAt(0)) - Character.getNumericValue('a');
	}
		
	public static int rankToCoordinate(String fileAndRank){
		return width - Integer.parseInt(fileAndRank.toLowerCase().substring(1));
	}
	
	//checks if a path is unobstructed between two points
	public boolean checkPathIsEmpty(int oldX, int oldY, int newX, int newY){
		
		int deltaX = newX-oldX;
		int deltaY = newY-oldY;
		int tempX = oldX;
		int tempY = oldY;
		
		//if deltaX > 0, that means we moved to the right, otherwise we moved left 
		//thus we will want to move outward to the right
		int dx = deltaX > 0? 1: -1;
		
		//if deltaY > 0, that means we moved up, otherwise we moved down
		//thus we will want to move outward to the north
		int dy = deltaY > 0? 1: -1;
		
		//go from the old position to the new position, seeing if any pieces are in the way
		
		//horizontal movement
		if (deltaY == 0) {
			tempX = tempX + dx;
			for (int i = 0; i < Math.abs(deltaX)-1; i++) {
				if (gameboard[tempX][tempY] != null) {
					return false;
				}
				tempX = tempX + dx;
			} 
			return true;
		}
		
		//vertical movement
		if (deltaX == 0) {
			tempY = tempY + dy;
			for (int i = 0; i < Math.abs(deltaY)-1; i++) {
				if (gameboard[tempX][tempY] != null) {
					return false;
				}
				tempY = tempY + dy;
			}
			
			return true;
		}
		
		//diagonal movement
		if (deltaX != 0 && deltaY != 0) {
			tempX = tempX + dx;
			tempY = tempY + dy;
			for (int i = 0; i < Math.abs(deltaY)-1; i++) {
				if (gameboard[tempX][tempY] != null) {
					return false;
				}
				
				tempX = tempX + dx;
				tempY = tempY + dy;
			} 
		}
		
		return true;
	}
}
