package com.mygdx.game.gameEngine.entity;

import java.util.List;

public interface EntityManagement {
	public abstract void addEntity(Entity entity);
	public abstract void draw();
	public abstract void dispose();
	public abstract void update();
	public void setList(EntityManagement em);
	public List<Entity> getEntities();
	public List<Collidable> getCollidables();
}
