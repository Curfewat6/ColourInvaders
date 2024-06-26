package com.mygdx.game.gameLogic.screen;

import com.mygdx.game.gameEngine.utils.SoundsManager;
import org.lwjgl.opengl.GL20;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.gameEngine.entity.EntityManagement;
import com.mygdx.game.gameEngine.entity.EntityManager;
import com.mygdx.game.gameEngine.screen.*;

public class TitleScreen extends Screens{
	
	private EntityManagement em;
	private ScreenManagement screenList;
	
	private Skin skin;
	private Label title;
	private FitViewport fitViewport;
	private String background;
	
	private TextButton playButton;
	private TextButton guideButton;
	private TextButton settingButton;

	SoundsManager soundsManager = new SoundsManager();

	public TitleScreen(Game game, String name, String bgPath) 
	{
		super(game, Width, Height);
		setName(name);
		em = EntityManager.getInstance();
		screenList = ScreenManager.getInstance();
		background = bgPath;
		soundsManager.stop("music");
	}
	
	public void create()
	{
		// Creating new viewport for the stage
		fitViewport = new FitViewport(Screens.Width, Screens.Height);
		Stage newStage = new Stage(fitViewport);
		setStage(newStage);
		
		// Setting a new input for the current stage
	    Gdx.input.setInputProcessor(getStage());
		
		// Creation of the variables that would be displayed on the screen
		title = new Label("Hello!", skin);
		title.setPosition(Screens.Width / 2 - title.getWidth() / 2, Screens.Height / 2 + 100);
		
		// Play button
	    playButton = new TextButton("Start", skin);
		playButton.setSize(200,50);
		playButton.setPosition(Screens.Width / 2 - playButton.getWidth() / 2, 300);
	    playButton.addListener(new ClickListener() 
	    {
	        @Override
	        public void clicked(InputEvent event, float x, float y) 
	        {
		            		    	    		    	    
		    	    screenList.getScreen("INFO");

	        }
	    });
	    
	    // How to play button
	    guideButton = new TextButton("How to play?", skin);
	    guideButton.setSize(200,50);
		guideButton.setPosition(Screens.Width / 2 - playButton.getWidth() / 2, 200);
		guideButton.addListener(new ClickListener()
		{
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				screenList.getScreen("GUIDE");
			}
		});

		// Setting button
		settingButton = new TextButton("Settings", skin);
		settingButton.setSize(200,50);
		settingButton.setPosition(Screens.Width / 2 - playButton.getWidth() / 2, 100);
		settingButton.addListener(new ClickListener()
		{
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				screenList.getScreen("SETTING");
			}	
		});
	    
		// Setting background of the screen
	    setBackgroundImage(new Image(getTexture()));
	    getBackgroundImage().setSize(Screens.Width, Screens.Height);

        // Adding all the actors to the stage
        getStage().addActor(getBackgroundImage());
	    //getStage().addActor(title);
	    getStage().addActor(guideButton);
	    getStage().addActor(playButton);
	    getStage().addActor(settingButton);

	}


	@Override
	public void show() {
	    skin = new Skin(Gdx.files.internal("uiskin.json")); 
	    setTexture(new Texture(background));
	    create();
	}

	@Override
	public void render(float delta) {
		em.dispose();
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

	@Override
	public void dispose() {
				
	}	
}