package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class FireMageSpirit extends MageSpirit {
    private int RANGE = 7;  //well, why not
    public FireMageSpirit(Area area, DiscreteCoordinates coordinates, int intervalTime) {
        super(area, coordinates, intervalTime);
        attributeMainSpriteByMe("superpacman/FireMageSpirit");
    }
    /*
    public void update(float deltaTime){
        super.update(deltaTime);
    }*/

    @Override
    protected void doSomething(){
        DiscreteCoordinates invocationPoint = randomCoordinates(listOfAllCellsAround(getCurrentMainCellCoordinates(), 5));
        Fire fire = new Fire(getOwnerArea(), invocationPoint, 5);
        getOwnerArea().registerActor(fire);
    }
}
