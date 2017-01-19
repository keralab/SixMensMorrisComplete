package View;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * Instance of Board Setup Panel for View
 */
public class BoardSetupPanel extends JPanel {
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

	/**
	 * Construct a BoardSetupPanel instance with the needed components
	 */
	public BoardSetupPanel() {
		setBackground(new Color(240, 240, 240));
		setBounds(0, 0, 920, 720);
		setLayout(null);

		JLayeredPane boardSetupLayeredPane = new JLayeredPane();
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

		// Place Disk Button, Red
		buttonRedDisk = new JButton("Add Red Disk");
		buttonRedDisk.setBounds(getWidth() / 2 + 252, 275, 190, 50);
		boardSetupLayeredPane.add(buttonRedDisk, new Integer(1));

		// Place Disk Button, Blue
		buttonBlueDisk = new JButton("Add Blue Disk");
		buttonBlueDisk.setBounds(10, 275, 190, 50);
		boardSetupLayeredPane.add(buttonBlueDisk, 1);

		// Main Menu Button
		buttonMainMenu = new JButton("Main Menu");
		buttonMainMenu.setBounds(10, 10, 190, 50);
		boardSetupLayeredPane.add(buttonMainMenu, 1);

		// Main Menu Button
		buttonAnalyze = new JButton("Analyze");
		buttonAnalyze.setBounds(getWidth() - 200, getHeight() - 150, 190, 50);
		boardSetupLayeredPane.add(buttonAnalyze, 1);

		console = new JTable(tableModel);
		console.setBackground(Color.WHITE);
		console.setBounds(25, 520, getWidth() - 245, 120);
		console.setGridColor(Color.WHITE);
		boardSetupLayeredPane.add(console, 1);

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

}
