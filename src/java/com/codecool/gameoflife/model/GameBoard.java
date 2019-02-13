package com.codecool.gameoflife.model;

public class GameBoard {
    private Cell[][] board;
    private boolean borderLess;

    public Cell[][] getBoard() {
        return board;
    }

    public GameBoard(int boardSide) {
        this.borderLess = false;
        this.board = new Cell[boardSide][boardSide];
        this.populateTiles();
    }

    public GameBoard(Cell[][] board) {
        this.board = board;
    }

    public void toggleBorderLess() {
        this.borderLess = !borderLess;
    }

    private void populateTiles() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    public void randomizeBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j].setAlive(Math.random() > 0.5);
            }
        }
    }

    public void nextGeneration() {
        Cell [][] nextGen = new Cell[this.board.length][this.board[0].length];
        for (int i = 0; i < nextGen.length; i++) {
            for (int j = 0; j < nextGen[i].length; j++) {
                nextGen[i][j] = new Cell(this.cellLived(i, j));
            }
        }
        this.board =  nextGen;
    }

    private boolean cellLived(int x, int y) {
        int neighborsNumber = checkNeighbors(x, y);
        if (this.board[x][y].isAlive()) {
            return neighborsNumber == 2 || neighborsNumber == 3;
        } else {
            return neighborsNumber == 3;
        }
    }

    /**
     * Checks a neighboring cells and returns a number of alive ones.
     * Excludes the checked cell.
     * @param cellX - the X position of cell to check
     * @param cellY - the Y position of cell to check
     * @return number of alive cells.
     */
    public int checkNeighbors(int cellX, int cellY) {
        int aliveCount = 0;

        for (int offsetX = -1; offsetX < 2; offsetX++) {
            for (int offsetY = -1; offsetY < 2; offsetY++) {
                int neighborX = cellX + offsetX, neighborY = cellY + offsetY;

                if (offsetX == 0 && offsetY == 0) {
                    continue;
                }

                if (this.borderLess) {

                    if (neighborX < 0) {
                        neighborX = this.board.length - 1;
                    } else if (neighborX > this.board.length - 1) {
                        neighborX = 0;
                    }

                    if (neighborY < 0) {
                        neighborY = this.board[0].length - 1;
                    } else if (neighborY > this.board[0].length - 1) {
                        neighborY = 0;
                    }

                } else {

                    if (neighborX < 0 || neighborX > this.board.length - 1||
                        neighborY < 0 || neighborY > this.board[0].length - 1) {
                        continue;
                    }
                }

                if (this.board[neighborX][neighborY].isAlive()) {
                    aliveCount++;
                }
            }
        }
        return aliveCount;
    }

}
