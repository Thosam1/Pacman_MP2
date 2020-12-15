package ch.epfl.cs107.play.game.superpacman.area;

import java.util.Queue;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGraph;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

abstract public class SuperPacmanArea extends Area implements Logic{
	private Window window; 
	public SuperPacmanBehavior behavior;	
	
	public final float CAMERA_SCALE_FACTOR = 20.f;
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
	
	@Override
	public float getCameraScaleFactor() {
		return CAMERA_SCALE_FACTOR;
	}
	public void createArea(SuperPacmanBehavior behavior) {	//registering actors in the area //general, more detailed in subclasses/levels
		behavior.registerActors(this);
	}
	public void scareInBehavior (boolean choose) { //call the method in behavior to frighten the ghosts
		if(choose == true) {
			behavior.scareAllGhosts(true);
		}else {
			behavior.scareAllGhosts(false);
		}
				
	}
	public AreaGraph getGraph(){
		return behavior.getGraph();
	}

	public void allGhostToRefugeBehavior(){
		behavior.allGhostToRefuge();
	}
	
	public Queue<Orientation> shortestPath(DiscreteCoordinates main, DiscreteCoordinates target){
		return behavior.shortestPath(main, target);
	}
}
