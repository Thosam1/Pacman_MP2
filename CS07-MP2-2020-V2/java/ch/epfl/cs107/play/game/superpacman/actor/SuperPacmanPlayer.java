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
	private int hp;
	private int score;
	private Sprite sprite;
	protected final static int ANIMATION_DURATION = 8;
	private int SPEED = 6;
	public Orientation desiredOrientation;
	private Area area;
	private DiscreteCoordinates PLAYER_SPAWN_POSITION; //d�pend de l'aire actuelle !
	private final SuperPacmanPlayerHandler handler;
	protected boolean IMMORTAL = false;	//addedByMe
	protected float timerImmortal = 4.f; // addedByMe, d�cr�menter cette valeur par deltaTime et remettre IMMORTAL � false, avant de r�initialiser timerImmortal � 4.f quand elle atteint 0
	//peut �tre choisir la valeur en fonction du type de bonus ?
	
	private Sprite[][] sprites;	//addedByMe - i just declared them outside the constructor
	private Animation[] animations;
	
	public SuperPacmanPlayer(Area area, DiscreteCoordinates coordinates) {	//constructeur	-area = aire o� il appartient
		super(area, Orientation.RIGHT, coordinates);
		this.area = area;
		PLAYER_SPAWN_POSITION = coordinates;
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
	public void interactWith(Key key) {
    	key.collect();
    }
    public void interactWith(Bonus bonus) {
    	bonus.collect();
    }
    public void interactWith(Cherry cherry) {
    	cherry.collect();
    	this.score += cherry.score;
    }
    public void interactWith(Diamond diamond) {
    	diamond.collect();
    	this.score += diamond.score;
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
		
		//sprite.draw(canvas);
		
//		SuperPacmanPlayerStatusGUI.draw(canvas);
	}
		
	/*public Sprite spriteFrames(int i, Orientation desiredOrientation) {

		/*if (desiredOrientation == Orientation.DOWN) {
			return new RPGSprite("superpacman/pacman", 1, 2, this , new RegionOfInterest(0, i*16, 16, 32));
			}
		if (desiredOrientation == Orientation.UP) {
			return new RPGSprite("superpacman/pacman", 1, 2, this , new RegionOfInterest(32, i*16, 16, 32));
			}	
		if (desiredOrientation == Orientation.LEFT) {
			return new RPGSprite("superpacman/pacman", 1, 2, this , new RegionOfInterest(16, i*16, 16, 32));
			}	
		if (desiredOrientation == Orientation.RIGHT) {
			return new RPGSprite("superpacman/pacman", 1, 2, this , new RegionOfInterest(48, i*16, 16, 32));
			}*/
	
//}
		

	private class SuperPacmanPlayerHandler implements SuperPacmanInteractionVisitor{
		public void interactWith(Door door){
			setIsPassingADoor(door);
		}
		public void interactWith(Ghost ghost) {
			ghostEncounter(ghost);
		}
	}


	private void ghostEncounter (Ghost ghost) {
		if(this.IMMORTAL == true) {
			ghost.killed = true;
			this.score += ghost.GHOST_SCORE;
			ghost.backToRefuge(ghost.refuge);
		}
		if(this.IMMORTAL == false) {
			ghost.backToRefuge(ghost.refuge);
			this.hp -= 1;
			this.setCurrentPosition(PLAYER_SPAWN_POSITION.toVector());
			//METTRE UNE ANIMATION � ce moment l� ???
		}
		
	}
}