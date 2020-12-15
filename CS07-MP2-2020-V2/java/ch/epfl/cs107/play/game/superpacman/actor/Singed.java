package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RandomGenerator;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;

public class Singed extends Spirit {

    /**
     * Singed class will follow straight lines, and will leave behind different type of smokes based on the singed class that will interact with the player
     */

    private SuperPacmanArea area;
    //private final float intervalTime;
    //private float intervalTimer;
    private int type;

    protected DiscreteCoordinates previousMainCell;

    public Singed(Area area, DiscreteCoordinates coordinates) {
        super(area, coordinates);
        //this.intervalTime = intervalTime;


        if(type == 0){
            attributeMainSpriteByMe("superpacman/IceSinged");
        }
        if(type == 1){
            attributeMainSpriteByMe("superpacman/PoisonSinged");
        }
    }
    public void update(float deltaTime){
        spriteAnimations.update(deltaTime);

        deplacement(getNextOrientation(), SPEED);
        super.update(deltaTime);
    }

    @Override
    public void draw(Canvas canvas) {
        spriteAnimations.draw(canvas);
    }

    private void deplacement(Orientation next, int speed) {
        if(!this.isDisplacementOccurs()) {	//true if not moving	OR ON AN INTERSECTION? NODE IN THE GRAPH
            this.orientate(next);	//orientate the ghost
            move(SPEED);
        } else {
			move(SPEED);	//orientate the ghost each move - otherwise never get out of lv1
		}



    }
    //	@Override	//why override DOESNT WORK ?!????????
    private Orientation getNextOrientation() {
        int randomInt = RandomGenerator.getInstance().nextInt(4);	//(index 0-3 for the ordinal of orientation)
        Orientation nextOrientation = Orientation.fromInt(randomInt);
        return nextOrientation;
    }

}
