package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Collections;
import java.util.LinkedList;
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
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class Ghost extends Player {

	//General 
	protected DiscreteCoordinates refuge;
	private Sprite[] afraidSprites;	//only 2 images for afraid
	private Animation[] afraidAnimations;
	
	protected boolean AFRAID = false;
	
	protected final int GHOST_SCORE = 500;
    private final int FIELD_OF_VIEW = 5;
    protected boolean reevaluate = true;	//for pinky and inky
    public void setReevaluate(boolean c) {
    	if(c) {
    		reevaluate = true;
    	}else {
    		reevaluate = false;
    	}
    }
    
	
	/// Animation duration in frame number
    protected final static int ANIMATION_DURATION = 8;
    protected int SPEED = 6;
    private Area area;
	private final SuperPacmanGhostHandler handler;
	
 
    
    //for specific
    private Sprite mainSprite;
	private Sprite[][] mainSprites;	
	private Animation[] mainAnimations;
	
	private String nameOfMainSprite = "superpacman/ghost.blinky";
	/*protected void setNameOfMainSprite(String newName) {
		nameOfMainSprite = newName;
	}*/
	
//	protected Orientation nextOrientation;
//	protected Orientation actualOrientation;
	
	protected SuperPacmanPlayer playerMemory;
	protected boolean seePlayer = false;
	
    
    /**
	 * Ghost superclass, abstract level
	 * 
	 */

	public Ghost(Area area, DiscreteCoordinates coordinates) {	//constructeur	-area = aire o� il appartient
		super(area, Orientation.UP, coordinates);
		this.area = area;

		refuge = coordinates;
		
		//sprite will be afraidSprites default, when not afraid the animation will be above
		afraidSprites = RPGSprite.extractSprites("superpacman/ghost.afraid", 2, 1, 1, this, 16, 16);	//2 frames in each row, width 1, height 1, parent this, width of frame (nb pixels in the image), height of frame
		
		handler = new SuperPacmanGhostHandler();
	}
	public void attributeMainSprite(String nameSprite){
		nameOfMainSprite = nameSprite;
		mainSprites = RPGSprite.extractSprites(nameOfMainSprite, 2, 1, 1, this, 16, 16,	//4 frames in each row, width 1, height 1, parent this, width of frame (nb pixels in the image), height of frame
				new Orientation[] {Orientation.DOWN, Orientation.LEFT, Orientation.UP, Orientation.RIGHT}); //order Orientation[] orders of frame in the image
		//array of 4 Sprite[] 1 per orientation
		mainAnimations = Animation.createAnimations(ANIMATION_DURATION / 4, mainSprites);	//cr�e un tableau de 4 animations
	}
	
	public void update(float deltaTime) { // check constantly if the player is immortal or not, so the animation can change
//		checkAfraid();
		if(AFRAID == true) { //if true, no animation and default sprite (afraid)			
		}
		if(AFRAID == false) {
			mainAnimations[this.getOrientation().ordinal()].update(deltaTime);
		}
		super.update(deltaTime);
	}
	
	@Override
	public void draw(Canvas canvas) {
		if(AFRAID == false) {
			mainAnimations[this.getOrientation().ordinal()].draw(canvas);
		}
		if(AFRAID == true) {
			
		}
	
	}
	
//	//Questions
	
//	1) comment update et animer les fantomes effray�s et leur lien avec draw ?
	
//	2) l'int�r�t du Pacman handler, est-ce qu'il est appel� � chaque update ? j'ai l'impression qu'il faut l'utilser pour savoir quand activer la peur des fantomes
	
//	3) est-ce que le getFieldOfViewCells est bien appel�e � chaque update ou faut-il l'invoquer dans update pour savoir quand le personnage est l�
	
//	4) faut-il mettre abstract devant les fonctions qu'on va red�finir pour les sous-classes et faire la classe ghost une abstract class?
	
	
	
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());	//main cell
	}

	
	@SuppressWarnings("null")	//for view = null
	@Override
	public List<DiscreteCoordinates> getFieldOfViewCells() {	//no vision field
		List<DiscreteCoordinates> view = new LinkedList<>();

		DiscreteCoordinates main = getCurrentMainCellCoordinates();
		Vector current = main.toVector();
		
		for(int x = (int) (current.x -FIELD_OF_VIEW); x <= FIELD_OF_VIEW; x++) {
			for(int y = (int) (current.y-FIELD_OF_VIEW); y <= FIELD_OF_VIEW; y++) {
				DiscreteCoordinates actual = new DiscreteCoordinates(x, y);
	            view.add(actual);
			}
		}
		
		return view; //return the arraylist of fieldofview area
	} 

	@Override
	public boolean wantsCellInteraction() {
		return false;
	}

	@Override
	public boolean wantsViewInteraction() {
		return true;
	}

	@Override
	public void interactWith(Interactable other) {
		other.acceptInteraction(handler);	//interaction avec player		
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
	public boolean isViewInteractable() {
		return false;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((SuperPacmanInteractionVisitor)v).interactWith(this); //accepte de voir ses interactions avec les autres acteurs (qui sont aussi g�r�s par SuperPacmanInteractionVisitor)
		
	}	
	
//	 public void enterArea(Area area, DiscreteCoordinates position){
//	        area.registerActor(this);
//	        area.setViewCandidate(this);
//	        setOwnerArea(area);
//	        setCurrentPosition(position.toVector());
//	        resetMotion();
//	    }
//	 
//	  public void leaveArea(){
//	        getOwnerArea().unregisterActor(this);
//	    }
	
	
	/**
	 * All methods that are useful for ghost
	 */
		
		/*private void seePlayer() {
			List<DiscreteCoordinates> field = getFieldOfViewCells();
			boolean here = false;
			for(int i = 0; i < field.size(); i++) {
				if(playerMemory.getCurrentCells().equals(field.get(i))) {	//!!! WHY == DOES NOT WORK
					here = true;
					break;
				}
			}
			if(here = true) {
				seePlayer = true;
			}
		}*/
		
//		private void checkAfraid() {	//invincibility of player is treated by methods that will affect all ghosts at the same time. (in )
//			if(playerMemory.IMMORTAL == true) {
//				this.AFRAID = true;
//			}
//			if(playerMemory.IMMORTAL == false) {
//				this.AFRAID = false;
//			}
//		}
		
		public void setAfraid() {	//for Friday
			AFRAID = true;			
		}
		public void setNotAfraid() {
			AFRAID = false;
		}
		
		protected void backToRefuge(DiscreteCoordinates refuge) {
			setCurrentPosition(refuge.toVector());
		}

		
		protected void deplacement(int afraidSpeed, int normalSpeed) {
			if(AFRAID == true){
				this.move(afraidSpeed);
			}else {
				this.move(normalSpeed);
			}
		}
		
		


		public class SuperPacmanGhostHandler implements SuperPacmanInteractionVisitor{
			public void interactWith(SuperPacmanPlayer player) {
				playerMemory = player; //when in field of vision, memorise player
				seePlayer = true;	// back to false if eaten by player
				reevaluate = true;
			}
		}
		
		
}
	



