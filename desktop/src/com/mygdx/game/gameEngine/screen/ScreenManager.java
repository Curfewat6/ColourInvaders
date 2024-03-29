package com.mygdx.game.gameEngine.screen;

import java.util.ArrayList;
import java.util.List;

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
            {
            	// change the screen to the screen that matches the name in the parameter
            	screen.getGame().setScreen(screen);
            	
            }
        }
    }

	 
}