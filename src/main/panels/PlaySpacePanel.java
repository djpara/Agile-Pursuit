package main.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import jdk.nashorn.internal.objects.Global;
import main.drawingUtil.DrawingComponent2;
import main.enums.TetrinoType;
import main.gameObjects.GameBoardManager;
import main.gameObjects.Tetrino;
import main.globalVariables.GlobalVariables;

public class PlaySpacePanel extends JPanel {

	private static final long serialVersionUID = 2L;
	
	// Set play space height and width allows for calculating block sizes. 24x31 playspace creates a block of 30x30
	private final int mPlaySpaceWidth = 18;
    private final int mPlaySpaceHeight = 22; //31;
	
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
		mIsListening = true;
		
		mGameBoardManager = gameBoardManager;
		mParentPanel = parentPanel;
		
		mPlayedTetrinos = new ArrayList<Tetrino>();
		mInventorySimulationArray = new ArrayList<Tetrino>();
		
		configurePanel();
		drawCross();
		
		// TESTING = Fill the array
		Tetrino newTetrino;
		for (int i = 0; i < 15; ++i) {
			newTetrino = new Tetrino();
			newTetrino.setRandomTetrino();
			mInventorySimulationArray.add(newTetrino);
		}

		getNextTetrinoFromInventory();
		
		mPlaySpace = new TetrinoType[ mPlaySpaceWidth * mPlaySpaceHeight ];
		
		addKeyListener(new Adapter());

		clearPlaySpace();
		
		repaint();
	}
	
	/**
	 * Configures the panel - background color, sets the size
	 */
	private void configurePanel() {
		setBackground(GlobalVariables.DEFAULT_BACK);

		setPreferredSize(new Dimension(GlobalVariables.PLAY_SPACE_PANEL_WIDTH, GlobalVariables.PLAY_SPACE_PANEL_HEIGHT));
	}
	
	private void drawCross(){
		DrawingComponent2 DC = new DrawingComponent2();
		DC.setPreferredSize(new Dimension(GlobalVariables.PLAY_SPACE_PANEL_WIDTH, GlobalVariables.PLAY_SPACE_PANEL_HEIGHT));
		add(DC);
	}
	
	/**
	 * Calculates the tetrino block width
	 * @return
	 */
	private int getBlockWidth() {
		return (int) getSize().getWidth()/mPlaySpaceWidth;
	}
	
	/**
	 * Calculates the tetrino block height
	 * @return
	 */
	private int getBlockHeight() {
		return (int) getSize().getHeight()/mPlaySpaceHeight;
	}
	
	/**
	 * Gets the tetrino type currently at x, y coordinate
	 * @param x
	 * @param y
	 * @return
	 */
	TetrinoType tetrinoTypeAt(int x, int y) {
		return mPlaySpace[(y * mPlaySpaceWidth) +  x];
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		Dimension size = getSize();
		int playSpaceTop = (int) (size.getHeight() - mPlaySpaceHeight * getBlockHeight());

		// Draws the entire playspace - does not draw NONE tiles
		for (int i = 0; i < mPlaySpaceHeight; ++i) {
			 for (int j = 0; j < mPlaySpaceWidth; ++j) {
	                TetrinoType tetrinoType = tetrinoTypeAt(j, mPlaySpaceHeight - i - 1);
	                if (tetrinoType != TetrinoType.NONE) {
	                    drawTetrinoBlock(g, 0 + j * getBlockWidth(),
	                            playSpaceTop + i * getBlockHeight(), getColorForTetrinoType(tetrinoType));
	                }
	          }
		}

		// Draws the current tetrino piece that's being moved around
		TetrinoType tetrinoType = mSelectedTetrino.getShape(); 
		if (tetrinoType != TetrinoType.NONE) {
            for (int i = 0; i < 4; ++i) {
                int x = mSelectedTetrinoXCoordinate + mSelectedTetrino.getX(i);
                int y = mSelectedTetrinoYCoordinate - mSelectedTetrino.getY(i);
                
                int drawX = 0 + x * getBlockWidth();
                int drawY = playSpaceTop + (mPlaySpaceHeight - y) * getBlockHeight();
                
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
        for (int i = 0; i < mPlaySpaceWidth * mPlaySpaceHeight; ++i) {
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
    		repaint();
    }
    
    /**
     * 
     */
    private void getNextTetrinoFromInventory() {

    	// TESTING - 
    	mSelectedTetrino = mInventorySimulationArray.remove(0);
    	
    	mSelectedTetrinoXCoordinate = mPlaySpaceWidth / 2 + 1;
    	mSelectedTetrinoYCoordinate = mPlaySpaceHeight / 2 + 1;
    		
    }
    
    /**
     * 
     */
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

		int minX = Math.min(selectedTetrinoXCoordinate, selectedTetrinoXCoordinate + tetrino.minX());
		int minY = Math.min(selectedTetrinoYCoordinate, selectedTetrinoYCoordinate + tetrino.minY());

		int maxX = Math.max(selectedTetrinoXCoordinate, selectedTetrinoXCoordinate + tetrino.maxX());
		int maxY = Math.max(selectedTetrinoYCoordinate, selectedTetrinoYCoordinate + tetrino.maxY());

		if (minX < 0
			|| maxX == mPlaySpaceWidth
			|| minY == 0
			|| maxY == mPlaySpaceHeight + 1) {
			return;
		}

		mSelectedTetrinoXCoordinate = selectedTetrinoXCoordinate;
		mSelectedTetrinoYCoordinate = selectedTetrinoYCoordinate;
		
		mSelectedTetrino = tetrino;
		repaint();
	}
	
	/**
	 * Sets the Tetrinos to the play space
	 */
	private void placeTetrino() {
		// TODO
		
		for (int i = 0; i < 4; ++i) {
            int x = mSelectedTetrinoXCoordinate + mSelectedTetrino.getX(i);
            int y = mSelectedTetrinoYCoordinate - mSelectedTetrino.getY(i) - 1;
            mPlaySpace[(y * mPlaySpaceWidth) + x] = mSelectedTetrino.getShape();
        }
		
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
	    			moveTetrino(mSelectedTetrino, mSelectedTetrinoXCoordinate, mSelectedTetrinoYCoordinate - 1);
	    			break;
	    		case KeyEvent.VK_UP:
	    			moveTetrino(mSelectedTetrino, mSelectedTetrinoXCoordinate, mSelectedTetrinoYCoordinate + 1);
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
