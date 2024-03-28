package com.mygdx.game.gameLogic.screen;

import com.badlogic.gdx.Game;
import com.mygdx.game.gameEngine.screen.Screens;
import com.mygdx.game.gameLogic.level.LevelSpecifier;

public class ScreenFactory {
	public static Screens getScreen(ScreenEnum screen, String name, Game game, String bgPath) 
	{
		switch (screen) 
		{
		case TITLESCREEN:
			return new TitleScreen(game, name, bgPath);
		case GUIDESCREEN:
			return new GuideScreen(game, name, bgPath);
		case POOLSCREEN:
			return new PoolScreen(game, name, bgPath);
		case SETTINGSCREEN:
			return new SettingScreen(game, name, bgPath);
		case GAMEINFOSCREEN:
			return new GameInfoScreen(game, name, bgPath);
		case GAMESCREEN:
			return new GameScreen(game, name, bgPath);
		case ENDSCREEN:	
			return new EndScreen(game, name,bgPath);
		default:
			return null;
		}

		
	}
}
