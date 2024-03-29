package com.mygdx.game.gameEngine.collision;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.gameEngine.entity.Collidable;
import com.mygdx.game.gameEngine.entity.EntityManagement;
import com.mygdx.game.gameEngine.entity.EntityManager;

public class CollisionManager implements CollisionManagement{
	
	private EntityManagement em;
	private static CollisionManager instance;
	
	public CollisionManager() {
	    em = EntityManager.getInstance();
	}
	
	public static CollisionManager getInstance() {
		if(instance==null)
		{
			instance = new CollisionManager();
		}
		return instance;
	}
	
    public boolean checkCollision() {
        
        for (int i = 0; i < em.getCollidables().size(); i++) {
            Collidable a = em.getCollidables().get(i);
            
            for (int j = 1; j < em.getCollidables().size(); j++) {
                Collidable b = em.getCollidables().get(j);
                if (a != b && a.collideEntity(b)) {
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
   
