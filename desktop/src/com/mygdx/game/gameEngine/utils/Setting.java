package com.mygdx.game.gameEngine.utils;

public class Setting {
    private static Setting instance;
    public int difficult = 0;

    public Setting() {

    }

    public static Setting getInstance() {
        if(instance==null)
        {
            instance = new Setting();
        }
        return instance;
    }
}
