package com.mygdx.game.gameLogic.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.gameEngine.entity.*;

public class Bullet extends Colliable{
	
    Vector2 vel;
	String color;

	public Bullet( float posX, float posY,float dirX,float dirY,String color) {
		super("b_blue.png", posX, posY, 10);
		vel = new Vector2(dirX * speed,dirY * speed);
		this.color = color;
		if(color.equals("RED")){
			this.tex = new Texture(Gdx.files.internal("b_red.png"));
		}
		if(color.equals("GREEN")){
			this.tex = new Texture(Gdx.files.internal("b_green.png"));
		}
		if(color.equals("BLUE")){
			this.tex = new Texture(Gdx.files.internal("b_blue.png"));
		}
		if(color.equals("ORANGE")){
			this.tex = new Texture(Gdx.files.internal("b_orange.png"));
		}
		if(color.equals("YELLOW")){
			this.tex = new Texture(Gdx.files.internal("b_yellow.png"));
		}

	}

	@Override
	public void update() {
		if(posX < -100 || posX > 1000 || posY < -100 || posY > 1000){
			destroyFlag = true;
		}
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public Rectangle getRectBound() {
		return new Rectangle(posX,posY,tex.getWidth(),tex.getHeight());
	}
	public void setVel(Vector2 vel) {
        this.vel = vel;
    }
    public Vector2 getVel() {
        return vel;
    }
	@Override
	public void dispose() {
		tex.dispose();
	}
}
