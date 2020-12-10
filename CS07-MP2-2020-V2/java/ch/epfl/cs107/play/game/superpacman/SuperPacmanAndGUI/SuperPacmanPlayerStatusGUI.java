package ch.epfl.cs107.play.game.superpacman.SuperPacmanAndGUI;

import java.awt.Color;
import java.text.AttributedCharacterIterator;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.game.rpg.actor.Player;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.game.actor.Graphics;

public class SuperPacmanPlayerStatusGUI implements Graphics{
	private SuperPacmanPlayer player;
	private float height;
	private float width;
	private Vector anchor;
	private TextGraphics scoreTextGraphic;
	private ImageGraphics lifeYellow;
	private ImageGraphics lifeGray;
	private final float DEPTH = 10000;//nombre élevé pour avoir la priorité
	
	public SuperPacmanPlayerStatusGUI(SuperPacmanPlayer player){
	/*width = canvas.getScaledWidth();
	height = canvas.getScaledHeight();*/
		this.player = player;

	
	/*anchor = canvas.getTransform().getOrigin().sub(new Vector(width/2, height/2));
	scoreTextGraphic = new TextGraphics(String.valueOf(player.score), 1.f, Color.YELLOW, Color.BLACK, 2.f, true, true, new Vector(width/2, 0.f)); 
	
	lifeYellow = new
			ImageGraphics(ResourcePath.getSprite("superpacman/lifeDisplay"),
			1.f, 1.f, new RegionOfInterest(0, 0, 64, 64),
			anchor.add(new Vector(0, height - 1.375f)), 1, DEPTH);
	lifeGray = new
			ImageGraphics(ResourcePath.getSprite("superpacman/lifeDisplay"),
			1.f, 1.f, new RegionOfInterest(64, 0, 64, 64),
			anchor.add(new Vector(0, height - 1.375f)), 1, DEPTH);*/
	
	//comment faire pour faire en sorte que cette classe soit seulement accessible a la classe SuperPacman ou a ses sous classes
	//Ca ne fonctionne pas en passant la classe en protected
	}
	public void update(float deltaTime) {
		//scoreTextGraphic.setText(Integer.toString((int)player.score));
		
		
		/*for (int i=0; i<5; i++) {
			for(int j=0; j<player.hp; ++j){
				
				ImageGraphics life = new
					ImageGraphics(ResourcePath.getSprite("superpacman/lifeDisplay"),
					1.f, 1.f, new RegionOfInterest(0, 0, 64, 64),
					anchor.add(new Vector(0.5f+i*10, height - 1.375f)), 1, DEPTH);
			}
			for(int w=player.hp; w<5; w++) {
				ImageGraphics life = new
					ImageGraphics(ResourcePath.getSprite("superpacman/lifeDisplay"),
					1.f, 1.f, new RegionOfInterest(64, 0, 64, 64),
					anchor.add(new Vector(0.5f+i*10, height - 1.375f)), 1, DEPTH);
			}
		}*/
	}
	public void getLife(Canvas canvas){
		anchor = canvas.getTransform().getOrigin().sub(new Vector(width/2, height/2));
		for (int i=0; i<5; i++) {
			for(int j=0; j<player.hp; ++j){
				/*new ImageGraphics(ResourcePath.getSprite("superpacman/lifeDisplay"),
				1.f, 1.f, new RegionOfInterest(0, 0, 64, 64),
				anchor.add(new Vector(0.5f+i*10, height - 1.375f)), 1, DEPTH).draw(canvas);
				*/
				new ImageGraphics(ResourcePath.getSprite("superpacman/lifeDisplay"),
						1.f, 1.f, new RegionOfInterest(0, 0, 64, 64),
						anchor.add(new Vector(0.5f+i*10, height - 1.375f)), 1, DEPTH).draw(canvas);}
			for(int w=player.hp; w<5; w++) {
				new ImageGraphics(ResourcePath.getSprite("superpacman/lifeDisplay"),
				1.f, 1.f, new RegionOfInterest(64, 0, 64, 64),
				anchor.add(new Vector(0.5f+i*10, height - 1.375f)), 1, DEPTH).draw(canvas);
			}
		}
	}
	public void getScore(Canvas canvas) {
		scoreTextGraphic = new TextGraphics(String.valueOf(player.score), 1.f, Color.YELLOW, Color.BLACK, 2.f, true, true, new Vector(canvas.getScaledWidth()/2, 0.f)); 
		scoreTextGraphic.draw(canvas);
	}
	@Override
	public void draw(Canvas canvas) {
		//scoreTextGraphic.setText(Integer.toString((int)player.score));
		/*getLife(canvas);
		getScore(canvas);*/
		}
	}
