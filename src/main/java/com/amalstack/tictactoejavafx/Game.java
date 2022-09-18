package com.amalstack.tictactoejavafx;

import com.amalstack.tictactoejavafx.event.GameCompletedEvent;
import com.amalstack.tictactoejavafx.event.GameCompletedListener;
import com.amalstack.tictactoejavafx.event.GamePlayEvent;
import com.amalstack.tictactoejavafx.event.GamePlayListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * Represents an instance of a game.
 * @author Amal Krishna
 */
public class Game {
    private final Board board = new Board();
    private final Player player1;
    private final Player player2;
    private final List<GameCompletedListener> completedListeners = new ArrayList<>();
    private final List<GamePlayListener> playListeners = new ArrayList<>();
    private final Parameters parameters;
    private Player nextToPlay;

    public Game(Parameters parameters) {
        this.parameters = parameters;
        this.player1 = parameters.player1();
        this.player2 = parameters.player2();
        nextToPlay = parameters.first();
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void addCompletedListener(GameCompletedListener listener) {
        completedListeners.add(listener);
    }

    public void addPlayListener(GamePlayListener listener) {
        playListeners.add(listener);
    }

    public BoardView getBoardView() {
        return new BoardView(board);
    }

    public boolean isComplete() {
        return isWinning() || board.isFull();
    }

    private boolean isWinning() {
        return IntStream.range(0, 3)
                .anyMatch(n -> board.isRowMarked(n) || board.isColumnMarked(n))
                || board.isLeftDiagonalMarked()
                || board.isRightDiagonalMarked();
    }

    public void start() {
        nextTurn();
    }

    public Game newGame() {
        return new Game(parameters);
    }

    private void nextTurn() {
        if (isComplete()) {
            throw new IllegalStateException("Next turn requested for completed game.");
        }
        nextToPlay.sendMoveRequest(
                new MoveRequest(
                        nextToPlay,
                        getBoardView(),
                        this::receiveMove
                )
        );
    }

    private void receiveMove(Move move) {
        int moveRow = move.row();
        int moveColumn = move.column();
        if (board.getSymbolAt(moveRow, moveColumn) != null) {
            throw new IllegalArgumentException("MoveProvider returned move at already marked grid. ");
        }
        board.setSymbolAt(moveRow, moveColumn, nextToPlay.getSymbol());
        onGamePlay(move);
        if (isComplete()) {
            onGameCompleted();
            return;
        }
        switchTurn();
        nextTurn();
    }

    private void switchTurn() {
        nextToPlay = nextToPlay == player2
                ? player1
                : player2;
    }

    private void onGameCompleted() {
        for (var listener : completedListeners) {
            listener.onGameComplete(
                    new GameCompletedEvent(
                            new Result(this, isWinning() ? nextToPlay : null)
                    )
            );
        }
    }

    private void onGamePlay(Move move) {
        for (var listener : playListeners) {
            listener.onGamePlay(
                    new GamePlayEvent(
                            this,
                            nextToPlay,
                            move)
            );
        }
    }

    /**
     * Encapsulates the result of a game play.
     * @author Amal Krishna
     */
    public static class Result {
        private final Game game;
        private final Player winner;

        private Result(Game game, Player winner) {
            this.game = game;
            this.winner = winner;
        }

        public boolean hasWinner() {
            return winner != null;
        }

        public boolean isDraw() {
            return winner == null;
        }

        public Optional<Player> getWinner() {
            return Optional.ofNullable(winner);
        }

        public Game getGame() {
            return game;
        }
    }

    /**
     * The initialization parameters for the game.
     * @author Amal Krishna
     */
    public record Parameters(Player player1, Player player2, Player first) {
        public Parameters {
            if (player1 == player2) {
                throw new IllegalArgumentException("Player 1 and Player 2 must be two different players");
            }
            if (first == null) {
                first = player1;
            }
            if (first != player1 && first != player2) {
                throw new IllegalArgumentException("Player to start first must be either player 1 or player 2");
            }
        }
    }
}
