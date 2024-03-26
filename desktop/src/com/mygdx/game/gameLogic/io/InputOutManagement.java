package com.mygdx.game.gameLogic.io;

import com.mygdx.game.gameEngine.screen.PauseCallBack;

public interface InputOutManagement {
	
	public String handleInput();
	public void setPauseCallback(PauseCallBack pcb);

}
