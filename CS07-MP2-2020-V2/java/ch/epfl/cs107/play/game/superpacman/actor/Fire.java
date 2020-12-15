package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.superpacman.SuperPacmanAndGUI.SuperPacman;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Fire extends Magic{
    private final SuperPacmanFireHandler handler;
    private boolean debuffed = false;
    private final float burnScore = 400.f;

    public Fire(Area area, DiscreteCoordinates coordinates, float lifeTimeSpan) {
        super(area, coordinates, lifeTimeSpan);
        attributeMainSpriteByMe("superpacman/fire", 7);
        handler = new SuperPacmanFireHandler();
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

    /**
     *  only interacts with player, kill the player and decrease its score
     */

    class SuperPacmanFireHandler  implements SuperPacmanInteractionVisitor {    //cannot burn other ghost, because they already died
        public void interactWith(SuperPacmanPlayer player){
            if(!debuffed){
                player.pacmanHasDied(); //send the player back to its initial positon
                player.setScore(player.getScore() - burnScore);
                debuffed = true;
            }
        }
    }
}
