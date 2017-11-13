package main.gameObjects;

import java.util.Random;

import main.enums.TetrinoType;

public class Tetrino {

    private TetrinoType mShape;
    private int mCoordinates[][];
    
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
        switch (tetrinoType) {
            case NONE:
                mCoordinates = new int[][]{ {  0,  0 }, {  0,  0 }, {  0,  0 }, {  0,  0 } };
                break;
            case S:
                mCoordinates = new int[][]{ {  0, -1 }, {  0,  0 }, { -1,  0 }, { -1,  1 } };
                break;
            case Z:
                mCoordinates = new int[][]{ {  0, -1 }, {  0,  0 }, {  1,  0 }, {  1,  1 } };
                break;
            case I:
                mCoordinates = new int[][]{ {  0, -1 }, {  0,  0 }, {  0,  1 }, {  0,  2 } };
                break;
            case T:
                mCoordinates = new int[][]{ { -1,  0 }, {  0,  0 }, {  1,  0 }, {  0,  1 } };
                break;
            case SQUARE:
                mCoordinates = new int[][]{ {  0,  0 }, {  1,  0 }, {  0,  1 }, {  1,  1 } };
                break;
            case J:
                mCoordinates = new int[][]{ { -1, -1 }, {  0, -1 }, {  0,  0 }, {  0,  1 } };
                break;
            case L:
                mCoordinates = new int[][]{ {  1, -1 }, {  0, -1 }, {  0,  0 }, {  0,  1 } };
                break;
            default:
                break;
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
    
    public int getY(int i) { return mCoordinates[i][1]; }
    
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

    public int maxX() {
        int m = mCoordinates[0][0];

        for (int i=0; i < 4; i++) {
            m = Math.max(m, mCoordinates[i][0]);
        }

        return m;
    }

    public int maxY() {
        int m = mCoordinates[0][1];

        for (int i=0; i < 4; i++) {
            m = Math.max(m, mCoordinates[i][1]);
        }

        return m;
    }

    public Tetrino rotateLeft() {
        if (mShape == TetrinoType.SQUARE) {
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
        if (mShape == TetrinoType.SQUARE)
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