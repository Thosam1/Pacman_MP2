package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.*;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Path;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;


public class Inky extends IntelligentGhost {
	private final int MAX_DISTANCE_WHEN_SCARED = 5;	//random distance from refuge
	int MAX_DISTANCE_WHEN_NOT_SCARED = 10;


	public Inky(Area area, DiscreteCoordinates coordinates) {
		super(area, coordinates);
		this.attributeMainSprite("superpacman/ghost.inky");
	}
	
	public void update(float deltaTime) {
		deplacement(getNextOrientation(getRefuge(), getRefuge(), MAX_DISTANCE_WHEN_SCARED, MAX_DISTANCE_WHEN_NOT_SCARED), SPEED, SPEED_AFRAID);
		super.update(deltaTime); //taking care of afraid animation
	}

}


