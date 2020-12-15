package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class StoneMageSpirit extends MageSpirit {

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



        PoisonSmoke poison_Smoke = new PoisonSmoke(getOwnerArea(), previousMainCell, 3);
        getOwnerArea().registerActor(poison_Smoke);
    }

}
