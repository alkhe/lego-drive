package edu.mbhs.lego.drive;

public class DriveAction extends Action {
	public DriveAction(Driver d) {
		super(d, Action.DRIVE_ACTION);
	}

	private int lastIncident = 0;
	private int previousLeft = 0;
	private int previousRight = 0;

	public void act(int leftColor, int rightColor) {
		if (leftColor == ColorIdentifier.BLACK && rightColor == ColorIdentifier.BLACK) {
			throttle = 1;
			trim = 0;
		}
		else if (leftColor == ColorIdentifier.RED || rightColor == ColorIdentifier.RED) {

		}
		else if (leftColor == ColorIdentifier.WHITE
				|| leftColor == ColorIdentifier.YELLOW
				|| leftColor == ColorIdentifier.GRAY) {
			nextAction = new CorrectionAction(driver, this, lastIncident, -1);
		}
		else if (rightColor == ColorIdentifier.WHITE
				|| rightColor == ColorIdentifier.YELLOW
				|| rightColor == ColorIdentifier.GRAY) {
			nextAction = new CorrectionAction(driver, this, lastIncident, 1);
		}

		lastIncident++;
		step++;
	}
}
