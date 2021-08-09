package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

import java.util.Queue;
import java.util.function.Predicate;

import static ch.epfl.cs107.play.math.DiscreteCoordinates.distanceBetween;

public class Pinky extends IntelligentGhost {

	// Pinky constants
	private static final String SPRITE_NAME = "superpacman/ghost.pinky";
	private static final int MIN_DISTANCE_WHEN_SCARED = 5;
	private static final int MAX_RANDOM_ATTEMPTS = 200; // Max attempts when searching a path


	public Pinky(Area area, DiscreteCoordinates coordinates) {
		super(area, coordinates);
		this.attributeMainSprite(SPRITE_NAME);
	}
	
	public void update(float deltaTime) {
		super.update(deltaTime); //taking care of afraid animation
	}

	/**
	 * Computes a new target for the Pinky ghost.
	 *
	 * @return (DiscreteCoordinates) The new target
	 */
	@Override
	protected DiscreteCoordinates computeTarget() {
		// Aim for the player to eat it
		DiscreteCoordinates playerPos = getPlayerPosition();
		if (!getAfraid() && playerPos != null && isReachable(playerPos)) {
			return playerPos;
		}

		// When Pinky is afraid and knows where the player is, it reaches for a cell away from the player
		if (playerPos != null) {
			Predicate<DiscreteCoordinates> isFarFromPlayer =
					cellPos -> DiscreteCoordinates.distanceBetween(playerPos, cellPos) >= MIN_DISTANCE_WHEN_SCARED
							&& isPathSafe(cellPos);

			SuperPacmanArea area = (SuperPacmanArea) getOwnerArea();
			DiscreteCoordinates escapeCellPosition = area.getRandomCellPosition(isFarFromPlayer, MAX_RANDOM_ATTEMPTS);
			if (escapeCellPosition != null) {
				return escapeCellPosition;
			}
		}

		// Otherwise, go to a random cell on the map
		return getRandomReachableCellPosition();
	}

	/**
	 * Is the path to the given cell safe? (e.g. the shortest path doesn’t pass near the player)
	 *
	 * @param cellPos the cell position to check
	 * @return true if the path is safe; false otherwise
	 */
	private boolean isPathSafe(DiscreteCoordinates cellPos) {

		// If the player is not memorized, the path is always safe.
		DiscreteCoordinates playerPos = getPlayerPosition();
		if (playerPos == null) {
			return true;
		}

		// Compute the path (we assume here that createShortestPath always yields the same results in the same conditions)
		DiscreteCoordinates currentPosition = getCurrentMainCellCoordinates();
		Queue<Orientation> path = createShortestPath(currentPosition, cellPos);

		if (path == null) { // A path that doesn’t exist is not safe and must be recomputed
			return false;
		}

		// For each step of the path, verify that the position of the ghost is not too close to Pacman
		for (Orientation orientation : path) {
			currentPosition = currentPosition.jump(orientation.toVector());
			// if pacman is close to the current position
			if (DiscreteCoordinates.distanceBetween(currentPosition, playerPos) < 2) return false;
		}

		return true;
	}

}
