package GameModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Model of a Game Board. This class contains all game logic.
 *
 */
public class Board {

	private Graph board;
	public Stone[] stonesOnBoard;
	private final int number_of_locations;
	public Team currentTurn;
	public int turnCount = 0;
	public boolean isFirstPhase = true;

	private int numberRedStones = 0, numberBlueStones = 0;

	/**
	 * Constructs a board instance by:
	 * <li>Building a graph to represent the board and how nodes are connected
	 * </li>
	 * <li>Calculating the start Player</li>
	 * 
	 * @see Graph
	 */
	public Board() {
		number_of_locations = 16;
		stonesOnBoard = new Stone[number_of_locations];
		board = new Graph(number_of_locations);

		// Connect all edges to form a 6 mens morris board
		board.addEdge(0, 1, Direction.HORIZONTAL);
		board.addEdge(0, 6, Direction.VERTICLE);
		board.addEdge(1, 2, Direction.HORIZONTAL);
		board.addEdge(1, 4, Direction.VERTICLE);
		board.addEdge(2, 9, Direction.VERTICLE);

		board.addEdge(3, 4, Direction.HORIZONTAL);
		board.addEdge(3, 7, Direction.VERTICLE);
		board.addEdge(4, 5, Direction.HORIZONTAL);
		board.addEdge(5, 8, Direction.VERTICLE);

		board.addEdge(6, 7, Direction.HORIZONTAL);
		board.addEdge(6, 13, Direction.VERTICLE);
		board.addEdge(7, 10, Direction.VERTICLE);
		board.addEdge(8, 12, Direction.VERTICLE);
		board.addEdge(8, 9, Direction.HORIZONTAL);
		board.addEdge(9, 15, Direction.VERTICLE);

		board.addEdge(10, 11, Direction.HORIZONTAL);
		board.addEdge(11, 12, Direction.HORIZONTAL);
		board.addEdge(11, 14, Direction.VERTICLE);

		board.addEdge(13, 14, Direction.HORIZONTAL);
		board.addEdge(14, 15, Direction.HORIZONTAL);

		// Figure out who begins the game
		calculateStartPlayer();
	}

	/**
	 * Randomly assign the starting player.
	 */
	private void calculateStartPlayer() {
		Random rand = new Random();
		if (rand.nextInt() % 2 == 0)
			this.currentTurn = Team.BLUE;
		else
			this.currentTurn = Team.RED;
	}

	/**
	 * Place a Stone at a given position
	 * 
	 * @param s
	 *            - Stone instance to be placed
	 * @param position
	 *            - Position Stone is being placed with respect to the graph
	 * @return - true: Stone successfully placed - false: Stone cannot be placed
	 *         at given location
	 * @see Stone
	 */
	public boolean placeStone(Stone s, int position) {
		if (stonesOnBoard[position] != null) {
			return false;
		} else {
			stonesOnBoard[position] = s;
			if (currentTurn == Team.RED) {
				numberRedStones++;
			} else if (currentTurn == Team.BLUE) {
				numberBlueStones++;
			}
			if (checkMills(position) == GameState.NO_STATE) {
				switchTurn();
			}
			return true;

		}
	}
	/**
	 * Returns whether or not the stone at the chosen position is eligible for removal
	 * @param position the position of the stone to be tested
	 * @return true: the chosen stone is eligible to be removed - false: the chosen stone is not eligible to be removed
	 */
	public boolean removalCheck(int position){
		int count = 0;
		boolean onlyMills = false;
		for (int i = 0; i < number_of_locations; i++){
			if(stonesOnBoard[i]!= null && checkMills(i) != GameState.NO_STATE){
				count++;
			}
		}
		if(count == (numberBlueStones+numberRedStones)){
			onlyMills = true;
		}
		if(checkMills(position)!= GameState.NO_STATE && onlyMills == false){
			return false;
		}
		else{
			return true;
		}
		
	}
	/**
	 * remove a stone from the board
	 * @param position the stone to remove
	 */
	public void removeStone(int position) {
		if(stonesOnBoard[position].getTeam() == Team.RED){
			numberRedStones--;
		}
		else{
			numberBlueStones--;
		}
		stonesOnBoard[position] = null;
		switchTurn();
	}
	/**
	 * Switches the turn to the opposite team
	 */
	private void switchTurn() {
		turnCount++;
		currentTurn = (currentTurn == Team.BLUE) ? Team.RED : Team.BLUE;

		if (turnCount >= 12) {
			isFirstPhase = false;

		}
	}
	/**
	 * Is the stone at the selected position part of the same team as the current team?
	 * @param position the position of the stone to check
	 * @return true: the stone is the same team - false: the stone is not part of the same team
	 */
	public boolean isSameTeam(int position){

			if (stonesOnBoard[position] != null && currentTurn == stonesOnBoard[position].getTeam()) {
				return true;
			} else {
				return false;
			}
	}

