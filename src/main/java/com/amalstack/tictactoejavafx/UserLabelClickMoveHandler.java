package com.amalstack.tictactoejavafx;

import javafx.scene.control.Label;

public class UserLabelClickMoveHandler implements MoveHandler {
    private MoveRequest request;

    public UserLabelClickMoveHandler(Label[][] labels) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int row = i, col = j;
                labels[i][j].setOnMouseClicked(mouseEvent -> {
                    if (request != null
                            && !request.isServed()
                            && labels[row][col].getText().isBlank()) {
                        request.respond(new Move(row, col));
                    }
                });
            }
        }
    }

    @Override
    public void handleMoveRequest(MoveRequest request) {
        this.request = request;
    }
}
