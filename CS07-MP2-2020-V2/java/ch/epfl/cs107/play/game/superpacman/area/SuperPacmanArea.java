package ch.epfl.cs107.play.game.superpacman.area;

import java.util.List;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

abstract public class SuperPacmanArea extends Area{
	private Window window; //addedByMe
	SuperPacmanBehavior behavior;	//addedByMe
	
	public final float CAMERA_SCALE_FACTOR = 15.f;
	@Override
    public boolean begin(Window window, FileSystem fileSystem) {
		this.window = window; //addedByMe
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
}
