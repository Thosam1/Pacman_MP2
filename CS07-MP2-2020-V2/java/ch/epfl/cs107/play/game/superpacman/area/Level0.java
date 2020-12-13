package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.superpacman.SuperPacmanAndGUI.Gate;
import ch.epfl.cs107.play.game.superpacman.actor.Key;
import ch.epfl.cs107.play.game.superpacman.actor.Lever;
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
        registerActor(new Door("superpacman/Level1",Level1.PLAYER_SPAWN_POSITION, Logic.TRUE, this, Orientation.UP,new DiscreteCoordinates(5,9),new DiscreteCoordinates(6,9)));	//Logic.TRUE -> portes allum�es, le player devrait pouvoir les traverser
        Key key = new Key(this, new DiscreteCoordinates(3,4));
        registerActor(key);
        registerActor(new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(5,8), key,true));
        registerActor(new Gate(this, Orientation.LEFT, new DiscreteCoordinates(6,8), key,true));
        Lever lever = new Lever(this, new DiscreteCoordinates(5,7),false);
        registerActor(lever);
        registerActor(new Gate(this, Orientation.DOWN, new DiscreteCoordinates(4,7), lever,true));
        registerActor(new Gate(this, Orientation.LEFT, new DiscreteCoordinates(6,6), lever,false));
	}
	
	@Override
	public boolean isOn() {//we are not using this method, but it allows us to open Gates when all diamonds have been picked up
		if(numberOfDiamonds>0) {
			return false;
			}
		else return true;
	}

	@Override
	public boolean isOff() {
		// we are not using this method
		return false;
	}

	@Override
	public float getIntensity() {
		// we are not using this method for now
		return 0;
	}

}
