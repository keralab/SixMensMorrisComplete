package GameController;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.FileNotFoundException;

import javax.swing.JLabel;
import javax.swing.Timer;


import GameModel.AnalyzeBoard;
import GameModel.Board;
import GameModel.BoardStateError;
import GameModel.GameState;
import GameModel.Stone;
import GameModel.Team;
import View.ApplicationView;
import View.BoardSetupPanel;
import View.MainMenuPanel;
import View.StartGamePanel;
import View.FileBrowser;

/**
 * The Controller class contains the following functionality:
 * <ul>
 * <li>Connect the view elements to software interrupts if the user interacts
 * with them.</li>
 * <li>Connect the view to the appropriate model</li>
 * <li>Communicate between the view, model, and user</li>
 * </ul>
 */
public class Controller {
	// View
	private ApplicationView view;
	private MainMenuPanel mainMenu;
	private BoardSetupPanel boardSetup;
	private StartGamePanel startGame;
	private FileBrowser fileBrowser;
	// Model
	private Board boardModel;
	private Boolean redSelected = false, blueSelected = false;
	private String userDataFile;
	// Variables
	private int posOne;
	private int posTwo;
	private boolean choosingNode = false;
	private boolean milling = false;
	private boolean redWins = false;
	private boolean blueWins = false;

	/**
	 * This function constructs a controller instance that sets up the
	 * controller to control the view provided.
	 * 
	 * @param view
	 *            - An ApplicationView instance for the controller to control
	 * @see ApplicationView
	 * @see MainMenuPanel
	 * @see BoardSetupPanel
	 */
	public Controller(ApplicationView view) {
		this.view = view;
		
		this.mainMenu = view.getMainMenuPanel();
		this.boardSetup = view.getBoardSetUpPanel();
		this.startGame = view.getStartGamePanel();
		
		// Set-up the Application for the User
		connectMainMenuElements();
		connectBoardSetupModeElements();
		connectGlobalElements();
		connectStartGameElements();
		// Run the application
		runProgram();
	}

	/**
	 * runProgram invokes the view to be visible and pushes it to a thread
	 * separating view and model running conflicts.
	 */
	private void runProgram() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Redraw the stones being placed on the board, on their proper nodes.
	 * 
	 * @see Graphics2D
	 * @see Board
	 */
	private void redrawBoardSetup() {
		Graphics2D g = (Graphics2D) boardSetup.getGraphics();
		Stone[] s = boardModel.getStonesOnBoard();
		int nodes[][] = boardSetup.getNodeLocations();
		g.setStroke(new BasicStroke(20f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		for (int i = 0; i < s.length; i++) {
			if (s[i] != null) {
				if (s[i].getTeam().equals(Team.BLUE)) {
					g.setColor(Color.BLUE);
				} else {
					g.setColor(Color.RED);
				}
				g.drawLine(nodes[i][0] + 10, nodes[i][1] + 10, nodes[i][0] + 10, nodes[i][1] + 10);
			}
		}
	}
	
	/**
	 * Redraw the stones being placed on the board, on their proper nodes.
	 * 
	 * @see Graphics2D
	 * @see Board
	 */
	
	private void redrawBoardStartGame() {
		updateTeamLabel();
		Graphics2D g = (Graphics2D) startGame.getGraphics();
		Stone[] s = boardModel.getStonesOnBoard();
		int nodes[][] = startGame.getNodeLocations();
		g.setStroke(new BasicStroke(20f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		for (int i = 0; i < s.length; i++) {
			
			if (s[i] != null) {
				if (s[i].getTeam().equals(Team.BLUE)) {
					g.setColor(Color.BLUE);
				} else {
					g.setColor(Color.RED);
				}
				g.drawLine(nodes[i][0] + 10, nodes[i][1] + 10, nodes[i][0] + 10, nodes[i][1] + 10);
			}

		}
		
		
	}
	/**
	 * clears the screen to allow redrawing of stones
	 * 
	 * @see Board
	 */
	public void clearScreen(){
		startGame.redrawBG();
		
	}
	

	/**
	 * This class connects elements to view that are required in ever viewing
	 * panel.
	 * <ul>
	 * <li>Connects a 25Hz Timer to a redraw interrupt.</li>
	 * </ul>
	 */
	private void connectGlobalElements() {
		// Create a 25Hz timer and connect it to the view's JFrame,
		// ensuring all panel instances have this interrupt.
		Timer t = new Timer(1000 / 25, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (boardModel != null) {
					redrawBoardSetup();
					redrawBoardStartGame();
					updateTeamLabel();
					updateGameState();
					
				}
			}
		});
		// Start the timer
		t.start();
	}

	/**
	 * Connect main menu panel elements to the controller through adding event
	 * and action listeners.
	 * <p>
	 * <ul>
	 * The following controls are added here:
	 * <li>Link the main menu's "Start Game" JButton to a mouse listener waiting
	 * for clicks, this instantiates a new Board instance</li>
	 * <li>Link the main menu's "Board Set-up" JButton to a mouse listener
	 * waiting for clicks, this instantiates a new AnalyzeBoard instance for the
	 * model</li>
	 * <li>Link the main menu's "Options" JButton to a mouse listener waiting
	 * for clicks</li>
	 * <li>Link the main men's "Open Game" JButton to a mouse listener waiting for clicks </li>
	 * </ul>
	 */
	private void connectMainMenuElements() {

		// Connect "Start Game" JButton to Controller
		mainMenu.getButtonStartGame().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				view.changePanelToStartGame();
				boardModel = new Board();
			}
		});

