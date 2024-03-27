package com.mygdx.game.gameLogic.level;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.gameEngine.entity.EntityManagement;


public class LevelSpecifier {
	private int levelNumber;
    private String bgPath;
	
	public LevelSpecifier(int levelNumber, String bgPath, EntityManagement entityManager, int x)
	{
		this.levelNumber = levelNumber;
		this.bgPath = bgPath;
//		String[] TargetList = {"Target"};         
//		String[] PlayerList = {"Player","Bullet"};         
//		for (int i = 0; i < x; i++) 
//		{             
//			EntityCreation.createEntity(TargetList, entityManager);         
//			}         
//		EntityCreation.createEntity(PlayerList, entityManager);
		
	}

	public String getBgPath()
	{
		return bgPath;
	}
	
	public int getLevelNumber() {
		return levelNumber;
	}
	
	public void setBgPath(String bgPath)
	{
		this.bgPath = bgPath;
	}

	public void setLevelNumber(int levelNumber) {
		this.levelNumber = levelNumber;
	}

	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	

}
