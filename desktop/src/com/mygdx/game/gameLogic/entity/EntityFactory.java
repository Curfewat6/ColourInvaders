package com.mygdx.game.gameLogic.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Interpolation.Exp;
import com.mygdx.game.gameEngine.entity.Entity;
import com.mygdx.game.gameEngine.entity.EntityManagement;

public class EntityFactory {

    private Player player;
    private Cannon cannon;
    private Enemy enemy;
    private Explosion explosion;

    private static EntityFactory instance;

    public static EntityFactory getInstance() {
        if(instance==null)
        {
            instance = new EntityFactory();
        }
        return instance;
    }

    public Entity createEntity(EntityEnum entity, Vector2 bulletPos, Vector2 angleDir, String color) {
        switch (entity) {
            case BULLET:
                return new Bullet(bulletPos.x,bulletPos.y,angleDir.x,angleDir.y,color);
            default:
                return null;
        }
    }
    public Entity createEntity(EntityEnum entity, EntityManagement entitylist) {
        switch (entity) {
            case CANNON:
                cannon = new Cannon();
                entitylist.addEntity(cannon);
                return cannon;
            case PLAYER:
                player = new Player();
                entitylist.addEntity(player);
                return player;
            case ENEMY:
                enemy = new Enemy();
                entitylist.addEntity(enemy);
                return enemy;
            default:
                return null;
        }
    }
    public Entity createEntity(EntityEnum entity, EntityManagement entitylist, Entity otherE, Entity e) {
        switch (entity) {
            case EXPLOSION:
                if(((Bullet) e).getColor().equals(((Enemy)otherE).getColor())){
                    explosion = new Explosion(otherE.getPosX() - 32,otherE.getPosY() - 32,((Bullet)e).getColor());
                    //entitylist.addEntity(explosion);
                    return explosion;   
                }
                else{
                    explosion = new Explosion(e.getPosX() - 32,e.getPosY() - 32,((Bullet)e).getColor());
                    //entitylist.addEntity(explosion);
                    return explosion;   
                }
                
            default:
                return null;
        }
    }
    
}
