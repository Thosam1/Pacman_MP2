package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class IceSinged  extends Singed{

    private Area area;
    public IceSinged(Area area, DiscreteCoordinates coordinates) {
        super(area, coordinates);
        this.area = area;
        attributeMainSpriteByMe("superpacman/IceSinged");
    }

    public void update(float deltaTime){
        super.update(deltaTime);
    }

    @Override
    protected void doSomething() {
        IceWind ice_wind = new IceWind(area, previousMainCell, 3);
        getOwnerArea().registerActor(ice_wind);
    }
}
