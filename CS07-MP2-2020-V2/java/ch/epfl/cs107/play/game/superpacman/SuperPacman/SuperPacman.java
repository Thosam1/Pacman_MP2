package ch.epfl.cs107.play.game.superpacman.SuperPacman;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.rpg.RPG;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;
import ch.epfl.cs107.play.game.superpacman.area.Level0;
import ch.epfl.cs107.play.game.superpacman.area.Level1;
import ch.epfl.cs107.play.game.superpacman.area.Level2;
import ch.epfl.cs107.play.game.superpacman.area.Level3;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class SuperPacman extends RPG{

	private SuperPacmanPlayer player;
	private final String[] areas = {"superpacman/Level0", "superpacman/Level1", "superpacman/Level2", "superpacman/Level3"};
	private final DiscreteCoordinates[] startingPositions = {new DiscreteCoordinates(10,1), 
															 new DiscreteCoordinates(15,6),
															 new DiscreteCoordinates(15,29),
															 new DiscreteCoordinates(15,6),};

	private int areaIndex;
	@Override
	public String getTitle() {
		return "Super Pac-Man";
	}
	
	public void update(float deltaTime) {
		super.update(deltaTime);
		//scareGhosts();
	}

	/**creates the three areas*/
	private void createAreas(){
		addArea(new Level0());
		addArea(new Level1());
		addArea(new Level2());
		//addArea(new Level3());
	}
	public void end() {}
	
	/**begin creates the Areas, creates the player, initializes it and sets the current 
      area as the one designated by (attribute) areaIndex*/
	public boolean begin(Window window, FileSystem fileSystem) {
		if (super.begin(window, fileSystem)) {
			createAreas();
			areaIndex = 0;
			Area area = setCurrentArea(areas[areaIndex], true);
			player = new SuperPacmanPlayer(area, startingPositions[areaIndex]);
			initPlayer(player);
			return true;
		}
		return false;
	}

	private void loadingScreen(){

	}
	private void titleScreen(){

	}
	private void selectMenu(){	//with play and leaderboard score

	}
	private void gameFinished(){

	}
	private void newLeaderBoard(){

	}
	//ajouter du son lors du mouvement de pacman, lors des bonus - invincibilit??, mettre en pause quand le joueur meurt,




/*
	private void scareGhosts(){													//cette methode complique les choses, directement appeler cela dans une m??thode dans SuperPacmanPlayer - timer / au d??but et ?? la fin de l'invincibilit??
		SuperPacmanArea area = (SuperPacmanArea) getCurrentArea();
		if(player.getIMMORTAL()) {	//if immortal, set all ghosts to scared			
			area.scareInBehavior(true);			
		}else {
			area.scareInBehavior(false);	//if not, set all ghosts to not afraid
		}
	}
 */
}