	/**
	 * Move a stone at position currentPosition to newPosition
	 * 
	 * @param currentPosition
	 *            location on graph where stone resides
	 * @param newPosition
	 *            where player wants stone to be
	 * @return -true stone can move to new position and was moved -false stone
	 *         cannot move to new position stone was not moved
	 */
	public boolean moveStone(int currentPosition, int newPosition) {
		boolean isAdjacent = false;
		Stone stoneAtCurrent = stonesOnBoard[currentPosition];
		Stone stoneAtNew = stonesOnBoard[newPosition];
		if (stoneAtCurrent != null && stoneAtCurrent.getTeam().equals(currentTurn)) {

			// Make sure that current and new are adjacent
			for (VertexAdjacency vertex : board.adj(currentPosition)) {
				if (vertex.getV() == newPosition) {
					isAdjacent = true;
					break;
				}
			}

			// Invalid Move
			if (!isAdjacent || stoneAtCurrent == null || stoneAtNew != null) {
				return false;
			}
			// Valid Move
			else {
				stonesOnBoard[newPosition] = stoneAtCurrent;
				stonesOnBoard[currentPosition] = null;
				// only switch turns if a mill is not created
				// turn is switched in removeStone method otherwise
				if (checkMills(newPosition) == GameState.NO_STATE) {
					switchTurn();
				}

				return true;
			}
		}

		return false; // Null node or opposing teams turn
	}

	// This method is being left for refference later, will not be used
	/*
	 * public GameState checkMills(){ for(int i = 0; i < number_of_locations;
	 * i++){ Team teamAtI = stonesOnBoard[i].getTeam(); for(VertexAdjacency
	 * adgToI : board.adj(i)){ if(stonesOnBoard[adgToI.getV()].getTeam() ==
	 * teamAtI){ Direction directionOfConnection = adgToI.getD();
	 * for(VertexAdjacency v : board.adj(adgToI.getV())){ if(v.getV() != i &&
	 * v.getD() == directionOfConnection &&
	 * stonesOnBoard[v.getV()].getTeam().equals(teamAtI)){
	 * if(teamAtI.equals(Team.BLUE)) return GameState.PLAYER_BLUE_MILLS; else
	 * return GameState.PLAYER_RED_MILLS; } } } } } return GameState.NO_STATE; }
	 */

