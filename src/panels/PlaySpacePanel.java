package panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import enums.TetrinoType;
import gameObjects.GameBoardManager;
import gameObjects.Tetrino;
import globalVariables.GlobalVariables;

public class PlaySpacePanel extends JPanel {

	private static final long serialVersionUID = 2L;
	
	// Set play space height and width allows for calculating block sizes. 24x31 playspace creates a block of 30x30
	private final int playSpaceWidth = 24;
    private final int playSpaceHeight = 31;
	
	private GameBoardManager mGameBoardManager;
	
	private JPanel mParentPanel;
	
	// TESTING = simulated inventory tetrinos (dummy code)
	private ArrayList<Tetrino> mInventorySimulationArray;
	
	private ArrayList<Tetrino> mPlayedTetrinos;
	private Tetrino mSelectedTetrino;
	private TetrinoType[] mPlaySpace;
	
	private int mSelectedTetrinoXCoordinate = 0;
	private int mSelectedTetrinoYCoordinate = 0;
	
	private boolean mIsListening;
	
	public PlaySpacePanel(GameBoardManager gameBoardManager, JPanel parentPanel) {
		setFocusable(true);
		
		mGameBoardManager = gameBoardManager;
		mParentPanel = parentPanel;
		
		mPlayedTetrinos = new ArrayList<Tetrino>();
		mInventorySimulationArray = new ArrayList<Tetrino>();
		
		configurePanel();
		
		// TESTING = Fill the array
		Tetrino newTetrino = new Tetrino();
		for (int i = 0; i < 15; ++i) {
			newTetrino.setRandomTetrino();
			mInventorySimulationArray.add(newTetrino);
		}

		getNextTetrinoFromInventory();
		
		mPlaySpace = new TetrinoType[ playSpaceWidth * playSpaceHeight ];
		
		addKeyListener(new Adapter());

		clearPlaySpace();
		
		repaint();
	}
	
	/**
	 * Configures the panel - background color, sets the size
	 */
	private void configurePanel() {
		setBackground(GlobalVariables.DEFAULT_BACK);
		
		int mPanelWidth = mParentPanel.getPreferredSize().width - GlobalVariables.CUSHION;
		int mPanelHeight = (int)(mParentPanel.getPreferredSize().getHeight() * 0.95 - GlobalVariables.CUSHION);
		
		setPreferredSize(new Dimension(mPanelWidth, mPanelHeight));
	}
	
	/**
	 * Calculates the tetrino block width
	 * @return
	 */
	private int getBlockWidth() {
		return (int) getSize().getWidth()/playSpaceWidth;
	}
	
	/**
	 * Calculates the tetrino block height
	 * @return
	 */
	private int getBlockHeight() {
		return (int) getSize().getHeight()/playSpaceHeight;
	}
	
