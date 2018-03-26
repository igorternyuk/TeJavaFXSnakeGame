package com.igorternyuk.snake.model;

import java.awt.*;

/**
 * Created by igor on 26.03.18.
 */
public enum Direction {
    RIGHT(1, 0), UP(0, -1), LEFT(-1, 0), DOWN(0, 1);

    private Point vector;

    Direction(final int dx, final int dy) {
        this.vector = new Point(dx, dy);
    }

    public Point getVector() {
        return this.vector;
    }
}
