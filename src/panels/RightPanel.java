package panels;

import java.awt.Dimension;

import javax.swing.JPanel;

import gameObjects.GameBoardManager;
import globalVariables.GlobalVariables;

public class RightPanel extends JPanel {

	private static final long serialVersionUID = 11L;
	
	// Game Board manager instance
	private GameBoardManager mGameBoardManagerInstance;
	
	// Panel references
	private PlayerTitlePanel mPlayerTitlePanel;
	private PlaySpacePanel mPlaySpacePanel;

	public RightPanel() {
		super();
	
		this.mGameBoardManagerInstance = GameBoardManager.getGameBoardManagerInstance();
		
		this.configurePanel();
		
		this.addPanels();
	}
	
	/**
	 * Configures the panel - background color, sets the size
	 */
	private void configurePanel() {
		this.setBackground(GlobalVariables.L_R_PANEL_BACK);
		this.setPreferredSize(new Dimension(GlobalVariables.GAME_BOARD_WIDTH/2 - GlobalVariables.L_R_CUSHION, 
											GlobalVariables.GAME_BOARD_HEIGHT - GlobalVariables.L_R_CUSHION));
	}
	
	private void addPanels() {
		// Create the player title panel
		mPlayerTitlePanel = new PlayerTitlePanel(mGameBoardManagerInstance, this);
				
		// Create the play space panel
		mPlaySpacePanel = new PlaySpacePanel(mGameBoardManagerInstance, this);
		
		// Add panels to this panel
		this.add(mPlayerTitlePanel);
		this.add(mPlaySpacePanel);
	}
}
