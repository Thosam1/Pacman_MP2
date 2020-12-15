package ch.epfl.cs107.play.game.superpacman.handler;

import ch.epfl.cs107.play.game.rpg.handler.RPGInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.actor.*;

/**interaction avec Door et Wall déja implémenté
 * Les interactions avec Gate dépendent du signal associé*/
public interface SuperPacmanInteractionVisitor  extends RPGInteractionVisitor{
	default void interactWith(Bonus bonus) {}
	default void interactWith(Diamond diamond) {}
	default void interactWith(Cherry cherry) {}
	default void interactWith(Key key) {}
	default void interactWith(Lever lever) {}
	default void interactWith(Ghost ghost) {}
	default void interactWith(IntelligentGhost smartGhost) {}
	default void interactWith(Blinky blinkyGhost) {}
	default void interactWith(SuperPacmanPlayer player) {}
	default void interactWith(Spirit spirit){}
	default void interactWith(Magic magic){}
	default void interactWith(MudRock MudRock){}
	//default void interactWith(IceWind iceWind){}
	//default void interactWith(PoisonSmoke poisonSmoke){}
	//default void interactWith(Spirit spirit){}
}