		// Connect "Board Set-up" JButton to Controller
		mainMenu.getButtonBoardSetup().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				view.changePanelToBoardSetup();
				boardModel = new AnalyzeBoard();
			}
		});

		// Connect "Options" JButton to Controller
		mainMenu.getButtonOptions().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});
		
		// Connect "Open Game" JButton to Controller
		mainMenu.getButtonOpen().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// the open game button allows the user to open up a file they saved of their
				// progress and let them keep playing from where they left off
				boardModel =  new Board();
				fileBrowser = new FileBrowser();
				userDataFile = fileBrowser.FileBox("Open");			
				boardModel.Open(userDataFile);
				boardModel.place();
				view.changePanelToStartGame();
				
				
			
			}
		});
	}

	/**
	 * Connect board setup panel elements to the controller.
	 * <p>
	 * <ul>
	 * The following controls are added here:
	 * <li>"Main Menu" JButton is linked to the controller for user
	 * interactions, on click change view panel to Main Menu</li>
	 * <li>"Add Blue Disk" JButton linked to the controller for user
	 * interactions, on click select Blue Stones for Placement</li>
	 * <li>"Add Red Disk" JButton linked to the controller for user
	 * interactions, on click select Red Stones for Placement</li>
	 * <li>"Analyze" JButton linked to the controller for user interactions, on
	 * click Analyze current board status and report any errors to user through
	 * the console</li>
	 * <li>Board Node labels 0->15 are connected to the controller, on click set
	 * selected Stone team to NodeX where X = [0, 15]</li>
	 * </ul>
	 */
	private void connectBoardSetupModeElements() {
		// Debug tool, provides click location uncomment for use
		/*
		 * boardSetup.addMouseListener(new MouseAdapter() {
		 * 
		 * @Override public void mouseReleased(MouseEvent e) {
		 * System.out.println("x:" + e.getX() + "," + "y:" + e.getY()); } });
		 */
		// Set up Controls for main menu button
		boardSetup.getButtonMainMenu().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				view.changePanelToMainMenu();
				boardModel = null;
			}
		});

		// Set up Controls for add blue disk button
		boardSetup.getButtonBlueDisk().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				blueSelected = true;
				redSelected = false;
			}
		});

		// Set up Controls for add red disk button
		boardSetup.getButtonRedDisk().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				redSelected = true;
				blueSelected = false;
			}
		});

		// Set up Controls for analyze button
		boardSetup.getButtonAnalyze().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				redSelected = false;
				blueSelected = false;
				boardSetup.clearConsole();
				if (boardModel instanceof AnalyzeBoard) {
					for (BoardStateError bse : ((AnalyzeBoard) (boardModel)).analyzeBoard()) {
						boardSetup.addConsoleMessage(bse.getMsg());
					}
				}
			}
		});

		// =========Connect All Nodes========= //
		JLabel[] boardNodes = boardSetup.getBoardNodes();

		boardNodes[0].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (redSelected || blueSelected)
					boardModel.placeStone(new Stone((redSelected) ? Team.RED : Team.BLUE), 0);
			}
		});

		boardNodes[1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (redSelected || blueSelected)
					boardModel.placeStone(new Stone((redSelected) ? Team.RED : Team.BLUE), 1);
			}
		});

		boardNodes[2].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (redSelected || blueSelected)
					boardModel.placeStone(new Stone((redSelected) ? Team.RED : Team.BLUE), 2);
			}
		});

		boardNodes[3].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (redSelected || blueSelected)
					boardModel.placeStone(new Stone((redSelected) ? Team.RED : Team.BLUE), 3);
			}
		});

		boardNodes[4].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (redSelected || blueSelected)
					boardModel.placeStone(new Stone((redSelected) ? Team.RED : Team.BLUE), 4);
			}
		});

		boardNodes[5].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (redSelected || blueSelected)
					boardModel.placeStone(new Stone((redSelected) ? Team.RED : Team.BLUE), 5);
			}
		});

		boardNodes[6].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (redSelected || blueSelected)
					boardModel.placeStone(new Stone((redSelected) ? Team.RED : Team.BLUE), 6);
			}
		});

		boardNodes[7].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (redSelected || blueSelected)
					boardModel.placeStone(new Stone((redSelected) ? Team.RED : Team.BLUE), 7);
			}
		});

		boardNodes[8].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (redSelected || blueSelected)
					boardModel.placeStone(new Stone((redSelected) ? Team.RED : Team.BLUE), 8);
			}
		});

		boardNodes[9].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (redSelected || blueSelected)
					boardModel.placeStone(new Stone((redSelected) ? Team.RED : Team.BLUE), 9);
			}
		});

		boardNodes[10].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (redSelected || blueSelected)
					boardModel.placeStone(new Stone((redSelected) ? Team.RED : Team.BLUE), 10);
			}
		});

		boardNodes[11].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (redSelected || blueSelected)
					boardModel.placeStone(new Stone((redSelected) ? Team.RED : Team.BLUE), 11);
			}
		});

		boardNodes[12].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (redSelected || blueSelected)
					boardModel.placeStone(new Stone((redSelected) ? Team.RED : Team.BLUE), 12);
			}
		});

		boardNodes[13].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (redSelected || blueSelected)
					boardModel.placeStone(new Stone((redSelected) ? Team.RED : Team.BLUE), 13);
			}
		});

		boardNodes[14].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (redSelected || blueSelected)
					boardModel.placeStone(new Stone((redSelected) ? Team.RED : Team.BLUE), 14);
			}
		});

		boardNodes[15].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (redSelected || blueSelected)
					boardModel.placeStone(new Stone((redSelected) ? Team.RED : Team.BLUE), 15);
			}
		});
	}
	/**
	 * Updates the text of the team label based on the current team.
	 * 
	 */
	private void updateTeamLabel(){
		if(boardModel.currentTurn == Team.BLUE){
			startGame.setTurnText("BLUE", Color.BLUE);
		}
		else if(boardModel.currentTurn == Team.RED){
			startGame.setTurnText("RED", Color.RED);
		}
	}
	/**
	 * updates the text of the state display
	 * 
	 */
	private void updateGameState(){
		if(boardModel.getNumberBlueStones()<=2 && boardModel.isFirstPhase == false){
			startGame.setResultText("RED WINS!", Color.RED);
		}
		else if(boardModel.getNumberRedStones()<=2 && boardModel.isFirstPhase == false){
			startGame.setResultText("BLUE WINS!", Color.BLUE);
		}
		else if(boardModel.isFirstPhase == false && choosingNode == false && milling == false){
			startGame.setResultText("Pick a stone to move", Color.WHITE);
		}
		else if(boardModel.isFirstPhase == false && choosingNode == true && milling == false){
			startGame.setResultText("Choose an adjacent node to move to", Color.WHITE);
		}
		else if(boardModel.isFirstPhase == false && choosingNode == true && milling == true){
			startGame.setResultText("Choose an enemy stone to remove", Color.WHITE);
		}
		else if(boardModel.isFirstPhase == true  && milling == false){
			startGame.setResultText((12 - (boardModel.getNumberBlueStones() + boardModel.getNumberRedStones())) + " more stones to place...", Color.WHITE);
		}
		else if(boardModel.isFirstPhase == true  && milling == true){
			startGame.setResultText("Choose an enemy stone to remove", Color.WHITE);
		}
	}
	/**
	 * The logic behind the player's ability to click on nodes for various functions
	 * @param nodeNumber The number corresponding to the node which you want to enable selection for.
	 * @see Board
	 */
	private void nodeSelectionLogic(int nodeNumber){
		
		if (boardModel.isFirstPhase == true && milling == false){
			boardModel.placeStone(new Stone(boardModel.currentTurn), nodeNumber);
			
			if(boardModel.checkMills(nodeNumber) != GameState.NO_STATE){
				System.out.println("MILL CREATED");
				milling = true;
				
			}
			else{
				System.out.println("MILL NOT CREATED");
				
			}
		}
		else if (boardModel.isFirstPhase == true && milling == true && !boardModel.isSameTeam(nodeNumber) && boardModel.removalCheck(nodeNumber)){
			System.out.println(boardModel.currentTurn);
			System.out.println(boardModel.isSameTeam(nodeNumber));
			boardModel.removeStone(nodeNumber);
			System.out.println(boardModel.getNumberRedStones());
			System.out.println(boardModel.getNumberBlueStones());
			clearScreen();
			milling = false;
		}
		else if(boardModel.isFirstPhase == false && choosingNode == false && milling == false && boardModel.isSameTeam(nodeNumber)){
			System.out.println("node NUMBER chosen");
			choosingNode = true;
			posOne = nodeNumber;
		}
		else if(boardModel.isFirstPhase == false && choosingNode == true && milling == false){
			System.out.println("move to node NUMBER");
			choosingNode = false;
			posTwo = nodeNumber;
			boardModel.moveStone(posOne, posTwo);
			clearScreen();

			if(boardModel.checkMills(posTwo) != GameState.NO_STATE){
				System.out.println("MILL CREATED");
				milling = true;
				choosingNode = true;
			}
			else{
				System.out.println("MILL NOT CREATED");
				
			}
		}
		else if(boardModel.isFirstPhase == false && choosingNode == true && milling == true && !boardModel.isSameTeam(nodeNumber) && boardModel.removalCheck(nodeNumber)){
				System.out.println("not same team");
				choosingNode = false;
				milling = false;
				posTwo = nodeNumber;
				boardModel.removeStone(posTwo);
				System.out.println(boardModel.getNumberRedStones());
				System.out.println(boardModel.getNumberBlueStones());
				clearScreen();
			
			
		}
		
	}
	
		
	
	/**
	 * Connect Start Game Panel elements to the controller.
	 * <p>
	 * <ul>
	 * The following controls are added here:
	 * <li>"Main Menu" JButton is linked to the controller for user
	 * interactions, on click change view panel to Main Menu</li>
	 * <li>"Save Game" JButton is linked to the controller for user 
	 * interaction, click open a File Browser dialog box</li>
	 * <li>Board Node labels 0->15 are connected to the controller, on click set
	 * selected Stone team to NodeX where X = [0, 15]</li>
	 * </ul>
	 */
	private void connectStartGameElements() {
		redSelected = true;
		blueSelected = false;
		startGame.getButtonMainMenu().addMouseListener(new MouseAdapter() {		//condense all of this into one function
			@Override
			public void mouseReleased(MouseEvent e) {
				view.changePanelToMainMenu();
				boardModel.isFirstPhase = true;
				boardModel = null;
				
			}
		});
		
		// get save
		startGame.getButtonSave().addMouseListener(new MouseAdapter(){
			@Override
			public void mouseReleased(MouseEvent e){
				// the save button allows the user to save their progress in the game and
				// open it up at a later time
				view.changePanelToStartGame();
				fileBrowser = new FileBrowser();
				userDataFile = fileBrowser.FileBox("Save");
				boardModel.Save(userDataFile);
				
			}}); 
	
		
		
		
		
		// =========Connect All Nodes========= //
		JLabel[] boardNodes = startGame.getBoardNodes();

		boardNodes[0].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				nodeSelectionLogic(0);
			}

			
		});

		boardNodes[1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				nodeSelectionLogic(1);
			}
		});

		boardNodes[2].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				nodeSelectionLogic(2);
				
			}
		});

		boardNodes[3].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				nodeSelectionLogic(3);
			}
		});

		boardNodes[4].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				nodeSelectionLogic(4);
			}
		});

		boardNodes[5].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				nodeSelectionLogic(5);
			}
		});

		boardNodes[6].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				nodeSelectionLogic(6);
			}
		});

		boardNodes[7].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				nodeSelectionLogic(7);
			}
		});

		boardNodes[8].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				nodeSelectionLogic(8);
			}
		});

		boardNodes[9].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				nodeSelectionLogic(9);
			}
		});

		boardNodes[10].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				nodeSelectionLogic(10);
			}
		});

		boardNodes[11].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				nodeSelectionLogic(11);
			}
		});

		boardNodes[12].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				nodeSelectionLogic(12);
			}
		});

		boardNodes[13].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				nodeSelectionLogic(13);
			}
		});

		boardNodes[14].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				nodeSelectionLogic(14);
			}
		});

		boardNodes[15].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				nodeSelectionLogic(15);
			}
		});
	}

}
