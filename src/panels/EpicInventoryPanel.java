package panels;

import java.awt.Dimension;

import javax.swing.JPanel;

import gameObjects.GameBoardManager;
import globalVariables.GlobalVariables;

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
		
		int preferredWidth = mParentPanel.getPreferredSize().width - GlobalVariables.CUSHION;
		int preferredHeight = (int)(mParentPanel.getPreferredSize().getHeight() * 0.55 - GlobalVariables.CUSHION);
		
		this.setPreferredSize(new Dimension(preferredWidth, preferredHeight));
	}
}
