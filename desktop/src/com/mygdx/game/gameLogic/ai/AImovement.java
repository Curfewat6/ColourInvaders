package com.mygdx.game.gameLogic.ai;

import com.mygdx.game.gameEngine.ai.AIManager;
import com.mygdx.game.gameEngine.entity.EntityManagement;
import com.mygdx.game.gameEngine.entity.EntityManager;
import com.mygdx.game.gameLogic.entity.Bullet;
import com.mygdx.game.gameLogic.entity.Enemy;

public class AImovement {

    private EntityManager entityManager;
    private AIManager aiManager;

    public void aiMovement(){
        entityManager = EntityManager.getInstance();
        aiEnemyMovement(entityManager);
        aiBulletMovement(entityManager);
    }

    public void aiEnemyMovement(EntityManagement entityManager) {
        aiManager = AIManager.getInstance();
        for (int i = 0; i < entityManager.getEntities().size(); i++) {
            if (aiManager.checkAI(entityManager.getEntities().get(i))
                    && entityManager.getEntities().get(i) instanceof Enemy) {
                Enemy enemy = (Enemy) entityManager.getEntities().get(i);
                enemy.setPosX(enemy.getPosX() + enemy.getVel().x);
                enemy.setPosY(enemy.getPosY() + enemy.getVel().y);
            }
        }

    }

    public void aiBulletMovement(EntityManagement entityManager) {
        aiManager = AIManager.getInstance();
        for (int i = 0; i < entityManager.getEntities().size(); i++) {
            if (aiManager.checkAI(entityManager.getEntities().get(i))
                    && entityManager.getEntities().get(i) instanceof Bullet) {
                Bullet bullet = (Bullet) entityManager.getEntities().get(i);
                bullet.setPosX(bullet.getPosX() + bullet.getVel().x);
                bullet.setPosY(bullet.getPosY() + bullet.getVel().y);
            }

        }
    }

}
