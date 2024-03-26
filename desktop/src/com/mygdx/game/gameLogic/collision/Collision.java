package com.mygdx.game.gameLogic.collision;

import com.mygdx.game.gameEngine.collision.CollisionManager;

public class Collision {
    public void checkCollision() {
        CollisionManager collisionManager = CollisionManager.getInstance();
        boolean collisionDetected = collisionManager.checkCollision();
        
        if (collisionDetected) {
            // Handle collision here
            System.out.println("Collision detected!");
            // Perform actions like stopping the game, reducing player health, etc.
        } else {
            // No collision detected
            System.out.println("No collision detected.");
            // Perform other actions if needed
        }
    }
}
