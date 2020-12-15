package ch.epfl.cs107.play.game.superpacman.area;

import java.util.ArrayList;
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
import ch.epfl.cs107.play.game.superpacman.actor.*;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class SuperPacmanBehavior extends AreaBehavior {
	
	//private List<Ghost> currentGhosts = new ArrayList<>();
	private List<Blinky> currentBlinkyGhosts = new ArrayList<>();
	private List<IntelligentGhost> currentSmartGhosts = new ArrayList<>();
	private final AreaGraph graph;
	protected AreaGraph getGraph(){
		return graph;
	}
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
	        			area.registerActor(new Wall(area, new DiscreteCoordinates(x,y), getNeighbours(x,y))); //Constructeur d'un WALL : 1) aire d'appartenance, 2) coordonnées du mur, 3) tableau 3x3 de booleans 
	        		}
	        		if(cellType == SuperPacmanCellType.FREE_WITH_BONUS) {
	        			area.registerActor(new Bonus(area, Orientation.UP, new DiscreteCoordinates(x,y))); //Constructeur d'un Bonus) aire d'appartenance, 2) orientation, 3) coordonnéees
	        		}
	        		if(cellType == SuperPacmanCellType.FREE_WITH_CHERRY) {
	        			area.registerActor(new Cherry(area, Orientation.UP, new DiscreteCoordinates(x,y))); //Constructeur d'un Cherry : 1) aire d'appartenance, 2) orientation, 3) coordonnées
	        		}
	        		if(cellType == SuperPacmanCellType.FREE_WITH_DIAMOND) {
  						area.registerActor(new Diamond(area, Orientation.UP, new DiscreteCoordinates(x,y))); //Constructeur d'un Diamond : 1) aire d'appartenance, 2) orientation 3) coordonnées
  						area.numberOfDiamonds +=1;
	        		}  
	        		if(cellType == SuperPacmanCellType.FREE_WITH_BLINKY) {
	        			Blinky blinky;
	        			IceSinged iceSinged;
	        			if(x < getWidth() / 2){	//first half - left
							blinky = new Blinky(area, new DiscreteCoordinates(x,y), true);
							//iceSinged = new IceSinged(area, new DiscreteCoordinates(12, 15));
						}else{
							blinky = new Blinky(area, new DiscreteCoordinates(x,y), false);
							//iceSinged = new IceSinged(area, new DiscreteCoordinates(17, 11));
						}

	        			area.registerActor(blinky);	
	        			currentBlinkyGhosts.add(blinky);

	        			//area.registerActor(iceSinged);
	        		}

	        		if(cellType == SuperPacmanCellType.FREE_WITH_INKY) {
	        			Inky inky = new Inky(area, new DiscreteCoordinates(x,y));	 
	        			area.registerActor(inky);	
	        			currentSmartGhosts.add(inky);

	        		}
	        		if(cellType == SuperPacmanCellType.FREE_WITH_PINKY) {
						Pinky pinky = new Pinky(area, new DiscreteCoordinates(x, y));
						area.registerActor(pinky);
						currentSmartGhosts.add(pinky);
					}


	        		if(cellType != SuperPacmanCellType.WALL) { //adding nodes in graph
	        			boolean hasLeftEdge = ((x > 0) && getCellType(x-1, y) != SuperPacmanCellType.WALL);	//depends if it st
	        			boolean hasUpEdge = ((y < getHeight()-1) && getCellType(x, y+1) != SuperPacmanCellType.WALL);
	        			boolean hasRightEdge = ((x < getWidth()-1) && getCellType(x+1, y) != SuperPacmanCellType.WALL);
	        			boolean hasDownEdge = ((y > 0) && getCellType(x, y-1) != SuperPacmanCellType.WALL);     			
	        			
	        			graph.addNode(new DiscreteCoordinates(x,y), hasLeftEdge, hasUpEdge, hasRightEdge, hasDownEdge);
	        		}
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


	/**
	 *	Methods useful for ghosts
	 */
	
	protected void scareAllGhosts(boolean choose) {	//if true - scare all ghosts // if false - make ghost not scared
		if(currentBlinkyGhosts != null){
			for(int i = 0; i < currentBlinkyGhosts.size(); i++) {
				if(choose == true) {
					currentBlinkyGhosts.get(i).setAfraid(true);
				}else {
					currentBlinkyGhosts.get(i).setAfraid(false);
				}
				//System.out.println(currentDumbGhosts + "All dumb Ghosts scared -");
			}
		}
		if(currentSmartGhosts != null){
			for(int i = 0; i < currentSmartGhosts.size(); i++) {
				if(choose == true) {
					currentSmartGhosts.get(i).setAfraid(true);
					currentSmartGhosts.get(i).setReevaluate(true);	//let them reevaluate their path
				}else {
					currentSmartGhosts.get(i).setAfraid(false);
					currentSmartGhosts.get(i).setReevaluate(true);	//let them reevaluate their path
				}
				//System.out.println(currentSmartGhosts + "All smart Ghosts scared -");
			}
		}
	}

	protected void allGhostToRefuge() {	//send ALL ghosts back to their refuge
		if(currentBlinkyGhosts != null){
			for (int i = 0; i < currentBlinkyGhosts.size(); i++) {
				//System.out.println("Ghost number " + i + " " + currentGhosts.get(i) + "refuge : " + currentGhosts.get(i).refuge);
				currentBlinkyGhosts.get(i).backToRefuge();
				//System.out.println("Ghost number " + i + " " + currentGhosts.get(i) + "position : " + currentGhosts.get(i).getPosition());
			}
		}
		if(currentSmartGhosts != null){
			for (int i = 0; i < currentSmartGhosts.size(); i++) {
				//System.out.println("Ghost number " + i + " " + currentGhosts.get(i) + "refuge : " + currentGhosts.get(i).refuge);
				currentSmartGhosts.get(i).backToRefuge();	//overwritten method is called
				//System.out.println("Ghost number " + i + " " + currentGhosts.get(i) + "position : " + currentGhosts.get(i).getPosition());
			}
		}

		//System.out.println(currentGhosts + "All Ghosts back to refuge -");
	}

	/**
	 *	return the list of orientation needed to go from main to target, if it doesn't exist, it will return null
	 */
	protected Queue<Orientation> shortestPath(DiscreteCoordinates main, DiscreteCoordinates target){
		Queue<Orientation> path = graph.shortestPath(main, target);
		return path;
	}
	

}





