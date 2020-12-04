package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AutomaticallyCollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Key extends AutomaticallyCollectableAreaEntity{
	private boolean ramasséOuPas;
	public Key(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		ramasséOuPas = false;
	}
	
	public void collect() {
//		super.collect();
		ramasséOuPas = true;
	}
	public boolean isOn() {
		return ramasséOuPas;
	}
}
