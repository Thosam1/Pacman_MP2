package ch.epfl.cs107.play.game.rpg.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;

public class AutomaticallyCollectableAreaEntity extends CollectableAreaEntity implements Interactable{
	public AutomaticallyCollectableAreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);//orientation n est pas utile pour les Collectables que nous avons implémenté
	}
	public void collect() {
        getOwnerArea().unregisterActor(this);
	}
	
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((SuperPacmanInteractionVisitor)v).interactWith(this);
    }
	@Override
	public void draw(Canvas canvas) {		
	}
}
