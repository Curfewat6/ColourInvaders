package com.mygdx.game.gameLogic.screen;

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

public class EndScreen extends Screens{
	
	private EntityManagement em;
	private ScreenManagement screenList;

	private Label score;
	private Skin skin;
	private TextButton mainMenuButton;
	private FitViewport fitViewport;
	private String background;

	public EndScreen(Game game, String name, String bgPath) 
	{
		super(game, Width, Height); 
		setName(name);
		em = EntityManager.getInstance();
		screenList = ScreenManager.getInstance();
		
		background = bgPath;

	}
	
	public void create()
	{
		// Creating new viewport for the stage
		fitViewport = new FitViewport(Screens.Width, Screens.Height);
		Stage newStage = new Stage(fitViewport);
		setStage(newStage);
		
		// Setting a new input for the current stage
		Gdx.input.setInputProcessor(getStage());

	    Label.LabelStyle labelStyle = new Label.LabelStyle();
	    labelStyle.font = skin.getFont("default-font");
	    labelStyle.fontColor = skin.getColor("black"); // Set font color to black using skin color definition

		// Creation of the variables that would be displayed on the screen
		// Displaying user final score
	    score = new Label("" + GameScreen.finalScore, labelStyle);
	    score.setPosition(Screens.Width / 2 - score.getWidth() / 2 - 10, Screens.Height / 2 - 175);
	    // Set font size for title
	    score.setFontScale(2.0f);
	    
		mainMenuButton = new TextButton("MainMenu", skin);
		mainMenuButton.setPosition(Screens.Width / 2 - mainMenuButton.getWidth() / 2, Screens.Height / 2 - 75);
		mainMenuButton.addListener(new ClickListener() 
	    {
	        @Override
	        public void clicked(InputEvent event, float x, float y) 
	        {
	    	    screenList.getScreen("TITLE");

	        }
	    });
		
		// Setting background of the screen
        setBackgroundImage(new Image(getTexture()));
        getBackgroundImage().setSize(Screens.Width, Screens.Height);

        // Adding all the actors to the stage
	    getStage().addActor(getBackgroundImage());
	    getStage().addActor(score);
	    getStage().addActor(mainMenuButton);
	}

	@Override
	public void show() {
	    skin = new Skin(Gdx.files.internal("uiskin.json"));
	    setTexture(new Texture(background));
	    create();
	    resetEM();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
	
	public void resetEM() {
		
		em.dispose();

	}
}