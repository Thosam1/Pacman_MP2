package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Collections;
import java.util.List;
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
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class SuperPacmanPlayer extends Player{
	private int hp; //nombre de vies du joueur: commence à 5 et termine à 0
	private int speedTimer;
	private float score; //score du joueur: commence à 0
	protected final static int ANIMATION_DURATION = 8; //durée du cycle de l'animation
	private int speed = 5;//low value ==> high speed
	private Orientation desiredOrientation;
	private Area area;
	private SuperPacmanArea superArea;	//for interaction with ghost - method to setAllGhosts to the same place
	private SuperPacmanPlayerStatusGUI status;
	private DiscreteCoordinates PLAYER_SPAWN_POSITION; //dépend de l'aire actuelle !
	private final SuperPacmanPlayerHandler handler;
	private boolean IMMORTAL = false;
	public boolean getIMMORTAL () {
		return IMMORTAL;
	}
	private float timerImmortal = 10.f; // décrémenter cette valeur par deltaTime et remettre IMMORTAL à false, avant de réinitialiser timerImmortal 4.f quand elle atteint 0
	private final float timeImmortal = 10.f;
	private boolean bonusEaten = false;
	private boolean consecutiveBonus = false;
	private boolean everyoneIsAfraid = false;

	private Sprite[][] sprites; //initialisation du tableau 2D qui contiendra les sprites de l'animation du player
	private Animation[] animations;
	
	/**Le constructeur de cette classe prend l'aire dans laquelle se trouve le player et ses coordonnées de départ
	 * Pour l'appelle de la superclasse, nous mettons Orientation.RIGHT par défault, mais cela  a très peu d'importance
	 * On initialise:
	 * * area: prend la valeur du area donné en attribut
	 * * superArea: prend la valeur du area donné en attribut, mais en SuperPacmanArea
	 * * hp: le player commence avec 5 points de vie
	 * * PLAYER_SPAWN_POSITION: coordonnées du spawn du player dans l'aire
	 * * desiredOrientation: initialement vers la droite pour aucune raison particulière
	 * * score: Commence à 0
	 * * speedTimer: Commence à 0, mais changera de valeur au cours du jeux grace aux interaction avec des Cherry
	 * * sprites et animations qui permettront de créer l'animation du pacman
	 * * une instance de la classe SuperPacmanPlayerHandler est stockée dans handler
	 * */
	public SuperPacmanPlayer(Area area, DiscreteCoordinates coordinates) {
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
	/**this method is called at every frame
	 * Its first function is to orientate the SuperPacmanPlayer depending on the key that is pressed by the player
	 * Then, depending on the desiredOrientation, it will make the Player move if it can get into the Cell in front of it
	 * *Some cells may contain a Wall or a Gate, which takeCellSpace
	 * The animation is updated and reset (depends on isDisplacementOccurs)
	 * */
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
	    		this.move(getSpeed());
	       
	        }
	       }
	    else {
	       	animations[desiredOrientation.ordinal()].update(deltaTime);
	    }

		/**
		 * 		Code that make the bonus buff lasts, eating consecutive bonuses will only set the timer back to its initial value --- 	{didn't find another way to set a timer ...}	---
		 */

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
			//System.out.println("immortality = " + IMMORTAL);

			//System.out.println("timer = " + timerImmortal + " seconds" );
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

	    super.update(deltaTime);  //important to call update on the superclass
	}

	/**The method draw is called at every frame and has two functions
	 * The first one is to draw the animation of the pacman: It needs to open and close its mouth, 
	 * and change orientation, when the Orientation of the movement changes
	 * Secondly, calling draw on status, draws the lives and the score at the top of the screen*/
	@Override
	public void draw(Canvas canvas) {
		animations[this.getOrientation().ordinal()].draw(canvas);
		status.draw(canvas);//draw les vies et le score

	}

	/**
	 * All methods related to interactable
	 */

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());	//main cell
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

	/**
	 * All methods related to interactor
	 */

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

	/**
	 *	All methods that are useful for SuperPacmanPlayer
	 */

	/*public DiscreteCoordinates getPlayerPosition(){
		DiscreteCoordinates pos = new DiscreteCoordinates((int) getPosition().x, (int) getPosition().y);
		return pos;
	}*/

	/**
	 * speed up the player when a cherry is eaten
	 * Can be adapted for other interactions (we have not chosen to implement too many interactions that increase speed)
	 * This method allows us to keep speed between 3 and 5
	 * it is impossible to get under 3, even if you eat multiple Cherry
	 */
	public void speedVariation() {
		if ((getSpeed()<5)&&(speedTimer == 60)) {
			//24 fps ==> toutes les 2.5s speed augmente de 1 jusqu'à atteindre 5
			//augmenter speed, diminue la vitesse du player, et manger un Cherry passe sa vitesse à 3 
			//ainsi il est plus rapide pendant 5 secondes
			increaseValueSpeed(1);
			speedTimer=0; 
			//réinitialise le timer
		}
		else if ((getSpeed()<5)&&(speedTimer!=60)){
			speedTimer += 1;
			//si speed est plus petit que 5, mais 60 frames ne sont pas passé, alors il faut rajouter 1 au timer
		
		}//else quand la vitesse est égale à 5, ne rien changer

	}

	/**
	 *	look if a button is pressed and change the desiredOrientation accordingly
	 */
	public void orientatePlayer(Orientation keyOrientation, Button b) {
		if (b.isDown()) {
			desiredOrientation = keyOrientation;
		}
	}
	/**Getters, Setters, Deacreasers and Increasers*/
	public void decreaseHp(int hp) {this.hp -= hp;}
	public int getHp() {return this.hp;}
	public void increaseScore(float score) {this.score += score;}
	public float getScore() {return this.score;}
	public void increaseValueSpeed(int speed) {this.speed += speed;}//increasing the value, reduces the actual speed of the player
	public void setSpeed(int speed) {this.speed = speed;}
	public int getSpeed() {return this.speed;}

	private class SuperPacmanPlayerHandler implements SuperPacmanInteractionVisitor{	//gets called whenever in field of vision or when there is an interaction
		/**All interactWith methods
		 * The method collect is defined in AutomaticallyCollectableAreaEntity and unregisters the collectables from the area
		 * 	Some of the Collectables Override it
		 * */

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
		/**A Lever changesSignal when in contact with a PacMan*/
		public void interactWith(Lever lever) {
			lever.changeSignal();
		}
		
		/**Cherry and Diamond add a SCORE to the score of the player
		 * the interaction with a Cherry sets the speed of the player to 3 (increase in speed)
		 * the interaction with a diamond subtracts 1 to numberOfDiamonds (important for the signal of the Area)
		 * We could redefine collect in Cherry and Diamond; however, it is easier for SuperPacmanPlayer to increase 
		 * his own score and speed thanks to his handler*/
		public void interactWith(Cherry cherry) {
			cherry.collect();
			increaseScore(cherry.SCORE);
			setSpeed(3);
	//    	System.out.println(score);   Test score
		}
		public void interactWith(Diamond diamond) {
			diamond.collect();
			increaseScore(diamond.SCORE);
			area.numberOfDiamonds -=1;
	//    	System.out.println(score);     Test score
			}
		
		public void interactWith(Ghost ghost){
		ghostEncounter(ghost);
		}
		public void interactWith(IntelligentGhost smartGhost) {
			if(IMMORTAL == true){
				smartGhost.backToRefuge();
			}

		}

		private void ghostEncounter (Ghost ghost) {
			if(IMMORTAL == true) {
				basicForget(ghost);
				increaseScore(ghost.GHOST_SCORE);
			}
			if(IMMORTAL == false) {
				//basicForget(ghost);
				superArea.allGhostToRefugeBehavior();	//works fine
				decreaseHp(1);
				setCurrentPosition(PLAYER_SPAWN_POSITION.toVector());
				resetMotion();
				//METTRE UNE ANIMATION à ce moment là ???
				}
			}
		private void basicForget(Ghost ghost) {
			ghost.backToRefuge();	//just setting them back to their refuge	!!! doesnt work individually
		}

	}
}
