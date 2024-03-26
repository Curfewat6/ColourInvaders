package com.mygdx.game.gameLogic.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.gameEngine.entity.Entity;

public class EntityFactory {

    public static Entity getEntity(EntityEnum entity, String texPath) {
        SpriteBatch batch = new SpriteBatch();
        switch (entity) {
//            case BULLET:
//                return new Bullet(texPath, 280,20,300,batch);
//            case TARGET:
//                float ranX = MathUtils.random(64,Gdx.graphics.getWidth()- 64);
//                float ranY = MathUtils.random(Gdx.graphics.getHeight()/2,Gdx.graphics.getHeight());
//                return new Target(texPath, ranX,ranY,300);
//            case PLAYER:
//                return new Player(texPath, 280,20,300);
            default:
                return null;
        }
    }
    
}
