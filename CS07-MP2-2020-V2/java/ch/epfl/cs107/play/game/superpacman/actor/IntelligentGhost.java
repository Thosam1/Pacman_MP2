package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Path;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.*;

public class IntelligentGhost extends Ghost {
    private SuperPacmanArea area;
    DiscreteCoordinates targetPos = null;
    Queue<Orientation> path;
    Path graphicPath = null;
    LinkedList<Orientation> pathList;
    protected int SPEED_AFRAID = 8; //faster when afraid for inky and pinky
    protected String nameSprite;


    public IntelligentGhost(Area area, DiscreteCoordinates coordinates) {
        super(area, coordinates);
        this.area = (SuperPacmanArea) area;

    }

    public void update(float deltaTime) { // ?necessary?
        super.update(deltaTime); //taking care of afraid animation
        //deplacement(getNextOrientation(), SPEED, SPEED_AFRAID);

    }



    @SuppressWarnings({ "null", "unused" })	//???
    private DiscreteCoordinates randomEscapeCoordinates(DiscreteCoordinates anchor, int maxRange) {
        List<DiscreteCoordinates> possibleCases = new LinkedList<>();
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
        int upperBound =  possibleCases.size();
        Random rand = new Random();
        int randomIndex = rand.nextInt(upperBound);

        return possibleCases.get(randomIndex);
    }




    protected Orientation getNextOrientation(DiscreteCoordinates from, DiscreteCoordinates fromNot,  int maxWhenScared, int maxWhenNotScared) {	//from is for when the ghost is afraid - refuge or player

        if(this.getCurrentMainCellCoordinates() == targetPos) {     //|| isDisplacementOccurs() == false
            setReevaluate(true);
        }

        if(reevaluate == true) {	//then check for the new path
            if(AFRAID == true) {
                    targetPos = randomEscapeCoordinates(from, maxWhenScared);
                }else{
                    if(seePlayer == true) {	//move towards player
                        targetPos = playerMemory.getPlayerPosition(); //new DiscreteCoordinates((int) playerMemory.getPosition().x, (int) playerMemory.getPosition().y)
                    }else {	//default
                        targetPos = randomEscapeCoordinates(fromNot, maxWhenNotScared);
                    }
            }
            setReevaluate(false);
        }

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

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(graphicPath != null){
            graphicPath.draw(canvas);	//drawing the path taken by ghost
        }
    }

    /*private class SuperPacmanIntelligentGhostHandler extends SuperPacmanGhostHandler {

    }*/
}
