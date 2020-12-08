package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.actor.Ghost.SuperPacmanGhostHandler;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Inky extends Ghost {

	private final int MAX_DISTANCE_WHEN_SCARED = 5;
	
	public Inky(Area area, DiscreteCoordinates coordinates) {
		super(area, coordinates);
		setNameOfMainSprite("superpacman/ghost.inky");
	}
	
	public void update(float deltaTime) { // ?necessary?
		super.update(deltaTime); //taking care of afraid animation
		deplacement();
	}
	
	
	private void deplacement() {
		if(AFRAID == true) {	//refuge
			
		}else {//view
		}
		
		
	}
	
	private void frightened() { //back to refuge
		
	}
	
	private class SuperPacmanBlinkyHandler extends SuperPacmanGhostHandler {
		
	}
}
