package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MageSpirit extends Spirit {

    private SuperPacmanArea area;
    private final float intervalTime;
    private float intervalTimer;
    //protected int RANGE;    //specific to each type of mageSpirit

    /**
     *  MageSpirit class is a class that moves randomly and create/impact the map at a distance every intervalTime seconds
     */
    public MageSpirit(Area area, DiscreteCoordinates coordinates, int intervalTime) {   //every intervalTime seconds, it will do something...
        super(area, coordinates);
        this.intervalTime = intervalTime;
        this.intervalTimer = intervalTime;
    }
    public void update(float deltaTime){
        deplacement(getNextOrientation(), SPEED);

        intervalTimer -= deltaTime;
        if(intervalTimer <= 0){
            doSomething();
            intervalTimer = intervalTime;
        }

        super.update(deltaTime);
    }

    private void deplacement(Orientation next, int speed) {
        //if(!this.isDisplacementOccurs()) {	//true if not moving	OR ON AN INTERSECTION? NODE IN THE GRAPH
            this.orientate(next);	//orientate the ghost
            move(SPEED);

    }

    protected void doSomething(){   //to redefine in subclasses

    }

    /**
     * @param anchor   the position of the center of the area we search
     * @param maxRange the range from the center
     * @return a list of DiscreteCoordinates corresponding to the cells an IntelligentGhost can enter, within an area
     */
    @SuppressWarnings({"null", "unused"})    //???
    protected List<DiscreteCoordinates> listOfAllCellsAround(DiscreteCoordinates anchor, int maxRange) {
        List<DiscreteCoordinates> possibleCases = new ArrayList<>();
        DiscreteCoordinates chosenOne;
        int x = anchor.x;
        int y = anchor.y;

        for (int i = -maxRange; i <= maxRange; i++) {
            for (int j = -maxRange; j <= maxRange; j++) {
                DiscreteCoordinates potential = new DiscreteCoordinates(x + i, y + j);
                if (getOwnerArea().canEnterAreaCells(this, Collections.singletonList(potential))) {    //same principle as used in SuperPacmanPlayer
                    possibleCases.add(potential);
                }
            }
        }
        return possibleCases;
    }

    /**
     * @param rangeArea
     * @return a random coordinate within a list of Discrete Coordinates
     */
    protected DiscreteCoordinates randomCoordinates(List<DiscreteCoordinates> rangeArea) {
        int upperBound = rangeArea.size();
        Random rand = new Random();
        int randomIndex = rand.nextInt(upperBound);
        return rangeArea.get(randomIndex);
    }

}
