package ch.epfl.cs107.play.game.superpacman.actor;

import java.awt.Color;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.game.actor.Graphics;
/**Une instance de cette classe est créée dans SuperPacmanPlayer
  la méthode draw de cette instance est alors invoquée dans la méthode draw du Player*/

public class SuperPacmanPlayerStatusGUI implements Graphics{
	private SuperPacmanPlayer player;
	//private float height; If we want the vectors to depend a certain height
	private Vector anchor;
	private final float DEPTH = 10000; //valeur élevé permet d'avoir la priorité sur les autres Graphics

	/**Le constructeur prend un SuperPacmanPlayer en paramètre et le stocke dans player
	 * protected pour que seul les acteurs et les sous classes puissent y accéder*/
	protected SuperPacmanPlayerStatusGUI(SuperPacmanPlayer player){
		this.player = player;
	}
	
	/**La méthode getLife est appelée dans draw de cette classe
	 * Sa fonction est de déssiner 5 vies (grise ou jaune)
	 * Cela dépend de la valeur de hp associé au player
	 * Le nombre de vie jaune est celui de hp, puis le reste sont grises
	 * Pour cela elle appele la méthode draw sur 5 instances ImageGraphics */
	public void getLife(Canvas canvas){
		anchor = canvas.getTransform().getOrigin();
		
		for(int j=0; j<(int)player.getHp(); ++j){
			new ImageGraphics(ResourcePath.getSprite("superpacman/lifeDisplay"),
			1.f, 1.f, new RegionOfInterest(0, 0, 64, 64),
			anchor.add(new Vector(-7f+j, 6f)), 1, DEPTH).draw(canvas);
		}
		for(int w=(int)player.getHp(); w<5; w++) {
			new ImageGraphics(ResourcePath.getSprite("superpacman/lifeDisplay"),
			1.f, 1.f, new RegionOfInterest(64, 0, 64, 64),
			anchor.add(new Vector(-7f+w, 6f)), 1, DEPTH).draw(canvas);
		}
	}
	
	/**La méthode getScore est appelée dans draw de cette classe
	 * Sa fonction est de déssiner le score du player
	 * cela se fait en appelant la méthode draw sur une instance de TextGraphics qui prend le score en paramètre*/
	public void getScore(Canvas canvas) {
		anchor = canvas.getTransform().getOrigin();// place le milieu de l' écran en référentiel
		new TextGraphics("Score: " + String.valueOf(player.getScore()), 1.2f, Color.BLACK, Color.YELLOW, 0.025f, true, true,
				anchor.add(new Vector(-1f, 6f))).draw(canvas); 
		//La première couleur désigne la couleur de l'intérieur des lettres tandis que la deuxième désigne celle de l'exterieur
	}
	
	/**Cette méthode est appelée à chaque frame pour créer une image fluide des vies et du score du player*/
	@Override
	public void draw(Canvas canvas) {
		getLife(canvas);
		getScore(canvas);
		}
	}
