package main;

import main.gameObjects.GameBoard;

public class Main {

	public static void main(String[] args) {
		GameBoard gameBoard = GameBoard.getGameBoardInstance();
		gameBoard.init();
	}

}
