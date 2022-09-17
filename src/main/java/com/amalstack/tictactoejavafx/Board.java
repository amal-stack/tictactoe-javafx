package com.amalstack.tictactoejavafx;

import java.util.Arrays;
import java.util.Objects;

/**
 * Represents the 3x3 game board where players will place their symbols during the game.
 * @author Amal Krishna
 */
public final class Board {
    private final Symbol[][] symbols = new Symbol[3][3];

    public Symbol getSymbolAt(int row, int col) {
        return symbols[row][col];
    }

    public void setSymbolAt(int row, int col, Symbol symbol) {
        symbols[row][col] = symbol;
    }

    public boolean isRowMarked(int row) {
        return symbols[row][0] != null
                && symbols[row][0] == symbols[row][1]
                && symbols[row][1] == symbols[row][2];
    }

    public boolean isColumnMarked(int col) {
        return symbols[0][col] != null
                && symbols[0][col] == symbols[1][col]
                && symbols[1][col] == symbols[2][col];
    }

    public boolean isLeftDiagonalMarked() {
        return symbols[0][0] != null
                && symbols[0][0] == symbols[1][1]
                && symbols[1][1] == symbols[2][2];
    }

    public boolean isRightDiagonalMarked() {
        return symbols[0][2] != null
                && symbols[0][2] == symbols[1][1]
                && symbols[1][1] == symbols[2][0];
    }

    public boolean isFull() {
        return Arrays.stream(symbols)
                .flatMap(Arrays::stream)
                .allMatch(Objects::nonNull);
    }
}
