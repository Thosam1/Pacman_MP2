package ch.epfl.cs107.play.game.areagame.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;

public class AutomaticallyCollectableAreaEntity extends CollectableAreaEntity implements Interactable{

	public AutomaticallyCollectableAreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
	}
	public void collect() {
		this.unregisterActor();
	}
	
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((SuperPacmanInteractionVisitor)v).interactWith(this);
    }
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		
	}
}
