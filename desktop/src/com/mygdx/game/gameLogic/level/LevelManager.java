package com.mygdx.game.gameLogic.level;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.game.gameEngine.screen.ScreenManager;
import com.mygdx.game.gameEngine.screen.Screens;

public class LevelManager implements LevelManagement{
	private List<LevelSpecifier> levelList;
	private static LevelManager instance;

	
	public LevelManager()
	{
		levelList = new ArrayList<>();
	}
	
	
	public static LevelManager getInstance()
	{
		if(instance==null)
		{
			instance = new LevelManager();
		}
		return instance;
	}
	
	public void dispose()
	{
		for (LevelSpecifier level : levelList)
		{
			level.dispose();
		}
		levelList.clear();
	}
	
	public LevelSpecifier getlevel(int levelNumber)
	{
		for (LevelSpecifier level : levelList)
		{
			if (level.getLevelNumber() == levelNumber) 
			{
				return level;
			}
		}
		return null;
	}

	@Override
	public void addLevel(LevelSpecifier level) 
	{
		levelList.add(level);
	}
	


}
