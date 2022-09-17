package com.amalstack.tictactoejavafx;

/**
 * Represents a player who participates in the game.
 * @author Amal Krishna
 */
public final class Player {
    private final String name;
    private final Symbol symbol;
    private final MoveHandler moveHandler;

    public Player(String name, Symbol symbol, MoveHandler moveHandler) {
        this.name = name;
        this.symbol = symbol;
        this.moveHandler = moveHandler;
    }

    public String getName() {
        return name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void sendMoveRequest(MoveRequest request) {
        moveHandler.handleMoveRequest(request);
    }
}

