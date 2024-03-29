package com.mygdx.game.gameLogic.screen;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.mygdx.game.gameEngine.utils.Setting;
import com.mygdx.game.gameEngine.utils.SoundsManager;
import com.mygdx.game.gameLogic.ai.AImovement;
import com.mygdx.game.gameLogic.collision.Collision;
import com.mygdx.game.gameLogic.entity.*;
import org.lwjgl.opengl.GL20;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.gameEngine.collision.CollisionManager;
import com.mygdx.game.gameEngine.entity.EntityManagement;
import com.mygdx.game.gameEngine.entity.EntityManager;
import com.mygdx.game.gameEngine.io.InputOutManagement;
import com.mygdx.game.gameEngine.io.InputOutputManager;
import com.mygdx.game.gameLogic.pcm.PlayerMovement;
import com.mygdx.game.gameEngine.pcm.PlayerControlManager;
import com.mygdx.game.gameEngine.screen.*;

public class GameScreen extends Screens implements PauseCallBack{
	private Skin skin;
	private EntityManagement entityList;
	private InputOutManagement ioManager;
	private ScreenManagement screenList;
	private Collision collision;
	private EntityFactory entityCreation;

	private Dialog pauseMenu;
	private boolean isPaused = false;
    private SpriteBatch batch;
    private FitViewport fitViewport;
	private String keyPressed;
	private String background;

	private SoundsManager soundsManager;
	private AImovement aiMovement;
	private PlayerMovement playerMovement;

	private Player player;
	private Cannon cannon;

	private Label wordLabel;
	private String word;
	private float spawnTime ;
	private float spawnTimeInterval;
	private Label scoreLabel;
	private int score = 0;
	
    public static int finalScore;


	public GameScreen(Game game, String name, String bgPath) 
	{
		super(game, Width, Height);
		setName(name);
		
		entityList = EntityManager.getInstance();
		PlayerControlManager.getInstance();
		ioManager = InputOutputManager.getInstance();
		ioManager.setPauseCallback(this);
		CollisionManager.getInstance();
		collision = Collision.getInstance();
		screenList = ScreenManager.getInstance();
		entityCreation = EntityFactory.getInstance();
		soundsManager = SoundsManager.getInstance();

		background = bgPath;
		
		batch = new SpriteBatch();
		EntityManager.getInstance().batch = batch;
		new BitmapFont();
	}
	
	public void create()
	{
		// Creating new viewport for the stage
		fitViewport = new FitViewport(Screens.Width, Screens.Height);
		Stage newStage = new Stage(fitViewport);
		setStage(newStage);
		
		// Setting a new input for the current stage
		Gdx.input.setInputProcessor(getStage());
		
		score = 0;

		// Creation of pause menu
		pauseMenu = new Dialog("", skin) {
		    public void result(Object obj) {
		        if ("QUIT".equals(obj)) {
		            resume();
		            screenList.getScreen("TITLE");
		        }
		    }
		};
		pauseMenu.text("Game Paused");
		pauseMenu.button("Go Home", "QUIT").addListener(new ClickListener() {
		    @Override
		    public void clicked(InputEvent event, float x, float y) {
		        super.clicked(event, x, y);
				soundsManager.stop("music");
		        event.stop(); // Consume the event to prevent it from propagating further
		    }
		});
		pauseMenu.hide();

		// Setting background of the screen
        setBackgroundImage(new Image(getTexture()));
        getBackgroundImage().setSize(Screens.Width, Screens.Height);

        // Creation of player entities
		cannon = (Cannon) entityCreation.createEntity(EntityEnum.CANNON,entityList);
		player = (Player) entityCreation.createEntity(EntityEnum.PLAYER,entityList);

		// Creation of Label for scores
		wordLabel = new Label(word,skin);
		wordLabel.setFontScale(2.2f);
		scoreLabel = new Label("Score: 0",skin);
		scoreLabel.setFontScale(1.5f);
		scoreLabel.setPosition(player.getPosX(),player.getPosY() + 20);
		scoreLabel.setColor(Color.CYAN);

		// Adding all the actors to the stage
		getStage().addActor(pauseMenu);
        getStage().addActor(getBackgroundImage());
		getStage().addActor(wordLabel);
		getStage().addActor(scoreLabel);

		// Play song from the soundsManager
		soundsManager.music();

		aiMovement = new AImovement();
		playerMovement = new PlayerMovement();

		// Spawn enemy based on different different difficulties
		spawnTime = 2;
		spawnTimeInterval = 0;
		switch (Setting.getInstance().difficult){
			case 0:
				spawnTime = 2;
				break;
			case 1:
				spawnTime = 1.5f;
				break;
			case 2:
				spawnTime = 0.5f;
				break;
		}
	}

	
	@Override
	public void pause() {
		pauseMenu.show(getStage());
	    isPaused = true;
		 
	}

	@Override
	public void resume() {
	    isPaused = false;
		pauseMenu.hide();
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
		fitViewport.apply();
		keyPressed = ioManager.handleInput();
		
		// Checks for user input to pause the game
		if(keyPressed.startsWith("pause")){
			togglePause();
		}
		
		// Running the game when its not paused
	    if (!isPaused) 
	    {
			word = playerMovement.PlayerMove();
	        entityList.update();
			aiMovement.aiMovement();
			
			// Ending game if user health reaches 0
			if(player.getLives() <= 0){
			    GameScreen.finalScore = score; // Assign the score to the static variable
				soundsManager.stop("music");
				screenList.getScreen("END");
				return;
			}
			score = collision.checkCollision(player,score,soundsManager);

			spawnTimeInterval -= Gdx.graphics.getDeltaTime();
			if(spawnTimeInterval < 0){
				spawnTimeInterval = spawnTime;
				entityCreation.createEntity(EntityEnum.ENEMY,entityList);
			}
	    }
		getStage().act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		getStage().draw();
		entityList.draw();
		wordLabel.setText(word);
		
		// Re-adjust actors positions when screen is being resized
		wordLabel.setPosition(player.getPosX() + 150 - wordLabel.getWidth(),100);
		changeTextColor(word);
        scoreLabel.setPosition(player.getPosX() + 200, player.getPosY() + 20);
		scoreLabel.setText("Score: " + String.valueOf(score));
	}

	// Change text color based on user input
	void changeTextColor(String color){
		wordLabel.setColor(Color.WHITE);
		if(color.equals("RED")){
			wordLabel.setColor(Color.RED);
		}
		if(color.equals("GREEN")){
			wordLabel.setColor(Color.GREEN);
		}
		if(color.equals("BLUE")){
			wordLabel.setColor(Color.BLUE);
		}
		if(color.equals("ORANGE")){
			wordLabel.setColor(Color.ORANGE);
		}
		if(color.equals("YELLOW")){
			wordLabel.setColor(Color.YELLOW);
		}
	}

	@Override
	public void resize(int width, int height) {
		
		cannon.setPosY(10);
		cannon.setPosX(300);
		
		player.setPosX(450);
		player.setPosY(10);

		getStage().getViewport().update(width, height, true);
	}


	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void togglePause() {
		isPaused = !isPaused;
		if(isPaused){
			pause();
			soundsManager.stop("music");
		} else{
			resume();
			soundsManager.music();
		}
	}	
}