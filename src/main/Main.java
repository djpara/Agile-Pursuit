package main;

import gameObjects.GameBoard;
import gameObjects.GridSpace;

public class Main {

	public static void main(String[] args) {
		GameBoard gameBoard = GameBoard.getGameBoardInstance();
		GridSpace gridspace = new GridSpace(10, 10);
	}

}
