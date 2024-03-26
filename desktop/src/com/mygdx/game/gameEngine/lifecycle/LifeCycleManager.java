package com.mygdx.game.gameEngine.lifecycle;
import com.mygdx.game.gameEngine.entity.EntityManagement;


public class LifeCycleManager implements LifeCycleManagement{
	
	 public LifeCycleManager() {}

	    // Start the simulation and initialize the first scene
	    public void startSimulation(EntityManagement entityManager) {

			//int x = 10;
			//ensure that the object is randomly place 
			// for (int i = 0; i < x; i++) {
			// 	float ranX = MathUtils.random(64,Gdx.graphics.getWidth()- 64);
			// 	float ranY = MathUtils.random(Gdx.graphics.getHeight()/2,Gdx.graphics.getHeight());
			// 	//entityManager.addEntity(new NonPlayable("droplet.png",ranX,ranY,2));
			// }
			//Creates all the Object needed
			//entityManager.addEntity(new Player("sukuna.png",280,0,300, batch));
			//entityManager.addEntity(new Triangle(150,250,350,50,150,50,Color.RED,200));
			//entityManager.addEntity(new Circle(50,50,50,Color.GREEN,200));
	    	
	    }

	    // Ends the simulation and disposes everything used
	    public void endSimulation(EntityManagement entityManager ) {
	        entityManager.dispose();
	    }
}
