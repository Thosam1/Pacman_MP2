package ch.epfl.cs107.play.game.areagame.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class AutomaticallyCollectableAreaEntity extends CollectableAreaEntity{

	public AutomaticallyCollectableAreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
	}
	public void collect() {
		//area.unregisterActor(this);
	}
	/*La prof a proposé ça mais je ne sais pas quoi en faire
	@Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((SuperPacmanInteractionVisitor)v).interactWith(this);
    }*/
	
}
