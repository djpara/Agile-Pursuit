package gameObjects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import enums.TetrinoType;
import globalVariables.GlobalVariables;

public class PlaySpace extends JPanel implements ActionListener {

	private static final long serialVersionUID = 2L;
	
	private ArrayList<Tetrino> mPlayedTetrinos;
	private Tetrino mSelectedTetrino;
	private TetrinoType[] mPlaySpace;
	
	private int mSelectedTetrinoXCoordinate = 0;
	private int mSelectedTetrinoYCoordinate = 0;
	
	private boolean mIsListening;
	
	public PlaySpace(GameBoard gameboard) {
		mPlayedTetrinos = new ArrayList<Tetrino>();
		mSelectedTetrino = new Tetrino();
		
		// TESTING = Fill the array
		mPlayedTetrinos.add(new Tetrino(TetrinoType.I));
		mPlayedTetrinos.add(new Tetrino(TetrinoType.S));
		mPlayedTetrinos.add(new Tetrino(TetrinoType.Z));
		mPlayedTetrinos.add(new Tetrino(TetrinoType.T));
		mPlayedTetrinos.add(new Tetrino(TetrinoType.L));
		mPlayedTetrinos.add(new Tetrino(TetrinoType.J));
		mPlayedTetrinos.add(new Tetrino(TetrinoType.SQUARE));
		
		mPlaySpace = new TetrinoType[ GlobalVariables.PLAY_SPACE_WIDTH * GlobalVariables.PLAY_SPACE_HEIGHT ];
	}
	
	/**
	 * Calculates the tetrino block width
	 * @return
	 */
	private int getBlockWidth() {
		return (int) getSize().getWidth()/GlobalVariables.PLAY_SPACE_WIDTH;
	}
	
	/**
	 * Calculates the tetrino block height
	 * @return
	 */
	private int getBlockHeight() {
		return (int) getSize().getHeight()/GlobalVariables.PLAY_SPACE_HEIGHT;
	}
	
	/**
	 * Gets the Tetrino type currently at x, y coordinate
	 * @param x
	 * @param y
	 * @return
	 */
	TetrinoType tetrinoTypeAt(int x, int y) {
		return mPlaySpace[(y * GlobalVariables.PLAY_SPACE_WIDTH) +  x];
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		Dimension size = getSize();
		int playSpaceTop = (int) size.getHeight() - GlobalVariables.PLAY_SPACE_HEIGHT * getBlockHeight();
		
		for (int i = 0; i < GlobalVariables.PLAY_SPACE_HEIGHT; ++i) {
			 for (int j = 0; j < GlobalVariables.PLAY_SPACE_WIDTH; ++j) {
	                TetrinoType tetrinoType = tetrinoTypeAt(j, GlobalVariables.PLAY_SPACE_HEIGHT - i - 1);
	                if (tetrinoType != TetrinoType.NONE)
	                    drawTetrinoBlock(g, 0 + j * getBlockWidth(),
	                            playSpaceTop + i * getBlockHeight(), getColorForTetrinoType(tetrinoType));
	            }
		}
		
		TetrinoType tetrinoType = mSelectedTetrino.getShape(); 
		if (tetrinoType != TetrinoType.NONE) {
            for (int i = 0; i < 4; ++i) {
                int x = mSelectedTetrinoXCoordinate + mSelectedTetrino.getX(i);
                int y = mSelectedTetrinoYCoordinate - mSelectedTetrino.getY(i);
                drawTetrinoBlock(g, 0 + x * getBlockWidth(),
                        playSpaceTop + (GlobalVariables.PLAY_SPACE_HEIGHT - y - 1) * getBlockHeight(),
                        getColorForTetrinoType(tetrinoType));
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
	 * Clears the play space and moves all of the played Tetrinos to the loading dock 
	 */
    private void clearPlaySpace() {
        for (int i = 0; i < GlobalVariables.PLAY_SPACE_WIDTH * GlobalVariables.PLAY_SPACE_HEIGHT; ++i) {
            mPlaySpace[i] = TetrinoType.NONE;
        }
        
        moveTetrinoesToLoadingDock();
    }
    
    /**
     * Moves the Tetrinos to the loading dock from the play space 
     */
    private void moveTetrinoesToLoadingDock() {
    	// TODO
    }

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
			int nextTetrinoIndex = mPlayedTetrinos.indexOf(mSelectedTetrino) + 1;
			mSelectedTetrino = mPlayedTetrinos.get(nextTetrinoIndex);
		case KeyEvent.VK_LEFT:
			moveTetrino(mSelectedTetrino, mSelectedTetrinoXCoordinate - 1, mSelectedTetrinoYCoordinate);
			break;
		case KeyEvent.VK_RIGHT:
			moveTetrino(mSelectedTetrino, mSelectedTetrinoXCoordinate + 1, mSelectedTetrinoYCoordinate);
			break;
		case KeyEvent.VK_DOWN:
			moveTetrino(mSelectedTetrino.rotateRight(), mSelectedTetrinoXCoordinate, mSelectedTetrinoYCoordinate);
			break;
		case KeyEvent.VK_UP:
			moveTetrino(mSelectedTetrino.rotateLeft(), mSelectedTetrinoXCoordinate, mSelectedTetrinoYCoordinate);
			break;
		case KeyEvent.VK_ENTER:
			placeTetrino(mSelectedTetrino);
			break;
		default:
			break;
		}
	}
	
	/**
	 * Moves the Tetrino within the play space
	 * @param tetrino
	 * @param selectedTetrinoXCoordinate
	 * @param selectedTetrinoYCoordinate
	 */
	public void moveTetrino(Tetrino tetrino, int selectedTetrinoXCoordinate, int selectedTetrinoYCoordinate) {
		for (int i = 0; i < 4; ++i) {
			mSelectedTetrinoXCoordinate = selectedTetrinoXCoordinate + tetrino.getX(i);
			mSelectedTetrinoYCoordinate = selectedTetrinoYCoordinate - tetrino.getY(i);
			
			if (selectedTetrinoXCoordinate < 0 
				|| selectedTetrinoXCoordinate >= GlobalVariables.PLAY_SPACE_WIDTH 
				|| selectedTetrinoYCoordinate < 0
				|| selectedTetrinoYCoordinate >= GlobalVariables.PLAY_SPACE_HEIGHT) {
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
	private void placeTetrino(Tetrino tetrino) {
		// TODO
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO
	}

}
