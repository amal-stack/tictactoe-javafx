package com.amalstack.tictactoejavafx.controller;

import com.amalstack.tictactoejavafx.Game;
import com.amalstack.tictactoejavafx.Player;
import com.amalstack.tictactoejavafx.Symbol;
import com.amalstack.tictactoejavafx.UserLabelClickMoveHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GameInputController {
    @FXML
    TextField player2textField;
    @FXML
    private TextField player1textField;

    public Game.Parameters getInput(Label[][] labels) {
        var handler = new UserLabelClickMoveHandler(labels);
        Player player1 = new Player(player1textField.getText(), Symbol.X, handler);
        Player player2 = new Player(player2textField.getText(), Symbol.O, handler);
        return new Game.Parameters(player1, player2, player1);
    }
}
