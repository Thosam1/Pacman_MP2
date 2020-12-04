package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.rpg.actor.Player;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.SuperPacmanAndGUI.SuperPacmanPlayerStatusGUI;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;


public class SuperPacmanPlayer extends Player{
	public int hp;
	public float score;
	private Sprite sprite;
	protected final static int ANIMATION_DURATION = 8;
	private int SPEED = 6;
	public Orientation desiredOrientation;
	private Area area;
	private DiscreteCoordinates coordinates;
	private final SuperPacmanPlayerHandler handler;
	protected boolean IMMORTAL = false;	//addedByMe
	
	private Sprite[][] sprites;	//addedByMe - i just declared them outside the constructor
	private Animation[] animations;
	
	public SuperPacmanPlayer(Area area, DiscreteCoordinates coordinates) {	//constructeur	-area = aire o� il appartient
		super(area, Orientation.RIGHT, coordinates);
		this.area = area;
		hp = 3;
		score = 0;
		sprites = RPGSprite.extractSprites("superpacman/pacman", 4, 1, 1, this, 64, 64,	//4 frames in each row, width 1, height 1, parent this, width of frame (nb pixels in the image), height of frame
                new Orientation[] {Orientation.DOWN, Orientation.LEFT, Orientation.UP, Orientation.RIGHT}); //order Orientation[] orders of frame in the image
        //array of 4 Sprite[] 1 per orientation
		
		animations = Animation.createAnimations(ANIMATION_DURATION / 4, sprites);	//cr�e un tableau de 4 animations
		handler = new SuperPacmanPlayerHandler();
		
	}
	
	public void update(float deltaTime) {
		super.update(deltaTime);
		Keyboard keyboard = getOwnerArea().getKeyboard();
			
	    orientatePlayer(Orientation.LEFT,keyboard.get(Keyboard.LEFT)); // for example, of Keyboard.LEFT is pressed, the desiredOrientation becomes LEFT
	    orientatePlayer(Orientation.UP,keyboard.get(Keyboard.UP));
	    orientatePlayer(Orientation.RIGHT,keyboard.get(Keyboard.RIGHT));
	    orientatePlayer(Orientation.DOWN,keyboard.get(Keyboard.DOWN));
	        
	       	
	    animations[this.getOrientation().ordinal()].update(deltaTime);
	        
	    if (!(isDisplacementOccurs())) {	//control if the player is moving at the time
	        										// -> if the player is not moving
	    	if (desiredOrientation!=null) {		//and if there is a desiredOrientation
	        	
	    		if (area.canEnterAreaCells(this,Collections.singletonList(getCurrentMainCellCoordinates().jump(desiredOrientation.toVector())))) {	//and if the area (in front of desiredOrientation) allows the player to enter
	        			
	    			orientate(desiredOrientation);
//	        			animations[desiredOrientation.ordinal()];
	    			this.move(SPEED); //selon le nombre de frames choisi 
	        		}
	    		
	       
	        }
	       }
	    else {
	       	animations[desiredOrientation.ordinal()].reset();
	        		
	        	}
	        	
	        
	    super.update(deltaTime);
	    }
	
	public void orientatePlayer(Orientation keyOrientation, Button b) {	// look if a button is pressed and change the desiredOrientation accordingly
		if (b.isDown()) {
			desiredOrientation = keyOrientation;
		}
	}
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());	//main cell
	}

	@Override
	public List<DiscreteCoordinates> getFieldOfViewCells() {	//no vision field
		return null;
	}

	@Override
	public boolean wantsCellInteraction() {
		return true;
	}

	@Override
	public boolean wantsViewInteraction() {
		return false;
	}

	@Override
	public void interactWith(Interactable other) {
		other.acceptInteraction(handler);
	}
    
	@Override
	public boolean takeCellSpace() {	//non traversable if true ?
		return true;
	}

	@Override
	public boolean isCellInteractable() {
		return true;
	}

	@Override
	public boolean isViewInteractable() {
		return true;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((SuperPacmanInteractionVisitor)v).interactWith(this); //accepte de voir ses interactions avec les autres acteurs (qui sont aussi g�r�s par SuperPacmanInteractionVisitor)
		
	}
	 public void enterArea(Area area, DiscreteCoordinates position){
	        area.registerActor(this);
	        area.setViewCandidate(this);
	        setOwnerArea(area);
	        setCurrentPosition(position.toVector());
	        resetMotion();
	    }
	 
	  public void leaveArea(){
	        getOwnerArea().unregisterActor(this);
	    }
	  
	@Override
	public void draw(Canvas canvas) {
		animations[this.getOrientation().ordinal()].draw(canvas);
		
		
//		SuperPacmanPlayerStatusGUI.draw(canvas);
	}
		

private class SuperPacmanPlayerHandler implements SuperPacmanInteractionVisitor{
	public void interactWith(Door door){
		setIsPassingADoor(door);
		}
	public void interactWith(Wall wall) {
		
	}
	public void interactWith(Key key) {
    	key.collect();
    }
    public void interactWith(Bonus bonus) {
    	bonus.collect();
    }
    public void interactWith(Cherry cherry) {
    	cherry.collect();
    	score += cherry.score;
    }
    public void interactWith(Diamond diamond) {
    	diamond.collect();
    	score += diamond.score;
    	}
}

}