package ch.epfl.cs107.play.game.tutos;

import ch.epfl.cs107.play.game.areagame.Cell;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.tutos.Tuto2Behavior.Tuto2CellType;

public class Tuto2Cell extends Cell{
	boolean isWalkable;
	private final Tuto2CellType tuto2CellType;
	public Tuto2Cell(int x, int y, Tuto2CellType type){
		super(x,y);
		tuto2CellType = type;
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
		
	}

	@Override
	protected boolean canLeave(Interactable entity) {
		return true;
	}

	@Override
	protected boolean canEnter(Interactable entity) {
		if (isWalkable == true) {
			return true;}
		else {return false;}
	}

}
