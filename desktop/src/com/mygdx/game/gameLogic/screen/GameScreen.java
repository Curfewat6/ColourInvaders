package com.mygdx.game.gameLogic.screen;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.mygdx.game.gameEngine.entity.Colliable;
import com.mygdx.game.gameEngine.utils.Setting;
import com.mygdx.game.gameEngine.utils.SoundsManager;
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

	private Dialog pauseMenu;
	private boolean isPaused = false;
    private SpriteBatch batch;
    private BitmapFont font;
    private int totalCollisions = 0;
	private FitViewport fitViewport;
	private String keyPressed;
	private LevelSpecifier level;
	private String background;

	SoundsManager soundsManager;


	Player player;
	Cannon cannon;

	Label wordLabel;
	String word;
	float spawnTime ;
	float spawnTimeInterval ;
	Label scoreLabel;
	int score = 0;

	public GameScreen(Game game, String name, LevelSpecifier level) 
	{
		super(game, Width, Height);
		setName(name);
		entityList = EntityManager.getInstance();
		playerControl = PlayerControlManager.getInstance();
		ioManager = InputOutputManager.getInstance();
		ioManager.setPauseCallback(this);
		collisionManager = CollisionManager.getInstance();
		aiManager = AIManager.getInstance();
		levelList = LevelManager.getInstance();
		screenList = ScreenManager.getInstance();
		background = level.getBgPath();
		
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
		        event.stop(); // Consume the event to prevent it from propagating further
		    }
		});
		pauseMenu.hide();
		getStage().addActor(pauseMenu);

        setBackgroundImage(new Image(getTexture()));
        getBackgroundImage().setSize(Screens.Width, Screens.Height);
        
        getStage().addActor(getBackgroundImage());

		player = new Player();
		cannon = new Cannon();
		entityList.addEntity(player);
		entityList.addEntity(cannon);

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
		//if (ioManager.handleInput()){
			//isPaused = !isPaused;
		//}
		keyPressed = ioManager.handleInput();
	    if (!isPaused) 
	    {
	    	if (keyPressed.startsWith("left")) {
				cannon.setRotateSpeed(3);
			} else if (keyPressed.startsWith("right")) {
				cannon.setRotateSpeed(-3);
			} else if (keyPressed.startsWith("SHOOT:") ) {
				Bullet bullet = cannon.shoot(word);
				if(bullet != null){
					entityList.addEntity(bullet);
				}
			}
			else if (keyPressed.startsWith("BACKSPACE") ) {
				if(word.length() > 0){
//					word = word.substring(0, word.length() - 1);
					word = "";
				}
			}

			else {
				cannon.setRotateSpeed(0);

				if(keyPressed.length() == 1){
					char typeChar = keyPressed.charAt(0);
					word += typeChar;

				}

			}
	        entityList.update();
			ScreenBounds();

			if(player.getLives() <= 0){
				soundsManager.stop("music");
				screenList.getScreen("END");
				return;
			}

			List<Entity> addedEntities  = new ArrayList<>();

	        for(Entity e : entityList.getEntities()){
				if(e instanceof Enemy){
					if(e.getPosY() <= 80){
						player.setLives(player.getLives()-1);
						e.setDestroyFlag(true);
						score -= 1;
						if(score < 0){
							score = 0;
						}
						soundsManager.play("death",1.0f);

						continue;
					}
				}

				// spawn explosion
				for(Entity otherE : entityList.getEntities()){
					if(e == otherE){
						continue;
					}
					if( e instanceof Bullet && otherE instanceof Enemy ){
						Rectangle a = ((Colliable)e).getRectBound();
						Rectangle b = ((Colliable)otherE).getRectBound();

						if(CollisionManager.getInstance().rectCollide(a,b)){
							if(((Bullet) e).getColor().equals(((Enemy)otherE).getColor())){
								score += 1;
								soundsManager.play("correct",1.0f);
								e.setDestroyFlag(true);
								otherE.setDestroyFlag(true);
								Explosion explosion = new Explosion(otherE.getPosX() - 32,otherE.getPosY() - 32,((Bullet)e).getColor());
								addedEntities.add(explosion);
							}
							else{
								Explosion explosion = new Explosion(e.getPosX() - 32,e.getPosY() - 32,((Bullet)e).getColor());
								addedEntities.add(explosion);
								e.setDestroyFlag(true);
								score -= 1;
								if(score < 0){
									score = 0;
								}
								player.setLives(player.getLives()-1);
								soundsManager.play("death",1.0f);
							}
						}
					}
				}
			}
			entityList.getEntities().addAll(addedEntities);
	
			// [ATTENTION!] Hi i commented this out to test my IO. You can uncomment it if need be. ~ Lucas <3
	        //System.out.println("Total collisions so far: " + totalCollisions);
	        
		    // Check if there have been any collisions
		    if (totalCollisions >= 50 ) 
		    {
		    	 // Switch to end game scene
		    	 // getGame().setScreen(new EndScreen(getGame()));
			    screenList.getScreen("END");

		    }

			///////////
			spawnTimeInterval -= Gdx.graphics.getDeltaTime();
			if(spawnTimeInterval < 0){
				spawnTimeInterval = spawnTime;
				entityList.addEntity(new Enemy());
			}

	    }
	    
		getStage().act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		getStage().draw();
		entityList.draw();
		wordLabel.setText(word);
		wordLabel.setPosition(player.getPosX() + 150 - wordLabel.getWidth(),100);
		changeTextColor(word);
		scoreLabel.setText("Score: " + String.valueOf(score));


        
		if (isPaused) {
		    // Example: Display a simple pause overlay
		    // batch.begin();
			// font.draw(batch, "Paused - Press Esc to Resume", 100, 150);
		    // batch.end();
		}

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
		// TODO Auto-generated method stub
		isPaused = !isPaused;
		if(isPaused){
			pause();
		} else{
			resume();
		}
	}
	
}