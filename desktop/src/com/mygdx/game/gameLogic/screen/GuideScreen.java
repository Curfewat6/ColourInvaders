package com.mygdx.game.gameLogic.screen;

import org.lwjgl.opengl.GL20;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.gameEngine.screen.ScreenManagement;
import com.mygdx.game.gameEngine.screen.ScreenManager;
import com.mygdx.game.gameEngine.screen.Screens;

public class GuideScreen extends Screens{
	
	private ScreenManagement screenList;
	
	private FitViewport fitViewport;
	private TextButton nextButton;
	private String background;
	
	private Skin skin;

	public GuideScreen(Game game, String name, String bgPath)
	{
		super(game, Width, Height);
		setName(name);
		background = bgPath;

		screenList = ScreenManager.getInstance();

	}

	@Override
	public void create() {
		// Creating new viewport for the stage
		fitViewport = new FitViewport(Screens.Width, Screens.Height);
		Stage newStage = new Stage(fitViewport);
		setStage(newStage);
		
		// Setting a new input for the current stage
	    Gdx.input.setInputProcessor(getStage());
	    
		// Creation of the variables that would be displayed on the screen
	    nextButton = new TextButton("Next", skin);
	    nextButton.setSize(60,40);
	    nextButton.setPosition(Screens.Width - nextButton.getWidth(), Screens.Height - nextButton.getHeight());
	    
	    nextButton.addListener(new ClickListener()
	    {
	    	@Override
	    	public void clicked(InputEvent event, float x, float y)
	    	{
	    	    screenList.getScreen("POOL");

	    	}
	    });
	    
		// Setting background of the screen
	    setBackgroundImage(new Image(getTexture()));
	    getBackgroundImage().setSize(Screens.Width, Screens.Height);

        // Adding all the actors to the stage
        getStage().addActor(getBackgroundImage());
	    getStage().addActor(nextButton);
	    
		
	}

	@Override
	public void dispose() {

	}

	@Override
	public void show() {
	    skin = new Skin(Gdx.files.internal("uiskin.json")); 
	    
	    setTexture(new Texture(background));
	    create();

	}

	@Override
	public void render(float delta){
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		fitViewport.apply();
		getStage().act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		getStage().draw();
		
	}

	@Override
	public void resize(int width, int height) {
		getStage().getViewport().update(width, height, true);
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}
	
}
