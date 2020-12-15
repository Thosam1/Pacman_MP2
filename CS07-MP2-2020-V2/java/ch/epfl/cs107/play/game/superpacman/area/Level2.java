package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.actor.Gate;
import ch.epfl.cs107.play.game.superpacman.actor.Key;
import ch.epfl.cs107.play.game.superpacman.actor.Lever;
import ch.epfl.cs107.play.game.superpacman.actor.Oscillateur;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.And;

public class Level2 extends SuperPacmanArea{
	/**spawn position in this level*/
	public final static DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(15,29);
	
	@Override
	public String getTitle() {
		return "superpacman/Level2";
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
        
        Key key1 = new Key(this, new DiscreteCoordinates(3,16));
        Key key2 = new Key(this, new DiscreteCoordinates(26,16));
        Key key3 = new Key(this, new DiscreteCoordinates(2,8));
        Key key4 = new Key(this, new DiscreteCoordinates(27,8));
        registerActor(key1);
        registerActor(key2);
        registerActor(key3);
        registerActor(key4);
       
        registerActor(new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(8,14), key1, true));
        registerActor(new Gate(this, Orientation.DOWN, new DiscreteCoordinates(5,12), key1, true));
        registerActor(new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(8,10), key1, true));
        registerActor(new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(8,8), key1, true));
        registerActor(new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(21,14), key2, true));
        registerActor(new Gate(this, Orientation.DOWN, new DiscreteCoordinates(24,12), key2 ,true));
        registerActor(new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(21,10), key2,true));
        registerActor(new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(21,8), key2,true));
        registerActor(new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(10,2), new And(key3, key4),true));
        registerActor(new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(19,2), new And(key3, key4),true));
        registerActor(new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(12,8), new And(key3, key4),true));
        registerActor(new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(17,8), new And(key3, key4),true));
        registerActor(new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(14,3), this,true));
        registerActor(new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(15,3), this,true));
        
        registerActor(new Gate(this, Orientation.LEFT, new DiscreteCoordinates(15,20), new Oscillateur(72, true,0),true));
        registerActor(new Gate(this, Orientation.LEFT, new DiscreteCoordinates(14,20), new Oscillateur(72, true,0),false));
        registerActor(new Gate(this, Orientation.LEFT, new DiscreteCoordinates(15,18), new Oscillateur(72, true,0),false));
        registerActor(new Gate(this, Orientation.LEFT, new DiscreteCoordinates(14,18), new Oscillateur(72, true,0),true));
        registerActor(new Gate(this, Orientation.LEFT, new DiscreteCoordinates(15,16), new Oscillateur(72, true,0),true));
        registerActor(new Gate(this, Orientation.LEFT, new DiscreteCoordinates(14,16), new Oscillateur(72, true,0),false));
        registerActor(new Gate(this, Orientation.LEFT, new DiscreteCoordinates(15,14), new Oscillateur(72, true,0),false));
        registerActor(new Gate(this, Orientation.LEFT, new DiscreteCoordinates(14,14), new Oscillateur(72, true,0),true));
        
        Lever lever1 = new Lever(this, new DiscreteCoordinates(23,25),false);
        registerActor(lever1);
        registerActor(new Gate(this, Orientation.LEFT, new DiscreteCoordinates(26,23), lever1,true));
        registerActor(new Gate(this, Orientation.UP, new DiscreteCoordinates(23,20), lever1,false));
        registerActor(new Gate(this, Orientation.UP, new DiscreteCoordinates(19,27), lever1,false));
        
        Lever lever2 = new Lever(this, new DiscreteCoordinates(6,25),false);
        registerActor(lever2);
        registerActor(new Gate(this, Orientation.LEFT, new DiscreteCoordinates(3,23), lever2,true));
        registerActor(new Gate(this, Orientation.UP, new DiscreteCoordinates(6,20), lever2,false));
        registerActor(new Gate(this, Orientation.UP, new DiscreteCoordinates(10,27), lever2,false));
	
        registerActor(new Gate(this, Orientation.LEFT, new DiscreteCoordinates(9,20), new Oscillateur(72, true,0),true));
        registerActor(new Gate(this, Orientation.LEFT, new DiscreteCoordinates(9,18), new Oscillateur(72, true,5),true));
        registerActor(new Gate(this, Orientation.LEFT, new DiscreteCoordinates(9,16), new Oscillateur(72, true,10),true));
        registerActor(new Gate(this, Orientation.LEFT, new DiscreteCoordinates(20,20), new Oscillateur(72, true,10),true));
        registerActor(new Gate(this, Orientation.LEFT, new DiscreteCoordinates(20,18), new Oscillateur(72, true,5),true));
        registerActor(new Gate(this, Orientation.LEFT, new DiscreteCoordinates(20,16), new Oscillateur(72, true,0),true));
	}
}