	/**
	 * Checks the board based on a stone being moved, seeing if this stone
	 * movement placed the player in a state of mills.
	 * 
	 * @param position
	 *            -position of movement event
	 * @return -GameState.PLAYER_BLUE_MILLS The blue player has moved a piece
	 *         granting them mills -GameState.PLAYER_RED_MILLS The red player
	 *         has moved a piece granting them mills -GameState.NO_STATE Player
	 *         has not moved into a state of mills
	 */
	public GameState checkMills(int position) {
		if (stonesOnBoard[position] != null) {
			Team teamAtI = stonesOnBoard[position].getTeam();
			List<Integer> horizontalToPosition = new ArrayList<>();
			List<Integer> verticleToPosition = new ArrayList<>();

			boolean isInMills = false;
			// Find what is Vertical and Horizontal to Position, add these as
			// entries to their
			// respective lists iff these positions contain a stone of the same
			// team.

			horizontalToPosition.add(position);
			verticleToPosition.add(position);

			for (VertexAdjacency node : board.adj(position)) {
				int vertex = node.getV();
				if (stonesOnBoard[vertex] != null && stonesOnBoard[vertex].getTeam().equals(teamAtI)) {
					if (node.getD().equals(Direction.HORIZONTAL))
						horizontalToPosition.add(vertex);
					else
						verticleToPosition.add(vertex);
				}
			}

			// For what is horizontal to position find all elements horizontal
			// to it and append them to the horizontal list iff they share a
			// common team

			for (Integer adjacentToPosition : horizontalToPosition) {
				for (VertexAdjacency node : board.adj(adjacentToPosition)) {

					if (horizontalToPosition.contains(node.getV()))
						continue;
					int vertex = node.getV();
					if (stonesOnBoard[vertex] != null && stonesOnBoard[vertex].getTeam().equals(teamAtI)) {

						if (node.getD().equals(Direction.HORIZONTAL)) {
							isInMills = true;
							break;
						}
					}
				}
			}

			// For what is verticle to position find all elements verticle to it
			// and append them to the verticle list iff they share a common team

			for (Integer adjacentToPosition : verticleToPosition) {
				for (VertexAdjacency node : board.adj(adjacentToPosition)) {

					if (verticleToPosition.contains(node.getV()))
						continue;
					
					int vertex = node.getV();
					if (stonesOnBoard[vertex] != null && stonesOnBoard[vertex].getTeam().equals(teamAtI)) {

						if (node.getD().equals(Direction.VERTICLE)) {
							isInMills = true;
							break;
						}
					}
				}
			}

			// The three criterion for mills to occur are:
			//		verticleToPosition or horizontalToPosition are 3 in length
			//		or a third element is found forcing mills from the above loops
			if (isInMills || verticleToPosition.size() == 3 || horizontalToPosition.size() == 3) {
				return (teamAtI.equals(Team.BLUE)) ? GameState.PLAYER_BLUE_MILLS : GameState.PLAYER_RED_MILLS;
			}
		}
		return GameState.NO_STATE;
	}

	/**
	 * Check if the Game has been completed
	 * 
	 * @return -GameState.PLAYER_RED_WIN means the red player has victory
	 *         -GameState.PLAYER_BLUE_WIN means the blue player has victory
	 *         -GameState.GAME_DRAW means the game has ended in a draw
	 *         -GameState.NO_STATE means the game has not completed
	 * @see GameState
	 */
	public GameState checkWin() {
		// If one player can not move any piece i.e. all pieces are cornered
		// lose
		// If one player has 2 pieces remaining he looses
		// TODO generate behavior
		return null;
	}

	/**
	 * Get and return the most dominant game state out of
	 * {@link #checkMills(int)} and {@link #checkWin()}
	 * 
	 * @param position
	 *            - Position on graph an event occurred at
	 * @return - Most dominant GameState of {@link #checkMills(int)} and
	 *         {@link #checkWin()}
	 */
	public GameState getBoardState(int position) {
		GameState mills = this.checkMills(position);
		GameState gameVictory = this.checkWin();

		if (!gameVictory.equals(GameState.NO_STATE))
			return gameVictory;
		else if (!mills.equals(GameState.NO_STATE))
			return mills;

		return GameState.NO_STATE;
	}

	/**
	 * Get the number of red Stones on the board
	 * 
	 * @return number of red Stones on the board
	 */
	public int getNumberRedStones() {
		return numberRedStones;
	}

	/**
	 * Set the number of red Stones on the board
	 */
	public void setNumberRedStones(int numberRedStones) {
		this.numberRedStones = numberRedStones;
	}

