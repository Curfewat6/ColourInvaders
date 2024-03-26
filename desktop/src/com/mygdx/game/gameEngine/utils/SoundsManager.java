package com.mygdx.game.gameEngine.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;

public class SoundsManager {

    public HashMap<String, Sound> sounds = new HashMap();

    public SoundsManager(){
        sounds.put("music",Gdx.audio.newSound(Gdx.files.internal("music2.mp3")));
        sounds.put("death",Gdx.audio.newSound(Gdx.files.internal(  "Randomize76.wav")));
        sounds.put("correct",Gdx.audio.newSound(Gdx.files.internal(  "Powerup24.wav")));

        music();
    }

    public void play(String sound,float volume){
        sounds.get(sound).play(volume);
    }

    public void music(){
        sounds.get("music").stop();
//        sounds.get("music").play();
        sounds.get("music").loop(0.5f);
    }

    public void stopMusic(){
        sounds.get("music").stop();
    }

    public void stop(String sound){
        sounds.get(sound).stop();
    }
}
