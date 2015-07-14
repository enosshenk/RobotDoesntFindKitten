package com.shenko.rfk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class RFKShenkoScreen implements Screen {
	
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
	
	public RFKShenkoScreen(RFKGame inGame)
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
		Logo = new Texture(Gdx.files.internal("logo_trans.png"));
		
		Alpha = 0f;
		MoveAlpha = 0f;
		BackdropY = -100f;
		LogoY = -50f;
		ShowTime = 0f;
		
		Mode = EMode.FadeIn;
	}

	@Override
	public void render(float delta) {
		
		if (Mode == EMode.FadeOut)
		{
			Alpha -= delta;
			
			if (Alpha < 0)
			{
				// Done
				Game.LogoScreenDone();
			}
		}
		
		if (Mode == EMode.Wait)
		{
			ShowTime += delta;
			
			if (ShowTime > 3)
			{
				Mode = EMode.FadeOut;
			}
		}
		
		if (Mode == EMode.FadeIn)
		{
			Alpha += delta;
			MoveAlpha += delta * 0.1;
			
			BackdropY = Math.lerp(BackdropY, 0, MoveAlpha);
			LogoY = Math.lerp(LogoY, 0, MoveAlpha);
			
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
