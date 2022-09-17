package com.amalstack.tictactoejavafx;

@FunctionalInterface
public interface MoveHandler {

    void handleMoveRequest(MoveRequest request);
}
