package com.mygdx.game.gameEngine.screen;

public interface ScreenManagement {
	public void addScreen(Screens screen);
	public void dispose();
    public void getScreen(String screenName);
}

