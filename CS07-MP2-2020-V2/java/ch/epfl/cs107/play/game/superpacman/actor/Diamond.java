package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AutomaticallyCollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Positionable;

public class Diamond extends AutomaticallyCollectableAreaEntity{
	public int score = 10;
	public Diamond(Area area, Orientation orientation, DiscreteCoordinates position, Positionable parent) {
		super(area, orientation, position);
		Sprite[] sprites =
				RPGSprite.extractSprites("superpacman/diamond", 4, 1, 1,
				parent , 16, 16);
	}
	
}
