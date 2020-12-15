package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.AutomaticallyCollectableAreaEntity;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Diamond extends AutomaticallyCollectableAreaEntity{
	public final int SCORE = 10; // score gagné quand un Diamond est mangé
	private Sprite sprite;
	
	/**area désigne l'aire du niveau et DiscreteCoordinates permet de connaitre la position initiale
	 * le constructeur initialise l'animation du Diamond qui permettra de créer la sensation de rotation*/
	public Diamond(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		sprite = new Sprite("superpacman/diamond", 1.f, 1.f,this);
	}
	
	/** la méthode draw est appelée sur sprite à chaque frame tant que le Diamond n a pas été mangé*/
	public void draw(Canvas canvas) {
		sprite.draw(canvas);
	}
	
	/** permet les interactions avec les interactors*/
	public void acceptInteraction(AreaInteractionVisitor v) {
        ((SuperPacmanInteractionVisitor)v).interactWith(this);
    }
}
