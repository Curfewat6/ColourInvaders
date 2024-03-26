package com.mygdx.game.gameEngine.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.gameEngine.pcm.PlayerControlManager;

public class Colliable extends Entity implements Collision{
	
	protected Texture tex;
    private Rectangle rectBound;
    private SpriteBatch batch;
    protected String texName;
	private boolean isAI;
    private PlayerControlManager playerControl;

	public Colliable(String texPath, float posX, float posY, float speed) {
		super(posX, posY, speed);
		this.tex = new Texture(Gdx.files.internal(texPath));
        // this rectBound is like a hit box for the texture objects
        this.rectBound = new Rectangle(posX, posY, tex.getWidth(), tex.getHeight());
        this.texName = texPath;
		this.isAI = true;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Batch batch) {
		// TODO Auto-generated method stub
		batch.begin();
		batch.draw(this.tex,posX,posY);
		batch.end();
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		if (tex instanceof Colliable) {
			Colliable t = (Colliable) tex;
			if(t.getName() != getName()) {
				return rectBound.overlaps(t.getRectBound());
			}
		}
		return false;
	}

}
