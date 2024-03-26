package com.mygdx.game.gameLogic.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.gameEngine.entity.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.gameEngine.pcm.PlayerControlManager;
import com.mygdx.game.gameEngine.utils.SpriteSheet;


public class Player extends NonColliable{
	
    private Texture tex;
    private String texName;
    private PlayerControlManager playerControl;
    SpriteSheet sheet;

    ShapeRenderer shapeRenderer;
    int lives;
    float healthBarWidth;
    float healthBarLiveWidth;

    public Player() {
        super("kitty.png", Gdx.graphics.getWidth()/2 + 150, 5, 0);
        sheet = new SpriteSheet(new Texture("kitty.png"),1,4);
        sheet.setPlay(0, 3, 0.1f, true);
        lives = 3;
        shapeRenderer = new ShapeRenderer();

        healthBarWidth = 30 * 3;
        healthBarLiveWidth = healthBarWidth / lives;
    }
 
    @Override
    public void render(Batch batch) {
        sheet.play();
    	batch.begin();
        TextureRegion t = sheet.getCurrentFrame();
        batch.draw(t, posX ,posY);
    	batch.end();
        drawHealthBar();
    }
    

    void drawHealthBar(){
        float x = posX + 70;
        float y = posY;

        for(int i = 0 ; i < lives;i++){
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.rect(x + healthBarLiveWidth * i,y ,healthBarLiveWidth,10);
            shapeRenderer.end();
        }

        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(x,y,healthBarWidth,10);
        shapeRenderer.end();

    }

    @Override
    public void update() {
    	//System.out.println("In " + texName + " at " + "(" + posX + ","+ posY +") with the speed of " + speed);
    }


    public Texture getTexture() {
        return tex;
    }

    public void dispose() {
        if(tex == null){
            return;
        }
        tex.dispose();
    }
	
	public String getName() {
		return texName;
	}

	public PlayerControlManager getPlayerControl() {
		return playerControl;
	}

	public void setPlayerControl(PlayerControlManager playerControl) {
		this.playerControl = playerControl;
	}

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}
