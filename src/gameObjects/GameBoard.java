package gameObjects;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class GameBoard extends JFrame {

	private static final long serialVersionUID = 1L;

	private static GameBoard gameBoardInstance;
	
	// Subviews
	private Playspace playspace = new Playspace(new BorderLayout(3, 3));
	
	// Constraints
	private GridBagConstraints playspaceConstraints = new GridBagConstraints(1, 1, 1, 1, 20.0, 20.0, GridBagConstraints.SOUTHEAST, 0, new Insets(1, 1, 1, 1), 1, 1);
	private GridBagConstraints gameLabelConstraints = new GridBagConstraints(1, 1, 1, 1, 20.0, 20.0, GridBagConstraints.SOUTHWEST, 0, new Insets(1, 1, 1, 1), 1, 1);
	
	
	private GameBoard(String s) {
		super(s);
		initialize();
	}
	
	private void initialize() {

		// Configure the the GameBoard
		setSize(1500, 1000);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());
		setLocationRelativeTo(null);
		
		// Create the game Label
		JLabel gameLabel = new JLabel("Agile Pursuit");
		gameLabel.setForeground(Color.BLUE);
		
		getContentPane().add(playspace, playspaceConstraints);
		getContentPane().add(gameLabel, gameLabelConstraints);
		
		setVisible(true);
	}
	
	public static GameBoard getGameBoardInstance() {
		if (gameBoardInstance == null) {
			gameBoardInstance = new GameBoard("Agile Pursuit");
		}
		return gameBoardInstance;
	}
}
