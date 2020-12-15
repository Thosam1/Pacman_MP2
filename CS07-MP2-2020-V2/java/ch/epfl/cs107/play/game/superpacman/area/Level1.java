package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.superpacman.actor.Gate;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class Level1 extends SuperPacmanArea{
	/**spawn position in this level
	 * needs to be public so that the doors in Level0 can call it*/
	public final static DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(15, 6);
	
	@Override
	public String getTitle() {
		return "superpacman/Level1";
	}
	
	/**@return PLAYER_SPAWN_POSITION: position where the players spawn in this Level*/
	public DiscreteCoordinates getPlayerSpawnPosition() {
		return PLAYER_SPAWN_POSITION;
	}
	
	/**creates the Area, creating and adding the Actors which are not added by the class SuperPacmanBehavior
	 * These actors are:
	 * *keys (Key)
	 * *levers (Lever)
	 * *gates (Gates)
	 * *oscillators (Oscillateur)
	 * the method registerActor adds Actors to the Area
	 * */
	public void createArea(SuperPacmanBehavior behavior) {
        super.createArea(behavior);
        registerActor(new Door("superpacman/Level2", Level2.PLAYER_SPAWN_POSITION, Logic.TRUE, this, Orientation.DOWN, new DiscreteCoordinates(14,0), new DiscreteCoordinates(15,0)));
        registerActor(new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(14,3), this, true));
        registerActor(new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(15,3), this, true));
        }
}
