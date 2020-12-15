package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class PoisonSmoke extends Magic{

    private final SuperPacmanPoisonSmokeHandler handler;
    private final int speedDebuff = 2;

    private boolean debuffedSpeed = false;
    private int speedAfterDebuff;

    private boolean scoreDebuff = false;

    public PoisonSmoke(Area area, DiscreteCoordinates coordinates, float lifeTimeSpan) {
        super(area, coordinates, lifeTimeSpan);
        attributeMainSpriteByMe("superpacman/PoisonSmoke");
        handler = new SuperPacmanPoisonSmokeHandler();
    }

    @Override
    public void interactWith(Interactable other) {  //redefine it in subclasses
        other.acceptInteraction(handler);
    }

    private class SuperPacmanPoisonSmokeHandler implements SuperPacmanInteractionVisitor {
        @Override
        public void interactWith(SuperPacmanPlayer player) {
            if(!debuffedSpeed){
                speedAfterDebuff = player.getSpeed() - speedDebuff;
                if(speedAfterDebuff <= 2){
                    player.setSpeed(2);
                }else{
                    player.setSpeed(speedDebuff);
                }
                debuffedSpeed = true;
            }
        }
    }
}
