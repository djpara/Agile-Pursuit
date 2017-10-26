package panels;

import java.awt.Dimension;

import javax.swing.JPanel;

import gameObjects.GameBoard;
import globalVariables.GlobalVariables;

public class PlayerTitlePanel extends JPanel {

	private static final long serialVersionUID = 3L;
	
	private GameBoard mGameBoard;

	public PlayerTitlePanel(GameBoard gameBoard) {
		this.mGameBoard = gameBoard;
		this.setBackground(GlobalVariables.DEFAULT_BACK);
		this.setPreferredSize(new Dimension(GlobalVariables.PLAYER_TITLE_WIDTH, GlobalVariables.PLAYER_TITLE_HEIGHT));
	}
	
}
