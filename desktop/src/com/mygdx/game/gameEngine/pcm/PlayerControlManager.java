package com.mygdx.game.gameEngine.pcm;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.gameEngine.entity.EntityManagement;
import com.mygdx.game.gameEngine.entity.EntityManager;
import com.mygdx.game.gameLogic.entity.Player;
import com.mygdx.game.gameEngine.screen.ScreenManager;

public class PlayerControlManager implements PlayerControlManagement {
	
    private EntityManagement entityManager;
    private String direction;
    private static PlayerControlManager instance;

    public PlayerControlManager() {
        entityManager = EntityManager.getInstance();
        this.direction = null;
    }
    
    public static PlayerControlManager getInstance() {
		if(instance==null)
		{
			instance = new PlayerControlManager();
		}
		return instance;
	}
    

    public void handlingPlayerInput() {
        entityManager.getEntities().forEach(entity -> {
            if (entity instanceof Player) {
                Player textureObject = (Player) entity;
                    handleBucketInput(textureObject);
            }
        });	
    }
    
    @Override
	public void setDirection(String dir) {
		// TODO Auto-generated method stub
		this.direction = dir;
	}

    public void handleBucketInput(Player textureObject) {
        if (this.direction == "left") {
            textureObject.setPosX(textureObject.getPosX() - (textureObject.getSpeed() * Gdx.graphics.getDeltaTime()));
            this.direction = null;
        }
        if (this.direction == "right") {
            textureObject.setPosX(textureObject.getPosX() + (textureObject.getSpeed() * Gdx.graphics.getDeltaTime()));
            this.direction = null;
        }

        if (this.direction == "shoot"){
            // Do something
        }
    }

    public void movePlayerBasedOnDirection() {
        // Assuming you've set the direction before calling this method
        handlingPlayerInput(); // This now immediately moves the player based on the latest direction
    }
}

