package com.igorternyuk.snake;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by igor on 25.03.18.
 */
public class MainController {
    @FXML
    private Canvas canvas;
    private GraphicsContext gc;

    public void initialize(URL location, ResourceBundle resources) {
        this.gc = this.canvas.getGraphicsContext2D();
    }
}
