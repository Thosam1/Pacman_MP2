package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RandomGenerator;
import ch.epfl.cs107.play.window.Canvas;


public class Blinky extends Ghost {
	private Area area;
	private boolean starting = true;		//reinitialise / recall function if blinky back to basis / eaten or player is eaten
	private boolean left;
	private final int startingMoves = 5;
	private int remainingStartingMoves = 5;
	// if left == true //down, move - left, move, move - up, move, move
	// if left == false //down, move - right, move, move, down, move, move

	public Blinky(Area area, DiscreteCoordinates coordinates, boolean left) {	//if left blinky : true - if right blinky : false
		super(area, coordinates);
		this.area = area;
		this.attributeMainSprite("superpacman/ghost.blinky");
		this. left = left;
		System.out.println(coordinates);
		//System.out.println("Width : " + area.getWidth());
		//System.out.println("Height : " + area.getHeight());
	}

	public void update(float deltaTime) { // ?necessary?
		super.update(deltaTime); //taking care of afraid animation
		/*if(starting){
			initialMovements(left);
			if(startingMoves == 0){
				starting = false;
				remainingStartingMoves = startingMoves;
			}
		}else{
			deplacement(getNextOrientation(), SPEED, SPEED);//possibilité d avoir deux vitesses différentes
		}
		System.out.println(getOrientation());
		System.out.println(remainingStartingMoves);
		*/
		deplacement(getNextOrientation(), SPEED, SPEED);//possibilité d avoir deux vitesses différentes
	}

	private void deplacement(Orientation next, int speed, int afraidSpeed) {

		if(!this.isDisplacementOccurs()) {	//true if not moving	OR ON AN INTERSECTION? NODE IN THE GRAPH
			this.orientate(next);	//orientate the ghost
			deplacement(afraidSpeed, speed);
		} /*else {
			deplacement(afraidSpeed, speed);	//orientate the ghost each move - otherwise never get out of lv1
		}*/



	}
//	@Override	//why override DOESNT WORK ?!????????
	private Orientation getNextOrientation() {	//to redefine
		//how to find the next orientation
		int randomInt = RandomGenerator.getInstance().nextInt(4);	//(index 0-3 for the ordinal of orientation)
		Orientation nextOrientation = Orientation.fromInt(randomInt);
		return nextOrientation;
	}
	/*
	private void initialMovements (boolean leftStart) {    //blinky above pinky and inky in lv1
		if (leftStart) {
			switch (remainingStartingMoves) {
				case 5:
					orientate(Orientation.DOWN);
					move(SPEED);
					remainingStartingMoves -= 1;
					break;
				case 4:
					orientate(Orientation.RIGHT);
					move(SPEED);
					remainingStartingMoves -= 1;
					break;
				case 3:
					move(SPEED);
					remainingStartingMoves -= 1;
					break;
				case 2:
					orientate(Orientation.RIGHT);
					move(SPEED);
					remainingStartingMoves -= 1;
					break;
				case 1:
					move(SPEED);
					remainingStartingMoves -= 1;
					break;

				default:
			}


		} else {
			switch (remainingStartingMoves) {
				case 5:
					orientate(Orientation.DOWN);
					move(SPEED);
					break;
				case 4:
					orientate(Orientation.LEFT);
					move(SPEED);
					break;
				case 3:
					move(SPEED);
					break;
				case 2:
					orientate(Orientation.RIGHT);
					move(SPEED);
					break;
				case 1:
					move(SPEED);
					break;
				default:
			}
		}

	}*/

//	/**
//	 *  backToRefuge, also set the memory to null and send a reevaluate signal (for the targetPosition)
//	 */
//	@Override
//	public void backToRefuge() {
//		resetMotion();
//		area.leaveAreaCells(this, getEnteredCells());
//		setCurrentPosition(this.refuge.toVector());
//		area.enterAreaCells(this, getCurrentCells());
//	}

}