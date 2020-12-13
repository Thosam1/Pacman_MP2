package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.AutomaticallyCollectableAreaEntity;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class Key extends AutomaticallyCollectableAreaEntity implements Logic{
	private boolean ramasseOuPas; //cette valeur devient true quand le Key a été mangé
	private Sprite sprite;
	
	/**le constructeur initialise le sprite du Key
	 * la valeur initiale de ramasseOuPas est false car on veut que le signal de Key soit dabord false*/
	public Key(Area area, DiscreteCoordinates position) {
		super(area, Orientation.RIGHT, position);
		ramasseOuPas = false;
		sprite = new Sprite("superpacman/key", 1.f, 1.f,this);
	}
	
	/**la méthode collect de Key est redéfinie et appele la méthode de la super classe, car
	 * nous voulons que Key disparaisse et que sont signal devienne true quand elle est mangée
	 * cette méthode est appelée lors de l'interaction avec un superPacmanPlayer*/
	public void collect() {
		super.collect();
		ramasseOuPas = true;
	}
	
	/**cette méthode est appelée par la les instances de Gate
	 * la valeur de ramasseOuPas décidera si le Gate sera affiché ou pas*/
	@Override
	public boolean isOn() {
		return ramasseOuPas;
	}
	
	/**la méthode draw est appelée sur sprite à chaque frame tant que Key n'a pas été mangé
	 * par le player*/
	public void draw(Canvas canvas) {
		sprite.draw(canvas);
	}

	/** permet les interactions avec les interactors*/
	public void acceptInteraction(AreaInteractionVisitor v) {
        ((SuperPacmanInteractionVisitor)v).interactWith(this);
    }
	
	/**Les deux méthodes suivantes héritées de Logic ne nous sont pas utile*/
	@Override
	public boolean isOff() {
		return false;
	}

	@Override
	public float getIntensity() {
		return 0;
	}
}
