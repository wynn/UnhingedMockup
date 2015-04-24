package unhinged.game;

import java.util.ArrayList;

import unhinged.objects.*;

/**
 *
 */
public class Board {
	
	public Entity[][] gameboard;
	public static int length = 20;
	public static int width = 20;
	public ArrayList<Player> players = new ArrayList<Player>();
	Monster m;
	
	public Board(){
		gameboard = new Entity[length][width];
		initializeBoard();
	}
	
	public void initializeBoard(){
		PortalRoom portalRoom1 = null;
		
		
		try
		{
			int left = 0, top = 0, right = 5, bottom = 5;
			if(legalCoordinates(left, top, right, bottom))
			{
				portalRoom1 = new PortalRoom(this, left, top, right, bottom, true, true, true, true);
				//Room r = new Room(this, left, top, right, bottom);
			}
		
			left = 10;
			top = 10;
			right = 15;
			bottom = 15;
		
			if(legalCoordinates(left, top, right, bottom))
			{
				Room r = new Room(this, left, top, right, bottom);
			}
		}
		catch(Exception e)
		{
			System.out.println("Unable to initialize rooms! Walls would spawn on top of one or more entities, please restart the game and check your board initialization settings.");
			return;
		}
		
		
		
		Portal por = new Portal(portalRoom1.bottomRight, 11, 11, 12, 11);
		portalRoom1.bottomRight.setDestinationPortal(por);
		gameboard[11][11] = por;	
		
		//chessboard[3][3] = new Player(false);
		gameboard[4][8] = new DeathTrap();
		
		Medic p = new Medic();
		players.add(p);
		
		gameboard[3][4] = p;
		
		Player p1 = new Player();
		players.add(p1);
		
		gameboard[4][9] = p1;
		
		Fighter p2 = new Fighter();
		players.add(p2);
		
		gameboard[4][3] = p2;
		
		m = new Mindflayer();
		gameboard[13][11] = m;
		players.add(m);
	}
	
	//The game is over when all players have no health remaining (or are insane) or...
	public boolean gameIsOver()
	{
		for(Player p: players)
		{
			if(p.health > 0 && !(p instanceof Monster)) return false;
		}
		
		return true;
	}

	//when all monsters have been killed
	public boolean isVictory()
	{
		return m != null && m.currHealth == 0;
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
