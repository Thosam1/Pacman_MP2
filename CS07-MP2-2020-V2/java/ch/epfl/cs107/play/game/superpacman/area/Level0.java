package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.superpacman.SuperPacmanAndGUI.Gate;
import ch.epfl.cs107.play.game.superpacman.actor.Key;
import ch.epfl.cs107.play.game.superpacman.actor.Lever;
import ch.epfl.cs107.play.game.superpacman.actor.Oscillateur;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class Level0 extends SuperPacmanArea{
	
	/**spawn position in this level*/
	private final DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(10, 1);
	@Override
	
	/**returns String name of the level */
	public String getTitle() {
		return "superpacman/Level0";
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
        super.createArea(behavior); //general, adding actors in the area/level
        
        //specific to the level :
        registerActor(new Door("superpacman/Level1", Level1.PLAYER_SPAWN_POSITION, Logic.TRUE, this, Orientation.UP,new DiscreteCoordinates(5,9),new DiscreteCoordinates(6,9)));	//Logic.TRUE -> portes allum�es, le player devrait pouvoir les traverser
        Key key = new Key(this, new DiscreteCoordinates(3,4));
        registerActor(key);
        //registerActor(new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(5,8), key,true));
        registerActor(new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(5,8), this,true));
        registerActor(new Gate(this, Orientation.LEFT, new DiscreteCoordinates(6,8), key,true));
        Lever lever = new Lever(this, new DiscreteCoordinates(5,7),false);
        registerActor(lever);
        registerActor(new Gate(this, Orientation.DOWN, new DiscreteCoordinates(4,7), lever,true));
        registerActor(new Gate(this, Orientation.LEFT, new DiscreteCoordinates(6,6), lever,false));
        
        Oscillateur oscillateur = new Oscillateur(72, true);
        registerActor(new Gate(this, Orientation.DOWN, new DiscreteCoordinates(2,6), oscillateur,true));
	}


}
