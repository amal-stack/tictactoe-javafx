package com.amalstack.tictactoejavafx.event;

import java.util.EventListener;

/**
 * Listener methods for the game play event.
 * @author Amal Krishna
 */
@FunctionalInterface
public interface GamePlayListener extends EventListener {
    void onGamePlay(GamePlayEvent event);
}
