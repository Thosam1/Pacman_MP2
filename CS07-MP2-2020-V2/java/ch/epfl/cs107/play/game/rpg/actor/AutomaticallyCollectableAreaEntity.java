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
	
	/**cette méthode est appelée lors des interactions entre un SuperPacmanPlayer et une des sousclasses
	 * de cette classe
	 * Sa première fonction est de unregister l'entité*/
	public void collect() {
        getOwnerArea().unregisterActor(this);
	}
	
	/**Permet au SuperPacmanPlayer d'avoir des interactions avec les Collectables
	 * Cette méthode est redéfinie par ses sous classes*/
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((SuperPacmanInteractionVisitor)v).interactWith(this);
    }
    
    /**La méthode draw est redéfinie par les sous classes*/
	@Override
	public void draw(Canvas canvas) {		
	}
}
