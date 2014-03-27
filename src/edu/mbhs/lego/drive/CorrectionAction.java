package edu.mbhs.lego.drive;

public class CorrectionAction extends Action {
	private Action action;
	private int lastIncident = 0;
	private int correctionSteps = 0;
	private int direction = 0;

	public CorrectionAction(Driver d, Action a, int li, int dir) {
		super(d, (dir < 0) ? Action.LEFT_CORRECTION_ACTION : Action.RIGHT_CORRECTION_ACTION);
		action = a;
		lastIncident = li;
		correctionSteps = (int) (50 / Math.sqrt(li / 500.0)); // HARDCODE
		direction = dir;
	}

	public void act(int leftColor, int rightColor) {
		if (step < correctionSteps) {
			throttle = 0.8f;
			trim = -0.2f * direction;
		}
		else if (step<correctionSteps * 3 / 2) {
			throttle = 0.8f;
			trim = 0.2f * direction;
		}
		else {
			nextAction = action;
		}
	}
}
