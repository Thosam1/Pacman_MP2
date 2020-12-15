package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.*;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public class Magic extends AreaEntity implements Interactor {
    private Area area;

    //General
    private DiscreteCoordinates invokeCoordinates;

    //Specific, to precise in each subclass
    private Sprite[] Sprite;
    private Animation spriteAnimations;
    /// Animation duration in frame number
    private final static int ANIMATION_DURATION = 8;

    protected final float lifeTimeSpan; //in seconds

    private int lifeSpanTimer;
    private boolean isDying = true;

    private String nameOfMainSprite = "superpacman/ghost.blinky";

    /**
     * Magic superclass, abstract level
     *
     */

    public Magic (Area area, DiscreteCoordinates coordinates, float lifeTimeSpan){//constructeur	-area = aire où il appartient
        super(area, Orientation.UP, coordinates);
        this.area = area;
        this.lifeTimeSpan = lifeTimeSpan;
        invokeCoordinates = coordinates;
        area.registerActor(this);
    }

    public void update(float deltaTime){
        spriteAnimations.update(deltaTime);

        if(isDying = true){
            lifeSpanTimer -= deltaTime;
            if(lifeSpanTimer < 0){
                area.leaveAreaCells(this, Collections.singletonList(invokeCoordinates));
                area.unregisterActor(this);
            }
        }
        super.update(deltaTime);
    }

    @Override
    public void draw(Canvas canvas) {
        spriteAnimations.draw(canvas);
    }

    /**
     * All methods related to interactable      --- not necessary to change them, but just to be sure   ---
     */

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());	//main cell
    }

    @Override
    public boolean takeCellSpace() {	//non traversable
        return false;
    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {	//TRUE IF WE PUT LASER EXTENSION
        return false;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((SuperPacmanInteractionVisitor)v).interactWith(this); //accepte de voir ses interactions avec les autres acteurs (qui sont aussi gérés par SuperPacmanInteractionVisitor)
    }

    /**
     * All methods related to interactor
     */

    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells() {
        return null;
    }

    @Override
    public boolean wantsCellInteraction() {
        return true;    //only interact with SuperPacman
    }

    @Override
    public boolean wantsViewInteraction() {
        return false;
    }

    @Override
    public void interactWith(Interactable other) {  //redefine it in subclasses
    }

    /**
     * All methods that are useful for Magic
     */

    protected void attributeMainSprite(String nameSprite){
        nameOfMainSprite = nameSprite;
        Sprite = RPGSprite.extractSprites("superpacman/ghost.afraid", 2, 1, 1, this, 16, 16);	//2 frames in each row, width 1, height 1, parent this, width of frame (nb pixels in the image), height of frame
        //array of 4 Sprite[] 1 per orientation
        spriteAnimations = new Animation(ANIMATION_DURATION / 4, Sprite);
    }
    protected void attributeMainSpriteByMe(String nameSprite){
        nameOfMainSprite = nameSprite;
        Sprite = RPGSprite.extractSprites("superpacman/ghost.afraid", 6, 1, 1, this, 16, 16);	//2 frames in each row, width 1, height 1, parent this, width of frame (nb pixels in the image), height of frame
        //array of 4 Sprite[] 1 per orientation
        spriteAnimations = new Animation(ANIMATION_DURATION / 4, Sprite);
    }

}
