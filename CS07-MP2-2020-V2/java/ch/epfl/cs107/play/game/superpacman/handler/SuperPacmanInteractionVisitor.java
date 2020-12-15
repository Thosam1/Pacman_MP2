package ch.epfl.cs107.play.game.superpacman.handler;

import ch.epfl.cs107.play.game.rpg.handler.RPGInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.SuperPacmanAndGUI.Gate;
import ch.epfl.cs107.play.game.superpacman.actor.*;

public interface SuperPacmanInteractionVisitor  extends RPGInteractionVisitor{
	default void interactWith(Bonus bonus) {}
	default void interactWith(Diamond diamond) {}
	//default void interactWith(AutomaticallyCollectableAreaEntity cherry) {} interactWith commun à tous les Collectable
	default void interactWith(Cherry cherry) {}
	default void interactWith(Key key) {}
	default void interactWith(Lever lever) {}
	default void interactWith(Gate gate) {}
	default void interactWith(Ghost ghost) {}
	default void interactWith(IntelligentGhost smartGhost) {}
	default void interactWith(Blinky blinkyGhost) {}
	default void interactWith(SuperPacmanPlayer player) {}

	default void interactWith(IceWind iceWind){}
	default void interactWith(PoisonSmoke poisonSmoke){}
	//default void interactWith(Spirit spirit){}
}//interaction avec Door et Wall déja implémenté
