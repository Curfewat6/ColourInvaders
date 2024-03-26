package com.mygdx.game.gameEngine.ai;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.gameEngine.entity.Colliable;
import com.mygdx.game.gameEngine.entity.Entity;
import com.mygdx.game.gameEngine.entity.EntityManagement;
import com.mygdx.game.gameEngine.entity.EntityManager;
import com.mygdx.game.gameEngine.entity.NonColliable;
import com.mygdx.game.gameLogic.entity.Player;
import com.mygdx.game.gameLogic.entity.Target;

public class AIManager implements AIManagement{
	
    private EntityManagement entityManager;
    private static AIManager instance;

    public AIManager() {
        entityManager = EntityManager.getInstance();
    }
    
    public static AIManager getInstance() {
		if(instance==null)
		{
			instance = new AIManager();
		}
		return instance;
	}
	@Override
	public boolean checkAI(Entity a) {

		if ((a instanceof Colliable)) {
			Colliable A = (Colliable) a;
			if(A.getAI() == true) {
				return true;
			}
		}		
		return false;
	}
}
