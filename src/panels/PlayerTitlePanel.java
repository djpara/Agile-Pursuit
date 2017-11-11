package panels;

import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import drawingUtil.DrawingComponent;
import gameObjects.GameBoardManager;
import globalVariables.GlobalVariables;

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
		
		int preferredWidth = mParentPanel.getPreferredSize().width - GlobalVariables.CUSHION;
		int preferredHeight = (int)(mParentPanel.getPreferredSize().getHeight() * 0.05 - GlobalVariables.CUSHION);
		
		this.setPreferredSize(new Dimension(preferredWidth, preferredHeight));
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
