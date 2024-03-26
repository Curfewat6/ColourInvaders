package com.mygdx.game.gameLogic.lifecycle;

import com.mygdx.game.gameEngine.entity.EntityManagement;
import com.mygdx.game.gameLogic.entity.EntityCreation;

public class LifecycleSim {
	
	 public LifecycleSim() {}

	    // Start the simulation and initialize the first scene
public void startSimulation(EntityManagement entityManager) {
        String[] TargetList = {"Target"};
        String[] PlayerList = {"Player","Bullet"};
        for (int i = 0; i < 10; i++) {
            EntityCreation.createEntity(TargetList, entityManager);
        }
        EntityCreation.createEntity(PlayerList, entityManager);
    }

// Ends the simulation and disposes everything used
public void endSimulation(EntityManagement entityManager ) {
        entityManager.dispose();
        
    }
}