	/**
	 * Gets the tetrino type currently at x, y coordinate
	 * @param x
	 * @param y
	 * @return
	 */
	TetrinoType tetrinoTypeAt(int x, int y) {
		return mPlaySpace[(y * playSpaceWidth) +  x];
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		Dimension size = getSize();
		int playSpaceMiddle = (int) (size.getHeight()/2);
		
		for (int i = 0; i < playSpaceHeight; ++i) {
			 for (int j = 0; j < playSpaceWidth; ++j) {
	                TetrinoType tetrinoType = tetrinoTypeAt(j, playSpaceHeight - i - 1);
	                if (tetrinoType != TetrinoType.NONE) {
	                    drawTetrinoBlock(g, 0 + j * getBlockWidth(),
	                            playSpaceMiddle + i * getBlockHeight(), getColorForTetrinoType(tetrinoType));
	                }
	          }
		}
		
		TetrinoType tetrinoType = mSelectedTetrino.getShape(); 
		if (tetrinoType != TetrinoType.NONE) {
            for (int i = 0; i < 4; ++i) {
                int x = mSelectedTetrinoXCoordinate + mSelectedTetrino.getX(i);
                int y = mSelectedTetrinoYCoordinate - mSelectedTetrino.getY(i);
                
                int drawX = playSpaceMiddle + x * getBlockWidth();
                int drawY = playSpaceMiddle + (0 - y - 1) * getBlockHeight();
                
                drawTetrinoBlock(g, drawX, drawY, getColorForTetrinoType(tetrinoType));
            }
        }
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
	 * Clears the play space and moves all of the played Tetrinos to the inventory 
	 */
    private void clearPlaySpace() {
        for (int i = 0; i < playSpaceWidth * playSpaceHeight; ++i) {
            mPlaySpace[i] = TetrinoType.NONE;
        }
        
        returnSelectedTetrino();
    }
    
    /**
     * Moves the Tetrinos to the inventory from the play space 
     */
    private void returnSelectedTetrino() {
    		// TODO
    	
    		// TESTING - Simulates returning mSelectedTetrino to the inventory and grabbing next tetrino in line 
    		mInventorySimulationArray.add(mSelectedTetrino);
    		getNextTetrinoFromInventory();
    }
    
    private void getNextTetrinoFromInventory() {
    		mSelectedTetrino = mInventorySimulationArray.get(0);
    }
    
    private void getNextTetrinoFromPlayed() {
    	int nextTetrinoIndex = mPlayedTetrinos.indexOf(mSelectedTetrino) + 1;
		mSelectedTetrino = mPlayedTetrinos.get(nextTetrinoIndex);
    }
	
	/**
	 * Moves the Tetrino within the play space
	 * @param tetrino
	 * @param selectedTetrinoXCoordinate
	 * @param selectedTetrinoYCoordinate
	 */
	private void moveTetrino(Tetrino tetrino, int selectedTetrinoXCoordinate, int selectedTetrinoYCoordinate) {
		for (int i = 0; i < 4; ++i) {
			mSelectedTetrinoXCoordinate = selectedTetrinoXCoordinate + tetrino.getX(i);
			mSelectedTetrinoYCoordinate = selectedTetrinoYCoordinate - tetrino.getY(i);
			
			if (selectedTetrinoXCoordinate < 0 
				|| selectedTetrinoXCoordinate >= playSpaceWidth 
				|| selectedTetrinoYCoordinate < 0
				|| selectedTetrinoYCoordinate >= playSpaceHeight) {
				return;
			}
			if (tetrinoTypeAt(selectedTetrinoXCoordinate, selectedTetrinoYCoordinate) != TetrinoType.NONE) {
				return;
			}
		}
		
		mSelectedTetrino = tetrino;
		repaint();
	}
	
	/**
	 * Sets the Tetrinos to the play space
	 * @param tetrino
	 */
	private void placeTetrino() {
		// TODO
		
		// TESTING - replaces mSelectedTetrino with next first item in mInventorySimulationArray
		mPlayedTetrinos.add(mSelectedTetrino);
		getNextTetrinoFromInventory();
		repaint();
	}

    class Adapter extends KeyAdapter {
	    	/**
	    	 * Controls the movement of the Tetrinos
	    	 * @param keyEvent
	    	 */
	    	public void keyPressed(KeyEvent keyEvent) {
	
	    		if (!mIsListening || mSelectedTetrino.getShape() == TetrinoType.NONE) {
	    			return;
	    		}
	
	    		int keycode = keyEvent.getKeyCode();
	
	    		switch (keycode) {
	    		case KeyEvent.VK_TAB:
	    			getNextTetrinoFromPlayed();
	    			break;
	    		case KeyEvent.VK_LEFT:
	    			moveTetrino(mSelectedTetrino, mSelectedTetrinoXCoordinate - 1, mSelectedTetrinoYCoordinate);
	    			break;
	    		case KeyEvent.VK_RIGHT:
	    			moveTetrino(mSelectedTetrino, mSelectedTetrinoXCoordinate + 1, mSelectedTetrinoYCoordinate);
	    			break;
	    		case KeyEvent.VK_DOWN:
	    			moveTetrino(mSelectedTetrino, mSelectedTetrinoXCoordinate, mSelectedTetrinoYCoordinate + 1);
	    			break;
	    		case KeyEvent.VK_UP:
	    			moveTetrino(mSelectedTetrino, mSelectedTetrinoXCoordinate, mSelectedTetrinoYCoordinate - 1);
	    			break;
	    		case KeyEvent.VK_SPACE:
	    			moveTetrino(mSelectedTetrino.rotateRight(), mSelectedTetrinoXCoordinate, mSelectedTetrinoYCoordinate);
	    			break;
	    		case KeyEvent.VK_L:
	    			moveTetrino(mSelectedTetrino.rotateLeft(), mSelectedTetrinoXCoordinate, mSelectedTetrinoYCoordinate);
	    			break;
	    		case KeyEvent.VK_ENTER:
	    			placeTetrino();
	    			break;
	    		case KeyEvent.VK_BACK_SPACE:
	    			returnSelectedTetrino();
	    			break;
	    		default:
	    			break;
	    		}
	    	}
    }
}
