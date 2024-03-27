package com.mygdx.game.gameLogic.screen;

import com.mygdx.game.gameEngine.screen.ScreenManager;
import com.mygdx.game.gameEngine.screen.Screens;
import com.mygdx.game.gameLogic.level.LevelSpecifier;
import com.badlogic.gdx.Game;
import com.mygdx.game.gameEngine.screen.ScreenManagement;

public class ScreenCreate extends ScreenManager{
	
	public void createScreen(String[] args, String name, Game game, ScreenManagement manager, String pgPath)
	{
		for (String arg : args)
		{
            if (arg.startsWith("TitleScreen"))
            {
            	Screens TitleScreen = (Screens) ScreenFactory.getScreen(ScreenEnum.TITLESCREEN, name, game, pgPath);
            	manager.addScreen(TitleScreen);
            }
			if (arg.startsWith("GuideScreen"))
            {
            	Screens GuideScreen = (Screens) ScreenFactory.getScreen(ScreenEnum.GUIDESCREEN, name, game, pgPath);
            	manager.addScreen(GuideScreen);
            }
			if (arg.startsWith("PoolScreen"))
            {
            	Screens PoolScreen = (Screens) ScreenFactory.getScreen(ScreenEnum.POOLSCREEN, name, game, pgPath);
            	manager.addScreen(PoolScreen);
            }
			if (arg.startsWith("GameInfoScreen"))
            {
            	Screens GameInfoScreen = (Screens) ScreenFactory.getScreen(ScreenEnum.GAMEINFOSCREEN, name, game, pgPath);
            	manager.addScreen(GameInfoScreen);
            }
            if (arg.startsWith("GameScreen"))
            {
            	Screens GameScreen = (Screens) ScreenFactory.getScreen(ScreenEnum.GAMESCREEN, name, game, pgPath);
            	manager.addScreen(GameScreen);

            }
            if (arg.startsWith("EndScreen"))
            {
            	Screens EndScreen = (Screens) ScreenFactory.getScreen(ScreenEnum.ENDSCREEN, name, game, pgPath);
            	manager.addScreen(EndScreen);
            }
		}
	}
}
