package com.mygdx.game.gameEngine.lifecycle;
import com.mygdx.game.gameEngine.entity.EntityManagement;


public class LifeCycleManager implements LifeCycleManagement{
	
	 public LifeCycleManager() {}

	    // Start the simulation and initialize the first scene
	    public void startSimulation(EntityManagement entityManager) {
	    	
	    }

	    // Ends the simulation and disposes everything used
	    public void endSimulation(EntityManagement entityManager ) {
	        entityManager.dispose();
	    }
}
