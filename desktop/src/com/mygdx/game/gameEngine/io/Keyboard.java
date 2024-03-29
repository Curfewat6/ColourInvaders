package com.mygdx.game.gameEngine.io;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.game.gameEngine.pcm.PlayerControlManagement;
import com.mygdx.game.gameEngine.screen.PauseCallBack;

//This class is only here to process keyboard input. Something like keybinds in games
public class Keyboard {
    private PlayerControlManagement pcm;
    private boolean isPaused = false;
    private StringBuilder inputBuffer = new StringBuilder();

    public Keyboard(PlayerControlManagement playerControl) {
        this.pcm = playerControl;
    }
    
    public void setPause(){
        isPaused = false;
    }

    public String handleKeyInput(PauseCallBack pcb) {
       setPause();
        if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
           
            if (pcb != null) {
                System.out.println(pcb);
                isPaused = !isPaused;
                if(isPaused){
                    System.out.println("PAUSE");
                    return "pause";
                }
            }
        } else if(!isPaused){
            if (Gdx.input.isKeyPressed(Keys.LEFT)) {
                return "left";
            }
            if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
                return "right";
            }

            if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
                String result = "SHOOT:" + inputBuffer.toString();
                inputBuffer.setLength(0);
                return result; 
            }
            if (Gdx.input.isKeyJustPressed(Keys.BACKSPACE)) {
                return "BACKSPACE";
            }

            for (int i = 29; i < 55; i++) {
                if (Gdx.input.isKeyJustPressed(i)) {
                    char typedChar = (char) (i + 36);

                    return String.valueOf(typedChar);
                }
            }

            return "TYPING:" + inputBuffer.toString();
        }

        return "no-moving";
    }
}
