package com.mygdx.game.gameLogic.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.gameEngine.entity.Collidable;
import com.mygdx.game.gameEngine.utils.Setting;
import com.mygdx.game.gameLogic.lifecycle.EnemyAssetsFactory;

import java.util.Random;

public class Enemy extends Collidable {
    private Vector2 vel;
    private String color;
    public Enemy() {
        super(EnemyAssetsFactory.getInstance().getRandom(), Gdx.graphics.getWidth()/2, 500, 0);
        Random random = new Random();
        posX = 50 + random.nextInt(300);

        speed = -0.5f;

        switch (Setting.getInstance().difficult){
            case 0:
                speed = -0.5f;
                break;
            case 1:
                speed = -1.0f;
                break;
            case 2:
                speed = -1.5f;
                break;
        }

        vel = new Vector2(0,speed);
        color = texName.split("\\.")[0].toUpperCase();
    }


    @Override
    public void update() {
    }

    @Override
    public Rectangle getRectBound() {
        return new Rectangle(posX,posY,tex.getWidth(),tex.getHeight());
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setVel(Vector2 vel) {
        this.vel = vel;
    }
    public Vector2 getVel() {
        return vel;
    }
    @Override
    public void dispose() {
        tex.dispose();
    }
}
