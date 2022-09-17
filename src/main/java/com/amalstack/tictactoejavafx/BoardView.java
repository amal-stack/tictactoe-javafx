package com.amalstack.tictactoejavafx;

/**
 * A read-only view of {@link Board}.
 * @author Amal Krishna
 */
public final class BoardView {
    private final Board board;

    public BoardView(Board board) {
        this.board = board;
    }

    public Symbol getBoardSymbolAt(int row, int col) {
        return board.getSymbolAt(row, col);
    }
}
