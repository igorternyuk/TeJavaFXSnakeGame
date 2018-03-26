package com.igorternyuk.snake.model;

import com.igorternyuk.snake.ResourceManager;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.igorternyuk.snake.ResourceManager.TILE_SIZE;

/**
 * Created by igor on 26.03.18.
 */
public class Snake {
    private static final ResourceManager rm = ResourceManager.getInstance();
    private static final int INITIAL_LENGTH = 3;
    private Game game;
    private List<Point> segments = new ArrayList<>();
    private Direction direction = Direction.RIGHT;

    Snake(final Game game) {
        this.game = game;
        init();
    }

    public List<Point> getSegments() {
        return Collections.unmodifiableList(this.segments);
    }

    public Direction getDirection() {
        return this.direction;
    }

    private Point getHead() {
        return this.segments.get(0);
    }

    void reset() {
        this.segments.clear();
        init();
        this.direction = Direction.RIGHT;
    }

    private void init() {
        for (int i = INITIAL_LENGTH - 1; i >= 0; --i) {
            this.segments.add(new Point(i, Game.FIELD_HEIGHT / 2));
        }
    }

    public void turn(final Direction direction) {
        if (Math.abs(this.direction.ordinal() - direction.ordinal()) != 2) {
            this.direction = direction;
        }
    }

    public void move() {
        final Point last = this.segments.get(this.segments.size() - 1);
        for (int i = this.segments.size() - 1; i > 0; --i) {
            this.segments.get(i).move(this.segments.get(i - 1).x, this.segments.get(i - 1).y);
        }
        final Point head = this.segments.get(0);
        head.translate(this.direction.getVector().x, this.direction.getVector().y);
        checkFieldBounds();
        if (isSelfCrossing() || checkPoison()) {
            this.game.setGameStatus(GameStatus.GAME_OVER);
        }
        if (checkFood()) {
            this.segments.add(new Point(last));
        }
    }

    private void checkFieldBounds() {
        final Point head = this.segments.get(0);
        if (head.getX() < 0) {
            head.translate(Game.FIELD_WIDTH, 0);
        } else if (head.getX() > Game.FIELD_WIDTH - 1) {
            head.translate(-Game.FIELD_WIDTH, 0);
        }

        if (head.getY() < 0) {
            head.translate(0, Game.FIELD_HEIGHT);
        } else if (head.getY() > Game.FIELD_HEIGHT - 1) {
            head.translate(0, -Game.FIELD_HEIGHT);
        }
    }

    private boolean checkFood() {
        final Food food = this.game.getFood();
        if (food.getPosition().equals(getHead())) {
            food.eat();
            return true;
        }
        return false;
    }

    private boolean checkPoison() {
        return this.game.getPoison().getPositions().stream().anyMatch(pill -> pill.equals(getHead()));
    }

    private boolean isSelfCrossing() {
        if (this.segments.size() <= 4) return false;
        final Point head = this.segments.get(0);
        return this.segments.subList(1, segments.size()).stream().anyMatch(segment -> segment.equals(head));

    }

    public void render(final GraphicsContext gc) {
        this.segments.forEach(segment -> gc.drawImage(getHead().equals(segment)
                        ? rm.getSnakeHeadImage()
                        : rm.getSnakeSegmentImage(),
                segment.getX() * TILE_SIZE, segment.getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE));
    }
}
