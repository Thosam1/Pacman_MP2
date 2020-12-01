package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.Cell;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.actor.Wall;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class SuperPacmanBehavior extends AreaBehavior {
	
	public SuperPacmanBehavior(Window window, String name) {	//constructeur du behavior
		super(window,name);
		  
        for (int x=0; x<getWidth(); x++) {
        	for (int y=0; y<getHeight(); y++) {
        		SuperPacmanCellType cellType = SuperPacmanCellType.toType(getRGB(getHeight() -1-y, x));	//donner un type au "Cells"
        		SuperPacmanCell cell = new SuperPacmanCell(x,y, cellType);
        		setCell(x,y,cell);}
        	}
	}

	protected void registerActors(Area area) {	//Si les types des cells sont des "WALL", alors enregistrer ces "CELL" en tant qu'ACTOR
		 for (int x=0; x<getWidth(); x++) {
	        	for (int y=0; y<getHeight(); y++) {
	        		SuperPacmanCellType cellType= getCellType(x,y);
	        		if(cellType == SuperPacmanCellType.WALL) {
	        			area.registerActor(new Wall(area, new DiscreteCoordinates(x,y), getNeighbours(x,y))); //Constructeur d'un WALL : 1) aire d'appartenance, 2) coordonnés du mur, 3) tableau 3x3 de booleans 
	        		}
	        	}}
	}
	private SuperPacmanCellType getCellType(int x, int y) {
		return ((SuperPacmanCell)getCell(x,y)).type;
	}
	
	public boolean[][] getNeighbours(int x, int y) {	//x, y coordinates of main cell / center
		boolean[] [] neighbourhood = new boolean[3] [3];
		    for(int i = -1; i <= 1; ++i) {
		    	for(int j = -1; j <= 1; ++j) {											// +1 |   | 			//      |   | 1,1	//addedByMe, an explanation maybe ?
		    		if ((x+i<getWidth())&&(x+i>-1)&&(y+j<getHeight())&&(y+j>-1)) {		//  0 |   | 			//      |   | 
		    			if (getCellType(x+i,y+j)==SuperPacmanCellType.WALL) {			// -1 | 0 | +1			//  0,0 |   |
		    				neighbourhood[i+1][j+1] = true;
		    			}
		    		}
		       }
		    }
		    return neighbourhood;
		    }
	
		public enum SuperPacmanCellType {			// contains all possible colors and method to get the type of a cell
			NONE(0), // never used as real content
			WALL ( -16777216), //black
			FREE_WITH_DIAMOND(-1), //white
			FREE_WITH_BLINKY (-65536), //red
			FREE_WITH_PINKY ( -157237), //pink
			FREE_WITH_INKY ( -16724737), //cyan
			FREE_WITH_CHERRY (-36752), //light red
			FREE_WITH_BONUS ( -16478723), //light blue
			FREE_EMPTY ( -6118750); // sort of gray
	    	
	    	final int type;
	    	
	    	
	    	SuperPacmanCellType(int type){	
	    		this.type = type;
	    	}
	    	
	    	public static SuperPacmanCellType toType(int type) {	// function - given a rgb value (int), return the type of CELL
	    		switch(type) {
	    		case -16777216:
	    			return WALL;
	    		case -1:
	    			return FREE_WITH_DIAMOND;
	    		case -65536:
	    			return FREE_WITH_BLINKY;
	    		case -157237: 				
	    			return FREE_WITH_PINKY;
	    		case -16724737:
	    			return FREE_WITH_INKY;  
	    		case -36752:
	    			return FREE_WITH_CHERRY; 
	    		case -16478723:
	    			return FREE_WITH_BONUS; 
	    		case -6118750:
	    			return FREE_EMPTY;
	    		default:
	    			return NONE;
	    		}
	    			
	    	}
	    }

	

public class SuperPacmanCell extends Cell{	//classe imbriquée
	private SuperPacmanCellType type;
	public SuperPacmanCell(int x, int y,SuperPacmanCellType type ) {	//constructeur
		super(x, y);
		this.type=type;
	}

//	public boolean canEnter() {	//addedByMe (j'ai mis en commentaire)
//		if (this.takeCellSpace() == true) {//comment je connais l instance de l acteur traversable ou non traversable)
//			return true;}
//		else {return false;}
//	}
	
//	@Override
//	protected boolean canEnter(Interactable entity) {		//addedByMe
//		if(this.type == SuperPacmanCellType.WALL && entity.takeCellSpace()) {	//if interactor takes the whole cell, returns false
//			return false;	
//		}else {
//			return false; //only if Cell contains traversable actors
//		}
//		
//	}
	
	@Override
	protected boolean canEnter(Interactable entity) {		//addedByMe
		if(this.takeCellSpace() == true) {	//if the cell is non-traversable (assuming only walls are non traversable)
			return false;	
		}else {
			return true; //only if Cell is traversabée
		}
		
	}

	@Override
	public boolean isCellInteractable() {
		return true;
	}

	@Override
	public boolean isViewInteractable() {
		return true;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean canLeave(Interactable entity) {
		return true;
	}
}
}


