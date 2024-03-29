package com.mygdx.game.gameEngine.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class Entity {
    protected float posX, posY, speed, width, height;
    protected String entName;
    protected Color texColor;
    protected boolean isAI;

    protected boolean destroyFlag = false;


    public Entity(float posX, float posY, float speed, Color texColor) {
        this.posX = posX;
        this.posY = posY;
        this.speed = speed;
        this.texColor = texColor;
    }
    
    public Entity(float posX, float posY, float speed) {
        this.posX = posX;
        this.posY = posY;
        this.speed = speed;
    }
    
    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public float getSpeed() {
        return speed;
    }

    public void setPosX(float x) {
        posX = x;
    }

    public void setPosY(float y) {
        posY = y;
    }

    public void setSpeed(float dp) {
        speed = dp;
    }

    public boolean isDestroyFlag() {
        return destroyFlag;
    }

    public void setDestroyFlag(boolean destroyFlag) {
        this.destroyFlag = destroyFlag;
    }

    public abstract String getName();
	public abstract void dispose();

	public abstract void render(Batch batch);
	public abstract void create();
	public abstract void update();
}
