package main.gameObjects;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import main.globalVariables.GlobalVariables;
import main.panels.LeftPanel;
import main.panels.RightPanel;

public class GameBoard extends JFrame {
	
	private static final long serialVersionUID = 1L;

	// Singleton Game Board instance
	private static GameBoard mGameBoardInstance;
	
	private GameBoard(String s) {
		super(s);
	}
	
	public void init() {
		
		this.configureFrame();
		
		this.addPanelsRL();
		
		this.presentFrame();
	}
	
	/**
	 * Configures the main frame
	 */
	private void configureFrame() {
		// Set the layout style
		this.getContentPane().setLayout(new FlowLayout());
		
		// Set background color
		this.getContentPane().setBackground(GlobalVariables.GAME_BOARD_BACK);

		// Configure the the GameBoard
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	/**
	 * Creates and adds the right and left panels to the main game board screen
	 */
	private void addPanelsRL() {
		// Separate the frame in two
		LeftPanel leftPanel = new LeftPanel();
		RightPanel rightPanel = new RightPanel();
				
		// Add the two panels
		add(leftPanel);
		add(rightPanel);
	}
	
	/**
	 * Presents and displays the main frame
	 */
	private void presentFrame() {
		// Packs the window proportionately given preferred panel sizes
		pack();
		
		// Centers and displays the window on user's screen
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/**
	 * Returns the static reference of the main Game Board - Singleton Pattern
	 * @return GameBoard
	 */
	public static GameBoard getGameBoardInstance() {
		if (mGameBoardInstance == null) {
			mGameBoardInstance = new GameBoard("Agile Pursuit");
		}
		return mGameBoardInstance;
	}
}