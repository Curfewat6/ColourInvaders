package com.mygdx.game.gameLogic.entity;

import com.mygdx.game.gameEngine.entity.Entity;
import com.mygdx.game.gameEngine.entity.EntityManagement;
import com.mygdx.game.gameEngine.entity.EntityManager;

public class EntityCreation extends EntityManager{

    public static void createEntity(String[] args, EntityManagement manager ){
            for (String arg : args){
                if (arg.startsWith("Bullet")){
                    Entity bullet = (Entity) EntityFactory.getEntity(EntityEnum.BULLET, arg);
                    manager.addEntity(bullet);
                if (arg.startsWith("Target")) {
                    Entity target = (Entity) EntityFactory.getEntity(EntityEnum.TARGET, arg);
                    manager.addEntity(target);
                } else if (arg.startsWith("Player")) {
                    Entity player = (Entity) EntityFactory.getEntity(EntityEnum.PLAYER, arg);
                    manager.addEntity(player);
                }
            }
        }
    }
    
}
