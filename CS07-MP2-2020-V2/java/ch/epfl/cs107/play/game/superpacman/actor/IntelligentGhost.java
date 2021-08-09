package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.function.Predicate;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Path;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public abstract class IntelligentGhost extends Ghost implements Interactor {
    private static final boolean DRAW_GRAPHIC_PATH_DEBUG = false; // set to true to to display graphic path (for debug purposes)

    protected boolean reevaluate = true;    //for pinky and inky

    private final SuperPacmanIntelligentGhostHandler handler2;
    private final int FIELD_OF_VIEW = 5;
    protected int SPEED_AFRAID = 5; //faster when afraid for inky and pinky
    protected SuperPacmanPlayer playerMemory;
    protected boolean seePlayer = false;

    private boolean stateTransition = false;
    public boolean getStateTransition(){
        return stateTransition;
    }

    public void setStateTransition(boolean stateTransition) {
        this.stateTransition = stateTransition;
    }

    private DiscreteCoordinates targetPos;
    private Queue<Orientation> path;
    private Path graphicPath;
    private LinkedList<Orientation> pathList = new LinkedList<Orientation>();


    public IntelligentGhost(Area area, DiscreteCoordinates coordinates) {
        super(area, coordinates);
        handler2 = new SuperPacmanIntelligentGhostHandler();
    }

    /**
     * Updates the state of the smart ghost.
     *
     * @param deltaTime elapsed time since last update, in seconds, non-negative
     */
    public void update(float deltaTime) {
        boolean wasInitiallyAfraid = getAfraid();

        // Follow the player when known
        if (!getAfraid() && playerMemory != null && isReachable(getPlayerPosition())) {
            targetPos = computeTarget();
        }

        super.update(deltaTime);
        //taking care of afraid animation

        // Recompute the target if the ghost becomes invulnerable
        if (wasInitiallyAfraid && !getAfraid()) {
            this.targetPos = computeTarget();
        }

        if(reevaluate){ // normally when afraid it reevaluate is set to true
            this.targetPos = computeTarget();

            reevaluate = false;
        }
    }



    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (graphicPath != null) {
            graphicPath.draw(canvas);    //drawing the path taken by ghost
        }
    }

    /**
     * All methods related to interactor
     */

    public List<DiscreteCoordinates> getFieldOfViewCells() {    //no vision field
        List<DiscreteCoordinates> view = new ArrayList<>();

        DiscreteCoordinates main = getCurrentMainCellCoordinates();
        for (int x = (int) (main.x - FIELD_OF_VIEW); x <= (int) (main.x + FIELD_OF_VIEW); x++) {    //cast (int) not necessary, but why not ?
            for (int y = (int) (main.y - FIELD_OF_VIEW); y <= (int) (main.y + FIELD_OF_VIEW); y++) {
                DiscreteCoordinates actual = new DiscreteCoordinates(x, y);
                view.add(actual);
            }
        }
        return view; //return the arraylist of fieldofview area
    }


    public boolean wantsCellInteraction() {
        return false;
    }

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
     * Get a random reachable cell in the current area.
     *
     * @return (DiscreteCoordinates) The coordinates of a reachable cell, or null if no suitable cell was found
     */
    protected DiscreteCoordinates getRandomReachableCellPosition() {
        return getRandomReachableCellPosition(pos -> true);
    }

    /**
     * Get a random reachable cell in the current area, satisfying a given condition.
     *
     * @param condition (Predicate<DiscreteCoordinates>) A condition that must be met by the coordinates
     * @return (DiscreteCoordinates) The coordinates of a reachable cell, or null if no suitable cell was found
     */
    protected DiscreteCoordinates getRandomReachableCellPosition(Predicate<DiscreteCoordinates> condition) {
        SuperPacmanArea area = (SuperPacmanArea)getOwnerArea();
        return area.getRandomCellPosition(pos -> condition.test(pos) && isReachable(pos), Integer.MAX_VALUE);
    }

    /**
     * Returns the shortest path between two targets
     *
     * @param targetStart The start position of the path
     * @param targetEnd   The end position of the path
     * @return The directions to follow. Can be null if the path doesn’t exist.
     */
    protected Queue<Orientation> createShortestPath(DiscreteCoordinates targetStart, DiscreteCoordinates targetEnd) {
        return ((SuperPacmanArea) getOwnerArea()).shortestPath(targetStart, targetEnd);
    }

    /**
     * Know if a given cell is reachable from the current ghost position
     *
     * @param target (DiscreteCoordinates) The target cell
     * @return true if the cell is reachable; false otherwise
     */
    protected boolean isReachable(DiscreteCoordinates target) {
        return createShortestPath(getCurrentMainCellCoordinates(), target) != null;
    }



    /**
     * Computes the next orientation of the ghost, depending on its current target.
     */

    protected Orientation getNextOrientation() {
        // If no target is currently chosen, try to generate a new one.
        if (targetPos == null) {
            this.targetPos = computeTarget();

            // Still no target? Pass this turn.
            if (this.targetPos == null) {
                return null;
            }
        }

        // Find a path to the target.
        // Note that we chose to reevaluate the path to the target at each step, to avoid having issues if the path
        // becomes unusable during its lifetime (e.g. if there is a gate that closes in the middle of the path).
        this.path = createShortestPath(getCurrentMainCellCoordinates(), this.targetPos);

        // If the path doesn’t exist, we need a new target.
        // This happens when the target is unreachable or it was already reached.
        // We don’t loop until a target is found here to avoid infinite loops.
        if (this.path == null) {
            this.targetPos = computeTarget();
            this.graphicPath = null;
            return null;
        }

        Orientation firstStep = path.peek();

        // Display the path during debug
        if (DRAW_GRAPHIC_PATH_DEBUG) {
            graphicPath = new Path(getPosition(), path);
        }

        return firstStep;
    }

    /**
     * This method is called when the ghost has nowhere to go and a new target should be generated
     *
     * @return (DiscreteCoordinates) The new target to follow.
     */
    protected abstract DiscreteCoordinates computeTarget();


    /**
     * Returns the position where the player is memorized by the ghost.
     *
     * @return (DiscreteCoordinates) The position of the player, can be null if the player is not memorized
     */
    protected DiscreteCoordinates getPlayerPosition() {
        if (playerMemory == null) {
            return null;
        }
        return playerMemory.getCurrentCells().get(0);
    }

    public DiscreteCoordinates getTarget() {
        return targetPos;
    }

    public void setTarget(DiscreteCoordinates target) {
        this.targetPos = target;
    }


    /**
     reevaluate
     true : - taking a new target position
        everyone is afraid
        see player for the first time / or after forgetting the player
        destination is reached

        take select path accordingly, if unexistant / null

                                        take basic target
     reevaluate = false

     else :
     follow the initial path
     if no displacement, reevaluate = true

      */


    /**
     *  backToRefuge, also set the memory to null and send a reevaluate signal (for the targetPosition)
     */
    @Override
    public void backToRefuge() {
        this.path = null;
        this.graphicPath = null;

        playerMemory = null;
        seePlayer = false;

        getOwnerArea().leaveAreaCells(this, getEnteredCells());
        abortCurrentMove();
        resetMotion();
        setCurrentPosition(this.getRefuge().toVector());
        getOwnerArea().enterAreaCells(this, getCurrentCells());


        this.targetPos = computeTarget();
    }

    public void setReevaluate(boolean c) {
        if(c) {
            reevaluate = true;
        }else {
            reevaluate = false;
        }
    }

    protected class SuperPacmanIntelligentGhostHandler implements SuperPacmanInteractionVisitor{
        @Override
        public void interactWith(SuperPacmanPlayer player) {
            playerMemory = player; //when in field of vision, memorise player
            seePlayer = true;
            if(!getAfraid()){
                setReevaluate(true);    //chase only if not afraid, otherwise, stick to the plan lol
            }
        }
    }
}
