package ch.epfl.cs107.play.game.superpacman.SuperPacmanAndGUI;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.math.RegionOfInterest;

public class Gate extends AreaEntity{
	private Sprite sprite;
	public boolean affichage;
	public boolean affichageInitilisation;
	Logic signal;
	
	/**
	 * @param area: The area in which the Gate is registered
	 * @param orientation: The orientation that the gate has, this determines what Sprite is created (There exist two different Sprites
	 * @param position: Cell in which the the gate is placed
	 * @param signal: The signal associated to the Gate will determine if it is drawn or not
	 * @param affichageInit: This boolean will determine if the Gate is drawn or not*/
	public Gate(Area area, Orientation orientation, DiscreteCoordinates position, Logic signal, boolean affichageInit) {
		super(area, orientation, position);
		this.signal = signal;
		affichageInitilisation = affichageInit;
		affichage = affichageInit;
		if ((orientation == Orientation.DOWN)|(orientation == Orientation.UP)) {
			sprite = new Sprite("superpacman/gate", 1.f, 1.f,this, new RegionOfInterest(0,0,64,64));
			}
		else if ((orientation == Orientation.LEFT)|(orientation == Orientation.RIGHT)) {
			sprite = new Sprite("superpacman/gate", 1.f, 1.f,this, new RegionOfInterest(0,64,64,64));
			}
	}
	
	/**l'affichage dépend du signal associé .isOn(), mais aussi de affichageInitialisation
	 * Ceci est intéréssant car ça nous permet d'avoir des Gate fermé et ouvert associé au meme signal*/
	public void update(float deltaTime) {
		if(affichageInitilisation) {
			if (signal.isOn()) {
				affichage = false; 
				//getOwnerArea().unregisterActor(this);//permet de retirer le player quand le signal est off
			}
				else affichage = true;
		}
		else {
			if (signal.isOn()) {
				affichage = true; 
				//getOwnerArea().unregisterActor(this);//permet de retirer le player quand le signal est off
			}
				else affichage = false;
			}
		}
	
	/**retourne les coordonnées du Gate*/
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	/**Les Gates sont traversable seulement si ils ne sont pas affiché*/
	@Override
	public boolean takeCellSpace() {
		if(affichage) {
			return true;}
		else return false;
	}

	/**seulement les interactions de contact sont acceptées*/
	@Override
	public boolean isCellInteractable() {
		return true;
	}

	@Override
	public boolean isViewInteractable() {
		return false;
	}

	/**Les Gates n'ont pas de interactWith pour l'instant*/
	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
	}
	

	/**comme dit précédemment, si le bollean affichage est true le sprite est déssiné, sinon il ne l'est pas*/
	@Override
	public void draw(Canvas canvas) {
		if (affichage) {
			sprite.draw(canvas);
			}
	}

}
