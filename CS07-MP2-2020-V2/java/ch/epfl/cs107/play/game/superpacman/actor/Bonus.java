package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.AutomaticallyCollectableAreaEntity;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Bonus extends AutomaticallyCollectableAreaEntity{
	protected final int ANIMATION_DURATION = 8;//3 fois par seconde
	private Animation animation;
	private Sprite[] sprites;
	
	/**le constructeur initialise l'animation du Bonus qui permettra de créer la sensation de rotation*/
	public Bonus(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		sprites = RPGSprite.extractSprites("superpacman/coin", 4, 1, 1, this , 16, 16);
		animation = new Animation(ANIMATION_DURATION, sprites, true);
		
	}
	/**méthode draw de Bonus qui déssine un des 4 sprites tous les 2 frames*/
	public void draw(Canvas canvas) {
		animation.draw(canvas);
	}
	/** méthode update qui permet d'avoir une animation continue
	 * on appele update sur l'animation à chaque deltaTime*/
	public void update(float deltaTime) {
		animation.update(deltaTime);
	}
	/** permet les interactions avec les interactors*/
	public void acceptInteraction(AreaInteractionVisitor v) {
        ((SuperPacmanInteractionVisitor)v).interactWith(this);
    }
}
