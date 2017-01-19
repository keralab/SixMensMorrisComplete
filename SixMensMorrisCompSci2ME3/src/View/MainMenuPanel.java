package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/** 
 * Main Menu JPanel representation instance object
 */
public class MainMenuPanel extends JPanel{
	
	// ================Buttons=============== //
	private JButton buttonOptions;
	private JButton buttonStartGame;
	private JButton buttonBoardSetup;
	private JButton buttonOpen;
	// ================Labels=============== //
	private JLabel titleLabel;
	
	/**
	 * Construct the main menu with the needed elements.
	 */
	public MainMenuPanel(){
		setBounds(0, 0, 1080, 720);
		setLayout(null);
		
		JLayeredPane mainMenuLayeredPane = new JLayeredPane();
		mainMenuLayeredPane.setSize(getWidth(), getHeight());
		
		ImageIcon background = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Resources/Menu_Background.png")));

		JLabel backgroundPlaceHolder = new JLabel();
		backgroundPlaceHolder.setBounds(0, 0, getWidth(), getHeight());
		backgroundPlaceHolder.setIcon(background);
		mainMenuLayeredPane.add(backgroundPlaceHolder, new Integer(0));

		

		
		// Title label
		titleLabel = new JLabel("Six Mans Morris");
		titleLabel.setBounds(323, 11, 274, 75);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setVerticalAlignment(SwingConstants.TOP);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		titleLabel.setForeground(Color.WHITE);
		mainMenuLayeredPane.add(titleLabel, new Integer(1));
		
		// Options Button
		buttonOptions = new JButton("Options");
		buttonOptions.setBounds(323, 350, 274, 75);
		mainMenuLayeredPane.add(buttonOptions, new Integer(1));
		
		//Open Button
		buttonOpen = new JButton("Open Game");
		buttonOpen.setBounds(323, 475, 274, 75);
		mainMenuLayeredPane.add(buttonOpen, new Integer(1));
		
		// Start Button
		buttonStartGame = new JButton("Start Game");
		buttonStartGame.setBounds(323, 100, 274, 75);
		mainMenuLayeredPane.add(buttonStartGame, new Integer(1));
	
		buttonBoardSetup = new JButton("Board Set-Up");
		buttonBoardSetup.setBounds(323, 225, 274, 75);
		mainMenuLayeredPane.add(buttonBoardSetup, new Integer(1));
		
		JLabel creditLabel = new JLabel();
		creditLabel.setText("An Application By: Riley McGee, Aaska Shah, Ben Petkovsek & Kerala Brendon");
		creditLabel.setBounds(450, 640, 500, 20);
		creditLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		creditLabel.setForeground(Color.WHITE);
		
		mainMenuLayeredPane.add(creditLabel, new Integer(1));
		add(mainMenuLayeredPane);	

	}
	
	/**
	 * Get the "Options" button
	 * @return "Options" JButton
	 */
	public JButton getButtonOptions() {
		return buttonOptions;
	}

	/**
	 * Get the "Start Game" button
	 * @return "Start Game" JButton
	 */
	public JButton getButtonStartGame() {
		return buttonStartGame;
	}

	/**
	 * Get the "Board Set-up" button
	 * @return "Board Set-up" JButton
	 */
	public JButton getButtonBoardSetup() {
		return buttonBoardSetup;
	}

	public JButton getButtonOpen(){
		return buttonOpen;
	}
	
}
