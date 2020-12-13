package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Collections;
import java.util.List;
import java.util.Timer;

import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.rpg.actor.Player;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.SuperPacmanAndGUI.Gate;
import ch.epfl.cs107.play.game.superpacman.SuperPacmanAndGUI.SuperPacman;
import ch.epfl.cs107.play.game.superpacman.SuperPacmanAndGUI.SuperPacmanPlayerStatusGUI;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class SuperPacmanPlayer extends Player{
	public int hp;
	public int speedTimer;
	public float score;
	protected final static int ANIMATION_DURATION = 8;
	public int speed = 5;//low value ==> high speed
	public Orientation desiredOrientation;
	private Area area;
	private SuperPacmanArea superArea;	//for interaction with ghost - method to setAllGhosts to the same place
	private SuperPacmanPlayerStatusGUI status;
	private DiscreteCoordinates PLAYER_SPAWN_POSITION; //dépend de l'aire actuelle !
	private final SuperPacmanPlayerHandler handler;
	private boolean IMMORTAL = false;	//addedByMe
	public boolean getIMMORTAL () {
		return IMMORTAL;
	}
	private float timerImmortal = 10.f; // addedByMe, décrémenter cette valeur par deltaTime et remettre IMMORTAL à false, avant de réinitialiser timerImmortal 4.f quand elle atteint 0
	//peut etre choisir la valeur en fonction du type de bonus ?
	private final float timeImmortal = 10.f;
	private boolean bonusEaten = false;
	private boolean consecutiveBonus = false;
	private boolean everyoneIsAfraid = false;


	private Sprite[][] sprites;	//addedByMe - i just declared them outside the constructor
	private Animation[] animations;
	
	public SuperPacmanPlayer(Area area, DiscreteCoordinates coordinates) {	//constructeur	-area = aire ou il appartient
		super(area, Orientation.RIGHT, coordinates);
		this.area = area;
		this.superArea = (SuperPacmanArea) area; //for the interaction with ghost - method to setAllGhosts to the same place

		PLAYER_SPAWN_POSITION = coordinates;
		hp = 5;
		desiredOrientation = Orientation.RIGHT;
		score = 0;
		speedTimer = 0;
		status = new SuperPacmanPlayerStatusGUI(this);
		sprites = RPGSprite.extractSprites("superpacman/pacman", 4, 1, 1, this, 64, 64,	//4 frames in each row, width 1, height 1, parent this, width of frame (nb pixels in the image), height of frame
                new Orientation[] {Orientation.DOWN, Orientation.LEFT, Orientation.UP, Orientation.RIGHT}); //order Orientation[] orders of frame in the image
        //array of 4 Sprite[] 1 per orientation
		
		animations = Animation.createAnimations(ANIMATION_DURATION / 4, sprites);	//créé un tableau de 4 animations
		handler = new SuperPacmanPlayerHandler();
		
		
	}
	
	public void update(float deltaTime) {
		Keyboard keyboard = getOwnerArea().getKeyboard();
			
	    orientatePlayer(Orientation.LEFT,keyboard.get(Keyboard.LEFT)); // for example, of Keyboard.LEFT is pressed, the desiredOrientation becomes LEFT
	    orientatePlayer(Orientation.UP,keyboard.get(Keyboard.UP));
	    orientatePlayer(Orientation.RIGHT,keyboard.get(Keyboard.RIGHT));
	    orientatePlayer(Orientation.DOWN,keyboard.get(Keyboard.DOWN));
	        
	       	
	    
	    
	    if (!(isDisplacementOccurs())) {	//control if the player is moving at the time
	        										// -> if the player is not moving
	    	if (desiredOrientation!=null) {		//and if there is a desiredOrientation
	        	
	    		if (getOwnerArea().canEnterAreaCells(this,Collections.singletonList(getCurrentMainCellCoordinates().jump(desiredOrientation.toVector())))) {	//and if the area (in front of desiredOrientation) allows the player to enter
	        			
	    			orientate(desiredOrientation);
        			animations[desiredOrientation.ordinal()].reset();

	        		}
	    		speedVariation();
	    		this.move(speed);
	       
	        }
	       }
	    else {
	       	animations[desiredOrientation.ordinal()].update(deltaTime);
	        		
	        	}

	    //didn't find another way to set a timer ...
		if(bonusEaten){
			if(!everyoneIsAfraid){
				SuperPacmanArea temp = (SuperPacmanArea) this.area;
				temp.scareInBehavior(true);
				everyoneIsAfraid = true;
			}
			if(consecutiveBonus){	//set back timer to initial
				timerImmortal = timeImmortal;
				consecutiveBonus = false;
			}

			this.IMMORTAL = true;
			System.out.println("immortality = " + IMMORTAL);

			System.out.println("timer = " + timerImmortal + " seconds" );
			timerImmortal -= deltaTime;
			if(timerImmortal < 0){
				this.IMMORTAL = false;
				System.out.println("immortality = " + IMMORTAL);
				bonusEaten = false;
				timerImmortal = timeImmortal;

				SuperPacmanArea temp = (SuperPacmanArea) this.area;
				temp.scareInBehavior(false);
				everyoneIsAfraid = false;
			}
		}

	    super.update(deltaTime);
	    }
	
	public void speedVariation() {//cette méthode est utilisé pour rendre le personnage plus rapide quand il mange un Cherry
		if ((speed<5)&&(speedTimer == 60)) {//24 fps ==> toutes les 2.5s speed augmente de 1 jusqu'à atteindre 5
			//augmenter speed, diminue la vitesse du player, ainsi il est plus rapide pendant 5 secondes
			speed +=1;
			speedTimer=0; //réinitialise le timer
		}
		else if ((speed<5)&&(speedTimer!=60)){
			speedTimer += 1;
		}//else quand la vitesse est égale à 5, ne rien changer
		
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
	public boolean takeCellSpace() {
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		return false;
	}

	@Override
	public boolean isViewInteractable() {
		return true;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((SuperPacmanInteractionVisitor)v).interactWith(this); //accepte de voir ses interactions avec les autres acteurs (qui sont aussi gérés par SuperPacmanInteractionVisitor
	}
	@Override
	public void draw(Canvas canvas) {
		animations[this.getOrientation().ordinal()].draw(canvas);
		status.draw(canvas);//draw les vies et le score
		
	}

	public DiscreteCoordinates getPlayerPosition(){
		DiscreteCoordinates pos = new DiscreteCoordinates((int) getPosition().x, (int) getPosition().y);
		return pos;
	}



	/*private void immortalityTimer(){
		setIMMORTAL(true);
		timerImmortal = timeImmortal;
		timerImmortal -= 1;
		if(timerImmortal <= 0){
			setIMMORTAL(false);
		}
	}
	/*
	private void immortalityMode(int timer){
		int countDownTime = timer;

		this.IMMORTAL = true;
		System.out.println("immortality = " + IMMORTAL);
		while(countDownTime > 0){
			System.out.println("timer = " + countDownTime + " seconds" );
			countDownTime -= 1;
		}
		this.IMMORTAL = false;
		System.out.println("immortality = " + IMMORTAL);
	}*/
	/*private void immortalityMode(float deltaTime){
		timerImmortal = timeImmortal;
		this.IMMORTAL = true;
		System.out.println("immortality = " + IMMORTAL);
		while(timerImmortal > 0){
			System.out.println("timer = " + timerImmortal + " seconds" );
			timerImmortal -= deltaTime;
		}
		this.IMMORTAL = false;
		System.out.println("immortality = " + IMMORTAL);
	}*/

	private class SuperPacmanPlayerHandler implements SuperPacmanInteractionVisitor{	//gets called whenever in field of vision or when there is an interaction
		

		public void interactWith(Door door){
			setIsPassingADoor(door);
		}

	public void interactWith(Key key) {
    	key.collect();
    }
    public void interactWith(Bonus bonus) {
    	bonus.collect();
    	if(!bonusEaten){
    		bonusEaten = true;
		}else{
    		consecutiveBonus = true;
		}
    }
    public void interactWith(Cherry cherry) {
    	cherry.collect();
    	score += cherry.SCORE;
    	speed = 3;
//    	System.out.println(score);   Test score
    }
    public void interactWith(Diamond diamond) {
    	diamond.collect();
    	score += diamond.SCORE;
    	area.numberOfDiamonds -=1;
//    	System.out.println(score);     Test score
    	}
    public void interactWith(Gate gate) {
    	
    }
    public void interactWith(Ghost ghost) {	//interact on contact ?!?!
		ghostEncounter(ghost);}
    
    
	private void ghostEncounter (Ghost ghost) {
		if(IMMORTAL == true) {		
			basicForget(ghost);
			score += ghost.GHOST_SCORE;
			}
		if(IMMORTAL == false) {
			//basicForget(ghost);
			superArea.allGhostToRefugeBehavior();
			hp -= 1;
			setCurrentPosition(PLAYER_SPAWN_POSITION.toVector());
			resetMotion();
			//METTRE UNE ANIMATION à ce moment là ???
			}
		}
	private void basicForget(Ghost ghost) {
		ghost.backToRefuge();
	}

}
}
