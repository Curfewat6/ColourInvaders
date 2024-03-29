package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.gameEngine.entity.EntityManagement;
import com.mygdx.game.gameEngine.entity.EntityManager;
import com.mygdx.game.gameEngine.lifecycle.LifeCycleManagement;
import com.mygdx.game.gameEngine.lifecycle.LifeCycleManager;
import com.mygdx.game.gameEngine.screen.ScreenManagement;
import com.mygdx.game.gameEngine.screen.ScreenManager;
import com.mygdx.game.gameLogic.screen.ScreenCreate;

public class GameMaster extends Game
{

	private EntityManagement entityList;
	private ScreenManagement screenList;
	private LifeCycleManagement lifeCycle;
	

	@Override
	public void create() 
	{
		
		lifeCycle = new LifeCycleManager();
		entityList = EntityManager.getInstance();
		screenList = ScreenManager.getInstance();
		
	    String[] initialScreen = {"TitleScreen"};
	    String[] GameInfoScreen = {"GameInfoScreen"};
	    String[] PoolScreen = {"PoolScreen"};
	    String[] SettingScreen = {"SettingScreen"};
	    String[] GameScreen = {"GameScreen"};
	    String[] EndScreen = {"EndScreen"};
	    String[] GuideScreen = {"GuideScreen"};


	    // Creating the various screens that would be used later on
	    new ScreenCreate().createScreen(initialScreen, "TITLE", this, (ScreenManager) screenList, "Titlescreen.jpg");
	    new ScreenCreate().createScreen(GuideScreen, "GUIDE", this, (ScreenManager) screenList, "Intro.png");
	    new ScreenCreate().createScreen(PoolScreen, "POOL", this, (ScreenManager) screenList, "Intro2.png");
	    new ScreenCreate().createScreen(SettingScreen, "SETTING", this, (ScreenManager) screenList, "settingbg.jpg");
	    new ScreenCreate().createScreen(GameInfoScreen, "INFO", this, (ScreenManager) screenList, "Difficulty.jpg");
	    new ScreenCreate().createScreen(GameScreen, "GAME1", this, (ScreenManager) screenList, "Gamebackground.jpg");
	    new ScreenCreate().createScreen(EndScreen, "END", this, (ScreenManager) screenList, "Endscreen.jpg");
	    
	    screenList.getScreen("TITLE");
		lifeCycle.startSimulation(entityList);
		
	}
	
	@Override
	public void render() 
	{
		//Refresh the screen to a blank canvas 
		ScreenUtils.clear(0,0,0.2f,1);
		
		//Render all the Object 
		super.render();
	}
	public void dispose() {
		//clear all object render
		lifeCycle.endSimulation(entityList);
		
	}
	@Override
	public void pause() {
		
	}
	@Override
	public void resume() {
		
	}
	@Override
	public void resize(int width,int height) {
		super.resize(width, height);
		
	}
}