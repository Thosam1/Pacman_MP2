package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Path;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.*;

public class IntelligentGhost extends Ghost implements Interactor {
    private SuperPacmanArea area;

    protected boolean reevaluate = true;	//for pinky and inky

    private final SuperPacmanIntelligentGhostHandler handler2;
    private final int FIELD_OF_VIEW = 5;
    protected int SPEED_AFRAID = 8; //faster when afraid for inky and pinky
    protected SuperPacmanPlayer playerMemory;
    protected boolean seePlayer = false;

    protected DiscreteCoordinates targetPos;
    protected Queue<Orientation> path;
    protected Path graphicPath;
    protected LinkedList<Orientation> pathList;


    public IntelligentGhost(Area area, DiscreteCoordinates coordinates) {
        super(area, coordinates);
        this.area = (SuperPacmanArea) area;
        handler2 = new SuperPacmanIntelligentGhostHandler();    //NECESSARY??
    }

    public void update(float deltaTime) { // ?necessary?
        super.update(deltaTime); //taking care of afraid animation
        //deplacement(getNextOrientation(), SPEED, SPEED_AFRAID);

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(graphicPath != null){
            graphicPath.draw(canvas);	//drawing the path taken by ghost
        }
    }

    /**
     * All methods related to interactor
     */

    public List<DiscreteCoordinates> getFieldOfViewCells() {	//no vision field
        List<DiscreteCoordinates> view = new ArrayList<>();

        DiscreteCoordinates main = getCurrentMainCellCoordinates();
        for(int x = (int) (main.x -FIELD_OF_VIEW); x <= (int) (main.x + FIELD_OF_VIEW); x++) {	//cast (int) not necessary, but why not ?
            for(int y = (int) (main.y -FIELD_OF_VIEW); y <= (int) (main.y + FIELD_OF_VIEW); y++) {
                DiscreteCoordinates actual = new DiscreteCoordinates(x, y);
                view.add(actual);
            }
        }
        return view; //return the arraylist of fieldofview area
    }


    public boolean wantsCellInteraction() {
        return true;
    } //SET TO FALSE !! - if set to false, there is a bug with the animation and I don't know how to fix this

    public boolean wantsViewInteraction() {
        return true;
    }


    public void interactWith(Interactable other) {
        other.acceptInteraction(handler2);
    }

    /* --- In case there is a specific interaction with IntelligentGhost ---
    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {       //to interact with intelligentGhost
        ((SuperPacmanInteractionVisitor)v).interactWith(this); //accepte de voir ses interactions avec les autres acteurs (qui sont aussi g�r�s par SuperPacmanInteractionVisitor)
    }*/

    /**
     * All methods useful for IntelligentGhosts     ---------------------------------------------------------------------------------------------------------------------------
     */

    /**
     *
     * @param anchor    the position of the center of the area we search
     * @param maxRange  the range from the center
     * @return  a list of DiscreteCoordinates corresponding to the cells an IntelligentGhost can enter, within an area
     */
    @SuppressWarnings({ "null", "unused" })	//???
    private List<DiscreteCoordinates> EscapeCoordinates(DiscreteCoordinates anchor, int maxRange) {
        List<DiscreteCoordinates> possibleCases = new ArrayList<>();
        DiscreteCoordinates chosenOne;
        int x = anchor.x;
        int y = anchor.y;

        for(int i = -maxRange; i <= maxRange; i++) {
            for(int j = -maxRange; j <= maxRange; j++) {
                DiscreteCoordinates potential = new DiscreteCoordinates(x+i, y+j);
                if(area.canEnterAreaCells(this, Collections.singletonList(potential))) {	//same principle as used in SuperPacmanPlayer
                    possibleCases.add(potential);
                }
            }
        }
        return possibleCases;
    }

