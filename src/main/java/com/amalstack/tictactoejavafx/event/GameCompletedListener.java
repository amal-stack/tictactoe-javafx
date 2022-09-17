package com.amalstack.tictactoejavafx.event;

import java.util.EventListener;

/**
 * Listener methods for the game completed event.
 * @author Amal Krishna
 */
@FunctionalInterface
public interface GameCompletedListener extends EventListener {
    void onGameComplete(GameCompletedEvent event);
}

