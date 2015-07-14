package com.shenko.rfk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;

public class RFKObject {
	
	private RFKGameScreen Screen;
	private MathUtils Math;
	public GridPoint2 Grid;
	
	private BitmapFont Font;
	
	private char Symbol;	
	private float[] Color = {0f, 0f, 0f};
	
	private char[] PossibleSymbols = { '~', '@', '$', '%', '&', '*', '?', '<', '>', '¢', '£', '©', '®', 'µ', '±', '¤', '¡', '¿' };

	private int Description;
	private String[] ItemDescriptions = {
		"It's Ranzo's used underwear.",
		"A better theme for SAGD10.",
		"A copy of Punch Fite 13: The Punchening.",
		"InternetJanitor's custom formulated cleaning solution.",
		"A bottle of Mister Pibb.",
		"A book entitled \"Big Harry Loves You\", for kids.",
		"A jar of over-priced supermarket cilantro.",
		"This is a bucket filled with tears of Unity devs.",
		"It seems to be a dead kitten. This doesn't count.",
		"Rule 34 artwork of Vlokk.",
		"Hey, it's an OUYA controller.",
		"It's a pile of UnrealEngine4 game jam entries.",
		"It's the entire back catalog of The Dollop.",
		"A bag of Surstromming Delight Chex Mix.",
		"Leftover pieces of Grover's house.",
		"It's a large stone Zardoz head, in the wrong game.",
		"Looks like a signed copy of Mad Ball. Cool.",
		"It's one of those iron coated in ceramic sort of things.",
		"A teensy radio controlled Zamboni.",
		"A six-pack of MOUNTAIN DEW GAMER FUEL.",
		"A printed copy of all of the Star Citizen promises.",
		"It's just a rock. A pebble.",
		"It's a cheap painted plastic chicken.",
		"A book of UnrealEngine4 tips written by crnobog and Rama.",
		"It's that guy that never actually talks in #sagamedev.",
		"This is a whole bunch of people that follow Ragdoll around games.",
		"It looks like Pysan Fain's hairpiece.",
		"A whole bunch of bad ads for e-cigarettes.",
		"A book titled \"How to Locate the Chicken Butt\"",
		"This is War Hero SpaceTexas. What is he doing here?",
		"A teensy pile of hopes and dreams from Elite Dangerous.",
		"Save files from Space Engineers servers.",
		"Notch's pile of Minecraft money.",
		"It's a driving cat! Look at him drive his little car!",
		"It's a jar full of sand and ants.",
		"Hey look it's a roast chicken. Robot is hungry.",
		"~-o@",
		"Fnord is the sound only ducks can hear."
	};
	
	public RFKObject(RFKGameScreen inScreen, GridPoint2 inGrid)
	{
		Screen = inScreen;
		Grid = inGrid;
		
		Symbol = PossibleSymbols[ Math.random( PossibleSymbols.length - 1) ];
		Color[0] = Math.random(0f, 1f);
		Color[1] = Math.random(0f, 1f);
		Color[2] = Math.random(0f, 1f);
		
		Description = Math.random(0, ItemDescriptions.length - 1);
		
		Font = new BitmapFont( Gdx.files.internal("RFKFont_Grad.fnt"), Gdx.files.internal("RFKFont_Grad.png"), true );
	}
	
	public String GetDescription()
	{
		return ItemDescriptions[Description];
	}
	
	public void Render(SpriteBatch Batch)
	{
		GridPoint2 DrawLoc = new GridPoint2();
		
		DrawLoc.x = Grid.x * 40;
		DrawLoc.y = Grid.y * 30;		

		Font.setColor(Color[0], Color[1], Color[2], 1f);
		Font.draw(Batch, String.valueOf(Symbol), DrawLoc.x, DrawLoc.y);

	}

}
