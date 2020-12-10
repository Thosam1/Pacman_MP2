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
	private boolean ramasseOuPas;
	private Sprite sprite;
	
	public Key(Area area, DiscreteCoordinates position) {
		super(area, Orientation.RIGHT, position);
		ramasseOuPas = false;
		sprite = new Sprite("superpacman/key", 1.f, 1.f,this);
	}
	
	public void collect() {
		super.collect();
		ramasseOuPas = true;
	}
	@Override
	public boolean isOn() {
		return ramasseOuPas;
	}
	public void update(float deltaTime) {
	}
	public void draw(Canvas canvas) {
		sprite.draw(canvas);
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

	public void acceptInteraction(AreaInteractionVisitor v) {
        ((SuperPacmanInteractionVisitor)v).interactWith(this);
    }
}
