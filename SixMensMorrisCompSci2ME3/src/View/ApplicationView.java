package View;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;

/**
 * Instance of most Parent View.
 *
 */
public class ApplicationView{

	// ================Windows=============== //
	private JPanel panels;
	private JFrame frame;
	private CardLayout cardLayout = new CardLayout();
	// ================Panels================ //
	private MainMenuPanel mainMenuPanel;
	private BoardSetupPanel boardSetupPanel;
	private StartGamePanel startGamePanel;
	
	/**
	 * Create the application. Set Native Look and Feel
	 */
	public ApplicationView(){
		//Set the system look and feel to native widgets
	    try {
	    	UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
	    } catch (UnsupportedLookAndFeelException | ClassNotFoundException |
	    		InstantiationException |IllegalAccessException e) {
	    	System.err.println("Native Widgets Not Found!");
	    	e.printStackTrace();
	    }
		initialize();
	}



	/**
	 * Initialize the contents of the frame.
	 * @see MainMenuPanel
	 * @see BoardSetupPanel
	 */
	private void initialize() {
		// ================Initialize Frame=============== //
		frame = new JFrame();
		frame.getContentPane().setEnabled(false);
		frame.setBounds(100, 100, 920, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		// ================Initialize Panels=============== //
		this.mainMenuPanel = new MainMenuPanel();
		this.boardSetupPanel = new BoardSetupPanel();
		this.startGamePanel = new StartGamePanel();
		
		panels = new JPanel(cardLayout);
		panels.add(mainMenuPanel, "MainMenu");
		panels.add(boardSetupPanel, "BoardSetup");
		panels.add(startGamePanel, "StartGame");
		
		frame.add(panels, BorderLayout.CENTER);
		frame.setContentPane(panels);
		changePanelToMainMenu();
	}

	// =========BEHAVIOR and SETTERS========= //
	/**
	 * Change current panel to boardSetupPanel
	 */
	public void changePanelToBoardSetup(){
		cardLayout.show(panels, "BoardSetup");
	}
	
	/**
	 * Change current panel to startGamePanel
	 */
	public void changePanelToStartGame(){
		cardLayout.show(panels, "StartGame");
	}
	
	/**
	 * Change current panel to mainMenuPanel
	 */
	public void changePanelToMainMenu(){
		cardLayout.show(panels, "MainMenu");
	}
	
	// ================GETTERS=============== //
	/**
	 * Get the Parent JFrame
	 * @return parent JFrame
	 * @see JFrame
	 */
	public JFrame getFrame() {
		return frame;
	}
	
	/**
	 * Get the board setup JPanel
	 * @return board setup JPanel
	 * @see BoardSetupPanel
	 */
	public BoardSetupPanel getBoardSetUpPanel() {
		return boardSetupPanel;
	}

	/**
	 * Get the main menu JPanel
	 * @return main menu JPanel
	 * @see MainMenuPanel
	 */
	public MainMenuPanel getMainMenuPanel() {
		return mainMenuPanel;
	}
	
	/**
	 * Get the start game JPanel
	 * @return start game JPanel
	 * @see StartGamePanel
	 */
	public StartGamePanel getStartGamePanel() {
		return startGamePanel;
	}
}
