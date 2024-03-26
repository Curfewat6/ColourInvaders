package com.mygdx.game.gameLogic.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.gameEngine.screen.ScreenManager;

public class SoundManager implements Disposable, SoundManagement {

  private Array<Music> sounds;
  private static SoundManager instance;

  public SoundManager() {
    sounds = new Array<>();
    loadSound();
  }

  public static SoundManager getInstance() {
    if(instance==null)
    {
      instance = new SoundManager();
    }
    return instance;
  }

  public void loadSound() {
    // Load sound files
    FileHandle musicFile1 = Gdx.files.internal("crab-rave.mp3");
    Music music1 = Gdx.audio.newMusic(musicFile1);
    sounds.add(music1);
  }
  public void playSound(int soundIndex, float volume) {
    if (soundIndex >= 0 && soundIndex < sounds.size) {
      sounds.get(soundIndex).play();
            sounds.get(soundIndex).setVolume(volume);
    }
  }

  @Override
  public void dispose() {
    // Dispose all loaded sounds
    for (Music music : sounds) {
      music.dispose();
    }
  }
}
