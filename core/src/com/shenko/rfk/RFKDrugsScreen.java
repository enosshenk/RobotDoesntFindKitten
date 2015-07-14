package com.shenko.rfk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RFKDrugsScreen implements Screen {

	private RFKGame Game;	
	private SpriteBatch Batch;
	private OrthographicCamera Camera;
	
	private float FadeAlpha = 0f;
	private float ShowTime = 0f;
	
	private Texture DrugsTexture;
	
	private enum EMode { FadeIn, Wait, FadeOut };
	private EMode Mode;

	public RFKDrugsScreen(RFKGame inGame)
	{
		Game = inGame;
	}
	
	@Override
	public void show() {
		
		Mode = EMode.FadeIn;
		DrugsTexture = new Texture(Gdx.files.internal("dontusedrugs.png"));
		
		Batch = new SpriteBatch();
		
		// Set up the camera
		Camera = new OrthographicCamera();
		Camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 1);
	}

	@Override
	public void render(float delta) {
		
		if (Mode == EMode.FadeOut)
		{
			FadeAlpha -= delta;
			
			if (FadeAlpha < 0)
			{
				// Done
				Game.DrugsScreenDone();
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
			FadeAlpha += delta;
			
			if (FadeAlpha > 1)
			{
				Mode = EMode.Wait;
				ShowTime = 0f;
				FadeAlpha = 1f;
			}
		}
		
		Batch.setColor(1f, 1f, 1f, FadeAlpha);
		Batch.begin();
		Batch.draw(DrugsTexture, 0, 0, 800, 600);
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
