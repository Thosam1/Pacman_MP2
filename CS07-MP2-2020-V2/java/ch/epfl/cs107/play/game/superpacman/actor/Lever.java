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
	
	public Lever(Area area, DiscreteCoordinates position, boolean isOn) {
		super(area, Orientation.LEFT, position); //orientation doesnt matter
		this.signal = isOn;
		timer = 0;
	}
	
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
		//else timer +=1;
		}
	
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
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		return true;
	}

	@Override
	public boolean isViewInteractable() {
		return false;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((SuperPacmanInteractionVisitor)v).interactWith(this);
	}

	@Override
	public boolean isOn() {
		return signal;
	}

	@Override
	public boolean isOff() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public float getIntensity() {
		// TODO Auto-generated method stub
		return 0;
	}

}
