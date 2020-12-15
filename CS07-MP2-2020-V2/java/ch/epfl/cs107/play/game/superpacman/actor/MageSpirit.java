package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class MageSpirit extends Spirit {

    private SuperPacmanArea area;
    private final float intervalTime;
    private float intervalTimer;
    protected DiscreteCoordinates previousMainCell;
    protected int RANGE;    //specific to each type of mageSpirit

    public MageSpirit(Area area, DiscreteCoordinates coordinates, int intervalTime) {   //every intervalTime seconds, it will do something...
        super(area, coordinates);
        this.intervalTime = intervalTime;
        this.intervalTimer = intervalTime;
    }
    public void update(float deltaTime){
        spriteAnimations.update(deltaTime);
        deplacement(getNextOrientation(), SPEED);

        intervalTimer -= deltaTime;
        if(intervalTimer <= 0){
            doSomething();
            intervalTimer = intervalTime;
        }

        super.update(deltaTime);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        spriteAnimations.draw(canvas);
    }

    private void deplacement(Orientation next, int speed) {
        //if(!this.isDisplacementOccurs()) {	//true if not moving	OR ON AN INTERSECTION? NODE IN THE GRAPH
            this.orientate(next);	//orientate the ghost
            move(SPEED);
        //}
    }

    protected void doSomething(){   //to redefine in subclasses

    }

}
