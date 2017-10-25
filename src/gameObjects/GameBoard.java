package gameObjects;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class GameBoard extends JFrame implements ActionListener {
	

	
	private static final long serialVersionUID = 1L;

	private static GameBoard gameBoardInstance;
	
	// Sub-views
	private PlaySpace playSpace;
	
	private GameBoard(String s) {
		super(s);
	}
	
	public void init() {

		// Configure the the GameBoard
		setSize(1500, 1000);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		// Create the game Label
		JLabel gameLabel = new JLabel("Agile Pursuit");
		gameLabel.setForeground(Color.BLUE);
		
		// Create the play space
		playSpace = new PlaySpace(gameBoardInstance);
		add(playSpace);
		
		setVisible(true);
	}
	
	public static GameBoard getGameBoardInstance() {
		if (gameBoardInstance == null) {
			gameBoardInstance = new GameBoard("Agile Pursuit");
		}
		return gameBoardInstance;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO
		
	}
}
