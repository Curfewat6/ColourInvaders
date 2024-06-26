package com.mygdx.game.gameLogic.screen;

import org.lwjgl.opengl.GL20;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.gameEngine.screen.ScreenManagement;
import com.mygdx.game.gameEngine.screen.ScreenManager;
import com.mygdx.game.gameEngine.screen.Screens;
import com.mygdx.game.gameEngine.utils.SoundsManager;

public class SettingScreen extends Screens{
	
	private ScreenManagement screenList;
    private SoundsManager soundsManager;
	
	private FitViewport fitViewport;
	private String background;
	
    private Slider volumeSlider;
	private TextButton homeButton;
    private Label volumeLabel;
	private Skin skin;
	
	// Field to store the volume
    private float savedVolume = 0.5f;

	public SettingScreen(Game game, String name, String bgPath) {
		
		super(game, Width, Height);
		setName(name);
		screenList = ScreenManager.getInstance();
		soundsManager = SoundsManager.getInstance();
		
		background = bgPath;
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
	    // Volume Label
	    volumeLabel = new Label("Sound Volume", skin);
	    volumeLabel.setPosition(Screens.Width / 2 - volumeLabel.getWidth() / 2, Screens.Height / 2 + 50);
	    
        // Volume Slider
        volumeSlider = new Slider(0f, 1f, 0.1f, false, skin);
        volumeSlider.setPosition(Screens.Width / 2 - volumeSlider.getWidth() / 2, Screens.Height / 2);
        volumeSlider.setValue(savedVolume); // Default or saved value
        volumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float volume = volumeSlider.getValue();
                savedVolume = volume; // Save the current volume value
                soundsManager.setGlobalVolume(volume); // Adjust volume
            }
        });
        
        // Home Button
        homeButton = new TextButton("Home", skin);
        homeButton.setPosition(Screens.Width / 2 - homeButton.getWidth() / 2, Screens.Height / 4); // Center horizontally
        homeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // Here you switch back to your main menu or another screen
	    	    screenList.getScreen("TITLE");
            }
        });
        
		// Setting background of the screen
	    setBackgroundImage(new Image(getTexture()));
	    getBackgroundImage().setSize(Screens.Width, Screens.Height);
        
        // Adding all the actors to the stage
	    getStage().addActor(getBackgroundImage());	    
	    getStage().addActor(volumeLabel);
        getStage().addActor(homeButton);
        getStage().addActor(volumeSlider);

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
	    skin = new Skin(Gdx.files.internal("uiskin.json")); 
	    setTexture(new Texture(background));
	    create();
        volumeSlider.setValue(savedVolume); // Ensure the volume slider is set to the saved value	
	}

	@Override
	public void render(float delta) {
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
