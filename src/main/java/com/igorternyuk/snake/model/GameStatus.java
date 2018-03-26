package com.igorternyuk.snake.model;

/**
 * Created by igor on 26.03.18.
 */
public enum GameStatus {
    PLAYING(""),
    PAUSE("Game paused"),
    GAME_OVER("Game over!!!");

    private String text;

    GameStatus(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }
}
