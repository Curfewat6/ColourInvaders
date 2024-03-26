package com.mygdx.game.gameEngine.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.gameEngine.entity.EntityManager;

import java.util.ArrayList;

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
