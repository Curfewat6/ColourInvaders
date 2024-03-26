package com.mygdx.game.gameLogic.level;


public interface LevelManagement {
	public LevelSpecifier getlevel(int level);
	public void dispose();
	public void addLevel(LevelSpecifier levelNumber);

}
