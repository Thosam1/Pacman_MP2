package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AutomaticallyCollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Key extends AutomaticallyCollectableAreaEntity{
	private boolean ramasséOuPas;
	private Sprite sprite;
	
	public Key(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		ramasséOuPas = false;
		sprite = new Sprite("superpacman/key", 1.f, 1.f,this);
	}
	
	public void collect() {
		super.collect();
		ramasséOuPas = true;
	}
	public boolean isOn() {
		return ramasséOuPas;
	}
	public void update(float deltaTime) {
		
	}
	public void draw(Canvas canvas) {
		sprite.draw(canvas);
	}
}
