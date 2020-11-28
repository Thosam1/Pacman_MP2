package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

abstract public class SuperPacmanArea extends Area{
	public final float CAMERA_SCALE_FACTOR = 15.f;
	@Override
    public boolean begin(Window window, FileSystem fileSystem) {
		if (super.begin(window, fileSystem)) {
			// Set the behavior map
	        SuperPacmanBehavior behavior = new SuperPacmanBehavior(window, getTitle());
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
	public void createArea(SuperPacmanBehavior behavior) {
		behavior.registerActors(this);
		registerActor(new Background(this));
		registerActor(new Foreground(this));
}
}
