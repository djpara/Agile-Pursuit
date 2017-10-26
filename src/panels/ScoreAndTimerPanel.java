package panels;

import java.awt.Dimension;

import javax.swing.JPanel;

import gameObjects.GameBoard;
import globalVariables.GlobalVariables;

public class ScoreAndTimerPanel extends JPanel {

	private static final long serialVersionUID = 4L;
	
	private GameBoard mGameBoard;

	public ScoreAndTimerPanel(GameBoard gameBoard) {
		this.mGameBoard = gameBoard;
		this.setBackground(GlobalVariables.DEFAULT_BACK);
		this.setPreferredSize(new Dimension(GlobalVariables.SCORE_AND_TIME_WIDTH, GlobalVariables.SCORE_AND_TIME_HEIGHT));
	}
	
}
