package panels;

import java.awt.Dimension;

import javax.swing.JPanel;

import gameObjects.GameBoard;
import globalVariables.GlobalVariables;

public class TriviaPanel extends JPanel {

	private static final long serialVersionUID = 6L;
	
	private GameBoard mGameBoard;

	public TriviaPanel(GameBoard gameBoard) {
		this.mGameBoard = gameBoard;
		this.setBackground(GlobalVariables.DEFAULT_BACK);
		this.setPreferredSize(new Dimension(GlobalVariables.TRIVIA_WIDTH, GlobalVariables.TRIVIA_HEIGHT));
	}
	
}
