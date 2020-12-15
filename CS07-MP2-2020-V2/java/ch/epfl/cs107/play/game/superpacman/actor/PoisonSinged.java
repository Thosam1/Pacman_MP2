package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class PoisonSinged extends Singed {

    public PoisonSinged (Area area, DiscreteCoordinates coordinates) {
        super(area, coordinates);
        attributeMainSpriteByMe("superpacman/PoisonSinged");
    }

    @Override
    protected void doSomething(){
        PoisonSmoke poison_Smoke = new PoisonSmoke(getOwnerArea(), previousMainCell, 3);
        getOwnerArea().registerActor(poison_Smoke);
    }
}
