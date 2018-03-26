package com.igorternyuk.snake.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Random;

import static com.igorternyuk.snake.ResourceManager.TILE_SIZE;

/**
 * Created by igor on 26.03.18.
 */
public class Game {
    public static final int FIELD_WIDTH = 20;
    public static final int FIELD_HEIGHT = 20;
    private static final int ADD_POISON_SCORE = 5;
    private static final Font FONT = new Font("Arial", 20);
    private final Snake snake;
    private final Food food;
    private final Poison poison;
    private final Random random = new Random();
    private int score;
    private GameStatus gameStatus = GameStatus.PLAYING;


    public Game() {
        this.snake = new Snake(this);
        this.food = new Food(this);
        this.poison = new Poison(this);
    }

    public GameStatus getGameStatus() {
        return this.gameStatus;
    }

    public void setGameStatus(final GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void prepareNewGame() {
        this.snake.reset();
        this.food.reset();
        this.poison.reset();
        this.gameStatus = GameStatus.PLAYING;
    }

    void updateScore() {
        ++this.score;
        if (this.score > 0 && (this.score % ADD_POISON_SCORE) == 0) {
            this.poison.addNew();
        }
    }

    public Snake getSnake() {
        return this.snake;
    }

    Poison getPoison() {
        return this.poison;
    }

    Food getFood() {
        return this.food;
    }

    public void turnSnake(final Direction direction) {
        this.snake.turn(direction);
    }

    public void tick() {
        if (this.gameStatus.equals(GameStatus.PLAYING)) {
            this.snake.move();
        }
    }

    public void render(final GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        this.food.render(gc);
        this.poison.render(gc);
        this.snake.render(gc);
        gc.setFont(FONT);
        gc.setFill(Color.YELLOW);
        gc.fillText("Score: " + this.score, TILE_SIZE * FIELD_WIDTH / 2, 35);
        gc.fillText(this.gameStatus.toString(), TILE_SIZE * (FIELD_WIDTH / 2 - 1), TILE_SIZE * FIELD_HEIGHT / 2);
    }
}
