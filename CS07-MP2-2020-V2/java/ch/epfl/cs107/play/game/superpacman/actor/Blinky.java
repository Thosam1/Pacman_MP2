package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RandomGenerator;
import ch.epfl.cs107.play.window.Canvas;


public class Blinky extends Ghost {
	//private boolean starting = true;		//reinitialise / recall function if blinky back to basis / eaten or player is eaten
	//private boolean left;
	//private final int startingMoves = 5;
	//private int remainingStartingMoves = 5;
	// if left == true //down, move - left, move, move - up, move, move
	// if left == false //down, move - right, move, move, down, move, move

	public Blinky(Area area, DiscreteCoordinates coordinates) {	//if left blinky : true - if right blinky : false
		super(area, coordinates);
		this.attributeMainSprite("superpacman/ghost.blinky");
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
		*/
		deplacement(getNextOrientation(), SPEED, SPEED);//possibilité d avoir deux vitesses différentes
	}

	private Orientation getNextOrientation() {
		int randomInt = RandomGenerator.getInstance().nextInt(4);	//(index 0-3 for the ordinal of orientation)
		Orientation nextOrientation = Orientation.fromInt(randomInt);
		return nextOrientation;
	}

	/**
	 * We tried to automate the firsts steps that blinky takes in lv1 so it can move in straight lines, however it took much time without results so we moved on - we wanted to put left or right (if left == false) in the constructor and move the blinky according to this boolean...
	 */
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