package com.mygdx.game.gameEngine.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class EntityManager implements EntityManagement{
	private List<Entity> entities;
	private List<Collidable> collidables;
	private static EntityManager instance;
    public SpriteBatch batch;
	
	public EntityManager() {
		instance = this;
        entities = new ArrayList<>();
        collidables = new ArrayList<>();
    }
	
	public static EntityManager getInstance() {
		if(instance==null)
		{
			instance = new EntityManager();
		}
		return instance;
	}
	
    public void addEntity(Entity entity) {
        entities.add(entity);
        if (entity instanceof Collidable) {
            collidables.add((Collidable) entity);
        }
    }

    public void draw() {
        for (Entity entity : entities) {
        	entity.create();
            entity.render(batch);
        }
    }

    public void update() {
        for (Entity entity : entities) {
            entity.update();
        }
        for (int i =0; i < entities.size();i++) {
            if(entities.get(i).destroyFlag){
                entities.remove(i);
            }
        }
    }

    public void dispose() {
        for (Entity entity : entities) {
            entity.dispose();
        }
        entities.clear();
    }
    
    public List<Entity> getEntities(){
    	return entities;
    }
    
    public List<Collidable> getCollidables() {
    	return collidables;
    }
    
    public void setList(EntityManagement el) {
    	for (Entity  entity: el.getEntities()) {
    		entities.add(entity);
    	}
    }
    
}
