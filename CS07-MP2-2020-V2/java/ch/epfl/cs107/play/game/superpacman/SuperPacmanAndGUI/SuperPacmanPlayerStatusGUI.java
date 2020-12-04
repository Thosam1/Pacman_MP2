package ch.epfl.cs107.play.game.superpacman.SuperPacmanAndGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class SuperPacmanPlayerStatusGUI extends Graphics{
	
	SuperPacmanPlayerStatusGUI(Canvas canvas, int m, float n,String nombreDePoints, float DEPTH){
	float width = canvas.getScaledWidth();
	float height = canvas.getScaledHeight();
	Vector anchor = canvas.getTransform().getOrigin().sub(new Vector(width/2, height/2));
	ImageGraphics life = new
			ImageGraphics(ResourcePath.getSprite("superpacman/lifeDisplay"),
			1.f, 1.f, new RegionOfInterest(m, 0, 64, 64),
			anchor.add(new Vector(n, height - 1.375f)), 1, DEPTH);
			life.draw(canvas);
	TextGraphics score = new TextGraphics(nombreDePoints, 1.f, Color.YELLOW, Color.BLACK, 2.f, true, true, new Vector(width/2, 0.f)); 
	score.draw(canvas);

	// est ce qu il faut créer une méthode draw/différente ou on peut tout instancier dans le corps du constructeur
	//je ne comprends pas comment faire pour que le pacman soit gris ou jaune
	//comment faire pour faire en sorte que cette classe soit seulement accessible a la classe SuperPacman ou a ses sous classes
	//Ca ne fonctionne pas en passant la classe en protected
	}
	/*
	public void draw(Canvas canvas) {//il faut passer m et n en paramètres?
		for (int i=0; i<5; i++) {
			for(int j=0; j<hp; ++j){
				ImageGraphics life = new
						ImageGraphics(ResourcePath.getSprite("superpacman/lifeDisplay"),
						1.f, 1.f, new RegionOfInterest(0, 0, 64, 64),
						anchor.add(new Vector(0,5+i*10, height - 1.375f)), 1, DEPTH);
						life.draw(canvas);
			}
			for(int w=hp; w<5; w++) {
				ImageGraphics life = new
						ImageGraphics(ResourcePath.getSprite("superpacman/lifeDisplay"),
						1.f, 1.f, new RegionOfInterest(64, 0, 64, 64),
						anchor.add(new Vector(0,5+i*10, height - 1.375f)), 1, DEPTH);
						life.draw(canvas);
			}
		TextGraphics score = new TextGraphics(nombreDePoints, 1.f, Color.YELLOW, Color.BLACK, 2.f, true, true, new Vector(width/2, 0.f)); 
		score.draw(canvas);

		}
	}//est ce qu il faut créer une instance de cette classe dans SuperPacmanPlayer pour pouvoir appeler draw
	*/
	
	@Override
	public void clearRect(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clipRect(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void copyArea(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Graphics create() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawArc(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean drawImage(Image arg0, int arg1, int arg2, ImageObserver arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean drawImage(Image arg0, int arg1, int arg2, Color arg3, ImageObserver arg4) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean drawImage(Image arg0, int arg1, int arg2, int arg3, int arg4, ImageObserver arg5) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean drawImage(Image arg0, int arg1, int arg2, int arg3, int arg4, Color arg5, ImageObserver arg6) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean drawImage(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8,
			ImageObserver arg9) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean drawImage(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8,
			Color arg9, ImageObserver arg10) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void drawLine(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawOval(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawPolygon(int[] arg0, int[] arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawPolyline(int[] arg0, int[] arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawRoundRect(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawString(String arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawString(AttributedCharacterIterator arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fillArc(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fillOval(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fillPolygon(int[] arg0, int[] arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fillRect(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fillRoundRect(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Shape getClip() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getClipBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Font getFont() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FontMetrics getFontMetrics(Font arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setClip(Shape arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setClip(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setColor(Color arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFont(Font arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPaintMode() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setXORMode(Color arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void translate(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

}
