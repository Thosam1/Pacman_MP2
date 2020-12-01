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
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public abstract class Ghost extends Player {

	//General 
	private DiscreteCoordinates refuge;
	private Sprite[] afraidSprites;	//only 2 images for afraid
	private Animation[] afraidAnimations;
	
	/// Animation duration in frame number
    protected final static int ANIMATION_DURATION = 8;
    private int SPEED = 6;
    private Area area;
	private final SuperPacmanGhostHandler handler;
	private boolean AFRAID = false;
    
    private final int GHOST_SCORE = 500;
    private final int FIELD_OF_VIEW = 5;
    
    //for specific
    protected Sprite mainSprite;
	protected Sprite[][] mainSprites;	
	protected Animation[] mainAnimations;
    
    /**
	 * Ghost superclass, abstract level
	 * 
	 */

	public Ghost(Area area, Orientation orientation, DiscreteCoordinates coordinates) {	//constructeur	-area = aire où il appartient
		super(area, orientation, coordinates);
		this.area = area;
		refuge = coordinates;
		afraidSprites = RPGSprite.extractSprites("superpacman/ghost.afraid", 2, 1, 1, this, coordinates.toVector(), 64, 64);	//4 frames in each row, width 1, height 1, parent this, width of frame (nb pixels in the image), height of frame
            
		handler = new SuperPacmanGhostHandler();
		
	}
	
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());	//main cell
	}

	
	@SuppressWarnings("null")	//for view = null
	@Override
	public List<DiscreteCoordinates> getFieldOfViewCells() {	//no vision field
		List<DiscreteCoordinates> view = null;
		DiscreteCoordinates main = getCurrentMainCellCoordinates();
		Vector current = main.toVector();
		
		for(int x = (int) (current.x -FIELD_OF_VIEW); x <= FIELD_OF_VIEW; x++) {
			for(int y = (int) (current.y-FIELD_OF_VIEW); y <= FIELD_OF_VIEW; y++) {
				DiscreteCoordinates actual = new DiscreteCoordinates(x, y);
	            view.add(actual);
			}
		}
		
		return view;
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
		return true;
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
		((SuperPacmanInteractionVisitor)v).interactWith(this); //accepte de voir ses interactions avec les autres acteurs (qui sont aussi gérés par SuperPacmanInteractionVisitor)
		
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
	
	public class SuperPacmanGhostHandler implements SuperPacmanInteractionVisitor{
		public void interactWith(SuperPacmanPlayer player) {
			if(player.IMMORTAL == true) {
				AFRAID = true;
			}
			if(player.IMMORTAL == false) {
				AFRAID = false;
			}
		}
	}

}
