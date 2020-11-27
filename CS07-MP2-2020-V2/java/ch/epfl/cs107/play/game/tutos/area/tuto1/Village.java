package ch.epfl.cs107.play.game.tutos.area.tuto1;

import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.tutos.actors.SimpleGhost;
import ch.epfl.cs107.play.game.tutos.area.SimpleArea;
import ch.epfl.cs107.play.math.Vector;

public class Village extends SimpleArea {

	protected void createArea() {
		SimpleGhost actor = new SimpleGhost(new Vector(18, 7),"ghost.2");
		registerActor(actor);
		registerActor(new Background(this));
		registerActor(new Foreground(this));
	}
	
	public String getTitle() {
		return "zelda/Village";
	}
}