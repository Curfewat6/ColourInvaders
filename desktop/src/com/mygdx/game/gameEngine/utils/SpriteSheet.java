package com.mygdx.game.gameEngine.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * The SpriteSheet class represents a sprite sheet and provides methods for managing and rendering frames from it.
 */
public class SpriteSheet {

    private Texture texture;
    private int rows;
    private int cols;

    /** An array of TextureRegion objects representing individual frames in the sprite sheet. */
    private TextureRegion[] frames;

    /** The elapsed time since the animation started. */
    private float countTime = 0;

    /** The starting frame index for animation playback. */
    private int from;

    /** The ending frame index for animation playback. */
    private int to;

    /** The current frame index being displayed. */
    private int current;

    /** The total time duration for each frame during animation playback. */
    private float time;

    /** A flag indicating whether the animation should loop. */
    private boolean loop;

    /** The width of each frame in the sprite sheet. */
    private int width;

    /** The height of each frame in the sprite sheet. */
    private int height;

    /**
     * Constructs a SpriteSheet object with the specified texture, number of rows, and number of columns.
     *
     * @param texture The texture containing the sprite sheet.
     * @param rows    The number of rows in the sprite sheet.
     * @param cols    The number of columns in the sprite sheet.
     */
    public SpriteSheet(Texture texture, int rows, int cols) {
        this.texture = texture;
        this.rows = rows;
        this.cols = cols;

        frames = new TextureRegion[rows * cols];
        TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth() / cols, texture.getHeight() / rows); // split the sprite sheet
        width = texture.getWidth() / cols;
        height = texture.getHeight() / rows;
        int ide = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                frames[ide] = tmp[i][j];
                ide++;
            }
        }
    }

    /**
     * Retrieves a specific frame from the sprite sheet.
     *
     * @param frame The index of the frame to retrieve.
     * @return The TextureRegion representing the specified frame.
     */
    public TextureRegion getTexture(int frame) {
        return frames[frame];
    }

    /**
     * Retrieves the current frame being displayed.
     *
     * @return The TextureRegion representing the current frame.
     */
    public TextureRegion getCurrentFrame() {
        return frames[current];
    }

    /**
     * Flips all frames in the sprite sheet horizontally or vertically.
     *
     * @param flipX True to flip horizontally, false otherwise.
     */
    void flip(boolean flipX) {
        int ide = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                frames[ide].flip(flipX, false);
                ide++;
            }
        }
    }

    public int getCurrent(){
        return current;
    }

    /**
     * Sets the parameters for playing an animation sequence.
     *
     * @param from The starting frame index.
     * @param to   The ending frame index.
     * @param time The total time duration for each frame during animation playback.
     * @param loop True to loop the animation, false otherwise.
     */
    public void setPlay(int from, int to, float time, boolean loop) {
        this.from = from;
        this.to = to;
        this.time = time;
        this.loop = loop;
        current = from;
    }

    /**
     * Plays the animation sequence based on the specified parameters.
     */
    public void play() {
        countTime += Gdx.graphics.getDeltaTime();
        if (countTime >= time) {
            countTime = 0;
            current++;
            if (current > to && loop) {
                current = from;
            } else if (current > to && !loop) {
                current = to;
            }
        }
    }

    /**
     * Draws the current frame at the specified position with optional scaling.
     *
     * @param batch   The Batch object used for drawing.
     * @param pos     The position to draw the frame.
     * @param scaleX  The horizontal scaling factor.
     * @param scaleY  The vertical scaling factor.
     */
    public void draw(Batch batch, Vector2 pos, float scaleX, float scaleY) {
        batch.draw(frames[current], pos.x, pos.y, width / 2, height / 2, width / cols, height, scaleX, scaleY, 0);
    }

    /**
     * Retrieves the width of each frame in the sprite sheet.
     *
     * @return The width of each frame.
     */
    public float getWidth() {
        return width;
    }

    /**
     * Retrieves the height of each frame in the sprite sheet.
     *
     * @return The height of each frame.
     */
    public float getHeight() {
        return height;
    }

    /**
     * Disposes of the underlying texture.
     */
    public void dispose() {
        texture.dispose();
    }
}
