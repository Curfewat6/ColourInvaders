package com.mygdx.game.gameLogic.lifecycle;

import java.util.*;

public class EnemyAssetsFactory {

    private static EnemyAssetsFactory instance;

    public static EnemyAssetsFactory getInstance() {
        if(instance==null)
        {
            instance = new EnemyAssetsFactory();
        }
        return instance;
    }
    List<String> texturesList;

    public EnemyAssetsFactory() {
        texturesList = Arrays.asList(
              "red.png",
              "green.png",
              "blue.png",
              "orange.png",
              "yellow.png"
        );
    }

    public String getRandom(){
        Random r = new Random();
        return texturesList.get(r.nextInt(texturesList.size()));
    }

}
