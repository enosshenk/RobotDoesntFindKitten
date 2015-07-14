package com.shenko.rfk;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RFKGame extends Game {

	@Override
	public void create() {		
	//	setScreen( new RFKGameScreen(this) );
		setScreen (new RFKDrugsScreen(this));
	}
	
	@Override
	public void render () {
		super.render();
	}
	
	public void DrugsScreenDone()
	{
		setScreen( new RFKShenkoScreen(this) );
	}
	
	public void LogoScreenDone()
	{
		setScreen( new RFKTitleScreen(this) );
	}
	
	public void TitleScreenDone()
	{
		setScreen( new RFKGameScreen(this) );
	}

}
