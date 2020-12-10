package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Path;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.actor.Ghost.SuperPacmanGhostHandler;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Inky extends Ghost {

	private final int MAX_DISTANCE_WHEN_SCARED = 5;	//random distance from refuge
	private SuperPacmanArea area;
	DiscreteCoordinates targetPos = null;
	
	
	public Inky(Area area, DiscreteCoordinates coordinates) {
		super(area, coordinates);
		this.area = (SuperPacmanArea) area;
		setNameOfMainSprite("superpacman/ghost.inky");
	}
	
	public void update(float deltaTime) { // ?necessary?
		super.update(deltaTime); //taking care of afraid animation
		deplacement();
	}
	
	
	private void deplacement() {
//		if(AFRAID == true) {	//refuge
//			frightened();	//target refuge
//			
//		}else { //if AFRAID = false //view
//			
//			//check and if player in field, catchPlayer	/ target player
//			
//			//else move normally
//		}
		deplacement(getNextOrientation());
		
	}
	
	private void frightened() { //back to refuge
		
	}
	
	private void catchPlayer() {
		
	}
	
	@SuppressWarnings({ "null", "unused" })	//???
	private DiscreteCoordinates randomEscapeCoordinates(DiscreteCoordinates anchor) {
		List<DiscreteCoordinates> possibleCases = null;
		DiscreteCoordinates chosenOne;
		int x = anchor.x;
		int y = anchor.y;
		
		for(int i = -MAX_DISTANCE_WHEN_SCARED; i <= MAX_DISTANCE_WHEN_SCARED; i++) {
			for(int j = -MAX_DISTANCE_WHEN_SCARED; j <= MAX_DISTANCE_WHEN_SCARED; j++) {
				DiscreteCoordinates potential = new DiscreteCoordinates(x+i, y+j);
				if(area.canEnterAreaCells(this, Collections.singletonList(potential))) {	//same principle as used in SuperPacmanPlayer
					possibleCases.add(potential);
				}
			}
		}
		int upperBound =  possibleCases.size();
		Random rand = new Random();
		int randomIndex = rand.nextInt(upperBound);
		
		return possibleCases.get(randomIndex);
	}
	
	
//	@Override
	private Orientation getNextOrientation() {	//to redefine			
		
		if(this.getCurrentMainCellCoordinates() == targetPos) {
			reevaluate = true;
		}
		
		if(reevaluate == true) {	//then check for the new path
			if(this.AFRAID == true) {
				targetPos = randomEscapeCoordinates(refuge);
			}else{
				if(seePlayer == true) {	//move towards player
					targetPos = new DiscreteCoordinates((int) playerMemory.getPosition().x, (int) playerMemory.getPosition().y);
				}else {	//default
					targetPos = randomEscapeCoordinates(this.getCurrentMainCellCoordinates());
				}
			}
		}
		
		Queue<Orientation> path = area.shortestPath(getCurrentMainCellCoordinates(), targetPos); //we ask to the area, the area asks to the behavior/graph
		Path graphicPath = new Path(this.getPosition(), new LinkedList<Orientation>(path));
		return path.poll();						
	}
	
	@Override
	public void draw(Canvas canvas) {
		super(canvas);
		if(AFRAID == false) {
			
		}
		
	
	}
	
	
	private class SuperPacmanBlinkyHandler extends SuperPacmanGhostHandler {
		
	}
}
