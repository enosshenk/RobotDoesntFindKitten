package com.shenko.rfk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;

public class RFKRobot {
	
	private RFKGameScreen Screen;
	private BitmapFont Font;	
	private GridPoint2 Location;
	
	public RFKRobot(RFKGameScreen inScreen)
	{
		Screen = inScreen;
		Font = new BitmapFont( Gdx.files.internal("RFKFont.fnt"), Gdx.files.internal("RFKFont.png"), true );
		
		Location = new GridPoint2(9, 9);
	}
	
	public void Render(SpriteBatch Batch)
	{
		Font.setColor(1f, 1f, 1f, 1f);
		
		Font.draw(Batch, "#", Location.x * 40, Location.y * 30);
	}
	
	public void TryMove(int Key)
	{
		if (Key == Input.Keys.LEFT)
		{
			if (Location.x > 0 && !Screen.CheckCollision( new GridPoint2( Location.x - 1, Location.y ) ))
			{
				Location.x--;
			}
		}
		else if (Key == Input.Keys.RIGHT)
		{
			if (Location.x < 19 && !Screen.CheckCollision( new GridPoint2( Location.x + 1, Location.y ) ))
			{
				Location.x++;
			}
		}
		else if (Key == Input.Keys.UP)
		{
			if (Location.y < 19 && !Screen.CheckCollision( new GridPoint2( Location.x, Location.y + 1) ))
			{
				Location.y++;
			}
		}
		else if (Key == Input.Keys.DOWN)
		{
			if (Location.y > 0 && !Screen.CheckCollision( new GridPoint2( Location.x, Location.y - 1) ))
			{
				Location.y--;
			}
		}
	}

}
