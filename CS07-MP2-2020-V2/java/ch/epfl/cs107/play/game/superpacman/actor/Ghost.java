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
	protected DiscreteCoordinates refuge; 	//BACK TO PROTECTED
	private Sprite[] afraidSprites;	//only 2 images for afraid - didnt find a way to keep []
	private Animation afraidAnimations;
	
	private boolean AFRAID = false;
	
	protected final int GHOST_SCORE = 500;

	
	/// Animation duration in frame number
    private final static int ANIMATION_DURATION = 8;
    protected int SPEED = 6;
    private Area area;
	private final SuperPacmanGhostHandler handler;
 
    
    //for specific
    private Sprite mainSprite;
	private Sprite[][] mainSprites;	
	private Animation[] mainAnimations;
	
	private String nameOfMainSprite = "superpacman/ghost.blinky";
	
//	protected Orientation nextOrientation;
//	protected Orientation actualOrientation;
	
    
    /**
	 * Ghost superclass, abstract level
	 * 
	 */

	public Ghost(Area area, DiscreteCoordinates coordinates) {	//constructeur	-area = aire où il appartient
		super(area, Orientation.UP, coordinates);
		this.area = area;

		refuge = coordinates;
		
		//sprite will be afraidSprites default, when not afraid the animation will be above
		afraidSprites = RPGSprite.extractSprites("superpacman/ghost.afraid", 2, 1, 1, this, 16, 16);	//2 frames in each row, width 1, height 1, parent this, width of frame (nb pixels in the image), height of frame
		afraidAnimations = new Animation(ANIMATION_DURATION / 4, afraidSprites);

		//attributeMainSprite(nameOfMainSprite);	//to avoid null pointer exception in draw and update

		handler = new SuperPacmanGhostHandler();
	}

	
	public void update(float deltaTime) { // check constantly if the player is immortal or not, so the animation can change
//		checkAfraid();
		if(AFRAID == true) { //if true, no animation and default sprite (afraid)
			afraidAnimations.update(deltaTime);
		}
		if(AFRAID == false) {
			mainAnimations[this.getOrientation().ordinal()].update(deltaTime);
		}
	/*
		System.out.println(getFieldOfViewCells());
		System.out.println();
		System.out.println(" ------------------------------------------------------------------------------------------------------------------------ ");
		System.out.println();
		*/
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
				new Orientation[] {Orientation.DOWN, Orientation.LEFT, Orientation.UP, Orientation.RIGHT}); //order Orientation[] orders of frame in the image
		//array of 4 Sprite[] 1 per orientation
		mainAnimations = Animation.createAnimations(ANIMATION_DURATION / 4, mainSprites);	//crée un tableau de 4 animations
	}
		
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
		
		public void backToRefuge() {
			//playerMemory = null;
			//seePlayer = false;
			resetMotion();
			area.leaveAreaCells(this, getEnteredCells());
			setCurrentPosition(this.refuge.toVector());
			area.enterAreaCells(this, getCurrentCells());
			//setReevaluate(true); //so the pinky doesn't follow the player
		}
		
		protected void deplacement(int afraidSpeed, int normalSpeed) {
			if(AFRAID == true){
				this.move(afraidSpeed);
			}else {
				this.move(normalSpeed);
			}
		}

		protected class SuperPacmanGhostHandler implements SuperPacmanInteractionVisitor{
			/*public void interactWith(SuperPacmanPlayer player) {
				System.out.println("There IS AN INTERACTION");
			}*/
		}
		
		
}
	



