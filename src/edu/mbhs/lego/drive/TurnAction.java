package edu.mbhs.lego.drive;

public class TurnAction extends Action {
	final int turnSteps = 170; // HARDCODE
	private int direction;

	public TurnAction(Driver d, int dir) {
		super(d, (dir == 0) ? Action.FORWARD_TURN_ACTION : (dir < 0) ? Action.LEFT_TURN_ACTION : Action.RIGHT_TURN_ACTION);
		direction = dir;
	}

	public void act(int leftColor,int rightColor) {
		if (type == Action.FORWARD_TURN_ACTION) {

		}
		else if (type == Action.LEFT_TURN_ACTION) {

		}
	}
}
