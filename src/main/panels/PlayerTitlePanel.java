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
		this.mGameBoardManager = gameBoardManager;
		this.mParentPanel = parentPanel;
		
		this.configurePanel();
		this.displayName(getPlayerName());
	}
	
	/**
	 * Configures the panel - background color, sets the size
	 */
	private void configurePanel() {
		this.setBackground(GlobalVariables.DEFAULT_BACK);
		
		GlobalVariables.PLAYER_TITLE_PANEL_WIDTH = mParentPanel.getPreferredSize().width - GlobalVariables.CUSHION;
		GlobalVariables.PLAYER_TITLE_PANEL_HEIGHT = (int)(mParentPanel.getPreferredSize().getHeight() * 0.05 - GlobalVariables.CUSHION);
		
		this.setPreferredSize(new Dimension(GlobalVariables.PLAYER_TITLE_PANEL_HEIGHT, GlobalVariables.PLAYER_TITLE_PANEL_HEIGHT));
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
