package com.mygdx.game.gameLogic.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.gameEngine.entity.NonCollidable;
import com.mygdx.game.gameEngine.utils.SpriteSheet;

public class Explosion extends NonCollidable{

    private Texture tex;
    private SpriteSheet sheet;

    public Explosion(float x,float y,String color) {
        super("kitty.png", x,y, 0);

        tex = new Texture("exp_blue.png");
        if(color.equals("RED")){
            this.tex = new Texture(Gdx.files.internal("exp_red.png"));
        }
        if(color.equals("GREEN")){
            this.tex = new Texture(Gdx.files.internal("exp_green.png"));
        }
        if(color.equals("BLUE")){
            this.tex = new Texture(Gdx.files.internal("exp_blue.png"));
        }
        if(color.equals("ORANGE")){
            this.tex = new Texture(Gdx.files.internal("exp_orange.png"));
        }
        if(color.equals("YELLOW")){
            this.tex = new Texture(Gdx.files.internal("exp_yellow.png"));
        }

        sheet = new SpriteSheet(tex,1,33);
        sheet.setPlay(0,32,0.02f,false);

    }
 
    @Override
    public void render(Batch batch) {
        sheet.play();
    	batch.begin();
        TextureRegion t = sheet.getCurrentFrame();
        batch.draw(t, posX ,posY);
    	batch.end();
    }
    
    @Override
    public void update() {
    	if(sheet.getCurrent() >= 32){
            destroyFlag = true;
        }
    }

    public Texture getTexture() {
        return tex;
    }

    public void dispose() {
        tex.dispose();
    }

}