    /**
     *
     * @param rangeArea
     * @return  a random coordinate within a list of Discrete Coordinates
     */
    private DiscreteCoordinates randomEscapeCoordinates(List<DiscreteCoordinates> rangeArea){
        int upperBound =  rangeArea.size();
        Random rand = new Random();
        int randomIndex = rand.nextInt(upperBound);
        return rangeArea.get(randomIndex);
    }

    /**
     *
     * @param from              the position of the center of the area around which the ghost will aim when scared (intelligent ghost)
     * @param fromNot           same but when not scared
     * @param maxWhenScared     max range from the center of the area when scared
     * @param maxWhenNotScared  same but when not scared
     * @return                  the next orientation
     */
    protected Orientation getNextOrientation(DiscreteCoordinates from, DiscreteCoordinates fromNot,  int maxWhenScared, int maxWhenNotScared) {

        if(targetPos != null && (this.getCurrentMainCellCoordinates() == targetPos || isDisplacementOccurs() == false)) {     //   || isDisplacementOccurs() == false    pour les gates ?
            this.setReevaluate(true);
        }

        if(reevaluate == true) {	//then check for the new path   Cases when reevaluate a path : 1) if destination is reached 2) if ghosts become scared / pacman eats becomes invincible 3) if pacman enters in the field of view
            findNewTargetPos(from, fromNot, maxWhenScared, maxWhenNotScared);
        }
        this.setReevaluate(false);

        /*while(path == null){
            findNewTargetPos(from, fromNot, maxWhenScared, maxWhenNotScared);
            path = area.shortestPath(getCurrentMainCellCoordinates(), targetPos); //we ask to the area, the area asks to the behavior/graph //dessiner un trait
        }*/

        path = area.shortestPath(getCurrentMainCellCoordinates(), targetPos); //we ask to the area, the area asks to the behavior/graph //dessiner un trait
        if(path == null){
            graphicPath = new Path(this.getPosition(), new LinkedList<Orientation>());  //dessin
            return getOrientation(); //possible!!!! -> take stay in the same orientation
        }else{
            pathList = new LinkedList<Orientation>(path);
            graphicPath = new Path(this.getPosition(), pathList);   //dessin
            return path.poll();
        }
    }

    /**
     *      same parameters as before,  --- find a new target position, based on the current situation  ---
     */
    private void findNewTargetPos(DiscreteCoordinates from, DiscreteCoordinates fromNot,  int maxWhenScared, int maxWhenNotScared) {
        if (this.getAfraid() == true) {
            targetPos = randomEscapeCoordinates(EscapeCoordinates(from, maxWhenScared));
        } else {
            if (seePlayer == true) {    //move towards player
                targetPos = playerMemory.getCurrentCells().get(0); //new DiscreteCoordinates((int) playerMemory.getPosition().x, (int) playerMemory.getPosition().y)
                //IF THE PATH IS NULL, THEN FORGET ABOUT THE PLAYER - CHOOSE DEFAULT
            } else {    //default
                targetPos = randomEscapeCoordinates(EscapeCoordinates(fromNot, maxWhenNotScared));
            }
        }
    }


    /**
     *  backToRefuge, also set the memory to null and send a reevaluate signal (for the targetPosition)
     */
    @Override
    public void backToRefuge() {
        playerMemory = null;
        seePlayer = false;
        resetMotion();
        area.leaveAreaCells(this, getEnteredCells());
        setCurrentPosition(this.refuge.toVector());
        area.enterAreaCells(this, getCurrentCells());
        setReevaluate(true); //so the pinky doesn't follow the player
    }

    public void setReevaluate(boolean c) {
        if(c) {
            reevaluate = true;
        }else {
            reevaluate = false;
        }
    }

    protected class SuperPacmanIntelligentGhostHandler extends SuperPacmanGhostHandler {
        @Override
        public void interactWith(SuperPacmanPlayer player) {
            playerMemory = player; //when in field of vision, memorise player
            seePlayer = true;	// back to false if eaten by player
            //System.out.println("INTERACTED" + getFieldOfViewCells());
        }
    }
}
