package ch.epfl.cs107.play.game.tutos.actors;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;


public class SimpleGhost extends Entity {
	private float hp;
	private TextGraphics hpText;
	private Sprite sprite;
	
	
	public SimpleGhost(Vector position, String SpriteName) {
		super(position);
		sprite = new Sprite(SpriteName, 1, 1.f, this);
		hp = 10;
		
		hpText = new TextGraphics(Integer.toString((int)hp), 0.4f, Color.BLUE);//hp représente le niveau d'énergie
		hpText.setParent(this);
		
		this.hpText.setAnchor(new Vector(-0.3f, 0.1f));
	}
	
	public boolean isWeak() {
		if (hp <= 0) {
		return true;}
		else {return false;}
	}
	public void strengthen() {
		hp = 10;
	}
	@Override
	public void update(float deltaTime) {
		hp-=deltaTime;
		if (isWeak()){hp=0;}
		hpText.setText(Integer.toString((int)hp));
	}

	@Override
	public void draw(Canvas canvas) {
		sprite.draw(canvas);
		hpText.draw(canvas);
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
