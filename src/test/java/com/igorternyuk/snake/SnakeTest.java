package com.igorternyuk.snake;

import com.igorternyuk.snake.model.Direction;
import com.igorternyuk.snake.model.Game;
import com.igorternyuk.snake.model.Snake;
import org.junit.Test;

import java.awt.*;

import static com.igorternyuk.snake.model.Game.FIELD_HEIGHT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by igor on 25.03.18.
 */
public class SnakeTest {
    @Test
    public void testSnakeTurn() {
        final Game game = new Game();
        final Snake snake = game.getSnake();
        for (final Direction direction : Direction.values()) {
            snake.turn(direction);
            assertThat(snake.getDirection(), is(direction));
        }
    }

    @Test
    public void testSnakeMovement() {
        final Game game = new Game();
        final Snake snake = game.getSnake();
        snake.move();
        assertThat(snake.getSegments().size(), is(3));
        assertThat(snake.getSegments().get(0), is(new Point(3, FIELD_HEIGHT / 2)));
        assertThat(snake.getSegments().get(1), is(new Point(2, FIELD_HEIGHT / 2)));
        assertThat(snake.getSegments().get(2), is(new Point(1, FIELD_HEIGHT / 2)));
    }
}
