package ch.epfl.cs107.play.game.superpacman.SuperPacmanAndGUI;

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
	SuperPacmanPlayer player;
	float height;
	float width;
	Vector anchor;
	
	SuperPacmanPlayerStatusGUI(Canvas canvas, SuperPacmanPlayer player, float DEPTH){
	width = canvas.getScaledWidth();
	height = canvas.getScaledHeight();
	this.player = player;
	anchor = canvas.getTransform().getOrigin().sub(new Vector(width/2, height/2));
	

	// est ce qu il faut créer une méthode draw/différente ou on peut tout instancier dans le corps du constructeur
	//je ne comprends pas comment faire pour que le pacman soit gris ou jaune
	//comment faire pour faire en sorte que cette classe soit seulement accessible a la classe SuperPacman ou a ses sous classes
	//Ca ne fonctionne pas en passant la classe en protected
	}
	/*
	public void draw(Canvas canvas) {//il faut passer m et n en paramètres?
		for (int i=0; i<5; i++) {
			for(int j=0; j<player.hp; ++j){
				ImageGraphics life = new
						ImageGraphics(ResourcePath.getSprite("superpacman/lifeDisplay"),
						1.f, 1.f, new RegionOfInterest(0, 0, 64, 64),
						anchor.add(new Vector(0,5+i*10, height - 1.375f)), 1, DEPTH);
						life.draw(canvas);
			}
			for(int w=hp; w<5; w++) {
				ImageGraphics life = new
						ImageGraphics(ResourcePath.getSprite("superpacman/lifeDisplay"),
						1.f, 1.f, new RegionOfInterest(64, 0, 64, 64),
						anchor.add(new Vector(0,5+i*10, height - 1.375f)), 1, DEPTH);
						life.draw(canvas);
			}
		TextGraphics score = new TextGraphics(String.valueOf(player.score), 1.f, Color.YELLOW, Color.BLACK, 2.f, true, true, new Vector(width/2, 0.f)); 
		score.draw(canvas);

		}
	}//est ce qu il faut créer une instance de cette classe dans SuperPacmanPlayer pour pouvoir appeler draw
	
	*/
}
