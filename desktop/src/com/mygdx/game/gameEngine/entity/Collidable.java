package com.mygdx.game.gameEngine.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.gameEngine.pcm.PlayerControlManager;

public class Collidable extends Entity implements CollidableEntity{
	
	protected Texture tex;
    private Rectangle rectBound;
    protected String texName;
	private boolean isAI;
    private PlayerControlManager playerControl;

	public Collidable(String texPath, float posX, float posY, float speed) {
		super(posX, posY, speed);
		this.tex = new Texture(Gdx.files.internal(texPath));
        // this rectBound is like a hit box for the texture objects
        this.rectBound = new Rectangle(posX, posY, tex.getWidth(), tex.getHeight());
        this.texName = texPath;
		this.isAI = true;
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void render(Batch batch) {
		batch.begin();
		batch.draw(this.tex,posX,posY);
		batch.end();
	}

	@Override
	public void create() {
		
	}

	@Override
	public void update() {
		
	}
	
	public Rectangle getRectBound() {
    	return rectBound;
    }
	
	public void updateRecPos(float x, float y) {
		rectBound.setPosition(x, y);
	}
	
	public String getName() {
		return texName;
	}
	public void setAI(boolean isAI) {
		this.isAI = isAI;
	}
	public boolean getAI() {
		return isAI;
	}

	public PlayerControlManager getPlayerControl() {
		return playerControl;
	}

	@Override
	public boolean collideEntity(Entity tex) {
		if (tex instanceof Collidable) {
			Collidable t = (Collidable) tex;
			if(t.getName() != getName()) {
				return rectBound.overlaps(t.getRectBound());
			}
		}
		return false;
	}

}
