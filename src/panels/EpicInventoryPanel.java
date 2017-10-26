package panels;

import java.awt.Dimension;

import javax.swing.JPanel;

import gameObjects.GameBoard;
import globalVariables.GlobalVariables;

public class EpicInventoryPanel extends JPanel {

	private static final long serialVersionUID = 5L;
	
	private GameBoard mGameBoard;

	public EpicInventoryPanel(GameBoard gameBoard) {
		this.mGameBoard = gameBoard;
		this.setBackground(GlobalVariables.DEFAULT_BACK);
		this.setPreferredSize(new Dimension(GlobalVariables.EPIC_INVENTORY_WIDTH, GlobalVariables.EPIC_INVENTORY_HEIGHT));
	}
	
}
