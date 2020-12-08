package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class Level0 extends SuperPacmanArea{
	public final DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(10, 1);
	@Override
	public String getTitle() {
		return "superpacman/Level0";
	}
	
	public DiscreteCoordinates getPlayerSpawnPosition() {
		return PLAYER_SPAWN_POSITION;
	}
	
	public void createArea(SuperPacmanBehavior behavior) {
        super.createArea(behavior); //general, adding actors in the area/level
        
        //specific to the level :
        Door door1 = new Door("superpacman/Level1",Level1.PLAYER_SPAWN_POSITION, Logic.TRUE, this, Orientation.UP,new DiscreteCoordinates(5,9));	//Logic.TRUE -> portes allum�es, le player devrait pouvoir les traverser
        Door door2 = new Door("superpacman/Level1",Level1.PLAYER_SPAWN_POSITION, Logic.TRUE, this, Orientation.UP,new DiscreteCoordinates(6,9));
        registerActor(door1);
        registerActor(door2);
        //Key key = new Key(this, Orientation.RIGHT, new DiscreteCoordinates(3,4));
        //registerActor(key);
       /* Gate gate1 = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(5,8), key);
        Gate gate2 = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(6,8), key);
        registerActor(gate1);
        registerActor(gate2);*/
        
	}

}
