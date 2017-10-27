package gameObjects;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import panels.EpicInventoryPanel;
import panels.PlaySpacePanel;
import panels.PlayerTitlePanel;
import panels.ScoreAndTimerPanel;
import panels.TriviaPanel;

public class GameBoardManager implements ActionListener {

	// Singleton Game Board manager instance
	private static GameBoardManager mGameBoardManagerInstance;
	
	// Game Board reference
	private GameBoard mGameBoardInstance;
	
	// Panel references
	private ScoreAndTimerPanel mScoreAndTimerPanel;
	private EpicInventoryPanel mEpicInventoryPanel;
	private TriviaPanel mTriviaPanel;
	private PlayerTitlePanel mPlayerTitlePanel;
	private PlaySpacePanel mPlaySpacePanel;
	
	private GameBoardManager() {
		mGameBoardInstance = GameBoard.getGameBoardInstance();
	}
	
	/**
	 * Returns the static reference of the main Game Board manager - Singleton Pattern
	 * @return GameBoardManager
	 */
	public static GameBoardManager getGameBoardManagerInstance() {
		if (mGameBoardManagerInstance == null) {
			mGameBoardManagerInstance = new GameBoardManager();
		}
		return mGameBoardManagerInstance;
	}
	
	/**
	 * Sets the score and timer panel
	 * @param scoreAndTimerPanel
	 */
	public void setScoreAndTimerPanel(ScoreAndTimerPanel scoreAndTimerPanel) {
		mScoreAndTimerPanel = scoreAndTimerPanel;
	}
	
	/**
	 * Sets the epic/inventory panel
	 * @param epicInventoryPanel
	 */
	public void setEpicInventoryPanel(EpicInventoryPanel epicInventoryPanel) {
		mEpicInventoryPanel = epicInventoryPanel;
	}
	
	/**
	 * Sets the trivia panel
	 * @param triviaPanel
	 */
	public void setTriviaPanel(TriviaPanel triviaPanel) {
		mTriviaPanel = triviaPanel;
	}
	
	/**
	 * Sets the player title panel
	 * @param playerTitlePanel
	 */
	public void setPlayerTitlePanel(PlayerTitlePanel playerTitlePanel) {
		mPlayerTitlePanel = playerTitlePanel;
	}
	
	/**
	 * Sets the play space panel
	 * @param playSpacePanel
	 */
	public void setPlaySpacePanel(PlaySpacePanel playSpacePanel) {
		mPlaySpacePanel = playSpacePanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO
		
	}
	
}