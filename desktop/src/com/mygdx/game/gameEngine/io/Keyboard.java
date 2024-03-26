package com.mygdx.game.gameEngine.io;
import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.gameEngine.pcm.PlayerControlManagement;
import com.mygdx.game.gameEngine.screen.PauseCallBack;

//This class is only here to process keyboard input. Something like keybinds in games
public class Keyboard {
    private PlayerControlManagement pcm;
    private boolean isPaused = false;
    private StringBuilder inputBuffer = new StringBuilder();
    private boolean isCapturing = true;

    public Keyboard(PlayerControlManagement playerControl) {
        this.pcm = playerControl;
    }

    private void captureTypingInput() {
        if (!isCapturing) return;

        // Only play with the 26 alphabets. all upper case 
        for (int i = 29; i < 55; i++) {
            if (Gdx.input.isKeyJustPressed(i)) {
                System.out.println(i);
                // I add 36 because the raw key code is not translated </3
                char typedChar = (char) (i + 36);
                
                inputBuffer.append(typedChar);
            }
        }
    }

    public String handleKeyInput(PauseCallBack pcb) {
       // boolean directionKeyPressed = false;
    
        if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
            if (pcb != null) {
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
                System.out.println("shoot");
                String result = "SHOOT:" + inputBuffer.toString();
                System.out.println(inputBuffer.toString());
                inputBuffer.setLength(0);
                return result; 
            }

//            captureTypingInput();
            for (int i = 29; i < 55; i++) {
                System.out.println(i);
                System.out.println("ccccccccccccccccccccccccccccccccccccc");

                if (Gdx.input.isKeyJustPressed(i)) {
                    // I add 36 because the raw key code is not translated </3
                    char typedChar = (char) (i + 36);
                    return String.valueOf(typedChar);
                }
            }

            return "";
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
