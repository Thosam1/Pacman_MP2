package ch.epfl.cs107.play.game.superpacman.handler;

import ch.epfl.cs107.play.game.rpg.handler.RPGInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.SuperPacmanAndGUI.Gate;
import ch.epfl.cs107.play.game.superpacman.actor.Bonus;
import ch.epfl.cs107.play.game.superpacman.actor.Cherry;
import ch.epfl.cs107.play.game.superpacman.actor.Diamond;
import ch.epfl.cs107.play.game.superpacman.actor.Ghost;
import ch.epfl.cs107.play.game.superpacman.actor.Key;
import ch.epfl.cs107.play.game.superpacman.actor.Lever;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;

public interface SuperPacmanInteractionVisitor  extends RPGInteractionVisitor{
	default void interactWith(Bonus bonus) {}
	default void interactWith(Diamond diamond) {}
	//default void interactWith(AutomaticallyCollectableAreaEntity cherry) {} interactWith commun à tous les Collectable
	default void interactWith(Cherry cherry) {}
	default void interactWith(Key key) {}
	default void interactWith(Lever lever) {}
	default void interactWith(Gate gate) {}
	default void interactWith(Ghost ghost) {}
	default void interactWith(SuperPacmanPlayer player) {}
}//interaction avec Door et Wall déja implémenté
