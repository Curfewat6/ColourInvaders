package com.mygdx.game.gameLogic.io;
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
       // boolean directionKeyPressed = false;
       setPause();
        if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
           
            if (pcb != null) {
                System.out.println("PAUSE");
                //pcb.togglePause();
                System.out.println(pcb);
                isPaused = !isPaused;
                return "pause";
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

//            captureTypingInput();

            for (int i = 29; i < 55; i++) {
                if (Gdx.input.isKeyJustPressed(i)) {
                    // I add 36 because the raw key code is not translated </3
                    char typedChar = (char) (i + 36);

                    return String.valueOf(typedChar);
                }
            }

            return "TYPING:" + inputBuffer.toString();
            // Imma take this out because we can only move left  & right and shoot

            // if (Gdx.input.isKeyPressed(Keys.W)) {
            //     return "up";
            //     //pcm.setDirection("up");
            //     //directionKeyPressed = true;
            // }
            // if (Gdx.input.isKeyPressed(Keys.S)) {
            //     return "down";
            //     //pcm.setDirection("down");
            //     //directionKeyPressed = true;
            // }
            // if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            //     pcm.setDirection("arrow-left");
            //     directionKeyPressed = true;
            // }
            // if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            //     pcm.setDirection("arrow-right");
            //     directionKeyPressed = true;
            // }
            // if (Gdx.input.isKeyPressed(Keys.UP)) {
            //     pcm.setDirection("arrow-up");
            //     directionKeyPressed = true;
            // }
            // if (Gdx.input.isKeyPressed(Keys.DOWN)) {
            //     pcm.setDirection("arrow-down");
            //     directionKeyPressed = true;
            // }
        }

        // if (directionKeyPressed) {
        //     pcm.handlingPlayerInput();
        // }
        return "no-moving";
    }
}
