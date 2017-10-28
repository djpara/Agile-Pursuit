package panels;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import gameObjects.GameBoardManager;
import globalVariables.GlobalVariables;

public class LeftPanel extends JPanel {

	private static final long serialVersionUID = 10L;
	
	// Game Board manager instance
	private GameBoardManager mGameBoardManagerInstance;
	
	// Panel References
	private ScoreAndTimerPanel mScoreAndTitlePanel;
	private EpicInventoryPanel mEpicInventoryPanel;
	private TriviaPanel mTriviaPanel;

	public LeftPanel() {
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
		// Create the score and timer panel
		mScoreAndTitlePanel = new ScoreAndTimerPanel(mGameBoardManagerInstance, this);
		
		// Create the epic/inventory panel
		mEpicInventoryPanel = new EpicInventoryPanel(mGameBoardManagerInstance, this);
		
		// Create the trivia panel
		mTriviaPanel = new TriviaPanel(mGameBoardManagerInstance, this);
		
		// Add panesl to this panel
		this.add(mScoreAndTitlePanel);
		this.add(mEpicInventoryPanel);
		this.add(mTriviaPanel);
	}
}