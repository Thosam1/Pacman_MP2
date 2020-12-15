package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class StoneMageSpirit extends MageSpirit {

    private int RANGE = 5;
    public StoneMageSpirit(Area area, DiscreteCoordinates coordinates, int intervalTime) {
        super(area, coordinates, intervalTime);
        attributeMainSpriteByMe("superpacman/StoneMageSpirit");
    }
    /*
    public void update(float deltaTime){
        super.update(deltaTime);
    }*/

    @Override
    protected void doSomething(){
        DiscreteCoordinates invocationPoint = randomCoordinates(listOfAllCellsAround(getCurrentMainCellCoordinates(), 5));


        Rock rock = new Rock(getOwnerArea(), invocationPoint, 10);
        getOwnerArea().registerActor(rock);
    }



}
