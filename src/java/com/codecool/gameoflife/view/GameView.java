package com.codecool.gameoflife.view;

import com.codecool.gameoflife.model.Cell;
import com.codecool.gameoflife.model.GameBoard;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameView extends HBox {

    private GridPane gameBoardView = new GridPane();

    public GameView() {
        this.getChildren().add(gameBoardView);
    }

    public void setGameBoardView(GameBoard gameBoard) {
        Cell[][] board = gameBoard.getBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                gameBoardView.add(new CellView(board[i][j]), i, j);
            }
        }
        this.gameBoardView.setGridLinesVisible(true);
    }
}
