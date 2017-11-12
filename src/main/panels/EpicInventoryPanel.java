package main.panels;

import java.awt.*;

import javax.swing.JPanel;

import jdk.nashorn.internal.objects.Global;
import main.enums.TetrinoType;
import main.gameObjects.GameBoardManager;
import main.gameObjects.Tetrino;
import main.globalVariables.GlobalVariables;

public class EpicInventoryPanel extends JPanel {

	private static final long serialVersionUID = 5L;

	// Set play space height and width allows for calculating block sizes. 27x19 playspace creates a block of 18x18
	private final int mEpicInventoryWidth = 27;
	private final int mEpicInventoryHeight = 19;

	private TetrinoType[] mEpicInventory;
	
	private GameBoardManager mGameBoardManager;
	
	private JPanel mParentPanel;

	public EpicInventoryPanel(GameBoardManager gameBoardManager, JPanel parentPanel) {
		this.mGameBoardManager = gameBoardManager;
		this.mParentPanel = parentPanel;
		
		this.configurePanel();

		mEpicInventory = new TetrinoType[ mEpicInventoryWidth * mEpicInventoryHeight ];

		clearPlaySpace();

		repaint();

		this.fillInventory();
	}
	
	/**
	 * Configures the panel - background color, sets the size
	 */
	private void configurePanel() {
		this.setBackground(GlobalVariables.DEFAULT_BACK);

		this.setPreferredSize(new Dimension(GlobalVariables.EPIC_INVENTORY_PANEL_WIDTH, GlobalVariables.EPIC_INVENTORY_PANEL_HEIGHT));
	}

	private void fillInventory() {
		Tetrino tetrino = new Tetrino();

		tetrino.setTetrinoType(TetrinoType.S);
		placeTetrino(tetrino, 5, 6);

		tetrino.setTetrinoType(TetrinoType.Z);
		placeTetrino(tetrino, 10, 6);

		tetrino.setTetrinoType(TetrinoType.I);
		placeTetrino(tetrino, 15, 6);

		tetrino.setTetrinoType(TetrinoType.T);
		placeTetrino(tetrino, 20, 6);

		tetrino.setTetrinoType(TetrinoType.L);
		placeTetrino(tetrino, 7, 14);

		tetrino.setTetrinoType(TetrinoType.J);
		placeTetrino(tetrino, 13, 14);

		tetrino.setTetrinoType(TetrinoType.SQUARE);
		placeTetrino(tetrino, 17, 14);
	}

	/**
	 * Clears the play space and moves all of the played Tetrinos to the inventory
	 */
	private void clearPlaySpace() {
		for (int i = 0; i < mEpicInventoryWidth * mEpicInventoryHeight; ++i) {
			mEpicInventory[i] = TetrinoType.NONE;
		}
	}

	/**
	 * Calculates the tetrino block width
	 * @return
	 */
	private int getBlockWidth() {
		return (int) getSize().getWidth()/mEpicInventoryWidth;
	}

	/**
	 * Calculates the tetrino block height
	 * @return
	 */
	private int getBlockHeight() {
		return (int) getSize().getHeight()/mEpicInventoryHeight;
	}

	/**
	 * Gets the tetrino type currently at x, y coordinate
	 * @param x
	 * @param y
	 * @return
	 */
	TetrinoType tetrinoTypeAt(int x, int y) {
		return mEpicInventory[(y * mEpicInventoryWidth) +  x];
	}

	public void paint(Graphics g) {
		super.paint(g);

		Dimension size = getSize();
		int playSpaceTop = (int) (size.getHeight() - mEpicInventoryHeight * getBlockHeight());

		// Draws the entire playspace - does not draw NONE tiles
		for (int i = 0; i < mEpicInventoryHeight; ++i) {
			for (int j = 0; j < mEpicInventoryWidth; ++j) {
				TetrinoType tetrinoType = tetrinoTypeAt(j, mEpicInventoryHeight - i - 1);
				if (tetrinoType != TetrinoType.NONE) {
					drawTetrinoBlock(g, 0 + j * getBlockWidth(),
							playSpaceTop + i * getBlockHeight(), getColorForTetrinoType(tetrinoType));
				}
			}
		}
	}

	/**
	 * Draws the blocks that make up the Tetrinos
	 * @param graphics
	 * @param x
	 * @param y
	 * @param color
	 */
	private void drawTetrinoBlock(Graphics graphics, int x, int y, Color color) {
		graphics.setColor(color);
		graphics.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);

		graphics.setColor(color.brighter());
		graphics.drawLine(x, y + getBlockHeight() - 1, x, y);
		graphics.drawLine(x, y, x + getBlockWidth() - 1, y);

		graphics.setColor(color.darker());
		graphics.drawLine(x + 1, y + getBlockHeight() - 1, x + getBlockWidth() - 1, y + getBlockHeight() - 1);
		graphics.drawLine(x + getBlockWidth() - 1, y + getBlockHeight() - 1, x + getBlockWidth() - 1, y + 1);
	}

	/**
	 * Sets the Tetrinos to the play space
	 */
	private void placeTetrino(Tetrino tetrino, int xCoordinate, int yCoordinate) {
		// TODO

		for (int i = 0; i < 4; ++i) {
			int x = xCoordinate + tetrino.getX(i);
			int y = yCoordinate - tetrino.getY(i) - 1;
			mEpicInventory[(y * mEpicInventoryWidth) + x] = tetrino.getShape();
		}

		repaint();
	}

	private Color getColorForTetrinoType(TetrinoType tetrinoType) {
		switch (tetrinoType) {
			case NONE:
				return GlobalVariables.NONE_COLOR;
			case Z:
				return GlobalVariables.Z_COLOR;
			case S:
				return GlobalVariables.S_COLOR;
			case I:
				return GlobalVariables.I_COLOR;
			case T:
				return GlobalVariables.T_COLOR;
			case L:
				return GlobalVariables.L_COLOR;
			case J:
				return GlobalVariables.J_COLOR;
			case SQUARE:
				return GlobalVariables.SQUARE_COLOR;
			default:
				return GlobalVariables.NONE_COLOR;
		}
	}

}
