package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.*;
import java.util.function.Predicate;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Path;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;


public class Inky extends IntelligentGhost {
	// Inky constants
	private static final String SPRITE_NAME = "superpacman/ghost.inky";
	private static final int MOVE_SPEED_WHEN_AFRAID = 12;
	private static final int MAX_DISTANCE_WHEN_SCARED = 5;
	private static final int MAX_DISTANCE_WHEN_NOT_SCARED = 10;


	public Inky(Area area, DiscreteCoordinates coordinates) {
		super(area, coordinates);
		this.attributeMainSprite(SPRITE_NAME);
	}
	
	public void update(float deltaTime) {
		super.update(deltaTime); //taking care of afraid animation
	}
	/**
	 * Get the move speed of the ghost
	 */
	@Override
	protected int getMoveSpeed() {
		// When Inky ghosts are afraid, they move faster than usually.
		return getAfraid() ? MOVE_SPEED_WHEN_AFRAID : super.getMoveSpeed();
	}

	/**
	 * Computes a new target for the Inky ghost.
	 * @return (DiscreteCoordinates) The new target
	 */
	@Override
	protected DiscreteCoordinates computeTarget() {
		// Aim for the player to eat it
		DiscreteCoordinates playerPos = getPlayerPosition();
		if (!getAfraid() && playerPos != null && isReachable(playerPos)) {
			return playerPos;
		}

		// Otherwise, return to the refuge.
		// The maximum distance (radius) to the refuge depends on whether the ghost is vulnerable or not.
		int maxRadius = getAfraid() ? MAX_DISTANCE_WHEN_SCARED : MAX_DISTANCE_WHEN_NOT_SCARED;
		Predicate<DiscreteCoordinates> isNearRefuge =
				cellPos -> DiscreteCoordinates.distanceBetween(cellPos, getRefuge()) <= maxRadius;
		return getRandomReachableCellPosition(isNearRefuge);
	}

}


