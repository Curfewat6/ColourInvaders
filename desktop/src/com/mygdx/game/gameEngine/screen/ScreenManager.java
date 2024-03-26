package com.mygdx.game.gameEngine.screen;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Game;
import com.mygdx.game.gameEngine.ai.AIManager;
import com.mygdx.game.gameEngine.entity.Entity;
import com.mygdx.game.gameEngine.entity.EntityManager;

public class ScreenManager implements ScreenManagement
{
	private List<Screens> screenList;
	private static ScreenManager instance;
	
	public ScreenManager() 
	{
		screenList = new ArrayList<>();
	}
	
	public static ScreenManager getInstance()
	{
		if(instance==null)
		{
			instance = new ScreenManager();
		}
		return instance;
	}
	
	public void addScreen(Screens screen)
	{
		screenList.add(screen);
	}
	
	public void dispose()
	{
		for (Screens screen : screenList)
		{
			screen.dispose();
		}
		screenList.clear();
	}
	

    
    public void getScreen(String screenName) 
    {
        for (Screens screen : screenList) 
        {
            if (screen.getName().equals(screenName)) 
            { // Assuming there's a getName() method in Screens
            	screen.getGame().setScreen(screen);
            	
            }
        }
    }
    
    public void changeScreen(Screens screen)
    {
    	//screenList.get().getGame().setScreen(screen);  

    }

	 
}