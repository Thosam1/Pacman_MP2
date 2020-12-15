package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RandomGenerator;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;

public abstract class Singed extends Spirit {

    /**
     * Singed class will follow straight lines, and will leave behind different types of objects (based on the singed class) that will interact with the player
     */

    protected DiscreteCoordinates previousMainCell;

    public Singed(Area area, DiscreteCoordinates coordinates) {
        super(area, coordinates);
    }

    public void update(float deltaTime){
        spriteAnimations.update(deltaTime);
        deplacement(getNextOrientation(), SPEED);

        if(isDisplacementOccurs()){ //if it is moving
            if(previousMainCell != getCurrentMainCellCoordinates() && previousMainCell != null){    //that means it has moved - has left previous cell
                doSomething();  //leave something behind for example
            }
        }
        previousMainCell = getCurrentMainCellCoordinates();
        super.update(deltaTime);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        spriteAnimations.draw(canvas);
    }

    private void deplacement(Orientation next, int speed) {
        if(!this.isDisplacementOccurs()) {
            this.orientate(next);
            move(SPEED);
        } else {
			move(SPEED);
		}
    }

    protected abstract void doSomething();  //redefine it in every subclasses

}
