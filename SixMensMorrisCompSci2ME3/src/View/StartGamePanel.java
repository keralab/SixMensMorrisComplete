package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * Instance of Start Game Panel for View
 */
public class StartGamePanel extends JPanel {
	// Pixel locations of board nodes [i][j] i = node number j = 0 means x,
	// 1 means y
	private int[][] nodeLocations = { { 210, 0 }, { 450, 0 }, { 690, 0 }, { 330, 120 }, { 450, 120 }, { 570, 120 },
			{ 210, 240 }, { 330, 240 }, { 570, 240 }, { 690, 240 }, { 330, 360 }, { 450, 360 }, { 570, 360 },
			{ 210, 480 }, { 450, 480 }, { 690, 480 } };

	// ================Tables================ //
	private JTable console;
	private DefaultTableModel tableModel = new DefaultTableModel(new Object[] { "Error" }, 0);
	// ================Buttons=============== //
	private JLabel[] boardNodes;
	private JButton buttonBlueDisk;
	private JButton buttonRedDisk;
	private JButton buttonMainMenu;
	private JButton buttonAnalyze;
	private JButton buttonSave;
	
	// ==============Labels================== //
	private JLabel resultLabel;
	private JLabel turnTitleLabel;
	private JLabel turnLabel;
	
	JLayeredPane boardSetupLayeredPane = new JLayeredPane();
	/**
	 * Construct a StartGamePanel instance with the needed components
	 */
	public StartGamePanel() {
		setBackground(new Color(240, 240, 240));
		setBounds(0, 0, 920, 720);
		setLayout(null);

		
		boardSetupLayeredPane.setSize(getWidth(), getHeight());

		// Background image contained in a label
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Resources/Game_Background.png"))));
		background.setBounds(0, 0, getWidth(), getHeight());
		boardSetupLayeredPane.add(background, -1);

		JLabel boardImage = new JLabel("");
		boardImage.setForeground(Color.WHITE);
		boardImage.setBackground(Color.WHITE);
		boardImage.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Resources/500px-Six_Men's_Morris.png"))));
		boardImage.setBounds(getWidth() / 2 - 252, 0, 504, 500);
		boardSetupLayeredPane.add(boardImage, 0);
		
		//result label
		resultLabel = new JLabel("Game in progress...");
		resultLabel.setBounds(25, 520, getWidth() - 60, 120);
		resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
		resultLabel.setVerticalAlignment(SwingConstants.TOP);
		resultLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		resultLabel.setForeground(Color.WHITE);
		boardSetupLayeredPane.add(resultLabel, new Integer(1));
		
		//current turn title label
		turnTitleLabel = new JLabel("Current Turn");
		turnTitleLabel.setBounds(350, 50, getWidth(), 120);
		turnTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		turnTitleLabel.setVerticalAlignment(SwingConstants.TOP);
		turnTitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		turnTitleLabel.setForeground(Color.WHITE);
		boardSetupLayeredPane.add(turnTitleLabel, new Integer(1));
		
		//current team label
		turnLabel = new JLabel("Current Team");
		turnLabel.setBounds(350, 100, getWidth(), 120);
		turnLabel.setHorizontalAlignment(SwingConstants.CENTER);
		turnLabel.setVerticalAlignment(SwingConstants.TOP);
		turnLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		turnLabel.setForeground(Color.WHITE);
		boardSetupLayeredPane.add(turnLabel, new Integer(1));
		

		// Main Menu Button
		buttonMainMenu = new JButton("Main Menu");
		buttonMainMenu.setBounds(300, 600, getWidth() - 600, 50);
		boardSetupLayeredPane.add(buttonMainMenu, 1);
		
		// Save Game Button
		buttonSave = new JButton("Save Game");
		buttonSave.setBounds(10, 275, 190, 50);
		boardSetupLayeredPane.add(buttonSave, 1);
		

		// Nodes 0 -> 15
		JLabel Node0 = new JLabel("");
		Node0.setBounds(nodeLocations[0][0], nodeLocations[0][1], 20, 20);
		boardSetupLayeredPane.add(Node0, 2);

		JLabel Node1 = new JLabel("");
		Node1.setBounds(nodeLocations[1][0], nodeLocations[1][1], 20, 20);
		boardSetupLayeredPane.add(Node1, 2);

		JLabel Node2 = new JLabel("");
		Node2.setBounds(nodeLocations[2][0], nodeLocations[2][1], 20, 20);
		boardSetupLayeredPane.add(Node2, 2);

		JLabel Node3 = new JLabel("");
		Node3.setBounds(nodeLocations[3][0], nodeLocations[3][1], 20, 20);
		boardSetupLayeredPane.add(Node3, 2);

		JLabel Node4 = new JLabel("");
		Node4.setBounds(nodeLocations[4][0], nodeLocations[4][1], 20, 20);
		boardSetupLayeredPane.add(Node4, 2);

		JLabel Node5 = new JLabel("");
		Node5.setBounds(nodeLocations[5][0], nodeLocations[5][1], 20, 20);
		boardSetupLayeredPane.add(Node5, 2);

		JLabel Node6 = new JLabel("");
		Node6.setBounds(nodeLocations[6][0], nodeLocations[6][1], 20, 20);
		boardSetupLayeredPane.add(Node6, 2);

		JLabel Node7 = new JLabel("");
		Node7.setBounds(nodeLocations[7][0], nodeLocations[7][1], 20, 20);
		boardSetupLayeredPane.add(Node7, 2);

		JLabel Node8 = new JLabel("");
		Node8.setBounds(nodeLocations[8][0], nodeLocations[8][1], 20, 20);
		boardSetupLayeredPane.add(Node8, 2);

		JLabel Node9 = new JLabel("");
		Node9.setBounds(nodeLocations[9][0], nodeLocations[9][1], 20, 20);
		boardSetupLayeredPane.add(Node9, 2);

		JLabel Node10 = new JLabel("");
		Node10.setBounds(nodeLocations[10][0], nodeLocations[10][1], 20, 20);
		boardSetupLayeredPane.add(Node10, 2);

		JLabel Node11 = new JLabel("");
		Node11.setBounds(nodeLocations[11][0], nodeLocations[11][1], 20, 20);
		boardSetupLayeredPane.add(Node11, 2);

		JLabel Node12 = new JLabel("");
		Node12.setBounds(nodeLocations[12][0], nodeLocations[12][1], 20, 20);
		boardSetupLayeredPane.add(Node12, 2);

		JLabel Node13 = new JLabel("");
		Node13.setBounds(nodeLocations[13][0], nodeLocations[13][1], 20, 20);
		boardSetupLayeredPane.add(Node13, 2);

		JLabel Node14 = new JLabel("");
		Node14.setBounds(nodeLocations[14][0], nodeLocations[14][1], 20, 20);
		boardSetupLayeredPane.add(Node14, 2);

		JLabel Node15 = new JLabel("");
		Node15.setBounds(nodeLocations[15][0], nodeLocations[15][1], 20, 20);
		boardSetupLayeredPane.add(Node15, 2);

		boardNodes = new JLabel[] { Node0, Node1, Node2, Node3, Node4, Node5, Node6, Node7, Node8, Node9, Node10,
				Node11, Node12, Node13, Node14, Node15 };

		add(boardSetupLayeredPane);
	}

