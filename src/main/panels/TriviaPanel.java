package main.panels;

import java.awt.Dimension;

import javax.swing.JPanel;

import jdk.nashorn.internal.objects.Global;
import main.gameObjects.GameBoardManager;
import main.globalVariables.GlobalVariables;

public class TriviaPanel extends JPanel {

	private static final long serialVersionUID = 6L;
	
	private GameBoardManager mGameBoardManager;
	
	private JPanel mParentPanel;

	public TriviaPanel(GameBoardManager gameBoardManager, JPanel parentPanel) {
		this.mGameBoardManager = gameBoardManager;
		this.mParentPanel = parentPanel;
		
		this.configurePanel();
	}
	
	/**
	 * Configures the panel - background color, sets the size
	 */
	private void configurePanel() {
		this.setBackground(GlobalVariables.DEFAULT_BACK);
		
//		int preferredWidth = mParentPanel.getPreferredSize().width - GlobalVariables.CUSHION;
		int preferredHeight = (int)(mParentPanel.getPreferredSize().getHeight() * 0.40 - GlobalVariables.CUSHION);
		
		this.setPreferredSize(new Dimension(GlobalVariables.TRIVIA_PANEL_WIDTH, preferredHeight));
	}
	
}
