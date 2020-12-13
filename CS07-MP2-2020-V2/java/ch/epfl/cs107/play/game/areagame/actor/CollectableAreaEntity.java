package ch.epfl.cs107.play.game.areagame.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public abstract class CollectableAreaEntity extends AreaEntity {

	public CollectableAreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		
	}
	
	/**Cette méthode permet d'obtenir les coordonnées des instances de cette classe*/
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	/**Les CollectableAreaEntity ne prennent pas la place des cellules dans lesquels ils se trouvent*/
	@Override
	public boolean takeCellSpace() {
		return false;
	}

	/**Les CollectableAreaEntity acceptent les interactions de contact*/
	@Override
	public boolean isCellInteractable() {
		return true;
	}

	/**Les CollectableAreaEntity n'acceptent pas les interaction de vue */
	@Override
	public boolean isViewInteractable() {
		return false;
	}
}
