package com.shenko.rfk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class RFKTitleScreen implements Screen {
	
	private RFKGame Game;
	private SpriteBatch Batch;
	private OrthographicCamera Camera;
	private MathUtils Math;
	
	private Texture Backdrop;
	private Texture Logo;
	
	private enum EMode { FadeIn, Wait, FadeOut }
	private EMode Mode;
	
	private float Alpha;
	private float MoveAlpha;
	private float BackdropY;
	private float LogoY;
	private float ShowTime;
	
	private boolean TextColorUp;
	private float TextColor;
	
	private BitmapFont Font;
	
	public RFKTitleScreen(RFKGame inGame)
	{
		Game = inGame;
	}
	
	@Override
	public void show() {
		Batch = new SpriteBatch();
		
		// Set up the camera
		Camera = new OrthographicCamera();
		Camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 1);
		
		Backdrop = new Texture(Gdx.files.internal("backdrop.png"));
		Logo = new Texture(Gdx.files.internal("gamelogo.png"));
		
		Alpha = 0f;
		MoveAlpha = 0f;
		BackdropY = -100f;
		LogoY = -50f;
		ShowTime = 0f;
		
		TextColorUp = true;
		TextColor = 0f;
		
		Mode = EMode.FadeIn;	
		
		Font = new BitmapFont( Gdx.files.internal("RFKFont_Grad.fnt"), Gdx.files.internal("RFKFont_Grad.png"), false );
		
		Gdx.input.setInputProcessor(new InputAdapter () {
			@Override
			public boolean keyDown (int keycode) {
				if (keycode == Input.Keys.SPACE )
				{
					Game.TitleScreenDone();
					return true;
				}
				else { return false; }
			}
		});
	}

	@Override
	public void render(float delta) {
		

		
		if (Mode == EMode.FadeIn)
		{
			Alpha += delta;
			MoveAlpha += delta * 0.1;
			
			BackdropY = Math.lerp(BackdropY, 0, MoveAlpha);
			LogoY = Math.lerp(LogoY, 15, MoveAlpha);
			
			if (Alpha > 1)
			{
				Mode = EMode.Wait;
				ShowTime = 0f;
				Alpha = 1f;
			}
		}

		Batch.setColor(1f, 1f, 1f, Alpha);
		Batch.begin();
		Batch.draw(Backdrop, -112, BackdropY, 1024, 768);
		Batch.draw(Logo, 0, LogoY, 800, 600);
		
		if (Mode == EMode.Wait)
		{
			if (TextColorUp)
			{
				TextColor += delta;
				if (TextColor > 1)
				{
					TextColorUp = false;
					TextColor = 1f;
				}
			}
			else
			{
				TextColor -= delta;
				if (TextColor < 0)
				{
					TextColorUp = true;
					TextColor = 0f;
				}
			}
			
			Font.setColor(TextColor, TextColor, TextColor, TextColor);
			Font.draw(Batch, "Press Space to play!", 250, 50);
		}
		
		Batch.end();		
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
