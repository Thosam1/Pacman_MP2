package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level2 extends SuperPacmanArea{
	public final DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(15,29);
	@Override
	public String getTitle() {
		return "superpacman/Level2";
	}
	public void createArea() {
        super.createArea();
        }
}