	// ================SETTERS=============== //
	/**
	 * Add a message to the console
	 * @param message message to be appended to the console 
	 */
	public void addConsoleMessage(String message) {
		Object[] a = { message };
		tableModel.addRow(a);
		repaint();
		revalidate();
		// clear the entries.
	}
	/*
	 * Clear the screen by redrawing the background
	 */
	public void redrawBG(){
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon("Resources\\Game_Background.png"));
		background.setBounds(0, 0, getWidth(), getHeight());
		boardSetupLayeredPane.add(background, -1);
	}
	/**
	 * Sets the text and colour of the state display on the game screen
	 * @param input The text to be set
	 * @param col The desired colour
	 */
	public void setResultText(String input, Color col){
		resultLabel.setText(input);
		resultLabel.setForeground(col);
	}
	/**
	 * Sets the text and colour of the turn display on the game screen
	 * @param input The text to be set
	 * @param col The desired colour
	 */
	public void setTurnText(String input, Color col){
		turnLabel.setText(input);
		turnLabel.setForeground(col);
	}

	/**
	 * Clears all entries in the console
	 */
	public void clearConsole() {
		DefaultTableModel m = (DefaultTableModel) console.getModel();
		tableModel.setRowCount(0);
		m.setRowCount(0);
	}

	// ================GETTERS=============== //

	/**
	 * Get array of pixel locations for the Nodes
	 * @return - int[][] where array[i][j] = pixel location for node i at
	 * 			x for j = 0, y for j = 1
	 */
	public int[][] getNodeLocations() {
		return nodeLocations;
	}

	/**
	 * Get Board Node buttons, they are labels a they are not visible
	 * @return JLabel array of Board Nodes 0 to 15
	 */
	public JLabel[] getBoardNodes() {
		return boardNodes;
	}

	/**
	 * Get the "Add Blue Disk" button
	 * @return "Add Blue Disk" JButton
	 */
	public JButton getButtonBlueDisk() {
		return buttonBlueDisk;
	}

	/**
	 * Get the "Add Red Disk" button
	 * @return "Add Red Disk" JButton
	 */
	public JButton getButtonRedDisk() {
		return buttonRedDisk;
	}

	/**
	 * Get the "Main Menu" button
	 * @return "Main Menu" JButton
	 */
	public JButton getButtonMainMenu() {
		return buttonMainMenu;
	}

	/**
	 * Get the "Analyze" button
	 * @return "Analyze" JButton
	 */
	public JButton getButtonAnalyze() {
		return buttonAnalyze;
	}
	
	/**
	 * Get the "Save Game" button
	 * @return "Save Game" JButton
	 */
	public JButton getButtonSave(){
		return buttonSave;
	}
	

}