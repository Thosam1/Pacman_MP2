package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.actor.Ghost.SuperPacmanGhostHandler;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Inky extends Ghost {

	public Inky(Area area, Orientation orientation, DiscreteCoordinates coordinates, SuperPacmanPlayer player) {
		super(area, orientation, coordinates, player);
		setNameOfMainSprite("superpacman/ghost.inky");
	}
	
	public void update(float deltaTime) { // ?necessary?
		super.update(deltaTime); //taking care of afraid animation
	}
	
	
	private class SuperPacmanBlinkyHandler extends SuperPacmanGhostHandler {
		
	}
}
