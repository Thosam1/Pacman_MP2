package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

import java.util.ArrayList;
import java.util.List;
/*
public class Sorcerer extends Spirit implements Interactor {


    private SuperPacmanArea area;

    private final SuperPacmanSpiritSorcererHandler handler2;
    private final int FIELD_OF_VIEW = 20;

    public Sorcerer(Area area, DiscreteCoordinates coordinates){
        super(area, coordinates);
        this.area = (SuperPacmanArea) area;
        handler2 = new Spirit.SuperPacmanSpiritSorcererHandler();
    }

    /**
     * All methods related to interactor
     */

    /*
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


}
*/