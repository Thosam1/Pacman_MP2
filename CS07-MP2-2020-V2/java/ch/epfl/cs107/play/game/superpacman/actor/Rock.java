package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Rock extends Magic {
    //private final SuperPacmanRockHandler handler;


    public Rock(Area area, DiscreteCoordinates coordinates, float lifeTimeSpan) {
        super(area, coordinates, lifeTimeSpan);
        attributeMainSpriteByMe("superpacman/RockInvokation", 6);
        //handler = new SuperPacmanRockHandler();
    }

    public void update(float deltaTime){
        super.update(deltaTime);
    }
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    /*
    @Override
    public void interactWith(Interactable other) {  //redefine it in subclasses
        other.acceptInteraction(handler);
    }
    */

    @Override
    public boolean takeCellSpace() {    //so no one can go through
        return true;
    }

    /*
    class SuperPacmanRockHandler  implements SuperPacmanInteractionVisitor {    //no specific interaction
    }*/
}
