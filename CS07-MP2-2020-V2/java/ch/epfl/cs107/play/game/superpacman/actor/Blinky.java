package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;


public class Blinky extends Ghost {

	
	public Blinky(Area area, Orientation orientation, DiscreteCoordinates coordinates) {
		super(area, orientation, coordinates);
		mainSprites = RPGSprite.extractSprites("superpacman/ghost.blinky", 2, 1, 1, this, 64, 64,	//4 frames in each row, width 1, height 1, parent this, width of frame (nb pixels in the image), height of frame
                new Orientation[] {Orientation.DOWN, Orientation.LEFT, Orientation.UP, Orientation.RIGHT}); //order Orientation[] orders of frame in the image
        //array of 4 Sprite[] 1 per orientation
		mainAnimations = Animation.createAnimations(ANIMATION_DURATION / 4, mainSprites);	//crée un tableau de 4 animations
	}


	private class SuperPacmanBlinkyHandler extends SuperPacmanGhostHandler {
		
	}
}
