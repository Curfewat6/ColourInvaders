package com.mygdx.game.gameLogic.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.gameEngine.ai.AIManager;
import com.mygdx.game.gameEngine.entity.*;

public class Target extends Colliable{
	
	private Texture tex;
    private Rectangle rectBound;
    private BitmapFont font;
    private String texName;
    private AIManager aiManager;

	public Target(String texPath, float posX, float posY, float speed) {
		super(texPath, posX, posY, speed);
	}
	
	@Override
	public void render(Batch batch) {
		// TODO Auto-generated method stub
		batch.begin();
		//batch.draw(tex, posX, posY, tex.getWidth(), tex.getHeight());
		font.draw(batch, texName, posX, posY);
		updateRecPos(posX, posY);
		batch.end();	
	}
	
	public AIManager getAImanager() {
		return aiManager;
	}

	public void setAIManager(AIManager aiManager) {
		this.aiManager = aiManager;
	}

}
