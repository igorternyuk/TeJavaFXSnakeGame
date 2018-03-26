package com.igorternyuk.snake;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class SnakeApp extends Application {
    private static final String TITLE_OF_PROGRAM = "JSnake";
    private static final int WINDOW_WIDTH = 320;
    private static final int WINDOW_HEIGHT = 375;
    private MainController controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("MainWindow.fxml"));
        Parent root = fxmlLoader.load();
        this.controller = fxmlLoader.getController();
        primaryStage.setTitle(TITLE_OF_PROGRAM);
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.setOnKeyReleased(this::onKeyReleased);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void onKeyReleased(final KeyEvent keyEvent) {
        final KeyCode code = keyEvent.getCode();
        if (code == KeyCode.RIGHT) {
            this.controller.turnSnakeRight();
        } else if (code == KeyCode.UP) {
            this.controller.turnSnakeUp();
        } else if (code == KeyCode.LEFT) {
            this.controller.turnSnakeLeft();
        } else if (code == KeyCode.DOWN) {
            this.controller.turnSnakeDown();
        } else if (code == KeyCode.ENTER) {
            this.controller.onBtnNewGameClicked();
        } else if (code == KeyCode.P) {
            this.controller.onBtnPauseClicked();
        } else if (code == KeyCode.Q) {
            this.controller.onBtnExitClicked();
        }
    }
}
