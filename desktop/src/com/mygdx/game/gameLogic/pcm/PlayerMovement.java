package com.mygdx.game.gameLogic.pcm;

import com.mygdx.game.gameEngine.entity.EntityManagement;
import com.mygdx.game.gameEngine.entity.EntityManager;
import com.mygdx.game.gameEngine.pcm.PlayerControlManager;
import com.mygdx.game.gameLogic.entity.Bullet;
import com.mygdx.game.gameLogic.entity.Cannon;
import com.mygdx.game.gameEngine.io.InputOutputManager;

public class PlayerMovement {
    private EntityManager entityManager;
    private PlayerControlManager playerManager;
    private InputOutputManager ioManager;
    private String keyPressed;
    private String word = "";

    public String PlayerMove() {
        entityManager = EntityManager.getInstance();
        ioManager = InputOutputManager.getInstance();
        return playerMovement(entityManager);
    }

    public String playerMovement(EntityManagement entityManager) {
        playerManager = PlayerControlManager.getInstance();
        keyPressed = ioManager.handleInput();
        for (int i = 0; i < entityManager.getEntities().size(); i++) {
            if (playerManager.handlingPlayerInput(entityManager.getEntities().get(i))
                    && entityManager.getEntities().get(i) instanceof Cannon) {
                Cannon cannon = (Cannon) entityManager.getEntities().get(i);
                if (keyPressed.startsWith("left")) {
                    cannon.setRotateSpeed(3);
                } else if (keyPressed.startsWith("right")) {
                    cannon.setRotateSpeed(-3);
                } else if (keyPressed.startsWith("SHOOT:")) {
                    Bullet bullet = cannon.shoot(word, entityManager);
                    if (bullet != null) {
                        entityManager.addEntity(bullet);
                    }
                } else if (keyPressed.startsWith("BACKSPACE")) {
                    if (word.length() > 0) {
                        word = "";
                    }
                } else {
                    cannon.setRotateSpeed(0);
                    if (keyPressed.length() == 1) {
                        char typeChar = keyPressed.charAt(0);
                        word += typeChar;
                    }
                }
            }
        }
        return word;

    }

}
