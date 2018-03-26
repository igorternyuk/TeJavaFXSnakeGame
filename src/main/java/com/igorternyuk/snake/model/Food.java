package com.igorternyuk.snake.model;

import com.igorternyuk.snake.ResourceManager;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;
import java.util.Random;

import static com.igorternyuk.snake.ResourceManager.TILE_SIZE;

/**
 * Created by igor on 26.03.18.
 */

class Food {
    private static final ResourceManager rm = ResourceManager.getInstance();
    private final Game game;
    private Point position;
    private Random random = new Random();

    Food(final Game game) {
        this.game = game;
        reset();
    }

    void reset() {
        while (true) {
            final int randX = this.random.nextInt(Game.FIELD_WIDTH);
            final int randY = this.random.nextInt(Game.FIELD_HEIGHT);
            final Point newFoodPosition = new Point(randX, randY);
            boolean isCollisionWithSnake = game.getSnake().getSegments().stream()
                    .anyMatch(segment -> segment.equals(newFoodPosition));
            boolean isCollisionWithPoison = false;
            if (game.getPoison() != null) {
                isCollisionWithPoison = game.getPoison().getPositions().stream()
                        .anyMatch(pill -> pill.equals(newFoodPosition));
            }
            if (!isCollisionWithSnake && !isCollisionWithPoison) {
                this.position = newFoodPosition;
                break;
            }
        }
    }

    void eat() {
        reset();
        this.game.updateScore();
    }

    Point getPosition() {
        return this.position;
    }


    void render(final GraphicsContext gc) {
        gc.drawImage(rm.getFoodImage(), position.x * TILE_SIZE,
                position.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }
}
