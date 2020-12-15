package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class MudRock extends Magic {
    private final SuperPacmanMudRockHandler handler;
    private final int speedDebuff = 3;
    private boolean debuffed = false;
    private int speedAfterDebuff;

    public MudRock(Area area, DiscreteCoordinates coordinates, float lifeTimeSpan) {
        super(area, coordinates, lifeTimeSpan);
        attributeMainSpriteByMe("superpacman/MudRock");
        handler = new SuperPacmanMudRockHandler();
    }

    public void update(float deltaTime){
        super.update(deltaTime);
    }
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    public void interactWith(Interactable other) {  //redefine it in subclasses
        other.acceptInteraction(handler);
    }

    class SuperPacmanMudRockHandler  implements SuperPacmanInteractionVisitor {
        @Override
        public void interactWith(SuperPacmanPlayer player) {
            if(!debuffed){ //if have not debuffed yet
                speedAfterDebuff = player.getSpeed() - speedDebuff;

                if(speedAfterDebuff == player.getBASE_SPEED() - speedDebuff){   //-> player not buffed
                    player.setSpeed(speedAfterDebuff);
                }else if (speedAfterDebuff <= 2){
                    player.setSpeed(2);
                }else{
                    player.setSpeed(speedDebuff);
                }
                debuffed = true;
            }
        }
    }
}
