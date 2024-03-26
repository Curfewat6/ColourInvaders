package com.mygdx.game.gameLogic.ai;
import com.mygdx.game.gameLogic.entity.Target;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.gameEngine.ai.AIManager;
import com.mygdx.game.gameEngine.entity.EntityManagement;
import com.mygdx.game.gameLogic.entity.Bullet;

public class AImovement {

    private EntityManagement entityManager;
    private AIManager aiManager;
    
    public void moveBulletUp(Bullet bullet) {
        bullet.setPosX(bullet.getPosX());
        bullet.setPosY(bullet.getPosY() + 1);
    }

    public void aiTargetMovement() {
         entityManager.getEntities().forEach(entity -> {
             if (aiManager.checkAI(entity) == true) {
                 if(entity instanceof Target) {
                     Target A = (Target) entity;
                     movementSet(A);
                }
            } 
         });	
     }
     public void aiBulletMovement() {
        entityManager.getEntities().forEach(entity -> {
            if (aiManager.checkAI(entity) == true) {
                if(entity instanceof Bullet) {
                    Bullet A = (Bullet) entity;
                    moveBulletUp(A);
               }
           } 
        });	
    }
    
     public void movementSet(Target droplet) {
    	
	 	float ranX = MathUtils.random(64,Gdx.graphics.getWidth() - 64);
	 	if(droplet.getPosY() > 0 ) {
	 		droplet.setPosY(droplet.getPosY() - droplet.getSpeed());
			
	 	}else if(droplet.getPosY() <= 0) {
	 		if(droplet.getSpeed() < 10) {
	 			droplet.setSpeed(droplet.getSpeed() + 2);
	 			droplet.setPosY(400);
	 			droplet.setPosX(ranX);
	 		}else {
	 			droplet.setPosY(400);
	 			droplet.setPosX(ranX);
	 		}
	 	}
    	
     }
    
     public void resetMovement(Target droplet) {
     	float ranX = MathUtils.random(64,Gdx.graphics.getWidth() - 64);
     	if(droplet.getSpeed() < 10) {
	 		droplet.setSpeed(droplet.getSpeed() + 2);
	 		droplet.setPosY(400);
	 		droplet.setPosX(ranX);
	 	}else {
	 		droplet.setPosY(400);
	 		droplet.setPosX(ranX);
	 	}
     }
    
}
