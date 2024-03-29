package com.mygdx.game.gameEngine.io;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.gameEngine.pcm.PlayerControlManager;
import com.mygdx.game.gameEngine.screen.PauseCallBack;
import com.mygdx.game.gameEngine.io.Keyboard;

public class InputOutputManager implements InputOutManagement {
    private Keyboard keyboard;
    private static InputOutputManager instance;
    private PauseCallBack pcb;
    private String keys;

    public InputOutputManager() {
        this.keyboard = new Keyboard(PlayerControlManager.getInstance());
    }
    
    public static InputOutputManager getInstance() {
		if(instance==null)
		{
			instance = new InputOutputManager();
		}
		return instance;
	}
    
    public void setPauseCallback(PauseCallBack pcb) {
        this.pcb = pcb;
    }

     @Override
     public String handleInput() {
         if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            // keys = keyboard.handleKeyInput(pcb);
            // System.out.println(keys);
            // return keys;
            return keyboard.handleKeyInput(pcb);
         }
         return "no-input";
         // If you want to add more input devices u can put them here <3
     }
}