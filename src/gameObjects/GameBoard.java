package gameObjects;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import globalVariables.GlobalVariables;
import panels.EpicInventoryPanel;
import panels.PlaySpacePanel;
import panels.PlayerTitlePanel;
import panels.ScoreAndTimerPanel;
import panels.TriviaPanel;

public class GameBoard extends JFrame implements ActionListener {
	

	
	private static final long serialVersionUID = 1L;

	// Singleton Game Board instance
	private static GameBoard mGameBoardInstance;
	
	// Panels
	private ScoreAndTimerPanel mScoreAndTimerPanel;
	private EpicInventoryPanel mEpicInventoryPanel;
	private TriviaPanel mTriviaPanel;
	private PlayerTitlePanel mPlayerTitlePanel;
	private PlaySpacePanel mPlaySpacePanel;
	
	
	// Constraints
	private GridBagConstraints mScoreAndTimerPanelConstraints;
	private GridBagConstraints mEpicInventoryPanelConstraints;
	private GridBagConstraints mTriviaPanelConstraints;
	private GridBagConstraints mPlayerTitlePanelConstraints;
	private GridBagConstraints mPlaySpacePanelConstraints;
	
	private GameBoard(String s) {
		super(s);
		
		getContentPane().setLayout(new GridBagLayout());
	}
	
	public void init() {

		// Configure the the GameBoard
		setSize(GlobalVariables.GAME_BOARD_WIDTH, GlobalVariables.GAME_BOARD_HEIGHT);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		// Create the score and time panel and set constraints
		mScoreAndTimerPanel = new ScoreAndTimerPanel(mGameBoardInstance);
		getScoreAndTimerPanelConstraints();
		add(mScoreAndTimerPanel, mScoreAndTimerPanelConstraints);
		
		// Create the epic/inventory panel and set constraints
		mEpicInventoryPanel = new EpicInventoryPanel(mGameBoardInstance);
		getEpicInvetoryPanelConstraints();
		add(mEpicInventoryPanel, mEpicInventoryPanelConstraints);
		
		// Create the trivia panel and set constraints
		mTriviaPanel= new TriviaPanel(mGameBoardInstance);
		getTriviaPanelConstraints();
		add(mTriviaPanel, mTriviaPanelConstraints);
		
		// Create player title panel and set constraints
		mPlayerTitlePanel = new PlayerTitlePanel(mGameBoardInstance);
		getPlayerTitlePanelConstraints();
		add(mPlayerTitlePanel, mPlayerTitlePanelConstraints);
		
		// Create the play space panel and set constraints
		mPlaySpacePanel = new PlaySpacePanel(mGameBoardInstance);
		getPlaySpacePanelConstraints();
		add(mPlaySpacePanel, mPlaySpacePanelConstraints);
		
		setVisible(true);
	}
	
	public static GameBoard getGameBoardInstance() {
		if (mGameBoardInstance == null) {
			mGameBoardInstance = new GameBoard("Agile Pursuit");
		}
		return mGameBoardInstance;
	}
	
	/**
	 * Creates and configures the score and timer panel constraints
	 */
	private void getScoreAndTimerPanelConstraints() {
		mScoreAndTimerPanelConstraints = new GridBagConstraints();
		
		mScoreAndTimerPanelConstraints.gridx = 0;
		mScoreAndTimerPanelConstraints.gridy = 0;
		
		mScoreAndTimerPanelConstraints.gridwidth  = 1; //GlobalVariables.SCORE_AND_TIME_WIDTH;
		mScoreAndTimerPanelConstraints.gridheight = 1; //GlobalVariables.SCORE_AND_TIME_HEIGHT;
		
		mScoreAndTimerPanelConstraints.weightx = 0.49;
		mScoreAndTimerPanelConstraints.weighty = 0.50;
		
		mScoreAndTimerPanelConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		
		mScoreAndTimerPanelConstraints.insets = GlobalVariables.insets;
	}
	
