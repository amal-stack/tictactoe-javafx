package com.amalstack.tictactoejavafx;

/**
 * Encapsulates a single move made by a player for placing their symbol on the game board.
 * @param row The index of the row of the game board.
 * @param column The index of the column of the game board.
 * @author Amal Krishna
 */
public record Move(int row, int column) {
}
