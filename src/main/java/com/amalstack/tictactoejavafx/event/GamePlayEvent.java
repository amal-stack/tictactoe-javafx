package com.amalstack.tictactoejavafx.event;

import com.amalstack.tictactoejavafx.Game;
import com.amalstack.tictactoejavafx.Move;
import com.amalstack.tictactoejavafx.Player;

import java.util.EventObject;

/**
 * Encapsulates the event arguments for the game play event that is fired when a player makes a move.
 * @author Amal Krishna
 */
public class GamePlayEvent extends EventObject {
    private final Game game;
    private final Player player;
    private final Move move;

    public GamePlayEvent(
            Game game,
            Player player,
            Move move) {
        super(game);
        this.game = game;
        this.player = player;
        this.move = move;
    }

    public Game getGame() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }

    public Move getMove() {
        return move;
    }
}
