package com.mygdx.game.gameEngine.entity;

import com.badlogic.gdx.graphics.g2d.Batch;

public class NonCollidable extends Entity {

	private boolean isAI;
	private String texPath;

	public NonCollidable(String texPath, float posX, float posY, float speed) {
		super(posX, posY, speed);
		this.texPath = texPath;
		this.isAI = false;
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void render(Batch batch) {

	}

	@Override
	public void create() {

	}

	@Override
	public void update() {

	}

	@Override
	public String getName() {
		return texPath;
	}

    public boolean getAI() {
        return isAI;
    }

}
