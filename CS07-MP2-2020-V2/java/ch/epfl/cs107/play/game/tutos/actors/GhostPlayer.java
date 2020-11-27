package ch.epfl.cs107.play.game.tutos.actors;

import java.awt.Button;
import java.awt.Color;
import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class GhostPlayer extends MovableAreaEntity{
	private float hp;
	private TextGraphics hpText;
	private Sprite sprite;
	private final static int ANIMATION_DURATION = 8;
	Area area;
	public GhostPlayer(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		this.area = area;
		sprite = new Sprite("ghost.2", 1, 1.f, this);
		hp = 10;
		
		hpText = new TextGraphics(Integer.toString((int)hp), 0.4f, Color.BLUE);//hp représente le niveau d'énergie
		hpText.setParent(this);
		
		this.hpText.setAnchor(new Vector(-0.3f, 0.1f));

	}
	public void update(float deltaTime) {
		//Keyboard keyboard = getWindow().getKeyboard() ;
		//Button key = keyboard.get(Keyboard.UP) ;
		Keyboard keyboard = area.getKeyboard();
		if (keyboard.get(Keyboard.LEFT).isDown()) {
			if (getOrientation().equals(Orientation.LEFT)) {
				move(ANIMATION_DURATION);
			}
			else {
				orientate(Orientation.LEFT);}}
		
		if (keyboard.get(Keyboard.RIGHT).isDown()) {
			if (getOrientation().equals(Orientation.RIGHT)) {
				move(ANIMATION_DURATION);
			}
			else {
				orientate(Orientation.RIGHT);}}
		
		if (keyboard.get(Keyboard.UP).isDown()) {
			if (getOrientation().equals(Orientation.UP)) {
				move(ANIMATION_DURATION);
			}
			else {
				orientate(Orientation.RIGHT);}}
		
		if (keyboard.get(Keyboard.DOWN).isDown()) {
			if (getOrientation().equals(Orientation.DOWN)) {
				move(ANIMATION_DURATION);
		}
			else {
				orientate(Orientation.RIGHT);}}
		
		super.update(deltaTime);
		hp-=deltaTime;
		if (isWeak()){hp=0;}
		hpText.setText(Integer.toString((int)hp));
	}
	
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
				

	}

	@Override
	public boolean takeCellSpace() {
		return true;
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
		// normal que ce soit vide pour l instant
		
	}
	public void enterArea(Area area, DiscreteCoordinates position) {
		area.registerActor(this);
		setCurrentPosition(position.toVector());
		//getCurrentArea().setViewCandidate(this);
		setOwnerArea(area);
		resetMotion();
	}
	public void leaveArea(Area area, DiscreteCoordinates position) {
		area.unregisterActor(this);
		
	}

	@Override
	public void draw(Canvas canvas) {
		sprite.draw(canvas);
		hpText.draw(canvas);
		
	}
	public boolean isWeak() {
		if (hp <= 0) {
		return true;}
		else {return false;}
	}
	public void strengthen() {
		hp = 10;
	}
	public void moveDown(){
		setCurrentPosition(getPosition().add(0.f, -0.05f));//movement in x and y coordinates
	}
	public void moveUp(){
		setCurrentPosition(getPosition().add(0.f, 0.05f));
	}
	public void moveLeft(){
		setCurrentPosition(getPosition().add(-0.05f, 0.f));
	}
	public void moveRight(){
		setCurrentPosition(getPosition().add(0.05f, 0.f));
	}
}
