package com.igorternyuk.snake;

import com.igorternyuk.snake.model.Direction;
import com.igorternyuk.snake.model.Game;
import com.igorternyuk.snake.model.GameStatus;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by igor on 25.03.18.
 */
public class MainController implements Initializable {
    private static final String EXIT_DIALOG_WINDOW_MESSAGE = "Do you really want to exit?";
    private static final String EXIT_DIALOG_WINDOW_TITLE = "Confirm exit, please";
    private static final int FPS = 60;
    private static final double DELAY = 0.2;
    private final Game game = new Game();
    private double time = 0.0;
    private long oldNow = 0;
    @FXML
    private Canvas canvas;
    private GraphicsContext gc;
    private AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            double dt = (now - oldNow) * 1e-9;
            time += (oldNow == 0) ? (1.0 / FPS) : dt;
            oldNow = now;
            if (time >= DELAY) {
                game.tick();
                game.render(gc);
                time = 0.0;
            }
        }
    };

    public void initialize(URL location, ResourceBundle resources) {
        this.gc = this.canvas.getGraphicsContext2D();
        startTimer();
    }

    public void onBtnNewGameClicked() {
        this.game.prepareNewGame();
    }

    public void onBtnPauseClicked() {
        if (this.game.getGameStatus().equals(GameStatus.PAUSE)) {
            startTimer();
        } else if (this.game.getGameStatus().equals(GameStatus.PLAYING)) {
            stopTimer();
        }
    }

    private void startTimer() {
        timer.start();
        game.setGameStatus(GameStatus.PLAYING);
    }

    private void stopTimer() {
        timer.stop();
        oldNow = 0;
        game.setGameStatus(GameStatus.PAUSE);
        game.render(gc);
    }

    public void onBtnExitClicked() {
        final Alert alert = new Alert(Alert.AlertType.WARNING, EXIT_DIALOG_WINDOW_MESSAGE,
                ButtonType.YES, ButtonType.NO);
        alert.setTitle(EXIT_DIALOG_WINDOW_TITLE);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            Platform.exit();
            System.exit(0);
        } else {
            alert.close();
        }
    }

    void turnSnakeRight() {
        this.game.turnSnake(Direction.RIGHT);
    }

    void turnSnakeUp() {
        this.game.turnSnake(Direction.UP);
    }

    void turnSnakeLeft() {
        this.game.turnSnake(Direction.LEFT);
    }

    void turnSnakeDown() {
        this.game.turnSnake(Direction.DOWN);
    }
}
