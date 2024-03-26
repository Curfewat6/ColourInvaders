package com.mygdx.game.gameLogic.screen;

import com.badlogic.gdx.Game;
import com.mygdx.game.gameEngine.screen.Screens;
import com.mygdx.game.gameLogic.level.LevelSpecifier;

public class ScreenFactory {
	public static Screens getScreen(ScreenEnum screen, String name, Game game, LevelSpecifier level) 
	{
		switch (screen) 
		{
		case TITLESCREEN:
			return new TitleScreen(game, name, level);
		case GAMEINFOSCREEN:
			return new GameInfoScreen(game, name, level);
		case GAMESCREEN:
			return new GameScreen(game, name, level);
		case ENDSCREEN:	
			return new EndScreen(game, name, level);
		default:
			return null;
		}

		
	}
}
