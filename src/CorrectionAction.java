

public class CorrectionAction extends Action {
	private Action action;
	private int
			lastIncident,
			correctionSteps,
			direction,
			forwardAmount = 120
		;
	final float
			turnAmount = 0.2f // HARDCODE
		;
	final double CORRECTION_FACTOR = 1.0;
	
	public CorrectionAction(Driver d,Action a,int li,int dir,boolean round) {
		super(d,(dir<0) ? Action.LEFT_CORRECTION_ACTION : Action.RIGHT_CORRECTION_ACTION);
		action = a;
		lastIncident = (li<1) ? 1 : li;
		//correctionSteps = (int) (50/Math.sqrt(li/500.0));
		correctionSteps = 60;// + (int) (Math.asin(CORRECTION_FACTOR/lastIncident)/Math.PI*180);
		direction = dir;
	}
	
	public void act(int leftColor,int rightColor) {
		if (step==0) {
			pivot = true;
			leftMotor = 1;
			rightMotor = 1;
			rotate = -direction*correctionSteps;
			step++;
		}
		else if (step==1) {
			pivot = false;
			leftMotor = 1;
			rightMotor = 1;
			rotate = forwardAmount;
			step++;
		}
		else if (step==2) {
			pivot = true;
			leftMotor = 1;
			rightMotor = 1;
			rotate = direction*45;
			step++;
		}
		else {
			System.out.println("EC");
			nextAction = action;
		}
	}
}
