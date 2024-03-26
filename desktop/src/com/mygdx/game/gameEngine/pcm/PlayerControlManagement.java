package com.mygdx.game.gameEngine.pcm;

public interface PlayerControlManagement {

	void handlingPlayerInput();

	void setDirection(String dir);
	public void movePlayerBasedOnDirection();

}

