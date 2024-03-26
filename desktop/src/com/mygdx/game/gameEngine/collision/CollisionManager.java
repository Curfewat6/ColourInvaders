package com.mygdx.game.gameEngine.collision;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.gameEngine.ai.AIManagement;
import com.mygdx.game.gameEngine.ai.AIManager;
import com.mygdx.game.gameEngine.entity.Colliable;
import com.mygdx.game.gameEngine.entity.Entity;
import com.mygdx.game.gameEngine.entity.EntityManagement;
import com.mygdx.game.gameEngine.entity.EntityManager;

public class CollisionManager implements CollisionManagement{
	
	private EntityManagement em;
	private AIManagement ai;
	private static CollisionManager instance;
	
	public CollisionManager() {
	    em = EntityManager.getInstance();
	    ai = AIManager.getInstance();
	}
	
	public static CollisionManager getInstance() {
		if(instance==null)
		{
			instance = new CollisionManager();
		}
		return instance;
	}
	
    public boolean checkCollision() {
        float ranX = MathUtils.random(64, Gdx.graphics.getWidth() - 64);
        
        for (int i = 0; i < em.getCollidables().size(); i++) {
            Colliable a = em.getCollidables().get(i);
            
            for (int j = 1; j < em.getCollidables().size(); j++) {
                Colliable b = em.getCollidables().get(j);
                if (a != b && a.collideEntity(b)) {
                    // ai.resetMovement(b);
                    return true; // Collision detected, return true
                }
            }
        }
        return false; // No collision detected
    }

	public boolean rectCollide(Rectangle a, Rectangle b) {
		Rectangle rectangle = a;
		Rectangle r = b;
		if (r.x < rectangle.x + rectangle.width &&
				r.x + r.width > rectangle.x &&
				r.y < rectangle.y + rectangle.height &&
				r.height + r.y > rectangle.y) {
			return true;
		}
		return false;
	}
}
   
