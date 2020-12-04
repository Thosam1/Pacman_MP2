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
        Door door1 = new Door("superpacman/Level1", Level2.PLAYER_SPAWN_POSITION, Logic.TRUE, this, Orientation.DOWN,new DiscreteCoordinates(14,0));
        Door door2 = new Door("superpacman/Level1", Level2.PLAYER_SPAWN_POSITION, Logic.TRUE, this, Orientation.DOWN,new DiscreteCoordinates(15,0));
        registerActor(door1);
        registerActor(door2);
        /*
        Gate gate1 = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(14,3), signal);
        Gate gate2 = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(15,3), signal);
        */
        
	}
}
