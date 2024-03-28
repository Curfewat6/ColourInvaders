package com.mygdx.game.gameEngine.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;

public class SoundsManager {
	
    private static SoundsManager instance;

    public HashMap<String, Sound> sounds = new HashMap();
    private float globalVolume = 1.0f; // Default volume is max (1.0)

    public SoundsManager(){
        sounds.put("music",Gdx.audio.newSound(Gdx.files.internal("music2.mp3")));
        sounds.put("death",Gdx.audio.newSound(Gdx.files.internal(  "Randomize76.wav")));
        sounds.put("correct",Gdx.audio.newSound(Gdx.files.internal(  "Powerup24.wav")));

        //music();
    }
    
    public static SoundsManager getInstance() {
        if (instance == null) {
            instance = new SoundsManager();
        }
        return instance;
    }

    public void play(String sound, float volume) {
        if (sounds.containsKey(sound)) {
            long id = sounds.get(sound).play(globalVolume * volume); // Adjust this line to account for global volume
            sounds.get(sound).setVolume(id, globalVolume * volume); // Ensure volume is set correctly
        }
    }
    
    public void setGlobalVolume(float volume) {
        globalVolume = volume;
        System.out.println("Global Volume Set To: " + globalVolume); // Debugging print statement

    }

    public void music(){
        sounds.get("music").stop();
//        sounds.get("music").play();
        sounds.get("music").loop(globalVolume);
    }

    public void stopMusic(){
        sounds.get("music").stop();
    }

    public void stop(String sound){
        sounds.get(sound).stop();
    }
}
