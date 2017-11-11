package main.gameObjects;

import java.util.Random;

import main.enums.TetrinoType;

public class Tetrino {

    private TetrinoType mShape;
    private int mCoordinates[][];
    private int[][][] mCoordinatesTable;
    
    private Random mRandom;

    public Tetrino(TetrinoType tetrinoType) {
    		mCoordinates = new int[4][2];
    		setTetrinoType(tetrinoType);
    }
    
    public Tetrino() {

        mCoordinates = new int[4][2];
        setTetrinoType(TetrinoType.NONE);

    }

    public void setTetrinoType(TetrinoType tetrinoType) {

        mCoordinatesTable = new int[][][] {
                { {  0,  0 }, {  0,  0 }, {  0,  0 }, {  0,  0 } },
                { {  0, -1 }, {  0,  0 }, { -1,  0 }, { -1,  1 } },
                { {  0, -1 }, {  0,  0 }, {  1,  0 }, {  1,  1 } },
                { {  0, -1 }, {  0,  0 }, {  0,  1 }, {  0,  2 } },
                { { -1,  0 }, {  0,  0 }, {  1,  0 }, {  0,  1 } },
                { {  0,  0 }, {  1,  0 }, {  0,  1 }, {  1,  1 } },
                { { -1, -1 }, {  0, -1 }, {  0,  0 }, {  0,  1 } },
                { {  1, -1 }, {  0, -1 }, {  0,  0 }, {  0,  1 } }
        };

        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < 2; ++j) {
                mCoordinates[i][j] = mCoordinatesTable[tetrinoType.ordinal()][i][j];
            }
        }
        
        mShape = tetrinoType;
    }

    private void setX(int i, int x) { 
    		mCoordinates[i][0] = x;   	
    }
    
    private void setY(int i, int y) { 
    		mCoordinates[i][1] = y; 
    }
    
    public int getX(int i) {
    		return mCoordinates[i][0];
    }
    
    public int getY(int i) {
    		return mCoordinates[i][1];
    }
    
    public TetrinoType getShape()  {
    		return mShape;
    }
    
    public void setRandomTetrino() {
    		mRandom = new Random();
    		int x = Math.abs(mRandom.nextInt()) % 7 + 1;
    		TetrinoType[] values = TetrinoType.values();
    		setTetrinoType(values[x]);
    }

    public int minX() {
        int m = mCoordinates[0][0];
        
        for (int i=0; i < 4; i++) {
            m = Math.min(m, mCoordinates[i][0]);
        }
        
        return m;
    }

    public int minY() {
        int m = mCoordinates[0][1];
        
        for (int i=0; i < 4; i++) {
            m = Math.min(m, mCoordinates[i][1]);
        }
        
        return m;
    }

    public Tetrino rotateLeft() {
        if (mShape == TetrinoType.S) {
            return this;
        }

        Tetrino result = new Tetrino();
        result.mShape = mShape;

        for (int i = 0; i < 4; ++i) {
            result.setX(i,  getY(i));
            result.setY(i, -getX(i));
        }
        
        return result;
    }

    public Tetrino rotateRight() {
        if (mShape == TetrinoType.S)
            return this;

        Tetrino result = new Tetrino();
        result.mShape = mShape;

        for (int i = 0; i < 4; ++i) {
            result.setX(i, -getY(i));
            result.setY(i,  getX(i));
        }
        
        return result;
    }
}