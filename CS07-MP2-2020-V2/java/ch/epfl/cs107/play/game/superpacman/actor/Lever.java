package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class Lever extends AreaEntity implements Logic{
	private boolean signal;
	private Sprite sprite;
	private int timer;
	
	/**area désigne le niveau et DiscreteCoordinates permet de connaitre la position initiale
	 * la valeur isOn permet de donner le signal initial du Lever*/
	public Lever(Area area, DiscreteCoordinates position, boolean isOn) {
		super(area, Orientation.LEFT, position); //orientation doesnt matter
		this.signal = isOn;
		timer = 0;
	}
	
	/**cette méthode permet de changer le signal si 72 frames se sont écoulé depuis le dernier
	 * changement de signal (72 frames représente environ 3 secondes)
	 * Cette condition est importante car il faut que le player ait le temps de sortir de la cellule
	 * C'est le interactWith de SuperPacmanPlayer qui appele cette méthode*/
	public void changeSignal() {
		if(timer>=72) {
			if (signal) {
				signal = false;
				timer=0;
			}
			else {
				signal = true;
				timer=0;
			}
		}
		}
	
	/**A chaque fois que cette méthode est appelée (à chaque frame) timer augmente de 1
	 * ce qui est important pour l'appel de la méthode changeSignal
	 * si le signal est true, le Lever est vers le bas sinon il est vers le haut
	 * */
	@Override
	public void draw(Canvas canvas) {
		timer+=1;
		if(signal) {
			sprite = new Sprite("LeverDown", 1.f, 1.f,this);
			sprite.draw(canvas);
		}
		else {
			sprite = new Sprite("LeverUp", 1.f, 1.f,this);
			sprite.draw(canvas);
		}
	}
	
	/**Cette méthode donne les coordonnées de la cellule dans laquelle se trouve le lever*/
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	/**Les interactors peuvent passer sur les cellules qui contiennent un Lever*/
	@Override
	public boolean takeCellSpace() {
		return false;
	}

	/**Lever accepte les interactions de contact*/
	@Override
	public boolean isCellInteractable() {
		return true;
	}

	/**Lever n'accepte pas les interactions de vue*/
	@Override
	public boolean isViewInteractable() {
		return false;
	}

	/**Permet au SuperPacmanPlayer d'avoir des interactions avec les Lever*/
	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((SuperPacmanInteractionVisitor)v).interactWith(this);
	}

	/**Cette méthode appelée par Gate retourne true ou false ce qui détermine si le gate associé
	 * sera déssiné*/
	@Override
	public boolean isOn() {
		return signal;
	}

	/**les deux méthodes héritées suivantes ne sont pas utilisées*/
	@Override
	public boolean isOff() {
		return false;
	}

	@Override
	public float getIntensity() {
		return 0;
	}

}
