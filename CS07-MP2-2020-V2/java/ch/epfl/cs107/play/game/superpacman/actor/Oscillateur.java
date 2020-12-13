package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.signal.logic.Logic;

public class Oscillateur implements Logic {
	private int Timer;
	private int frameInterval;
	private boolean signalOn;
	
	public Oscillateur(int frameInterval, boolean initialSignal) {
		Timer = 0;
		this.frameInterval = frameInterval;
		signalOn = initialSignal;
		}
	
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
