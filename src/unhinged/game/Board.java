package unhinged.game;

import java.util.ArrayList;
import java.util.Random;

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
		try
		{
			//room 1
			int left = 0, top = 7, right = 4, bottom = 11;
			if(legalCoordinates(left, top, right, bottom))
			{
				Room r = new Room(this, left, top, right, bottom);
			}
			
			//room 2
			left = 4; top = 5; right = 8; bottom = 13;
			if(legalCoordinates(left, top, right, bottom))
			{
				Room r = new Room(this, left, top, right, bottom);
			}
			
			//room 3
			left = 4;top = 1;right = 10;bottom = 5;
			if(legalCoordinates(left, top, right, bottom))
			{
				Room r = new Room(this, left, top, right, bottom);
			}
			
			//room 4
			left = 10;top = 0;right = 15;bottom = 5;
			if(legalCoordinates(left, top, right, bottom))
			{
				Room r = new Room(this, left, top, right, bottom);
			}
			
			//room 5
			left = 8;top = 5;right = 12;bottom = 9;
			if(legalCoordinates(left, top, right, bottom))
			{
				Room r = new Room(this, left, top, right, bottom);
			}
			
			//room 6
			left = 12;top = 6;right = 15;bottom = 12;
			if(legalCoordinates(left, top, right, bottom))
			{
				Room r = new Room(this, left, top, right, bottom);
			}
			
			//room 7
			left = 8;top = 9;right = 12;bottom = 13;
			if(legalCoordinates(left, top, right, bottom))
			{
				Room r = new Room(this, left, top, right, bottom);
			}
			
			//room 8
			left = 4;top = 13;right = 10;bottom = 18;
			if(legalCoordinates(left, top, right, bottom))
			{
				Room r = new Room(this, left, top, right, bottom);
			}
			
			//room 9
			left = 10;top = 13;right = 15;bottom = 18;
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
		
		//manually add doors...
		//left and right doors
		
		//door 1
		int y = 8, x = 4;
		gameboard[x][y] = new Door(x-1, y, x+1, y);
		
		//door 2
		y = 9; x = 4;
		gameboard[x][y] = new Door(x-1, y, x+1, y);
		
		//door 3
		y = 10; x = 4;
		gameboard[x][y] = new Door(x-1, y, x+1, y);
		
		//door 4
		y = 7; x = 8;
		gameboard[x][y] = new Door(x-1, y, x+1, y);
		
		//door 5
		y = 11; x = 8;
		gameboard[x][y] = new Door(x-1, y, x+1, y);
		
		//door 6
		y = 2; x = 10;
		gameboard[x][y] = new Door(x-1, y, x+1, y);
		
		//door 7
		y = 8; x = 12;
		gameboard[x][y] = new Door(x-1, y, x+1, y);
		
		//door 8
		y = 10; x = 12;
		gameboard[x][y] = new Door(x-1, y, x+1, y);
		
		//door 9
		y = 15; x = 10;
		gameboard[x][y] = new Door(x-1, y, x+1, y);
		
		//top and bottom doors
		
		//door 10
		y = 5; x = 6;
		gameboard[x][y] = new Door(x, y+1, x, y-1);
				
		//door 11
		y = 5; x = 9;
		gameboard[x][y] = new Door(x, y+1, x, y-1);
		
		//door 10
		y = 13; x = 6;
		gameboard[x][y] = new Door(x, y+1, x, y-1);
						
		//door 11
		y = 13; x = 9;
		gameboard[x][y] = new Door(x, y+1, x, y-1);
		
		//chessboard[3][3] = new Player(false);
		//gameboard[4][8] = new DeathTrap();
		
		Medic p = new Medic();
		players.add(p);
		
		gameboard[3][8] = p;
		
		Fighter p2 = new Fighter();
		players.add(p2);
		
		gameboard[3][9] = p2;
		
		m = new Mindflayer();
		
		Random r = new Random();
		int rnd = r.nextInt(3);
		if(rnd == 0){
			gameboard[14][9] = m;
		}
		if(rnd == 1){
			gameboard[14][3] = m;
		}
		if(rnd == 2){
			gameboard[14][15] = m;
		}
		
		players.add(m);
	}
	
	//The game is over when all players have no health remaining (or are insane) or...
	public boolean gameIsOver()
	{
		for(Player p: players)
		{
			if(p.maxHealth > 0 && !(p instanceof Monster)) return false;
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