	/**
	 * Get the number of blue Stones on the board
	 * 
	 * @return number of blue Stones on the board
	 */
	public int getNumberBlueStones() {
		return numberBlueStones;
	}

	/**
	 * Set the number of blue Stones on the board
	 */
	public void setNumberBlueStones(int numberBlueStones) {
		this.numberBlueStones = numberBlueStones;
	}

	/**
	 * Return an array of stones representing board state
	 * 
	 * @return Stone[] where array[i] = Stone at Vertex i on graph, array size
	 *         of 16
	 */
	public Stone[] getStonesOnBoard() {
		return this.stonesOnBoard;
	}

	public int getNumberofLocations() {
		return this.number_of_locations;
	}

	/**
	 * Saves the users progress of the game so they can open it later on
	 * 
	 * @param userDataFile - the location at which the user would like to save their progress
	 */
	public void Save(String userDataFile) {
		try {
			PrintWriter pr = new PrintWriter(new FileWriter(userDataFile));	
			// write the appropriate values into the file that will be required when the user
			// opens it up again
			pr.println(Attributes.CURRENT_TURN.getAtt()+":"+currentTurn.getMsg());
			
			for (int i = 0; i < stonesOnBoard.length; i++) {
					if(stonesOnBoard[i]==null){
						pr.print(Attributes.STONES_BOARD.getAtt()+":"+null);	
					}
					else{
						pr.print(Attributes.STONES_BOARD.getAtt()+":"+stonesOnBoard[i].getTeam().getMsg());
					}
					if(i!=stonesOnBoard.length-1){
						pr.print(",");
					}else{
						pr.println();
				}
			}
			pr.println(Attributes.TURN_COUNT.getAtt()+":"+ turnCount);
			pr.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	
	
	
/**
 * Reads the file the user wants to open, the file is their previously saved game
 * 
 * @param userDataFile - the location of the file the user wants to open 
 * 
 * 
 */
	public void Open(String userDataFile) { 
		String[] team = new String[getNumberofLocations()];
		String line ;	
		try {			
			BufferedReader read = new BufferedReader(new FileReader(userDataFile));
			line = read.readLine();
			// keeps reading as long as there are lines to read
			// reads and places the values in the appropriate variables
			// these variables will be used to place the stones onto the board
			while(line!=null){				
				if(line.split(":")[0].compareTo(Attributes.CURRENT_TURN.getAtt()) == 0){
					currentTurn = Team.getTeam(line.split(":")[1]);
				}
				else if(line.split(":")[0].compareTo(Attributes.STONES_BOARD.getAtt()) == 0  ){
					team = line.split(",");
					for(int i = 0; i < team.length; i++){						
						if(team[i].split(":")[1].compareTo("null") == 0){
							continue;							
						}
						else{
							stonesOnBoard[i] = new Stone(Team.getTeam(team[i].split(":")[1]));							
						}							
					}
				}
				else if(line.split(":")[0].compareTo(Attributes.TURN_COUNT.getAtt()) == 0){
					turnCount = Integer.parseInt(line.split(":")[1]);					
				}
				line=read.readLine();
			}
			read.close();

		} catch (IOException e) {
			System.out.println("File not found");

		}	
	}
/**
 * Places the stones on the board from the opened file.
 */
	public void place() {
		int blue = 0;
		int red = 0;
		// loops through the stonesOnBoard array and places the stones on the board
		// increments the number of stones that were placed as well
		for (int i = 0; i < stonesOnBoard.length; i++) {
			if (stonesOnBoard[i] == null) {
				continue;
			} else {
				placeStone(stonesOnBoard[i], i);
				if (stonesOnBoard[i].getTeam() == Team.BLUE) {
					blue++;
				} 
				else {
					red++;
				}
			}
		}
		// sets the new number of blue&red stones 
		setNumberBlueStones(blue);
		setNumberRedStones(red);
		
		// the firstPhase is false if the turnCount >=12
		if(turnCount >= 12){
			isFirstPhase = false;
		}
		

	}

}
