package ch.epfl.cs107.play.game.tutos;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.Cell;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;

public class Tuto2Behavior extends AreaBehavior{

    public Tuto2Behavior(Window window, String name) {
    	super(window,name);
  
        for (int x=0; x<getWidth(); x++) {
        	for (int y=0; y<getHeight(); y++) {
        		Tuto2CellType cellType = Tuto2CellType.toType(getRGB(getHeight() -1-y, x));
        		Cell cell = new Tuto2Cell(x,y,cellType);
        		setCell(x,y,cell);
        	}}}
    
    public enum Tuto2CellType {
    	NULL(0, false),
    	WALL(-16777216, false), // #000000 RGB code of black
    	IMPASSABLE(-8750470, false), // #7A7A7A , RGB color of gray
    	INTERACT(-256, true), // #FFFF00 , RGB color of yellow
    	DOOR(-195580, true), // #FD0404 , RGB color of red
    	WALKABLE(-1, true),; // #FFFFFF , RGB color of white
    	
    	final int type;
    	final boolean isWalkable;
    	
    	Tuto2CellType(int type , boolean isWalkable){
    	this.type = type;
    	this.isWalkable = isWalkable;
    	}
    	public static Tuto2CellType toType(int type) {
    		switch(type) {
    		case -16777216:
    			return WALL;
    		case -8750470:
    			return IMPASSABLE;
    		case -256:
    			return INTERACT;
    		case -195580:
    			return DOOR;
    		case -1:
    			return WALKABLE;
    		default:
    			return NULL;
    		}
    			
    	}
    	}


}
