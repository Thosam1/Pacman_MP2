package ch.epfl.cs107.play.game.superpacman.area;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.function.Predicate;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGraph;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RandomGenerator;
import ch.epfl.cs107.play.signal.Signal;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

abstract public class SuperPacmanArea extends Area implements Logic{
	private Window window; 
	public SuperPacmanBehavior behavior;

	public final float CAMERA_SCALE_FACTOR = 20.f;
	
	/**creates the area which is visually represented on a window that pops up on the screen of the player
	 * creates an instance named behavior, of SuperPacmanBehavior*/
	@Override
    public boolean begin(Window window, FileSystem fileSystem) {
		this.window = window; 
		if (super.begin(window, fileSystem)) {
			// Set the behavior map
	        behavior = new SuperPacmanBehavior(window, getTitle());
	        setBehavior(behavior);
	        createArea(behavior);
	            return true;
	        }
	        return false;
	    }
	
	/**returns the scale factor, which is the zoom
	 * higher values mean that the player can see a bigger part of the Level*/
	@Override
	public float getCameraScaleFactor() {
		return CAMERA_SCALE_FACTOR;
	}
	
	/**registers the Actors created in the SuperPacmnaBehavior*/
	public void createArea(SuperPacmanBehavior behavior) {//general, more detailed in subclasses/levels
		behavior.registerActors(this);
	}
	
	/**call the method in behavior to frighten the ghosts*/
	public void scareInBehavior (boolean choose) {
		behavior.scareAllGhosts(choose);
	}
	public AreaGraph getGraph(){
		return behavior.getGraph();
	}

	/**calls method allGhostToRefuge on the attribute behavior*/
	public void allGhostToRefugeBehavior(){
		behavior.allGhostToRefuge();
	}
	
	/**calls method shortestPath on the attribute behavior*/
	public Queue<Orientation> shortestPath(DiscreteCoordinates main, DiscreteCoordinates target){
		return behavior.shortestPath(main, target);
	}
	
	/**calls method setSignalOfNode on the attribute behavior*/
	public void setSignalOfNode(DiscreteCoordinates coordinatesOfNode, Logic signalAsk){
		behavior.setSignalOfNode(coordinatesOfNode, signalAsk);
	}


	/**if the numberOfDiamonds left in the Area is equal to 0, meaning that they have all been eaten,
	 * then isOn returns true
	 * (look at Area for a detailed explanation of numberOfDiamonds)*/
	@Override
	public boolean isOn() {
		if(numberOfDiamonds>0) {
			return false;
			}
		else return true;
	}
	/**The following inherited methods are not used*/
	@Override
	public boolean isOff() {
		// we are not using this for now
		return false;
	}

	@Override
	public float getIntensity() {
		//we are not using this for now
		return 0;
	}

	/**
	 * Get a random cell’s coordinates in this area, satisfying a given condition.
	 *
	 * @param condition (Predicate<DiscreteCoordinates>) A condition that must be met by the coordinates
	 * @param maxTries (int) The maximum amount of tries that must be done before giving up
	 * @return (DiscreteCoordinates) The coordinates of a reachable cell, or null if no suitable cell was found
	 */
	public DiscreteCoordinates getRandomCellPosition(Predicate<DiscreteCoordinates> condition, int maxTries) {
		assert maxTries >= 0;

		// Limit the maximum tries count to all cells
		int cellsCount = getWidth() * getHeight();
		if (maxTries > cellsCount) {
			maxTries = cellsCount;
		}

		Set<DiscreteCoordinates> tries = new HashSet<>();
		while (tries.size() < maxTries) {
			DiscreteCoordinates randomPosition = new DiscreteCoordinates(
					RandomGenerator.getInstance().nextInt(getWidth()),
					RandomGenerator.getInstance().nextInt(getHeight())
			);

			if (tries.contains(randomPosition)) { // Ignore positions already tried
				continue;
			}

			if (condition.test(randomPosition)) {
				// We found a match!
				return randomPosition;
			}

			// Otherwise, remember the position to not try it again
			tries.add(randomPosition);
		}

		// If no position was found
		return null;
	}
}
