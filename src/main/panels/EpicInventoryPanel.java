package main.panels;

import java.awt.*;

import javax.swing.JPanel;

import jdk.nashorn.internal.objects.Global;
import main.enums.TetrinoType;
import main.gameObjects.GameBoardManager;
import main.globalVariables.GlobalVariables;

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

		this.setPreferredSize(new Dimension(GlobalVariables.EPIC_INVENTORY_PANEL_WIDTH, GlobalVariables.EPIC_INVENTORY_PANEL_HEIGHT));
	}

//	public void paint(Graphics g) {
//		super.paint(g);
//
//		Dimension size = getSize();
//		int playSpaceTop = (int) (size.getHeight() - mPlaySpaceHeight * getBlockHeight());
//
//		// Draws the entire playspace - does not draw NONE tiles
//		for (int i = 0; i < mPlaySpaceHeight; ++i) {
//			for (int j = 0; j < mPlaySpaceWidth; ++j) {
//				TetrinoType tetrinoType = tetrinoTypeAt(j, mPlaySpaceHeight - i - 1);
//				if (tetrinoType != TetrinoType.NONE) {
//					drawTetrinoBlock(g, 0 + j * getBlockWidth(),
//							playSpaceTop + i * getBlockHeight(), getColorForTetrinoType(tetrinoType));
//				}
//			}
//		}
//
//		// Draws the current tetrino piece that's being moved around
//		TetrinoType tetrinoType = mSelectedTetrino.getShape();
//		if (tetrinoType != TetrinoType.NONE) {
//			for (int i = 0; i < 4; ++i) {
//				int x = mSelectedTetrinoXCoordinate + mSelectedTetrino.getX(i);
//				int y = mSelectedTetrinoYCoordinate - mSelectedTetrino.getY(i);
//
//				int drawX = 0 + x * getBlockWidth();
//				int drawY = playSpaceTop + (mPlaySpaceHeight - y) * getBlockHeight();
//
//				drawTetrinoBlock(g, drawX, drawY, getColorForTetrinoType(tetrinoType));
//			}
//		}
//	}
}
