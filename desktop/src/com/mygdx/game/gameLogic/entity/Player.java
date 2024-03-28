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
    private SpriteSheet sheet;

    private ShapeRenderer shapeRenderer;
    private int lives;
    private float healthBarWidth;
    private float healthBarLiveWidth;
    private float x,y,height;


    public Player() {
        super("kitty.png", Gdx.graphics.getWidth()/2 + 150, 5, 0);
        sheet = new SpriteSheet(new Texture("kitty.png"),1,4);
        sheet.setPlay(0, 3, 0.1f, true);
        lives = 3;
        shapeRenderer = new ShapeRenderer();
        x = Gdx.graphics.getWidth()/5 * 4;
        y = 12;
        height = Gdx.graphics.getHeight()/45;

        healthBarWidth = Gdx.graphics.getWidth()/20 * 3;
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

        for(int i = 0 ; i < lives;i++){
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.rect(x + healthBarLiveWidth * i,y ,healthBarLiveWidth,height);
            shapeRenderer.end();
        }

        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(x,y,healthBarWidth,height);
        shapeRenderer.end();

    }
    public void setX(float x){
        this.x = x;
    }
    public void setY(float y){
        this.y = y;
    }

    @Override
    public void update() {
    
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
