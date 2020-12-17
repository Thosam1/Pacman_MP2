package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.*;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;


public class Ghost extends MovableAreaEntity {

	//General 
	private DiscreteCoordinates refuge; 	//BACK TO PROTECTED

	//sprite will be afraidSprites default, when not afraid the animation will be above
	private Sprite[] afraidSprites = RPGSprite.extractSprites("superpacman/ghost.afraid", 2, 1, 1, this, 16, 16);	//2 frames in each row, width 1, height 1, parent this, width of frame (nb pixels in the image), height of frame

	private Animation afraidAnimations = new Animation(ANIMATION_DURATION / 4, afraidSprites);
	
	private boolean AFRAID = false;
	private final int GHOST_SCORE = 500;

	
	/// Animation duration in frame number
    private final static int ANIMATION_DURATION = 8;
    protected int SPEED = 6;
 
    
    //for specific
    private Sprite mainSprite;
	private Sprite[][] mainSprites;	
	private Animation[] mainAnimations;
	
	private String nameOfMainSprite = "superpacman/ghost.blinky";

    /**
	 * Ghost superclass, abstract level
	 * 
	 */

	public Ghost(Area area, DiscreteCoordinates coordinates) {	//constructeur	-area = aire où il appartient
		super(area, Orientation.UP, coordinates);
		refuge = coordinates;
	}

	
	public void update(float deltaTime) { // check constantly if the player is immortal or not, so the animation can change
		if(AFRAID == true) { //if true, no animation and default sprite (afraid)
			afraidAnimations.update(deltaTime);
		}else{
			mainAnimations[this.getOrientation().ordinal()].update(deltaTime);
		}
		super.update(deltaTime);
	}
	
	@Override
	public void draw(Canvas canvas) {
		if(AFRAID == true) {
			afraidAnimations.draw(canvas);
		}
		if(AFRAID == false) {
			mainAnimations[this.getOrientation().ordinal()].draw(canvas);
		}
	}

	/**
	 * All methods related to interactable
	 */

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());	//main cell
	}

	@Override
	public boolean takeCellSpace() {	//non traversable 
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		return true;
	}

	@Override
	public boolean isViewInteractable() {	//TRUE IF WE PUT LASER EXTENSION
		return false;
	}	//true if we add for bonus/laser

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((SuperPacmanInteractionVisitor)v).interactWith(this); //accepte de voir ses interactions avec les autres acteurs (qui sont aussi gérés par SuperPacmanInteractionVisitor)
	}
	
	
	/**
	 * All methods that are useful for ghost
	 */

	protected void attributeMainSprite(String nameSprite){
		nameOfMainSprite = nameSprite;
		mainSprites = RPGSprite.extractSprites(nameOfMainSprite, 2, 1, 1, this, 16, 16,	//4 frames in each row, width 1, height 1, parent this, width of frame (nb pixels in the image), height of frame
				new Orientation[] {Orientation.UP, Orientation.RIGHT, Orientation.DOWN, Orientation.LEFT}); //order Orientation[] orders of frame in the image
		//array of 4 Sprite[] 1 per orientation
		mainAnimations = Animation.createAnimations(ANIMATION_DURATION / 4, mainSprites);	//crée un tableau de 4 animations
	}

	/**
	 *	getters and setters common to all ghosts - useful -
	 */
		public void setAfraid(boolean afraid) {	//for Friday
			if(afraid){
				AFRAID = true;
			}else{
				AFRAID = false;
			}
		}
		protected boolean getAfraid(){
		return AFRAID;
		}
		protected DiscreteCoordinates getRefuge(){return refuge;}
		protected float getScore(){return GHOST_SCORE;}

		public void backToRefuge() {	//will be called by the area, or by the interaction with the player when immortal
			resetMotion();
			getOwnerArea().leaveAreaCells(this, getEnteredCells());
			setCurrentPosition(this.refuge.toVector());
			getOwnerArea().enterAreaCells(this, getCurrentCells());
		}
		
		protected void deplacement(int afraidSpeed, int normalSpeed) {	//used by subclasses to move
			if(AFRAID){
				this.move(afraidSpeed);
			}else{
				this.move(normalSpeed);
			}
		}
		protected void deplacement(Orientation next, int speed, int afraidSpeed) {
			if(!isDisplacementOccurs()){
				orientate(next);    //orientate the ghostorientate(next);    //orientate the ghost
				deplacement(afraidSpeed, speed); //moving the ghost in the orientation needed
			}
		}
		
		
}
	



