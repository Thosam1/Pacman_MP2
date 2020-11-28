package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
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
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class SuperPacmanPlayer extends Player{
	private int hp;
	private int score;
	private TextGraphics message;
	private Sprite sprite;
	private final static int ANIMATION_DURATION = 8;
	private int SPEED = 6;
	public Orientation desiredOrientation;
	private Area area;
	private DiscreteCoordinates coordinates;
	private final SuperPacmanPlayerHandler handler;
	
	public SuperPacmanPlayer(Area area, Orientation orientation, DiscreteCoordinates coordinates) {
		super(area, orientation, coordinates);
		this.area = area;
		hp = 3;
		score = 0;
		sprite = new Sprite("yellowDot", 1, 1.f, this);
		handler = new SuperPacmanPlayerHandler();
	}
	
	public void update(float deltaTime) {
			Keyboard keyboard= getOwnerArea().getKeyboard();
	        orientatePlayer(Orientation.LEFT,keyboard.get(Keyboard.LEFT));
	        orientatePlayer(Orientation.UP,keyboard.get(Keyboard.UP));
	        orientatePlayer(Orientation.RIGHT,keyboard.get(Keyboard.RIGHT));
	        orientatePlayer(Orientation.DOWN,keyboard.get(Keyboard.DOWN));
	        
	        if (!(isDisplacementOccurs())) {
	        	if (desiredOrientation!=null) {
	        		for (int i=0; i<4; i++) {
	        			sprite = spriteFrames(i, desiredOrientation);
	        	
	        		if (area.canEnterAreaCells(this,Collections.singletonList(getCurrentMainCellCoordinates().jump(desiredOrientation.toVector())))) {
	        			orientate(desiredOrientation);
	        			this.move(SPEED);
	        		}
	        	}
	        }
	        	else {
	        		//reset();
	        		
	        	}
	        	}
	        
	       super.update(deltaTime);
	       
	    }
	public void orientatePlayer(Orientation keyOrientation, Button b) {
		if (b.isDown()) {
			desiredOrientation = keyOrientation;
		}
	}
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public List<DiscreteCoordinates> getFieldOfViewCells() {
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
		((SuperPacmanInteractionVisitor)v).interactWith(this);
		
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
		sprite.draw(canvas);
		message.draw(canvas);}
		
	public Sprite spriteFrames(int i, Orientation desiredOrientation) {
		if (desiredOrientation == Orientation.DOWN) {
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
			}

		
	
private class SuperPacmanPlayerHandler implements SuperPacmanInteractionVisitor{
	public void interactWith(Door door){
			setIsPassingADoor(door);
	}
}
}
}