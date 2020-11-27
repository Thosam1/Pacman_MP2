package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.Signal;

public class Level1 extends SuperPacmanArea{
	public final DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(15, 6);
	@Override
	public String getTitle() {
		return "superpacman/Level1";
	}
	
	public void createArea(SuperPacmanBehavior behavior) {
        super.createArea(behavior);
        Door door1 = new Door("superpacman/Level1",new DiscreteCoordinates(15,29), Signal.ON, behavior, Orientation.DOWN,new DiscreteCoordinates(14,0));
        Door door2 = new Door("superpacman/Level1",new DiscreteCoordinates(15,29), Signal.ON, behavior, Orientation.DOWN,new DiscreteCoordinates(15,0));
        registerActor(door1);
        registerActor(door2);
        }
}
