package com.mygdx.game.gameLogic.screen;

import com.mygdx.game.gameEngine.utils.Setting;
import org.lwjgl.opengl.GL20;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.gameEngine.entity.EntityManagement;
import com.mygdx.game.gameEngine.entity.EntityManager;
import com.mygdx.game.gameEngine.screen.*;

public class GameInfoScreen extends Screens{
	
    private ScreenManagement screenList;
    
    private Skin skin;
	private FitViewport fitViewport;
    private Texture Image;
    private BitmapFont Font;
	private String background;

	private TextButton easyBtn;
	private TextButton normalBtn;
	private TextButton hardBtn;

	public GameInfoScreen(Game game, String name, String bgPath){
        super(game, Width, Height);
		setName(name);
        screenList = ScreenManager.getInstance();
		background = bgPath;

    }
	
    public void create(){
		// Creating new viewport for the stage
        fitViewport = new FitViewport(Screens.Width, Screens.Height);
        Stage newStage = new Stage(fitViewport);
		setStage(newStage);
		
		// Setting a new input for the current stage
		Gdx.input.setInputProcessor(getStage());
        
		// Creation of the variables that would be displayed on the screen
        Font = new BitmapFont();
        
        // Easy button
        easyBtn = new TextButton("Easy", skin);
		easyBtn.setSize(200,50);
		easyBtn.setPosition(Screens.Width/2 - easyBtn.getWidth()/2, 400);
		easyBtn.addListener(new ClickListener()
	    {
	        @Override
	        public void clicked(InputEvent event, float x, float y) 
	        {
				Setting.getInstance().difficult = 0;
				screenList.getScreen("GAME1");
	        }
	    });
		
        // Normal button
		normalBtn = new TextButton("Normal", skin);
		normalBtn.setSize(200,50);
		normalBtn.setPosition(Screens.Width/2 - easyBtn.getWidth()/2, 300);
		normalBtn.addListener(new ClickListener()
		{
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				Setting.getInstance().difficult = 1;
				screenList.getScreen("GAME1");
			}
		});
		
        // Hard Button
		hardBtn = new TextButton("Hard", skin);
		hardBtn.setSize(200,50);
		hardBtn.setPosition(Screens.Width/2 - easyBtn.getWidth()/2, 200);
		hardBtn.addListener(new ClickListener()
		{
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				Setting.getInstance().difficult = 2;
				screenList.getScreen("GAME1");
			}
		});

		// Setting background of the screen
		setBackgroundImage(new Image(getTexture()));
        getBackgroundImage().setSize(Screens.Width, Screens.Height);
        
        // Adding all the actors to the stage
        getStage().addActor(getBackgroundImage());
		getStage().addActor(easyBtn);
		getStage().addActor(normalBtn);
		getStage().addActor(hardBtn);

	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}	

	@Override
	public void show() 
	{
        skin = new Skin(Gdx.files.internal("uiskin.json")); 
        setTexture(new Texture(background));
	    create();
	}

    @Override
	public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (Gdx.input.isTouched()) {
		    System.out.println("Screen touched at: " + Gdx.input.getX() + ", " + Gdx.input.getY());
		}
		fitViewport.apply();
		getStage().act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		getStage().draw();
    }
    @Override
	public void resize(int width, int height) {
		getStage().getViewport().update(width, height, true);
	}

	@Override
	public void hide() {		
	}

	@Override
	public void dispose() {

        Image.dispose();
        Font.dispose();
		
	}
}
