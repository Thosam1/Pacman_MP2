package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Pinky extends Ghost {

	public Pinky(Area area, DiscreteCoordinates coordinates) {
		super(area, coordinates);
		setNameOfMainSprite("superpacman/ghost.pinky");
	}
	
	public void update(float deltaTime) { // ?necessary?
		super.update(deltaTime); //taking care of afraid animation
	}

}
