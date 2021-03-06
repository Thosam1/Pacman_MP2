package ch.epfl.cs107.play.game.superpacman.actor;

import java.awt.Color;
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
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.TextAlign;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;


public class SuperPacmanPlayer extends Player{
	private int hp; //nombre de vies du joueur: commence à 5 et termine à 0
	private int speedTimer;
	private float score; //score du joueur: commence à 0
	protected final static int ANIMATION_DURATION = 8; //durée du cycle de l'animation
	private final int BASE_SPEED = 5;
	private int speed = 5;//low value ==> high speed
	private Orientation desiredOrientation;
	private Area area;
	private SuperPacmanPlayerStatusGUI status;
	private DiscreteCoordinates PLAYER_SPAWN_POSITION; //dépend de l'aire actuelle !
	private final SuperPacmanPlayerHandler handler;
	private boolean IMMORTAL = false;
	private float timerImmortal = 10.f; // décrémenter cette valeur par deltaTime et remettre IMMORTAL à false, avant de réinitialiser timerImmortal 4.f quand elle atteint 0
	private final float timeImmortal = 10.f;
	private boolean bonusEaten = false;
	private boolean consecutiveBonus = false;
	private boolean everyoneIsAfraid = false;
	private boolean onSpeedDebuff = false;
	private boolean consecutiveSpeedDebuff = false;
	private float timerSpeedDebuff = .5f;
	private final float timeSpeedDebuff = .5f;
	private float scoreFinal;
	private int hpFinal;
	private boolean endOfGame = false;


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

		PLAYER_SPAWN_POSITION = coordinates;
		hp = 5; //may want to have a parameter for this
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

		//System.out.println(speed);
	    
	    
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
				((SuperPacmanArea)getOwnerArea()).scareInBehavior(true);
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
				//System.out.println("immortality = " + IMMORTAL);
				bonusEaten = false;
				timerImmortal = timeImmortal;

