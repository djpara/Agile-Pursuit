package main.panels;

import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import jdk.nashorn.internal.objects.Global;
import main.drawingUtil.DrawingComponent;
import main.gameObjects.GameBoardManager;
import main.globalVariables.GlobalVariables;

public class PlayerTitlePanel extends JPanel {

	private static final long serialVersionUID = 3L;

	private final Dimension mPreferredSize = new Dimension(GlobalVariables.PLAYER_TITLE_PANEL_WIDTH,
			GlobalVariables.PLAYER_TITLE_PANEL_HEIGHT);
	
	private GameBoardManager mGameBoardManager;
	
	private JPanel mParentPanel;

	public PlayerTitlePanel(GameBoardManager gameBoardManager, JPanel parentPanel) {
		mGameBoardManager = gameBoardManager;
		mParentPanel = parentPanel;
		
		configurePanel();
		displayName();
	}
	
	/**
	 * Configures the panel - background color, sets the size
	 */
	private void configurePanel() {
		setBackground(GlobalVariables.DEFAULT_BACK);
		
		setPreferredSize(mPreferredSize);
	}
	
	private String getPlayerName(){
		String input = JOptionPane.showInputDialog("Enter your name: ");
		return input;
	}

	private void displayName() {
		String playerName = getPlayerName();
		String text;

		if (playerName != null
				&& playerName != "") {
			text = "Developer: " + playerName;
		} else  {
			text = "Developer";
		}

		DrawingComponent DC = new DrawingComponent(text, 25, 17, 20);
		DC.setPreferredSize(mPreferredSize);
		add(DC);
	}
	
}
