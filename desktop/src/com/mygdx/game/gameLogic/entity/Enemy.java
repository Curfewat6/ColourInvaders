package com.mygdx.game.gameLogic.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.gameEngine.entity.Colliable;
import com.mygdx.game.gameEngine.utils.Setting;
import com.mygdx.game.gameLogic.lifecycle.EnemyAssetsFactory;

import java.util.Random;

public class Enemy extends Colliable {
    Vector2 vel;
    String color;
    public Enemy() {
        super(EnemyAssetsFactory.getInstance().getRandom(), Gdx.graphics.getWidth()/2 - 32, Gdx.graphics.getHeight() + 64, 0);
        Random random = new Random();
        posX = 50 + random.nextInt(500);

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
        posX += vel.x;
        posY += vel.y;
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
}
