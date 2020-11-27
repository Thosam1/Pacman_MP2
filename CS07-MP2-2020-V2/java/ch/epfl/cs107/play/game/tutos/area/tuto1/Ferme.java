package ch.epfl.cs107.play.game.tutos.area.tuto1;


import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.tutos.area.SimpleArea;
import ch.epfl.cs107.play.math.Vector;


public class Ferme extends SimpleArea{
	
	protected void createArea() {
		registerActor(new Background(this));
		registerActor(new Foreground(this));
	}
	
	public String getTitle() {
		return "zelda/Ferme";
	}
}
