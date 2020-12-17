package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.*;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RandomGenerator;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public class Spirit extends MovableAreaEntity {
    private DiscreteCoordinates spawn;

    /// Animation duration in frame number
    protected final static int ANIMATION_DURATION = 8;
    protected int SPEED = 6;
    private Area area;
    /*
    private Sprite[][] mainSprites;
    private Animation[] mainAnimations;
     */

    //for singed classes
    private Sprite[] Sprite;
    protected Animation spriteAnimations;

    private String nameOfMainSprite;

    public Spirit(Area area, DiscreteCoordinates coordinates){
        super(area, Orientation.UP, coordinates);
        this.area = area;
        spawn = coordinates;
    }
    /*
    protected void attributeMainSprite(String nameSprite){
        nameOfMainSprite = nameSprite;
        mainSprites = RPGSprite.extractSprites(nameOfMainSprite, 2, 1, 1, this, 16, 16,	//4 frames in each row, width 1, height 1, parent this, width of frame (nb pixels in the image), height of frame
                new Orientation[] {Orientation.DOWN, Orientation.LEFT, Orientation.UP, Orientation.RIGHT}); //order Orientation[] orders of frame in the image
        //array of 4 Sprite[] 1 per orientation
        mainAnimations = Animation.createAnimations(ANIMATION_DURATION / 4, mainSprites);	//cr�e un tableau de 4 animations
    }
     */
    protected void attributeMainSpriteByMe(String nameSprite){
        nameOfMainSprite = nameSprite;
        Sprite = RPGSprite.extractSprites(nameOfMainSprite, 6, 1, 1, this, 16, 16);	//2 frames in each row, width 1, height 1, parent this, width of frame (nb pixels in the image), height of frame
        //array of 4 Sprite[] 1 per orientation
        spriteAnimations = new Animation(ANIMATION_DURATION / 4, Sprite);
    }

    public void update(float deltaTime){
        spriteAnimations.update(deltaTime);
        super.update(deltaTime);
    }

    @Override
    public void draw(Canvas canvas) {
        spriteAnimations.draw(canvas);
    }

    public void backToSpawn(){
        resetMotion();
        getOwnerArea().leaveAreaCells(this, getEnteredCells());
        setCurrentPosition(this.spawn.toVector());
        getOwnerArea().enterAreaCells(this, getCurrentCells());
    }
    /**
     * All methods related to interactable
     */

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());	//main cell;
    }


    @Override
    public boolean takeCellSpace() {    //non traversable
        return false;
    }

    @Override
    public boolean isCellInteractable() {   //nobody can touch a spirit
        return true;
    }   //contact with walls

    @Override
    public boolean isViewInteractable() {   //nobody can see a spirit
        return false;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((SuperPacmanInteractionVisitor)v).interactWith(this); //accepte de voir ses interactions avec les autres acteurs (qui sont aussi gérés par SuperPacmanInteractionVisitor)
    }


    /**
     * All methods that are useful for Spirit
     */

    protected Orientation getNextOrientation() {
        int randomInt = RandomGenerator.getInstance().nextInt(4);	//(index 0-3 for the ordinal of orientation)
        Orientation nextOrientation = Orientation.fromInt(randomInt);
        return nextOrientation;
    }

}
