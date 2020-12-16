package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class MudRock extends Magic {
    private final SuperPacmanMudRockHandler handler;
    private final int speedDebuff = 2;  //be aware, if bigger speed = slower player
    private boolean debuffed = false;
    private int speedAfterDebuff;

    public MudRock(Area area, DiscreteCoordinates coordinates, float lifeTimeSpan) {
        super(area, coordinates, lifeTimeSpan);
        attributeMainSpriteByMe("superpacman/MudRock", 6);
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

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((SuperPacmanInteractionVisitor)v).interactWith(this); //accepte de voir ses interactions avec les autres acteurs (qui sont aussi gérés par SuperPacmanInteractionVisitor)
    }

    class SuperPacmanMudRockHandler  implements SuperPacmanInteractionVisitor {
        @Override
        public void interactWith(SuperPacmanPlayer player) {    //it will debuff the speed only once if player speed has not already been debuffed
            if(!debuffed){ //if have not debuffed yet
                speedAfterDebuff = player.getSpeed() + speedDebuff;

                if(speedAfterDebuff == player.getBASE_SPEED() + speedDebuff){   //-> player not buffed
                    player.setSpeed(speedAfterDebuff);
                }else if (speedAfterDebuff <= player.getBASE_SPEED() + speedDebuff){    //player was buffed previously
                    player.setSpeed(speedAfterDebuff);
                }else{
                    //that means the player has been debuffed once or more so don't do anything
                }
                debuffed = true;
            }
        }
    }
}