				((SuperPacmanArea)getOwnerArea()).scareInBehavior(false);
				everyoneIsAfraid = false;
			}
		}

		if(onSpeedDebuff){
			//System.out.println("SPEED DEBUFF ACTIVATED");
			if(consecutiveSpeedDebuff){
				timerSpeedDebuff = timeSpeedDebuff;
				consecutiveSpeedDebuff = false;
			}
			timerSpeedDebuff -= deltaTime;
			if(timerSpeedDebuff <= 0){
				this.onSpeedDebuff = false;
				timerSpeedDebuff = timeSpeedDebuff;
				this.setSpeed(getBASE_SPEED());
				//System.out.println("BACK TO BASE SPEED : " + getSpeed());
			}

		}

	    super.update(deltaTime);  //important to call update on the superclass
	}

	/**takes the final score and hp and changes the value of endOfGame
	 * becomes invincible*/
	public void stop() {
		scoreFinal = getScore();
		hpFinal = getHp();
		endOfGame = true;
		setImmortal();
	}
	
	/**The method draw is called at every frame and has two functions
	 * The first one is to draw the animation of the pacman: It needs to open and close its mouth, 
	 * and change orientation, when the Orientation of the movement changes
	 * Secondly, calling draw on status, draws the lives and the score at the top of the screen*/
	@Override
	public void draw(Canvas canvas) {
		if (endOfGame) {
			//add(new Vector(-1f, 8f))
			//anchor = canvas.getTransform().getOrigin();
			new Sprite("scoreboard", 20.f, 20.f,this, null, new Vector(-10f,-10f), 1.0f, 9000f).draw(canvas);
			new TextGraphics("Pacman and Pacwoman", 0.7f, Color.BLACK, Color.YELLOW, 0.025f, true, true,
					canvas.getTransform().getOrigin().add(new Vector(-4f,-6f)),TextAlign.Horizontal.LEFT, TextAlign.Vertical.BOTTOM, 1.0f, 10000f).draw(canvas); 
			new TextGraphics("can live together!", 0.7f, Color.BLACK, Color.YELLOW, 0.025f, true, true,
					canvas.getTransform().getOrigin().add(new Vector(-3f,-7f)),TextAlign.Horizontal.LEFT, TextAlign.Vertical.BOTTOM, 1.0f, 10000f).draw(canvas); 
			new TextGraphics("Victory", 2.5f, Color.BLUE, Color.YELLOW, 0.025f, true, true,
					canvas.getTransform().getOrigin().add(new Vector(-4f,5f)),TextAlign.Horizontal.LEFT, TextAlign.Vertical.BOTTOM, 1.0f, 10000f).draw(canvas); 
			new TextGraphics("Final Score: ", 2.5f, Color.BLACK, Color.YELLOW, 0.025f, true, true,
					canvas.getTransform().getOrigin().add(new Vector(-7f,2f)),TextAlign.Horizontal.LEFT, TextAlign.Vertical.BOTTOM, 1.0f, 10000f).draw(canvas); 
			new TextGraphics(String.valueOf(scoreFinal), 2.5f, Color.BLACK, Color.YELLOW, 0.025f, true, true,
					canvas.getTransform().getOrigin().add(new Vector(-5f,-1f)),TextAlign.Horizontal.LEFT, TextAlign.Vertical.BOTTOM, 1.0f, 10000f).draw(canvas); 
			new TextGraphics("Final Hp: " + String.valueOf(hpFinal), 2.5f, Color.BLACK, Color.YELLOW, 0.025f, true, true,
					canvas.getTransform().getOrigin().add(new Vector(-7f,-4.5f)),TextAlign.Horizontal.LEFT, TextAlign.Vertical.BOTTOM, 1.0f, 10000f).draw(canvas); 
		}
		else if (getHp()<=0) {
			new Sprite("scoreboard", 20.f, 20.f,this, null, new Vector(-10f,-10f), 1.0f, 9000f).draw(canvas);
			new TextGraphics("DEFEAT", 3.f, Color.BLUE, Color.YELLOW, 0.025f, true, true,
					canvas.getTransform().getOrigin().add(new Vector(-5f,0f)),TextAlign.Horizontal.LEFT, TextAlign.Vertical.BOTTOM, 1.0f, 10000f).draw(canvas); 
		}
		else {
			animations[this.getOrientation().ordinal()].draw(canvas);
			status.draw(canvas);//draw les vies et le score
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
	public boolean takeCellSpace() {
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		return true;	//interactions avec Magic
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
	public void decreaseHp(int hp) {
		if(hp>0) {
			this.hp -= hp;
			}
		else this.hp=0;
		}
	public int getHp() {return this.hp;}
	public void increaseScore(float score) {
		if(!endOfGame) {
		 this.score += score;}}
	public float getScore() {return this.score;}
	public void setScore(float score){this.score = score;}
	public void increaseValueSpeed(int speed) {this.speed += speed;}//increasing the value, reduces the actual speed of the player
	public void setSpeed(int speed) {this.speed = speed;}
	public int getSpeed() {return this.speed;}
	public int getBASE_SPEED(){return this.BASE_SPEED;}
	public void setPLAYER_SPAWN_POSITION(DiscreteCoordinates PLAYER_SPAWN_POSITION){ this.PLAYER_SPAWN_POSITION = PLAYER_SPAWN_POSITION;}	//so the area can change the spawn position based on the level
	public boolean getIMMORTAL () {
		return IMMORTAL;
	}
	public void setImmortal () {
		IMMORTAL = true;
	}
	//events that pacman can ask the area
	public void pacmanHasDied(){
		((SuperPacmanArea)getOwnerArea()).allGhostToRefugeBehavior();	//works fine - for the interaction with ghost - method to setAllGhosts to the same place
		decreaseHp(1);
		setSpeed(getBASE_SPEED());
		area.leaveAreaCells(this, getEnteredCells());
		setCurrentPosition(PLAYER_SPAWN_POSITION.toVector());
		area.enterAreaCells(this, getCurrentCells());
		resetMotion();
	}

	private class SuperPacmanPlayerHandler implements SuperPacmanInteractionVisitor{	//gets called whenever in field of vision or when there is an interaction
		/**All interactWith methods
		 * The method collect is defined in AutomaticallyCollectableAreaEntity and unregisters the collectables from the area
		 * 	Some of the Collectables Override it
		 * */

		public void interactWith(Door door){
			setIsPassingADoor(door);
			setPLAYER_SPAWN_POSITION(door.getOtherSideCoordinates());
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

		private void ghostEncounter (Ghost ghost) {
			if(getIMMORTAL()) {
				ghost.backToRefuge();	//just setting them back to their refuge	!!! doesnt work individually
				increaseScore(ghost.getScore());
			}
			if(!getIMMORTAL()) {
				pacmanHasDied();
				}
		}

		public void interactWith(IntelligentGhost smartGhost){
			if(getIMMORTAL()){
				smartGhost.backToRefuge();	//in addition, the memory of player is erased, and a signal to reevaluate the path is sent
			}
		}

		/**
		 *	Mudrock will activate a timer for the debuffed, otherwise the player would be slow until it dies !
		 */
		public void interactWith(MudRock mudRock){
			if(!onSpeedDebuff){
				onSpeedDebuff = true;		//it will activate the timer
			}else{
				consecutiveSpeedDebuff = true;
			}
		}
		/**stops game*/
		public void interactWith(Pacwoman pacwoman) {
			stop();
		}

	}
}
