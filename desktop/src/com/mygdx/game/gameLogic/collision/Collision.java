package com.mygdx.game.gameLogic.collision;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.gameEngine.collision.CollisionManager;
import com.mygdx.game.gameEngine.entity.Colliable;
import com.mygdx.game.gameEngine.entity.Entity;
import com.mygdx.game.gameEngine.entity.EntityManager;
import com.mygdx.game.gameEngine.utils.SoundsManager;
import com.mygdx.game.gameLogic.entity.Bullet;
import com.mygdx.game.gameLogic.entity.Enemy;
import com.mygdx.game.gameLogic.entity.EntityEnum;
import com.mygdx.game.gameLogic.entity.EntityFactory;
import com.mygdx.game.gameLogic.entity.Explosion;
import com.mygdx.game.gameLogic.entity.Player;

public class Collision {
    private static Collision instance;


    public static Collision getInstance() {
        if(instance==null)
        {
            instance = new Collision();
        }
        return instance;
    }
    public int checkCollision(Player player, int score, SoundsManager soundsManager) {
        CollisionManager collisionManager = CollisionManager.getInstance();
        EntityManager entityList = EntityManager.getInstance();
        EntityFactory entityCreation = EntityFactory.getInstance();

        // boolean collisionDetected = collisionManager.checkCollision();
        
        // if (collisionDetected) {
        //     // Handle collision here
        //     System.out.println("Collision detected!");
        //     // Perform actions like stopping the game, reducing player health, etc.
        // } else {
        //     // No collision detected
        //     System.out.println("No collision detected.");
        //     // Perform other actions if needed
        // }
        List<Entity> addedEntities  = new ArrayList<>();

	        for(Entity e : entityList.getEntities()){
				if(e instanceof Enemy){
					if(e.getPosY() <= 80){
						player.setLives(player.getLives()-1);
						e.setDestroyFlag(true);
						//score -= 1;
						if(score < 0){
							score = 0;
						}
						soundsManager.play("death",1.0f);
						continue;
					}
				}

				// spawn explosion
				for(Entity otherE : entityList.getEntities()){
					if(e == otherE){
						continue;
					}
					if( e instanceof Bullet && otherE instanceof Enemy ){
						Rectangle a = ((Colliable)e).getRectBound();
						Rectangle b = ((Colliable)otherE).getRectBound();

						if(collisionManager.rectCollide(a,b)){
							if(((Bullet) e).getColor().toUpperCase().equals(((Enemy)otherE).getColor().toUpperCase())){
								score += 1;
								if(player.getLives() < 3){
									player.setLives(player.getLives()+1);
								}
								soundsManager.play("correct",1.0f);
								e.setDestroyFlag(true);
								otherE.setDestroyFlag(true);
								Explosion explosion = (Explosion) entityCreation.createEntity(EntityEnum.EXPLOSION,entityList,otherE,e);
								addedEntities.add(explosion);

							}
							else{
								Explosion explosion = (Explosion) entityCreation.createEntity(EntityEnum.EXPLOSION,entityList,otherE,e);
								addedEntities.add(explosion);
								e.setDestroyFlag(true);
								//score -= 1;
								if(score < 0){
									score = 0;
								}
								player.setLives(player.getLives()-1);
								soundsManager.play("death",1.0f);
							}
						}
					}
				} 
			}
			entityList.getEntities().addAll(addedEntities);
            return score;
    }
}
