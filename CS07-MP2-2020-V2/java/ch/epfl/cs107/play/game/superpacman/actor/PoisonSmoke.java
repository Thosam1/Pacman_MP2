package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class PoisonSmoke extends Magic{

    private final SuperPacmanPoisonSmokeHandler handler;

    private boolean scoreDebuffed = false;
    private final float scoreDebuff = 60.f;

    public PoisonSmoke(Area area, DiscreteCoordinates coordinates, float lifeTimeSpan) {
        super(area, coordinates, lifeTimeSpan);
        attributeMainSpriteByMe("superpacman/PoisonSmoke", 6);
        handler = new SuperPacmanPoisonSmokeHandler();
    }

    @Override
    public void interactWith(Interactable other) {  //redefine it in subclasses
        other.acceptInteraction(handler);
    }

    private class SuperPacmanPoisonSmokeHandler implements SuperPacmanInteractionVisitor {
        @Override
        public void interactWith(SuperPacmanPlayer player) {
            if(!scoreDebuffed){
                player.setScore(player.getScore() - scoreDebuff);
                scoreDebuffed = true;
            }
        }
    }
}
