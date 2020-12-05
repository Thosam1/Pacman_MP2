package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RandomGenerator;
import ch.epfl.cs107.play.window.Canvas;


public class Blinky extends Ghost {

	
	public Blinky(Area area, DiscreteCoordinates coordinates) {
		super(area, coordinates);
		setNameOfMainSprite("superpacman/ghost.blinky");
	}

	public void update(float deltaTime) { // ?necessary?
		super.update(deltaTime); //taking care of afraid animation
	}
	
	
	@Override	//why override DOESNT WORK ?!????????
	private Orientation getNextOrientation() {	//to redefine
		//how to find the next orientation
		int randomInt = RandomGenerator.getInstance().nextInt(4);	//(index 0-3 for the ordinal of orientation)
		nextOrientation = Orientation.fromInt(randomInt);
		return nextOrientation;
	}
	
	private class SuperPacmanBlinkyHandler extends SuperPacmanGhostHandler {
		
	}
}