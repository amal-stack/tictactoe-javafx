package com.amalstack.tictactoejavafx.event;

import com.amalstack.tictactoejavafx.Game;

import java.util.EventObject;

/**
 * Encapsulates the event arguments for the event that is fired when a game is completed.
 * @author Amal Krishna
 */
public class GameCompletedEvent extends EventObject {
    private final Game.Result result;

    public GameCompletedEvent(Game.Result result) {
        super(result.getGame());
        this.result = result;
    }

    public Game.Result getGameResult() {
        return result;
    }
}

