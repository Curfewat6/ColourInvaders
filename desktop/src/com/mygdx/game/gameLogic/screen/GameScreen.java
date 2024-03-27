package com.mygdx.game.gameLogic.screen;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.mygdx.game.gameEngine.entity.Colliable;
import com.mygdx.game.gameEngine.utils.Setting;
import com.mygdx.game.gameEngine.utils.SoundsManager;
import com.mygdx.game.gameLogic.ai.AImovement;
import com.mygdx.game.gameLogic.collision.Collision;
import com.mygdx.game.gameLogic.entity.*;
import org.lwjgl.opengl.GL20;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.gameEngine.ai.AIManagement;
import com.mygdx.game.gameEngine.ai.AIManager;
import com.mygdx.game.gameEngine.collision.CollisionManagement;
import com.mygdx.game.gameEngine.collision.CollisionManager;
import com.mygdx.game.gameEngine.entity.Entity;
import com.mygdx.game.gameEngine.entity.EntityManagement;
import com.mygdx.game.gameEngine.entity.EntityManager;
import com.mygdx.game.gameLogic.io.InputOutManagement;
import com.mygdx.game.gameLogic.io.InputOutputManager;
import com.mygdx.game.gameLogic.level.LevelManagement;
import com.mygdx.game.gameLogic.level.LevelManager;
import com.mygdx.game.gameLogic.level.LevelSpecifier;
import com.mygdx.game.gameLogic.pcm.PlayerMovement;
import com.mygdx.game.gameEngine.pcm.PlayerControlManagement;
import com.mygdx.game.gameEngine.pcm.PlayerControlManager;
import com.mygdx.game.gameEngine.screen.*;

import java.util.ArrayList;
import java.util.List;


public class GameScreen extends Screens implements PauseCallBack{
	private Skin skin;
	private EntityManagement entityList;
	private CollisionManagement collisionManager;
	private PlayerControlManagement playerControl;
	private InputOutManagement ioManager;
	private AIManagement aiManager;
	private ScreenManagement screenList;
	private LevelManagement levelList;
	private Collision collision;
	private EntityFactory entityCreation;

	private Dialog pauseMenu;
	private boolean isPaused = false;
    private SpriteBatch batch;
    private BitmapFont font;
	private FitViewport fitViewport;
	private String keyPressed;
	private LevelSpecifier level;
	private String background;

	SoundsManager soundsManager;
	AImovement aiMovement;
	PlayerMovement playerMovement;


	Player player;
	Cannon cannon;

	Label wordLabel;
	String word;
	float spawnTime ;
	float spawnTimeInterval;
	Label scoreLabel;
	int score = 0;
	
    public static int finalScore;


	public GameScreen(Game game, String name, String bgPath) 
	{
		super(game, Width, Height);
		setName(name);
		entityList = EntityManager.getInstance();
		playerControl = PlayerControlManager.getInstance();
		ioManager = InputOutputManager.getInstance();
		ioManager.setPauseCallback(this);
		collisionManager = CollisionManager.getInstance();
		collision = Collision.getInstance();
		levelList = LevelManager.getInstance();
		screenList = ScreenManager.getInstance();
		entityCreation = EntityFactory.getInstance();
		background = bgPath;
		
		batch = new SpriteBatch();
		EntityManager.getInstance().batch = batch;
		font = new BitmapFont();
	}
	
	public void create()
	{
		fitViewport = new FitViewport(Screens.Width, Screens.Height);
		Stage newStage = new Stage(fitViewport);
		setStage(newStage);
		Gdx.input.setInputProcessor(getStage());

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
				score = 0;
		        event.stop(); // Consume the event to prevent it from propagating further
		    }
		});
		pauseMenu.hide();
		getStage().addActor(pauseMenu);

        setBackgroundImage(new Image(getTexture()));
        getBackgroundImage().setSize(Screens.Width, Screens.Height);
        
        getStage().addActor(getBackgroundImage());

		cannon = (Cannon) entityCreation.createEntity(EntityEnum.CANNON,entityList);
		player = (Player) entityCreation.createEntity(EntityEnum.PLAYER,entityList);

		skin = new Skin(Gdx.files.internal("uiskin.json"));

		word = "BLUE";
		wordLabel = new Label(word,skin);
		wordLabel.setFontScale(2.2f);
		scoreLabel = new Label("Score: 0",skin);
		scoreLabel.setFontScale(1.5f);
		scoreLabel.setPosition(player.getPosX() + 200,player.getPosY() + 20);
		scoreLabel.setColor(Color.CYAN);

		getStage().addActor(wordLabel);
		getStage().addActor(scoreLabel);

		soundsManager = new SoundsManager();
		soundsManager.music();

		aiMovement = new AImovement();
		playerMovement = new PlayerMovement();


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
	
	private void ScreenBounds() {
	    int screenWidth = Gdx.graphics.getWidth();
	    int screenHeight = Gdx.graphics.getHeight();

	    for (int i = 0; i < entityList.getEntities().size(); i++){
	   		Entity a = entityList.getEntities().get(i);
			   	if (!(a instanceof Player)) {
		             continue;
		        }	
		        Player player = (Player) a;
		        float newX = Math.max(0, Math.min(player.getPosX(), screenWidth - 64));
		 	    float newY = Math.max(0, Math.min(player.getPosY(), screenHeight - 64));

		 	    player.setPosX(newX);
		 	    player.setPosY(newY);
	    }
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		fitViewport.apply();
		//keyPressed = ioManager.handleInput();
	    if (!isPaused) 
	    {
			word = playerMovement.PlayerMove();
	        entityList.update();
			aiMovement.aiMovement();
			
			ScreenBounds();

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
		wordLabel.setPosition(player.getPosX() + 150 - wordLabel.getWidth(),100);
		changeTextColor(word);
		scoreLabel.setText("Score: " + String.valueOf(score));
	}

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