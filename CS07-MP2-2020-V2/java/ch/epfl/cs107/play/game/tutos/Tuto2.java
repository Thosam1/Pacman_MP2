package ch.epfl.cs107.play.game.tutos;

import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.tutos.actors.GhostPlayer;
import ch.epfl.cs107.play.game.tutos.actors.SimpleGhost;
import ch.epfl.cs107.play.game.tutos.area.tuto2.Ferme;
import ch.epfl.cs107.play.game.tutos.area.tuto2.Village;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class Tuto2 extends AreaGame{
	//private SimpleGhost player;
	private GhostPlayer player;
	@Override
	public void end() {
	}
	public void update(float deltaTime) {
		super.update(deltaTime);
		switchArea();
		/*
		Keyboard keyboard = getWindow().getKeyboard();//je créé l instance keyboard
		Button key = keyboard.get(Keyboard.UP);//le programme lit la touche que je tape
		if(key.isDown()) {//isDown signifie que j appuie sur la touche
			player.moveUp();//fait appel à la méthode moveUp de SimpleGhost
		}
		key = keyboard.get(Keyboard.DOWN) ;
		if(key.isDown()) {
			player.moveDown();
		}
		key = keyboard.get(Keyboard.LEFT) ;
		if(key.isDown()) {
			player.moveLeft();
		}
		key = keyboard.get(Keyboard.RIGHT) ;
		if(key.isDown()) {
			player.moveRight();
		}
		switchArea();*/
	}
	public String getTitle() {
		return "Tuto2";
	}
	private void createAreas() {
		addArea(new Ferme());//je rajoute les deux Areas qui sont des classes
							//que j ai créé dans un autre file
		addArea(new Village());
	}
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		if (super.begin(window , fileSystem)) {
			DiscreteCoordinates coordinates = new DiscreteCoordinates(2,10);
			createAreas();
			setCurrentArea("zelda/Ferme",true);//premier Area
			player = new GhostPlayer(getCurrentArea(), Orientation.DOWN, coordinates);
			//player = new SimpleGhost(new Vector(18,7), "ghost.1");//création du joueur
			getCurrentArea().registerActor(player);//rajoute le perso au area
			getCurrentArea().setViewCandidate(player);//place le perso au milieu de l ecran
			return true;
			}
			else return false;
		
	}
	public void switchArea() {
		if (player.isWeak()) {
			getCurrentArea().unregisterActor(player);
			if (getCurrentArea().getTitle().equals("zelda/Ferme")) {
				setCurrentArea("zelda/Village",true);//change de current Area, true établit le fait que 
														//le Area sera le meme si je reviens dessus
			
				getCurrentArea().registerActor(player);//rajoute un personnage au Area
				getCurrentArea().setViewCandidate(player);//met le perso au milieu de l'ecran
				player.strengthen();//méthode void que j'appel sur l'instance player
			}
			else {
				setCurrentArea("zelda/Ferme",true);
				getCurrentArea().registerActor(player);
				getCurrentArea().setViewCandidate(player);
				player.strengthen();
			}
		}
		
	}
}

