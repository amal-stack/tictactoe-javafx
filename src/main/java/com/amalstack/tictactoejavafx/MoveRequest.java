package com.amalstack.tictactoejavafx;

import java.util.function.Consumer;

public final class MoveRequest {
    private final BoardView boardView;
    private final Player player;
    private final Consumer<Move> onRespond;
    private boolean responded = false;

    MoveRequest(Player player, BoardView boardView, Consumer<Move> onRespond) {
        this.player = player;
        this.boardView = boardView;
        this.onRespond = onRespond;
    }


    public BoardView getBoardView() {
        return boardView;
    }

    public Player getPlayer() {
        return player;
    }

    public void respond(Move move) {
        onRespond.accept(move);
        responded = true;
    }

    public boolean isServed() {
        return responded;
    }
}
