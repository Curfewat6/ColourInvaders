package com.mygdx.game.gameLogic.sound;

public interface SoundManagement {
  public void loadSound();
  public void playSound(int soundIndex, float volume);
  public void dispose();
}
