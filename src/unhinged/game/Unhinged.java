package unhinged.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import unhinged.objects.Actor;
import unhinged.objects.DeathTrap;
import unhinged.objects.Interactable;
import unhinged.objects.Player;
import unhinged.objects.Portal;
import unhinged.objects.Wall;

/**
 * TODO:
 * Player and Monster abilities
 * More traps
 * More rooms
 * More robust error checking
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

				if (board.isVictory()) {
					System.out.println("All monsters have been killed. The players have won.");
					return;
				}

				// If a player is alive, go through their turn
				// Otherwise, skip their turn if they're dead
				if (p.currHealth > 0) {
					activePlayer = p;

					while (true) {
						try {
							System.out.println();
							System.out.print(p.getName() + "'s move: ");
							input = br.readLine();
							if (parseInput(input)) {
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

	public static void drawBoard() {

		String[][] result = new String[board.width][board.length];

		// fill board with empty squares
		for (int i = 0; i < board.width; i++) {
			for (int j = 0; j < board.length; j++) {
				result[i][j] = "   ";
			}
		}

		// fill board with entities
		for (int y = 0; y < board.width; y++) {
			for (int x = 0; x < board.length; x++) {
				if (board.gameboard[x][y] != null) {
					result[x][y] = board.gameboard[x][y].drawPiece() + " ";
				}
			}
		}

		// print out the board
		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board.width; x++) {
				System.out.print(result[x][y]);
			}
			System.out.print(board.length - y);
			System.out.println();
		}

		System.out.print(" ");
		for (int x = 0; x < board.width; x++) {
			System.out.print(((char) (97 + x)) + "  ");
		}
		System.out.println("");
	}

	static ArrayList<String> validCommands = new ArrayList<String>() {
		{
			add("move");
			add("interact");
			add("attack");
		}
	};

	public static boolean parseInput(String input) {
		String[] commands = input.split(" ");
		String command = commands[0];

		if (!validCommands.contains(command)) {
			System.out.println("Invalid input. Please try again.");
			return false;
		}

		else if (commands.length == 3) {
			String currentLoc = commands[1];
			String newLoc = commands[2];
			int oldX = 0, oldY = 0, newX = 0, newY = 0;

			if (currentLoc.length() >= 2 && newLoc.length() >= 2) {

				oldX = Board.fileToCoordinate(currentLoc);
				oldY = Board.rankToCoordinate(currentLoc);
				newX = Board.fileToCoordinate(newLoc);
				newY = Board.rankToCoordinate(newLoc);
			}

			// Interact with some object on the board
			if (command.equals("interact")) {
				return interactMove(oldX, oldY, newX, newY);
			}

			// Move to some object on the board
			// Moving to an interactable object will cause the player to
			// interact with it...
			// Do you want to keep that functionality?
			else if (command.equals("move")) {
				return executeMove(oldX, oldY, newX, newY);
			}

			// Attack another player on the board
			else if (command.equals("attack")) {
				return attackMove(oldX, oldY, newX, newY);
			}

			else {
				System.out.println("Invalid input. Please try again.");
				return false;
			}
		}

		else {
			System.out.println("Invalid input. Please try again.");
			return false;
		}
	}

	// Return true if a move is valid
	// Return false if a move is invalid
	public static boolean executeMove(int oldX, int oldY, int newX, int newY) {

		// Check if coordinates are on the board
		if (Board.legalCoordinates(oldX, oldY, newX, newY) == false) {
			System.out.println("Illegal move, try again.");
			return false;
		}

		// Check for existence of a piece at the old location
		if (board.gameboard[oldX][oldY] == null) {
			System.out.println("Illegal move, try again. No piece at first coordinate.");
			return false;
		}

		if (!(board.gameboard[oldX][oldY] instanceof Player)) {
			System.out.println("Invalid move.");
			return false;
		}

		Player p = (Player) board.gameboard[oldX][oldY];

		if (board.gameboard[newX][newY] instanceof Wall) {
			System.out.println("Can't move into walls.");
			return false;
		}

		if (!p.equals(activePlayer)) {
			System.out.println("Illegal move, try again. Player at coordinate is not the active player.");
			return false;
		}

		if (board.gameboard[newX][newY] != null) {

			if (board.gameboard[newX][newY] instanceof Interactable) {
				Interactable i = (Interactable) board.gameboard[newX][newY];

				if (i.interact(p)) {
					if (i instanceof DeathTrap) {
						// remove both the player and the death trap
						board.gameboard[oldX][oldY] = null;
						board.gameboard[newX][newY] = null;
						return true;
					}

					else if (i instanceof Portal) {
						// X, Y of coordinate of where the player ends up after
						// using the portal
						int destX = ((Portal) i).other.destinationX;
						int destY = ((Portal) i).other.destinationY;

						// Protip: Since the enemy monster will extend Player, a
						// possible victory condition
						// would be to telefrag the monster for an instant kill.
						if (board.gameboard[destX][destY] instanceof Player) {
							((Player) board.gameboard[destX][destY]).currHealth = 0;
							System.out.println(board.gameboard[destX][destY].getName() + " has been telefragged!");
						}

						board.gameboard[destX][destY] = null;
						board.gameboard[destX][destY] = board.gameboard[oldX][oldY];
						board.gameboard[oldX][oldY] = null;
						return true;
					}
				}
			}

			else {
				System.out.println("Space is not empty.");
				return false;
			}
		}

		if (!board.checkPathIsEmpty(oldX, oldY, newX, newY)) {
			System.out.println("Illegal move, try again. Other pieces are in the way.");
			return false;
		}

		// generic logic for other moving
		else if (!((Actor) board.gameboard[oldX][oldY]).canMove(oldX, oldY, newX, newY)) {
			System.out.println("Illegal move, try again.");
			return false;
		}

		// update status of pieces
		board.gameboard[newX][newY] = board.gameboard[oldX][oldY];
		board.gameboard[oldX][oldY] = null;

		return true;
	}

	public static boolean interactMove(int oldX, int oldY, int newX, int newY) {
		// Check if coordinates are on the board
		if (Board.legalCoordinates(oldX, oldY, newX, newY) == false) {
			System.out.println("Illegal move, try again.");
			return false;
		}

		// Check for existence of a piece at the old location
		if (board.gameboard[oldX][oldY] == null) {
			System.out.println("Illegal move, try again. No piece at first coordinate.");
			return false;
		}

		if (!(board.gameboard[oldX][oldY] instanceof Player)) {
			System.out.println("Invalid move.");
			return false;
		}

		// Check for existence of a piece at the old location
		if (board.gameboard[newX][newY] == null) {
			System.out.println("There's nothing to interact with.");
			return false;
		}

		if (board.gameboard[newX][newY] instanceof Wall) {
			System.out.println("Why would you want to interact with a wall? Try again.");
			return false;
		}

		Player p = (Player) board.gameboard[oldX][oldY];

		if (board.gameboard[newX][newY] != null) {

			if (board.gameboard[newX][newY] instanceof Interactable) {
				Interactable i = (Interactable) board.gameboard[newX][newY];

				if (i.interact(p)) {
					if (i instanceof DeathTrap) {
						// remove both the player and the death trap
						board.gameboard[oldX][oldY] = null;
						board.gameboard[newX][newY] = null;
						return true;
					}

					else if (i instanceof Portal) {
						// X, Y of coordinate of where the player ends up after
						// using the portal
						int destX = ((Portal) i).other.destinationX;
						int destY = ((Portal) i).other.destinationY;

						// Protip: Since the enemy monster will extend Player, a
						// possible victory condition
						// would be to telefrag the monster for an instant kill.
						if (board.gameboard[destX][destY] instanceof Player) {
							((Player) board.gameboard[destX][destY]).currHealth = 0;
							System.out.println(board.gameboard[destX][destY].getName() + " has been telefragged!");
						}

						board.gameboard[destX][destY] = null;
						board.gameboard[destX][destY] = board.gameboard[oldX][oldY];
						board.gameboard[oldX][oldY] = null;
						return true;
					}
				}
			}

			else {
				System.out.println("Error, a check was not added.");
				return false;
			}
		}

		return true;
	}

	public static boolean attackMove(int oldX, int oldY, int newX, int newY) {
		// Check if coordinates are on the board
		if (Board.legalCoordinates(oldX, oldY, newX, newY) == false) {
			System.out.println("Illegal move, try again.");
			return false;
		}

		// Check for existence of a piece at the old location
		if (board.gameboard[oldX][oldY] == null) {
			System.out.println("Illegal move, try again. No piece at first coordinate.");
			return false;
		}

		if (!(board.gameboard[oldX][oldY] instanceof Player)) {
			System.out.println("Invalid move.");
			return false;
		}

		// Check for existence of a piece at the old location
		if (board.gameboard[newX][newY] == null) {
			System.out.println("There's nothing to attack.");
			return false;
		}

		if (board.gameboard[newX][newY] instanceof Wall) {
			System.out.println("Why would you want to attack a wall? Try again.");
			return false;
		}

		Player attacker = (Player) board.gameboard[oldX][oldY];
		if (board.gameboard[newX][newY] instanceof Player) {
			Player defender = ((Player) board.gameboard[newX][newY]);

			if (defender.allied == attacker.allied) {
				System.out.println("Can't attack allied players...");
				return false;
			} else {
				System.out.println(attacker.getName() + " attacks " + defender.getName() + ".");

				if (!defender.isVisible) {
					System.out.println(defender.getName() + " was just an illusion!");
					System.out.println(attacker.getName() + " misses " + defender.getName() + ".");
				} else {
					System.out.println(attacker.getName() + " hits " + defender.getName() + "for " + attacker.attack
							+ " damage!");

					defender.currHealth -= attacker.attack;

					if (defender.currHealth < 0) {
						System.out.println(defender.getName() + " has been killed by " + attacker.getName() + "!");
						board.gameboard[newX][newY] = null;
					}
				}
			}
		}

		else {
			System.out.println("Error, a check was not added.");
			return false;
		}

		return true;
	}
}
