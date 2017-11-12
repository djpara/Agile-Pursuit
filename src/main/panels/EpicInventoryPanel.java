package main.panels;

import java.awt.Dimension;

import javax.swing.JPanel;

import jdk.nashorn.internal.objects.Global;
import main.gameObjects.GameBoardManager;
import main.globalVariables.GlobalVariables;

public class EpicInventoryPanel extends JPanel {

	private static final long serialVersionUID = 5L;
	
	private GameBoardManager mGameBoardManager;
	
	private JPanel mParentPanel;

	public EpicInventoryPanel(GameBoardManager gameBoardManager, JPanel parentPanel) {
		this.mGameBoardManager = gameBoardManager;
		this.mParentPanel = parentPanel;
		
		this.configurePanel();
	}
	
	/**
	 * Configures the panel - background color, sets the size
	 */
	private void configurePanel() {
		this.setBackground(GlobalVariables.DEFAULT_BACK);
		
		this.setPreferredSize(new Dimension(GlobalVariables.EPIC_INVENTORY_PANEL_WIDTH, GlobalVariables.EPIC_INVENTORY_PANEL_HEIGHT));
	}
}
