package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.SuperPacmanAndGUI.Gate;
import ch.epfl.cs107.play.game.superpacman.actor.Key;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level2 extends SuperPacmanArea{
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
        
        Gate gate1 = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(8,14), key1);
        Gate gate2 = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(5,12), key1);
        Gate gate3 = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(8,10), key1);
        Gate gate4 = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(8,8), key1);
        Gate gate5 = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(21,14), key2);
        Gate gate6 = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(24,12), key2);
        Gate gate7 = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(21,10), key2);
        Gate gate8 = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(21,8), key2);
        Gate gate9 = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(10,2), new And(key3, key4));
        Gate gate10 = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(19,2), new And(key3, key4));
        Gate gate11 = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(12,8), new And(key3, key4));
        Gate gate12 = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(17,8), new And(key3, key4));
        Gate gate13 = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(14,3), nomorediamond);
        Gate gate14 = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(15,3), nomorediamond);
	*/
	}
}
