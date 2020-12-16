package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

import static ch.epfl.cs107.play.math.DiscreteCoordinates.distanceBetween;

public class Pinky extends IntelligentGhost {

	private final int MIN_AFRAID_DISTANCE = 7;	//let's make it harder for pinky
	private int MAX_DISTANCE_WHEN_NOT_SCARED = 12;
	private int MAX_RANDOM_ATTEMPT = 50;
	private final int FINAL_MAX_RANDOM_ATTEMPT = 50;


	public Pinky(Area area, DiscreteCoordinates coordinates) {
		super(area, coordinates);
		this.attributeMainSprite("superpacman/ghost.pinky");
	}
	
	public void update(float deltaTime) {
		super.update(deltaTime); //taking care of afraid animation
		if(playerMemory == null){
			deplacement(getNextOrientation(getCurrentMainCellCoordinates(), getCurrentMainCellCoordinates(), MAX_DISTANCE_WHEN_NOT_SCARED, MAX_DISTANCE_WHEN_NOT_SCARED), SPEED, SPEED_AFRAID);
		}else{
			deplacement(getNextOrientation(playerMemory.getCurrentCells().get(0), getCurrentMainCellCoordinates(), MIN_AFRAID_DISTANCE, MAX_DISTANCE_WHEN_NOT_SCARED), SPEED, SPEED_AFRAID);
		}
	}

	@Override
	protected void deplacement(Orientation next, int speed, int afraidSpeed) { //different for pinky
		/*
		if(!this.isDisplacementOccurs()) {	//true if not moving
			this.orientate(next);	//orientate the ghost
			deplacement(afraidSpeed, speed);
		}else{
			deplacement(afraidSpeed, speed);
			/*if(AFRAID == true){
				this.move(afraidSpeed);
			}else {
				this.move(speed);
			}
		}
		*/
		if(!this.isDisplacementOccurs()){
			orientate(next);    //orientate the ghost
			deplacement(afraidSpeed, speed);
		}
		int dist = MIN_AFRAID_DISTANCE; //so it doesnt get messed up
		if(playerMemory != null) {
			dist = (int) distanceBetween(playerMemory.getCurrentCells().get(0), this.getCurrentMainCellCoordinates());
		}
		if(dist <= MIN_AFRAID_DISTANCE && MAX_RANDOM_ATTEMPT >= 0){
			reevaluate = true;
			MAX_RANDOM_ATTEMPT -= 1;
		}
		if (!seePlayer){
			MAX_RANDOM_ATTEMPT = FINAL_MAX_RANDOM_ATTEMPT;
		}
	}

}
