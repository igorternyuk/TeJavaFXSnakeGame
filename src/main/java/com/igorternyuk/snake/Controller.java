package com.igorternyuk.snake;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    @FXML
    private Canvas canvas;
    private GraphicsContext gc;

    public void initialize(URL location, ResourceBundle resources) {
        this.gc = this.canvas.getGraphicsContext2D();
    }
}
