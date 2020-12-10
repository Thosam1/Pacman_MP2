package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.SuperPacmanAndGUI.Gate;
import ch.epfl.cs107.play.game.superpacman.actor.Key;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.And;

public class Level2 extends SuperPacmanArea{
	public boolean noMoreDiamonds = false;
	public final static DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(15,29);
	@Override
	public String getTitle() {
		return "superpacman/Level2";
	}
	
	public DiscreteCoordinates getPlayerSpawnPosition() {
		return PLAYER_SPAWN_POSITION;
	}
	
	public void createArea(SuperPacmanBehavior behavior) {
        super.createArea(behavior);
        
        Key key1 = new Key(this, new DiscreteCoordinates(3,16));
        Key key2 = new Key(this, new DiscreteCoordinates(26,16));
        Key key3 = new Key(this, new DiscreteCoordinates(2,8));
        Key key4 = new Key(this, new DiscreteCoordinates(27,8));
        
        registerActor(new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(8,14), key1));
        registerActor(new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(5,12), key1));
        registerActor(new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(8,10), key1));
        registerActor(new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(8,8), key1));
        registerActor(new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(21,14), key2));
        registerActor(new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(24,12), key2));
        registerActor(new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(21,10), key2));
        registerActor(new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(21,8), key2));
        registerActor(new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(10,2), new And(key3, key4)));
        registerActor(new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(19,2), new And(key3, key4)));
        registerActor(new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(12,8), new And(key3, key4)));
        registerActor(new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(17,8), new And(key3, key4)));
        registerActor(new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(14,3), this));
        registerActor(new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(15,3), this));
	}
	
	@Override
	public boolean isOn() {
		if(numberOfDiamonds>0) {
			return false;
			}
		else return true;
	}

	@Override
	public boolean isOff() {
		// we are not using this for now
		return false;
	}

	@Override
	public float getIntensity() {
		//we are not using this for now
		return 0;
	}
}
