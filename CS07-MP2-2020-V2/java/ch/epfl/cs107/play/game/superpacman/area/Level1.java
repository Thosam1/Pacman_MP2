package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.superpacman.SuperPacmanAndGUI.Gate;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class Level1 extends SuperPacmanArea{
	public final static DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(15, 6);
	@Override
	public String getTitle() {
		return "superpacman/Level1";
	}
	
	public DiscreteCoordinates getPlayerSpawnPosition() {
		return PLAYER_SPAWN_POSITION;
	}
	
	public void createArea(SuperPacmanBehavior behavior) {
        super.createArea(behavior);
        registerActor(new Door("superpacman/Level1", Level2.PLAYER_SPAWN_POSITION, Logic.TRUE, this, Orientation.DOWN,new DiscreteCoordinates(14,0)));
        registerActor(new Door("superpacman/Level1", Level2.PLAYER_SPAWN_POSITION, Logic.TRUE, this, Orientation.DOWN,new DiscreteCoordinates(15,0)));
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
		// we are not using this method for now
		return false;
	}

	@Override
	public float getIntensity() {
		// we are not using this method for now
		return 0;
	}
}
