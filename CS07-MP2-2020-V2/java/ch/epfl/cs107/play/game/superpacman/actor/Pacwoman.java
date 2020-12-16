package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RandomGenerator;
import ch.epfl.cs107.play.window.Canvas;

public class Pacwoman extends MovableAreaEntity{
	private Sprite[][] sprites; //initialisation du tableau 2D qui contiendra les sprites de l'animation du player
	private Animation[] animations;
	protected final static int ANIMATION_DURATION = 4;
	private int speed;
	private Orientation desiredOrientation;
	protected DiscreteCoordinates previousMainCell;
	
	/**This constructor is very simple to SuperPacmanPlayer's constructor since they both have the same animation
	 * The only difference is that pacwoman is pink*/
	public Pacwoman(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		sprites = RPGSprite.extractSprites("superpacman/pacwoman", 4, 1, 1, this, 64, 64,	//4 frames in each row, width 1, height 1, parent this, width of frame (nb pixels in the image), height of frame
                new Orientation[] {Orientation.DOWN, Orientation.LEFT, Orientation.UP, Orientation.RIGHT}); //order Orientation[] orders of frame in the image
        //array of 4 Sprite[] 1 per orientation
		speed = 5;
		desiredOrientation = orientation;
		animations = Animation.createAnimations(ANIMATION_DURATION / 4, sprites);
	}

	/**The next methods allow for random movements in straight lines
	 * */
	public void update(float deltaTime) {
        deplacement(getNextOrientation(), speed);
        previousMainCell = getCurrentMainCellCoordinates();
        desiredOrientation = getNextOrientation();
		animations[desiredOrientation.ordinal()].update(deltaTime);
        super.update(deltaTime);
	}
	
	  private void deplacement(Orientation next, int speed) {
	        if(!this.isDisplacementOccurs()) {
	            this.orientate(next);
	            move(speed);
	        } else {
				move(speed);
			}
	    }
	  
	  protected Orientation getNextOrientation() {
	        int randomInt = RandomGenerator.getInstance().nextInt(4);	//(index 0-3 for the ordinal of orientation)
	        Orientation nextOrientation = Orientation.fromInt(randomInt);
	        return nextOrientation;
	    }
	
	  /**Pacwoman does not take cell space and is CellInteractable*/
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		return true;
	}

	@Override
	public boolean isViewInteractable() {
		return false;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((SuperPacmanInteractionVisitor)v).interactWith(this);
	}

	/**this methods draws PacWoman's animation at each frame
	 * which creates the animation of a mouth opening and closing*/
	@Override
	public void draw(Canvas canvas) {
		animations[this.getOrientation().ordinal()].draw(canvas);
	}

}
