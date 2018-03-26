package com.igorternyuk.snake.model;

import com.igorternyuk.snake.ResourceManager;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.igorternyuk.snake.ResourceManager.TILE_SIZE;

/**
 * Created by igor on 26.03.18.
 */
class Poison {
    private static final ResourceManager rm = ResourceManager.getInstance();
    private final Game game;
    private List<Point> positions = new ArrayList<>(Game.FIELD_WIDTH * Game.FIELD_HEIGHT / 2);
    private Random random = new Random();

    Poison(Game game) {
        this.game = game;
        reset();
    }

    void reset() {
        this.positions.clear();
        this.positions.add(getRandomPosition());
    }

    private Point getRandomPosition() {
        Point position = new Point(-1, -1);
        while (true) {
            final int randX = this.random.nextInt(Game.FIELD_WIDTH);
            final int randY = this.random.nextInt(Game.FIELD_HEIGHT);
            final Point newPillPosition = new Point(randX, randY);
            boolean isCollisionWithSnake = game.getSnake().getSegments().stream()
                    .anyMatch(segment -> segment.equals(newPillPosition));
            boolean isCollisionWithFood = game.getFood().getPosition().equals(newPillPosition);
            if (!isCollisionWithSnake && !isCollisionWithFood) {
                return newPillPosition;
            }
        }
    }

    void addNew() {
        this.positions.add(getRandomPosition());
    }

    List<Point> getPositions() {
        return Collections.unmodifiableList(this.positions);
    }

    void render(final GraphicsContext gc) {
        this.positions.forEach(pill -> {
            gc.drawImage(rm.getPoisonImage(), pill.getX() * TILE_SIZE,
                    pill.getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        });
    }
}
