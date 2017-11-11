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
	
	private GameBoardManager mGameBoardManager;
	
	private JPanel mParentPanel;

	public PlayerTitlePanel(GameBoardManager gameBoardManager, JPanel parentPanel) {
		mGameBoardManager = gameBoardManager;
		mParentPanel = parentPanel;
		
		configurePanel();
		displayName(getPlayerName());
	}
	
	/**
	 * Configures the panel - background color, sets the size
	 */
	private void configurePanel() {
		setBackground(GlobalVariables.DEFAULT_BACK);
		
		setPreferredSize(new Dimension(GlobalVariables.PLAYER_TITLE_PANEL_WIDTH, GlobalVariables.PLAYER_TITLE_PANEL_HEIGHT));
	}
	
	private String getPlayerName(){
		String input = JOptionPane.showInputDialog("Enter your name: ");
		return input;
	}
	private void displayName(String s){
		DrawingComponent DC = new DrawingComponent(s);
		DC.setPreferredSize(new Dimension((int)(mParentPanel.getPreferredSize().width - GlobalVariables.CUSHION),(int) (mParentPanel.getPreferredSize().getHeight() * 0.05 - GlobalVariables.CUSHION)));
		add(DC);
	}
	
}
