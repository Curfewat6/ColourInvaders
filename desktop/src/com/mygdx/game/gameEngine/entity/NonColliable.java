package com.mygdx.game.gameEngine.entity;

import com.badlogic.gdx.graphics.g2d.Batch;

public class NonColliable extends Entity {

	private boolean isAI;
	private String texPath;

	public NonColliable(String texPath, float posX, float posY, float speed) {
		super(posX, posY, speed);
		this.texPath = texPath;
		this.isAI = false;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Batch batch) {
		// TODO Auto-generated method stub

	}

	@Override
	public void create() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		return texPath;
	}

    public boolean getAI() {
        // TODO Auto-generated method stub
        return isAI;
    }

}
