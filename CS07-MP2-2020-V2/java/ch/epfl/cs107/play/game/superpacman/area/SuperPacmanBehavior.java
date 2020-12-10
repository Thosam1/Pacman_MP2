package ch.epfl.cs107.play.game.superpacman.area;

import java.util.List;
import java.util.Queue;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGraph;
import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.Cell;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.actor.Blinky;
import ch.epfl.cs107.play.game.superpacman.actor.Bonus;
import ch.epfl.cs107.play.game.superpacman.actor.Cherry;
import ch.epfl.cs107.play.game.superpacman.actor.Diamond;
import ch.epfl.cs107.play.game.superpacman.actor.Ghost;
import ch.epfl.cs107.play.game.superpacman.actor.Inky;
import ch.epfl.cs107.play.game.superpacman.actor.Pinky;
import ch.epfl.cs107.play.game.superpacman.actor.Wall;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class SuperPacmanBehavior extends AreaBehavior {
	
	private List<Ghost> currentGhosts;
	private final AreaGraph graph;
	
	public SuperPacmanBehavior(Window window, String name) {	//constructeur du behavior
		super(window,name);
		graph = new AreaGraph();
		int height = getHeight();
		int width = getWidth();
        for (int x=0; x< width; x++) {
        	for (int y=0; y<height; y++) {
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
	        			area.registerActor(new Wall(area, new DiscreteCoordinates(x,y), getNeighbours(x,y))); //Constructeur d'un WALL : 1) aire d'appartenance, 2) coordonnï¿½s du mur, 3) tableau 3x3 de booleans 
	        		}
	        		if(cellType == SuperPacmanCellType.FREE_WITH_BONUS) {
	        			area.registerActor(new Bonus(area, Orientation.UP, new DiscreteCoordinates(x,y))); //Constructeur d'un Bonus) aire d'appartenance, 2) orientation, 3) coordonnÃ©es
	        		}
	        		if(cellType == SuperPacmanCellType.FREE_WITH_CHERRY) {
	        			area.registerActor(new Cherry(area, Orientation.UP, new DiscreteCoordinates(x,y))); //Constructeur d'un Cherry : 1) aire d'appartenance, 2) orientation, 3) coordonnÃ©es
	        		}
	        		if(cellType == SuperPacmanCellType.FREE_WITH_DIAMOND) {
  						area.registerActor(new Diamond(area, Orientation.UP, new DiscreteCoordinates(x,y))); //Constructeur d'un Diamond : 1) aire d'appartenance, 2) orientation 3) coordonnÃ©es
  						area.numberOfDiamonds +=1;
	        		}  
/*	        		if(cellType == SuperPacmanCellType.FREE_WITH_BLINKY) {
	        			Blinky blinky = new Blinky(area, new DiscreteCoordinates(x,y));	 
	        			area.registerActor(blinky);	
	        			currentGhosts.add(blinky);
	        		}*/

/*	        		if(cellType == SuperPacmanCellType.FREE_WITH_INKY) {
	        			Inky inky = new Inky(area, new DiscreteCoordinates(x,y));	 
	        			area.registerActor(inky);	
	        			currentGhosts.add(inky);
	        		}*/
/*	        		if(cellType == SuperPacmanCellType.FREE_WITH_PINKY) {
        				Pinky pinky = new Pinky(area, new DiscreteCoordinates(x,y));	 
	        			area.registerActor(pinky);	
	        			currentGhosts.add(pinky);
*/	        		

/*	        		if(cellType != SuperPacmanCellType.WALL) { //adding nodes in graph
	        			boolean hasLeftEdge = ((x > 0) && getCellType(x-1, y) != SuperPacmanCellType.WALL);	//depends if it st
	        			boolean hasUpEdge = ((y < getHeight()-1) && getCellType(x, y+1) != SuperPacmanCellType.WALL);
	        			boolean hasRightEdge = ((x < getWidth()-1) && getCellType(x+1, y) != SuperPacmanCellType.WALL);
	        			boolean hasDownEdge = ((y > 0) && getCellType(x, y-1) != SuperPacmanCellType.WALL);     			
	        			
	        			graph.addNode(new DiscreteCoordinates(x,y), hasLeftEdge, hasUpEdge, hasRightEdge, hasDownEdge);
	        		}*/
	        	}
	        		    
	        }       

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
		    				neighbourhood[i+1][1-j] = true;
		    			}
		    		}//if ((x+i<getWidth())&&(x+i>-1)&&(y+j<getHeight())&&(y+j>-1)) {
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

	

	public class SuperPacmanCell extends Cell{	//classe imbriquï¿½e
		private SuperPacmanCellType type;
		public SuperPacmanCell(int x, int y,SuperPacmanCellType type ) {	//constructeur
			super(x, y);
			this.type=type;
		}
	
		@Override
		protected boolean canEnter(Interactable entity) {
			return !hasNonTraversableContent();
			}
		
			@Override
			public boolean isCellInteractable() {
				return false;
			}
		
			@Override
			public boolean isViewInteractable() {
				return false;
			}
		
			@Override
			public void acceptInteraction(AreaInteractionVisitor v) {
			}
		
			@Override
			protected boolean canLeave(Interactable entity) {
				return true;
			}
		
	}
	/**	Methods useful for ghosts
	 * 
	 * @param choose
	 */
	
	public void scareAllGhosts(boolean choose) {
		for(int i = 0; i < currentGhosts.size(); i++) {
			if(choose == true) {
				currentGhosts.get(i).setAfraid();
				currentGhosts.get(i).setReevaluate(true);
			}else {
				currentGhosts.get(i).setNotAfraid();
				currentGhosts.get(i).setAfraid();
				currentGhosts.get(i).setReevaluate(true);
			}			
		}
	}
	
	protected Queue<Orientation> shortestPath(DiscreteCoordinates main, DiscreteCoordinates target){
		Queue<Orientation> path = graph.shortestPath(main, target);
		return path;
	}
	

}





