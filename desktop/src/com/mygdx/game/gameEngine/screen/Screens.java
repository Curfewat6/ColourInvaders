package com.mygdx.game.gameEngine.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public abstract class Screens implements Screen
{
	private Game game;
	private Stage stage;
    private Image backgroundImage;
    private Texture texture;
    private String name;
    protected static float Width = 800;
	protected static float Height = 600;
	
	public Screens(Game game, float Width, float Height)
	{
		this.game = game;
		stage = new Stage();
		Screens.Width = Width;
		Screens.Height = Height;
	}
	
	public String getName()
	{
		return name;
	}
	
    public Game getGame() 
    {
        return game;
    }
    
    public Stage getStage() 
    {
        return stage;
    }
    
    public Image getBackgroundImage() 
    {
    	return backgroundImage;
    }
    
    public Texture getTexture() 
    {
    	return texture;
    }
    
    public void setName(String name)
    {
    	this.name = name;
    }
    
    public void setGame(Game game)
    {
    	this.game = game;
    }
	
    public void setStage(Stage stage)
    {
    	this.stage = stage;
    }
    
    public void setBackgroundImage(Image backgroundImage)
    {
    	this.backgroundImage = backgroundImage;
    }
    
    public void setTexture(Texture texture)
    {
    	this.texture = texture;
    }
    
    public abstract void create();
    public abstract void dispose();
	public abstract void show();
	public abstract void render(float delta);
	public abstract void resize(int width, int height);
	public abstract void pause();
	public abstract void resume();
	public abstract void hide();

}
