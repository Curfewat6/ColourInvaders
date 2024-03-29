package com.mygdx.game.gameEngine.ai;

import com.mygdx.game.gameEngine.entity.Collidable;
import com.mygdx.game.gameEngine.entity.Entity;

public class AIManager implements AIManagement{
	
    private static AIManager instance;
    
    public static AIManager getInstance() {
		if(instance==null)
		{
			instance = new AIManager();
		}
		return instance;
	}
	@Override
	public boolean checkAI(Entity a) {

		if ((a instanceof Collidable)) {
			Collidable A = (Collidable) a;
			if(A.getAI() == true) {
				return true;
			}
		}		
		return false;
	}
}
