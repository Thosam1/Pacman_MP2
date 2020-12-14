package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.AutomaticallyCollectableAreaEntity;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Cherry extends AutomaticallyCollectableAreaEntity {
	public final int SCORE = 200; //score gagné quand un cherry est mangé
	private Sprite sprite;
	
	/**area désigne l'aire associée au niveau et DiscreteCoordinates permet de connaitre la position initiale
	 * le constructeur initialise l'animation du Cherry qui permettra de créer la sensation de rotation*/
	public Cherry(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		sprite = new Sprite("superpacman/cherry", 1.f, 1.f,this);
	}
	
	/** la méthode draw est appelée sur sprite à chaque frame tant que le Cherry n a pas été mangé*/
	public void draw(Canvas canvas) {
		sprite.draw(canvas);
	}
	
	/** permet les interactions avec les interactors*/
	public void acceptInteraction(AreaInteractionVisitor v) {
        ((SuperPacmanInteractionVisitor)v).interactWith(this);
    }
}
