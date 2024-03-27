package com.mygdx.game.gameEngine.pcm;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.gameEngine.entity.Colliable;
import com.mygdx.game.gameEngine.entity.Entity;
import com.mygdx.game.gameEngine.entity.EntityManagement;
import com.mygdx.game.gameEngine.entity.EntityManager;
import com.mygdx.game.gameEngine.entity.NonColliable;
import com.mygdx.game.gameLogic.entity.Player;
import com.mygdx.game.gameEngine.screen.ScreenManager;

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
        if ((a instanceof NonColliable)) {
			NonColliable A = (NonColliable) a;
			if(A.getAI() == false) {
				return true;
			}
		}		
		return false;
    }
    
}

