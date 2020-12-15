package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class EarthSinged extends Singed{

    public EarthSinged (Area area, DiscreteCoordinates coordinates) {
        super(area, coordinates);
        attributeMainSpriteByMe("superpacman/EarthSinged");
    }

    @Override
    protected void doSomething() {
        MudRock mud_Rock = new MudRock(getOwnerArea(), previousMainCell, 3);
        getOwnerArea().registerActor(mud_Rock);
    }
}
