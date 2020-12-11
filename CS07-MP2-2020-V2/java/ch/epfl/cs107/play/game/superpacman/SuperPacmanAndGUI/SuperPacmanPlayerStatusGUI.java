package ch.epfl.cs107.play.game.superpacman.SuperPacmanAndGUI;

import java.awt.Color;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.game.actor.Graphics;
/*Une instance de cette classe est créée dans SuperPacmanPlayer
  la méthode draw de cette instance est alors invoquée dans la méthode draw du Player*/

public class SuperPacmanPlayerStatusGUI implements Graphics{
	private SuperPacmanPlayer player;
	//private float height; If we want the vectors to depend a certain height
	private Vector anchor;
	private final float DEPTH = 10000;//nombre élevé pour avoir la priorité
	
	public SuperPacmanPlayerStatusGUI(SuperPacmanPlayer player){
		this.player = player;
	}
	public void 
	getLife(Canvas canvas){
		anchor = canvas.getTransform().getOrigin();
		
		for(int j=0; j<(int)player.hp; ++j){
			new ImageGraphics(ResourcePath.getSprite("superpacman/lifeDisplay"),
			1.f, 1.f, new RegionOfInterest(0, 0, 64, 64),
			anchor.add(new Vector(-7f+j, 6f)), 1, DEPTH).draw(canvas);
		}
		for(int w=(int)player.hp; w<5; w++) {
			new ImageGraphics(ResourcePath.getSprite("superpacman/lifeDisplay"),
			1.f, 1.f, new RegionOfInterest(64, 0, 64, 64),
			anchor.add(new Vector(-7f+w, 6f)), 1, DEPTH).draw(canvas);
		}
	}
	public void getScore(Canvas canvas) {
		anchor = canvas.getTransform().getOrigin();// place le milieu de l' écran en référentiel
		new TextGraphics("Score: " + String.valueOf(player.score), 1.2f, Color.BLACK, Color.YELLOW, 0.025f, true, true,
				anchor.add(new Vector(-1f, 6f))).draw(canvas); 
		//La première couleur désigne la couleur de l'intérieur des lettres tandis que la deuxième désigne celle de l'exterieur
	}
	@Override
	public void draw(Canvas canvas) {
		getLife(canvas);
		getScore(canvas);
		}
	}
