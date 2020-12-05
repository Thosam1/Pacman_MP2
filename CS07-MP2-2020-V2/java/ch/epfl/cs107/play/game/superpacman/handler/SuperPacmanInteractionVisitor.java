package ch.epfl.cs107.play.game.superpacman.handler;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.rpg.handler.RPGInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.actor.Bonus;
import ch.epfl.cs107.play.game.superpacman.actor.Cherry;
import ch.epfl.cs107.play.game.superpacman.actor.Diamond;
import ch.epfl.cs107.play.game.superpacman.actor.Key;
import ch.epfl.cs107.play.game.superpacman.actor.Wall;

public interface SuperPacmanInteractionVisitor  extends RPGInteractionVisitor{
	default void interactWith(Interactable other) {System.out.println("interactWith that type doesnt exist");}
	default void interactWith(Bonus bonus) {}
	default void interactWith(Diamond diamond) {}
	default void interactWith(Cherry cherry) {}
	default void interactWith(Key key) {}
	
}
