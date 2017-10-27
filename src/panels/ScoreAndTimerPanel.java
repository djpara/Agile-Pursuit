package panels;

import java.awt.Dimension;

import javax.swing.JPanel;

import gameObjects.GameBoardManager;
import globalVariables.GlobalVariables;

public class ScoreAndTimerPanel extends JPanel {

	private static final long serialVersionUID = 4L;
	
	private GameBoardManager mGameBoardManager;
	
	private JPanel mParentPanel;

	public ScoreAndTimerPanel(GameBoardManager gameBoardManager, JPanel parentPanel) {
		this.mGameBoardManager = gameBoardManager;
		this.mParentPanel = parentPanel;
		
		this.configurePanel();
	}
	
	/**
	 * Configures the panel - background color, sets the size
	 */
	private void configurePanel() {
		this.setBackground(GlobalVariables.DEFAULT_BACK);
		
		int preferredWidth = mParentPanel.getPreferredSize().width - GlobalVariables.CUSHION;
		int preferredHeight = (int)(mParentPanel.getPreferredSize().getHeight() * 0.05 - GlobalVariables.CUSHION);
		
		this.setPreferredSize(new Dimension(preferredWidth, preferredHeight));
	}
	
}
