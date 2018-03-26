package com.igorternyuk.snake;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by igor on 26.03.18.
 */
public class ResourceManager {
    public static final int TILE_SIZE = 16;
    private static final String SPRITE_LOADING_ERROR_MESSAGE = "Could not load the sprite set";
    private static final int NUMBER_OF_IMAGES = 4;
    private static final String PATH_TO_SNAKE_SPRITE_SET = "img/snakeImages.png";
    private static ResourceManager instance = null;
    private BufferedImage spriteSet = null;
    private BufferedImage[] imagesOfGameEntities = null;

    private ResourceManager() {
        try {
            this.spriteSet = ImageIO.read(getClass().getClassLoader().getResource(PATH_TO_SNAKE_SPRITE_SET));
            this.imagesOfGameEntities = createImagesOfGameEntities();
        } catch (IOException ex) {
            System.out.println(SPRITE_LOADING_ERROR_MESSAGE);
            System.out.println(ex.getMessage());
        }
    }

    public static synchronized ResourceManager getInstance() {
        if (instance == null) {
            instance = new ResourceManager();
        }
        return instance;
    }

    private BufferedImage[] createImagesOfGameEntities() {
        final BufferedImage[] images = new BufferedImage[NUMBER_OF_IMAGES];
        for (int x = 0; x < NUMBER_OF_IMAGES; ++x) {
            images[x] = spriteSet.getSubimage(x * TILE_SIZE, 0, TILE_SIZE, TILE_SIZE);
        }
        return images;
    }

    public Image getSnakeSegmentImage() {
        return SwingFXUtils.toFXImage(this.imagesOfGameEntities[0], null);
    }

    public Image getSnakeHeadImage() {
        return SwingFXUtils.toFXImage(this.imagesOfGameEntities[1], null);
    }

    public Image getFoodImage() {
        return SwingFXUtils.toFXImage(this.imagesOfGameEntities[2], null);
    }

    public Image getPoisonImage() {
        return SwingFXUtils.toFXImage(this.imagesOfGameEntities[3], null);
    }

    public void dispose() {
        instance = null;
    }
}