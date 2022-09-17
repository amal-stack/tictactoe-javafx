package com.amalstack.tictactoejavafx.controller;

import com.amalstack.tictactoejavafx.Game;
import com.amalstack.tictactoejavafx.Move;
import com.amalstack.tictactoejavafx.TicTacToeApplication;
import com.amalstack.tictactoejavafx.event.GameCompletedEvent;
import com.amalstack.tictactoejavafx.event.GamePlayEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;

import java.io.IOException;

public final class GameController {
    @FXML
    private Label player1Label;
    @FXML
    private Label player2Label;
    @FXML
    private Label statusLabel;

    @FXML
    private Label grid00;

    @FXML
    private Label grid01;

    @FXML
    private Label grid02;

    @FXML
    private Label grid10;

    @FXML
    private Label grid11;

    @FXML
    private Label grid12;

    @FXML
    private Label grid20;

    @FXML
    private Label grid21;

    @FXML
    private Label grid22;

    private Label[][] labels;

    private Game game;

    @FXML
    private void initialize() {
        labels = new Label[][]{
                {grid00, grid01, grid02},
                {grid10, grid11, grid12},
                {grid20, grid21, grid22}
        };
        initializeNewGame();
    }

    private void initializeNewGame() {
        createInputDialog().showAndWait().ifPresent(parameters -> {
            game = new Game(parameters);
            game.addPlayListener(this::updateLabels);
            game.addCompletedListener(this::onGameComplete);

            player1Label.setText(parameters.player1().getName());
            player2Label.setText(parameters.player2().getName());

            game.start();
        });
    }

    private void onGameComplete(GameCompletedEvent event) {
        Game.Result result = event.getGameResult();
        if (result.isDraw()) {
            statusLabel.setText("The game is draw.");
            return;
        }
        result.getWinner()
                .ifPresent(winner -> statusLabel.setText(
                        winner.getName() + " has won the game."));
    }

    private Dialog<Game.Parameters> createInputDialog() {
        Dialog<Game.Parameters> dialog = new Dialog<>();
        FXMLLoader loader = new FXMLLoader(TicTacToeApplication.class
                .getResource("game-input-view.fxml"));
        try {
            DialogPane pane = loader.load();
            dialog.setDialogPane(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        GameInputController controller = loader.getController();
        dialog.setResultConverter(buttonType -> controller.getInput(labels));
        return dialog;
    }

    private void updateLabels(GamePlayEvent event) {
        Move move = event.getMove();
        labels[move.row()][move.column()]
                .setText(event
                        .getPlayer()
                        .getSymbol()
                        .toString());
    }
}