package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.SuperPacmanAndGUI.SuperPacman;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class IceWind extends Magic{

    private final SuperPacmanIceWindHandler handler;
    private final int speedDebuff = 4;
    private boolean debuffed = false;
    private int speedAfterDebuff;

    public IceWind(Area area, DiscreteCoordinates coordinates, float lifeTimeSpan) {
        super(area, coordinates, lifeTimeSpan);
        attributeMainSpriteByMe("superpacman/IceSmoke", 6);
        handler = new SuperPacmanIceWindHandler();
    }

    @Override
    public void interactWith(Interactable other) {  //redefine it in subclasses
        other.acceptInteraction(handler);
    }

    class SuperPacmanIceWindHandler  implements SuperPacmanInteractionVisitor {
        @Override
        public void interactWith(SuperPacmanPlayer player) {
            if(!debuffed){
                speedAfterDebuff = player.getSpeed() - speedDebuff;
                if(speedAfterDebuff <= 2){
                    player.setSpeed(2);
                }else{
                    player.setSpeed(speedDebuff);
                }
                debuffed = true;
            }
        }
    }
}
