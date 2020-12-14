package ch.epfl.cs107.play.game.superpacman.handler;

import ch.epfl.cs107.play.game.rpg.handler.RPGInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.actor.Bonus;
import ch.epfl.cs107.play.game.superpacman.actor.Cherry;
import ch.epfl.cs107.play.game.superpacman.actor.Diamond;
import ch.epfl.cs107.play.game.superpacman.actor.Ghost;
import ch.epfl.cs107.play.game.superpacman.actor.Key;
import ch.epfl.cs107.play.game.superpacman.actor.Lever;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;

/**interaction avec Door et Wall déja implémenté
 * Les interactions avec Gate dépendent du signal associé*/
public interface SuperPacmanInteractionVisitor  extends RPGInteractionVisitor{
	default void interactWith(Bonus bonus) {}
	default void interactWith(Diamond diamond) {}
	default void interactWith(Cherry cherry) {}
	default void interactWith(Key key) {}
	default void interactWith(Lever lever) {}
	default void interactWith(Ghost ghost) {}
	default void interactWith(SuperPacmanPlayer player) {}

	//default void interactWith(Spirit spirit){}
}
