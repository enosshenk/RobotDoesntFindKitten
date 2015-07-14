package com.shenko.rfk;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class RFKGameScreen implements Screen {
	
	private RFKGame Game;	
	private SpriteBatch Batch;
	private ShapeRenderer Shape;
	private OrthographicCamera Camera;
	private MathUtils Math;
	
	private BitmapFont Font;
	
	private RFKRobot Robot;
	private List<RFKObject> Objects;
	
	private boolean ShowMessage;
	private boolean MessageFading;
	private float MessageAlpha;
	private String Message;
	private float MessageTime;
	private float MessageTimeElapsed;
	
	public RFKGameScreen(RFKGame inGame)
	{
		Game = inGame;
	}

	@Override
	public void show() {
		
		Batch = new SpriteBatch();
		Shape = new ShapeRenderer();
		
		// Set up the camera
		Camera = new OrthographicCamera();
		Camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 1);
		
		Font = new BitmapFont( Gdx.files.internal("RFKFont_Small.fnt"), Gdx.files.internal("RFKFont_Small.png"), false );
		
		Robot = new RFKRobot(this);
		
		Message = new String();
		ShowMessage = false;
		MessageTime = 0f;
		MessageTimeElapsed = 0f;
		MessageFading = false;
		MessageAlpha = 1f;
		
		GenerateObjects();		
		
		Gdx.input.setInputProcessor(new InputAdapter () {
			@Override
			public boolean keyDown (int keycode) {
				if (keycode == Input.Keys.LEFT || keycode == Input.Keys.RIGHT || keycode == Input.Keys.UP || keycode == Input.Keys.DOWN)
				{
					Robot.TryMove(keycode);
					return true;
				}
				else if (keycode == Input.Keys.ESCAPE)
				{
					Gdx.app.exit();
					return true;
				}
				else { return false; }
			}
		});
	}

	@Override
	public void render(float delta) {
		
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        Camera.update();
        
		Batch.setProjectionMatrix(Camera.combined);
		Batch.setColor(1,1,1,1);
		
		Batch.begin();
		Robot.Render(Batch);
		
		for (RFKObject o : Objects)
		{
			o.Render(Batch);
		}
		Batch.end();
		
		Shape.setProjectionMatrix(Camera.combined);
		Batch.setColor(1,1,1,0.5f);
		

		
		if (ShowMessage)
		{
			MessageTimeElapsed += delta;
			if (MessageTimeElapsed > MessageTime)
			{
				MessageFading = true;
			}
			
			// Draw text backdrop		
			Shape.begin(ShapeType.Filled);
			Shape.setColor(MessageAlpha, MessageAlpha, MessageAlpha, MessageAlpha);
			Shape.rect(5, 5, 790, 20);
			Shape.end();
			Batch.begin();
			// Draw text
			Font.setColor(0.1f, 0.1f, 0.1f, MessageAlpha);
			Font.draw(Batch, Message, 8, 23);
			Batch.end();
		}
		
		if (MessageFading)
		{
			MessageAlpha -= delta;
			if (MessageAlpha < 0)
			{
				ShowMessage = false;
				Message = "";
			}
		}
	}
	
	public boolean CheckCollision(GridPoint2 inGrid)
	{
		
		for (RFKObject o : Objects)
		{
			if (o.Grid.x == inGrid.x && o.Grid.y == inGrid.y)
			{
				// Object is on that space
				ShowMessage( o.GetDescription() );
				return true;
			}
		}
		
		return false;
	}
	
	public void ShowMessage(String inString)
	{
		MessageTime = (float)(inString.length() * 0.1);
		MessageTimeElapsed = 0f;
		ShowMessage = true;
		Message = inString;
		MessageFading = false;
		MessageAlpha = 1f;
	}
	
	public void GenerateObjects() {
		
		Objects = new CopyOnWriteArrayList<RFKObject>();
		
		for (int x=0; x < 19; x++)
		{
			for (int y=0; y < 19; y++)
			{
				
				if (Math.randomBoolean(0.05f))
				{
					Objects.add( new RFKObject(this, new GridPoint2(x, y) ) );
				}
			}
		}		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
