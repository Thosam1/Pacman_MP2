package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.signal.logic.Logic;

public class Oscillateur implements Logic {
	private int Timer;
	private int frameInterval;
	private boolean signalOn;
	
	/**Le constructeur de cette classe initialise le Timer, le frameInterval et le signalOn
	 * frameInterval désigne le nombre de frames ou le signal sera true puis false
	 * initialSignal est la valeur initiale du signal
	 * Timer takes value of the parameter initialTimer
	 * */
	public Oscillateur(int frameInterval, boolean initialSignal, int initialTimer) {
		Timer = initialTimer;
		this.frameInterval = frameInterval;
		signalOn = initialSignal;
		}
	
	/**Si Timer est égale à frameInterval alors le timer est réinitialisé et le signal est changé
	 * sinon, le timer augmente de 1 et le signal ne change pas
	 * return signalOn qui est true ou false*/
		@Override
	public boolean isOn() {
		if(Timer==frameInterval) {
			Timer=0;
			if (signalOn) {
				return signalOn = false;
			}
			else return signalOn = true;
		}
		else {
			Timer += 1;
			return signalOn;
		}
	}

	/**les deux méthodes héritées suivantes ne sont pas utilisées*/
	@Override
	public boolean isOff() {
		// Only isOn matters
		return false;
	}

	@Override
	public float getIntensity() {
		// Only isOn matters
		return 0;
	}

}
