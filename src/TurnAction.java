

public class TurnAction extends Action {
	final int
			forwardAmount = 400
		;
	final float
			turnAmount = 0.33f // HARDCODE
		;
	private int direction;
	
	public TurnAction(Driver d,int dir) {
		super(d,(dir==0) ? Action.FORWARD_TURN_ACTION : (dir<0) ? Action.LEFT_TURN_ACTION : Action.RIGHT_TURN_ACTION);
		direction = dir;
	}

	public void act(int leftColor,int rightColor) {
		if (step==0) {
			pivot = false;
			leftMotor = 1;
			rightMotor = 1;
			rotate = forwardAmount;
			step++;
		}
		else if (step==1) {
			if (type==Action.FORWARD_TURN_ACTION) {
			}
			else if (type==Action.LEFT_TURN_ACTION) {
				pivot = true;
				leftMotor = 1;
				rightMotor = 1;
				rotate = 90;
			}
			else if (type==Action.RIGHT_TURN_ACTION) {
				pivot = true;
				leftMotor = 1;
				rightMotor = 1;
				rotate = -90;
			}
			step++;
		}
		else {
			driver.notifyPathCompletion();
		}
	}
}
