package ch.epfl.cs107.play.game.superpacman.SuperPacmanAndGUI;

import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.Signal;
import ch.epfl.cs107.play.window.Canvas;

public class Gate extends AreaEntity{

	public Gate(Area area, Orientation orientation, DiscreteCoordinates position, Signal key) {
		super(area, orientation, position);
	//	public Logic traversable = Logic.TRUE;
	}
	
	/*public Gate(Area area, Orientation orientation, DiscreteCoordinates position, Signal key1, Signal key2) {
		super(area, orientation, position);
		public Logic traversable = Logic.TRUE;
	}*/// pour créer des gates qui dépendent de deux clés
	
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean takeCellSpace() {
		return true;
	}

	@Override
	public boolean isCellInteractable() {
		return false;
	}

	@Override
	public boolean isViewInteractable() {
		return false;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		
	}

	//@Override
	//public void draw(Canvas canvas, Signal key1) {
		//if (key1 == Logic.TRUE) {
			
	//	}
		
	

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		
	}

}
