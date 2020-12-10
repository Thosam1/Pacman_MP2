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
	public final int SCORE = 10;
	private Sprite sprite;
	
	public Diamond(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		sprite = new Sprite("superpacman/diamond", 1.f, 1.f,this);
	}
	public void draw(Canvas canvas) {
		sprite.draw(canvas);
	}
	public void acceptInteraction(AreaInteractionVisitor v) {
        ((SuperPacmanInteractionVisitor)v).interactWith(this);
    }
}
