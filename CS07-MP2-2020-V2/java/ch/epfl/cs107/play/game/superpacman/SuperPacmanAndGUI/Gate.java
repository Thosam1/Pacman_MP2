package ch.epfl.cs107.play.game.superpacman.SuperPacmanAndGUI;

import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.Signal;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;

public class Gate extends AreaEntity{
	private Sprite sprite;
	Signal signal;
	public Gate(Area area, Orientation orientation, DiscreteCoordinates position, Signal signal) {
		super(area, orientation, position);
		this.signal = signal;
		if ((orientation == Orientation.DOWN)|(orientation == Orientation.UP)) {
			sprite = new Sprite("superpacman/key", 1.f, 1.f,this, new RegionOfInterest(0,0,64,64));
			}
		else ((orientation == Orientation.LEFT)|(orientation == Orientation.RIGHT)) {
			sprite = new Sprite("superpacman/key", 1.f, 1.f,this, new RegionOfInterest(0,64,64,64));
			}

	}
	
	public void update(float deltaTime) {
		if (signal.isOn()) {
			 getOwnerArea().unregisterActor(this);
		}
	}
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return null;
	}

	@Override
	public boolean takeCellSpace() {
		return true;
	}

	@Override
	public boolean isCellInteractable() {
		return true;
	}

	@Override
	public boolean isViewInteractable() {
		return false;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		 ((SuperPacmanInteractionVisitor)v).interactWith(this);
	}
	

	@Override
	public void draw(Canvas canvas) {
	/*
		sprite.draw(canvas);
		*/
	}

}
