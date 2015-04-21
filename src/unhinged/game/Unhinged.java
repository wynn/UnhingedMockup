package unhinged.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import unhinged.objects.Actor;
import unhinged.objects.DeathTrap;
import unhinged.objects.Interactable;
import unhinged.objects.Player;
import unhinged.objects.Portal;
import unhinged.objects.Wall;
/**
 * todo
 *multiplayer support
 *traps
 *types of rooms
 *player types
 *stats
 *interactable
 */
public class Unhinged {
	
	public static Board board;
	static Player activePlayer = null;

	public static void main(String[] args) {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = null;
		
		board = new Board();
		drawBoard();
		
		while (true) {
			input = null;

			for (Player p : board.players) {
				input = null;

				if (board.gameIsOver()) {
					System.out.println("All players have been eliminated. Game over.");
					return;
				}

				//If a player is alive, go through their turn
				//Otherwise, skip their turn if they're dead
				if (p.health > 0) {
					activePlayer = p;

					while (true) {
						try {
							System.out.println();
							System.out.print(p.getName() + "'s move: ");
							input = br.readLine();
							if(parseInput(input)){
								System.out.println();
								drawBoard();
								break;
							}
						} catch (IOException e) {
							System.out.println("Invalid input. Try again.");
						}
					}
				}
			}
		}
	}
	
	public static void drawBoard(){
		
		String[][] result = new String[board.width][board.length];
		
		//fill board with empty squares
		for (int i = 0; i < board.width; i++){
			for (int j = 0; j < board.length; j++){
				result[i][j] = "   ";
			}
		}	
		
		//fill board with entities
		for (int y = 0; y < board.width; y++){
			for (int x = 0; x < board.length; x++){
				if (board.gameboard[x][y] != null){
					result[x][y] = board.gameboard[x][y].drawPiece() + " ";
				}
			}
		}
		
		//print out the board
		for (int y = 0; y < board.length; y++){
			for (int x = 0; x < board.width; x++){
				System.out.print(result[x][y]);
			}
			System.out.print(board.length-y);
			System.out.println();
		}
		
		System.out.print(" ");
		for(int x = 0; x < board.width; x++)
		{
			System.out.print(((char) (97 + x)) + "  ");
		}
		System.out.println(""); 
	}
	
	
	public static boolean parseInput(String input){
		String[] command = input.split(" ");
		
		if(command.length == 2)
		{			
			String currentLoc = command[0];
			String newLoc = command[1];
			
			if (currentLoc.length() >= 2 && newLoc.length() >= 2 ){	
				
				int oldX = Board.fileToCoordinate(currentLoc);
				int oldY = Board.rankToCoordinate(currentLoc);
				int newX = Board.fileToCoordinate(newLoc);
				int newY = Board.rankToCoordinate(newLoc);

				return executeMove(oldX, oldY, newX, newY);
			}
			
			else{
				System.out.println("Invalid input. Please try again.");
				return false;
			}
		}
		
		else{
			System.out.println("Invalid input. Please try again.");
			return false;
		}
	}
	
	//Return true if a move is valid
	//Return false if a move is invalid
	public static boolean executeMove(int oldX, int oldY, int newX, int newY){
		
		//Check if coordinates are on the board
		if (Board.legalCoordinates(oldX, oldY, newX, newY) == false){
			System.out.println("Illegal move, try again.");
			return false;
		}
		
		//Check for existence of a piece at the old location
		if (board.gameboard[oldX][oldY] == null){ 
			System.out.println("Illegal move, try again. No piece at first coordinate.");
			return false;
		}
		
		if (!(board.gameboard[oldX][oldY] instanceof Player)){ 
			System.out.println("Invalid move.");
			return false;
		}
		
		Player p = (Player)board.gameboard[oldX][oldY];
		
		if (board.gameboard[newX][newY] instanceof Wall){ 
			System.out.println("Can't move into walls.");
			return false;
		}
		
		if (!p.equals(activePlayer)) {
			System.out.println("Illegal move, try again. Player at coordinate is not the active player.");
			return false;
		}
		
		if (board.gameboard[newX][newY] != null) {
			
			if (board.gameboard[newX][newY] instanceof Interactable){ 
				Interactable i = (Interactable)board.gameboard[newX][newY];
				
				if(i.interact(p))
				{
					if(i instanceof DeathTrap)
					{
						//remove both the player and the death trap
						board.gameboard[oldX][oldY] = null;
						board.gameboard[newX][newY] = null;
						return true;
					}
					
					else if(i instanceof Portal)
					{
						//X, Y of coordinate of where the player ends up after using the portal
						int destX = ((Portal)i).other.destinationX; 
						int destY = ((Portal)i).other.destinationY;
						
						//Protip: Since the enemy monster will extend Player, a possible victory condition
						//would be to telefrag the monster for an instant kill.
						if(board.gameboard[destX][destY] instanceof Player)
						{
							((Player)board.gameboard[destX][destY]).health = 0;
							System.out.println(board.gameboard[destX][destY].getName() + " has been telefragged!");
						}
						
						board.gameboard[destX][destY] = board.gameboard[oldX][oldY];
						board.gameboard[oldX][oldY] = null;
						return true;
					}
				}
			}
			
			else
			{
				System.out.println("Space is not empty.");
				return false;
			}
		}
		
		if (!board.checkPathIsEmpty(oldX, oldY, newX, newY)) {
			System.out.println("Illegal move, try again. Other pieces are in the way.");
			return false;
		}
		 
		//generic logic for other moving
		else if (!((Actor)board.gameboard[oldX][oldY]).canMove(oldX, oldY, newX, newY)) {
			System.out.println("Illegal move, try again.");
			return false;
		}
		
		//update status of pieces
		board.gameboard[newX][newY] = board.gameboard[oldX][oldY];	
		board.gameboard[oldX][oldY] = null;
		
		return true;
	}
}
