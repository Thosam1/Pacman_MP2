package ch.epfl.cs107.play.game.superpacman.SuperPacmanAndGUI;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.RPG;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;
import ch.epfl.cs107.play.game.superpacman.area.Level0;
import ch.epfl.cs107.play.game.superpacman.area.Level1;
import ch.epfl.cs107.play.game.superpacman.area.Level2;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class SuperPacman extends RPG{

	private SuperPacmanPlayer player;
	private final String[] areas = {"superpacman/Level0", "superpacman/Level1","superpacman/Level2"};
	private final DiscreteCoordinates[] startingPositions = {new DiscreteCoordinates(10,1), 
															 new DiscreteCoordinates(15,6),
															 new DiscreteCoordinates(15,29)};

	private int areaIndex;
	@Override
	public String getTitle() {
		return "Super Pac-Man";
	}
	
	public void update(float deltaTime) {
		super.update(deltaTime);
		//scareGhosts();
	}

	private void createAreas(){
		addArea(new Level0());
		addArea(new Level1());
		addArea(new Level2());
	}
	public void end() {}
	
	public boolean begin(Window window, FileSystem fileSystem) {
		if (super.begin(window, fileSystem)) {

			createAreas();
			areaIndex = 2;
			Area area = setCurrentArea(areas[areaIndex], true);
			player = new SuperPacmanPlayer(area, startingPositions[areaIndex]);
			initPlayer(player);
			return true;
		}
		return false;
	}
/*
	private void scareGhosts(){													//cette methode complique les choses, directement appeler cela dans une méthode dans SuperPacmanPlayer - timer / au début et à la fin de l'invincibilité
		SuperPacmanArea area = (SuperPacmanArea) getCurrentArea();
		if(player.getIMMORTAL()) {	//if immortal, set all ghosts to scared			
			area.scareInBehavior(true);			
		}else {
			area.scareInBehavior(false);	//if not, set all ghosts to not afraid
		}
	}
 */
}
