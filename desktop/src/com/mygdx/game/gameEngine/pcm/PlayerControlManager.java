package com.mygdx.game.gameEngine.pcm;

import com.mygdx.game.gameEngine.entity.Entity;
import com.mygdx.game.gameEngine.entity.NonCollidable;

public class PlayerControlManager implements PlayerControlManagement {
	
    private static PlayerControlManager instance;

    public PlayerControlManager() {}
    
    public static PlayerControlManager getInstance() {
		if(instance==null)
		{
			instance = new PlayerControlManager();
		}
		return instance;
	}
    

    public boolean handlingPlayerInput(Entity a) {
        if ((a instanceof NonCollidable)) {
			NonCollidable A = (NonCollidable) a;
			if(A.getAI() == false) {
				return true;
			}
		}		
		return false;
    }
    
}