	/**
	 * Creates and configures the epic/inventory panel constraints
	 */
	private void getEpicInvetoryPanelConstraints() {
		mEpicInventoryPanelConstraints = new GridBagConstraints();
		
		mEpicInventoryPanelConstraints.gridx = 0;
		mEpicInventoryPanelConstraints.gridy = 1; //GlobalVariables.SCORE_AND_TIME_HEIGHT;
		
		mEpicInventoryPanelConstraints.gridwidth  = 1; //GlobalVariables.EPIC_INVENTORY_WIDTH;
		mEpicInventoryPanelConstraints.gridheight = 6; //GlobalVariables.EPIC_INVENTORY_HEIGHT;
		
		mEpicInventoryPanelConstraints.weightx = 0.49;
		mEpicInventoryPanelConstraints.weighty = 0.50;
		
		mEpicInventoryPanelConstraints.anchor = GridBagConstraints.BASELINE_LEADING;
		
		mEpicInventoryPanelConstraints.insets = GlobalVariables.insets;
	}
	
	/**
	 * Creates and configures the trivia panel constraints
	 */
	private void getTriviaPanelConstraints() {
		mTriviaPanelConstraints = new GridBagConstraints();
		
		mTriviaPanelConstraints.gridx = 0;
		mTriviaPanelConstraints.gridy = 7; //GlobalVariables.GAME_BOARD_HEIGHT - GlobalVariables.TRIVIA_HEIGHT;
		
		mTriviaPanelConstraints.gridwidth  = 1; //GlobalVariables.TRIVIA_WIDTH;
		mTriviaPanelConstraints.gridheight = 3; //GlobalVariables.TRIVIA_HEIGHT;
		
		mTriviaPanelConstraints.weightx = 0.49;
		mTriviaPanelConstraints.weighty = 0.47;
		
		mTriviaPanelConstraints.anchor = GridBagConstraints.LAST_LINE_START;
		
		mTriviaPanelConstraints.insets = GlobalVariables.insets;
	}
	
	/**
	 * Creates and configures the player title panel constraints
	 */
	private void getPlayerTitlePanelConstraints() {
		mPlayerTitlePanelConstraints = new GridBagConstraints();
		
		mPlayerTitlePanelConstraints.gridx = 1; //GlobalVariables.GAME_BOARD_WIDTH - GlobalVariables.PLAYER_TITLE_WIDTH;
		mPlayerTitlePanelConstraints.gridy = 0;
		
		mPlayerTitlePanelConstraints.gridwidth  = 1; //GlobalVariables.PLAYER_TITLE_WIDTH;
		mPlayerTitlePanelConstraints.gridheight = 1; //GlobalVariables.PLAYER_TITLE_HEIGHT;
		
		mPlayerTitlePanelConstraints.weightx = 0.49;
		mPlayerTitlePanelConstraints.weighty = 0.50;
		
		mPlayerTitlePanelConstraints.anchor = GridBagConstraints.FIRST_LINE_END;
		
		mPlayerTitlePanelConstraints.insets = GlobalVariables.insets;
	}
	
	/**
	 * Creates and configures the play space panel constraints
	 */
	private void getPlaySpacePanelConstraints() {
		mPlaySpacePanelConstraints = new GridBagConstraints();
		
		mPlaySpacePanelConstraints.gridx = 1; //GlobalVariables.GAME_BOARD_WIDTH - GlobalVariables.PLAY_SPACE_WIDTH;
		mPlaySpacePanelConstraints.gridy = 2; //GlobalVariables.GAME_BOARD_HEIGHT - GlobalVariables.PLAY_SPACE_HEIGHT;
		
		mPlaySpacePanelConstraints.gridwidth  = 1; //GlobalVariables.PLAY_SPACE_WIDTH;
		mPlaySpacePanelConstraints.gridheight = 9; //GlobalVariables.PLAY_SPACE_HEIGHT;
		
		mPlaySpacePanelConstraints.weightx = 0.50;
		mPlaySpacePanelConstraints.weighty = 0.50;
		
		mPlaySpacePanelConstraints.anchor = GridBagConstraints.LAST_LINE_END;
		
		mPlaySpacePanelConstraints.insets = GlobalVariables.insets;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO
		
	}
}
