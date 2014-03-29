

public class FinishAction extends Action {
	private int parkSteps, direction;
	private int forwardAmount = Driver.MOTOR_PARK;
	private int stepsTaken = 0, lastIncident = 0;
	private boolean isSlow, isRound;
	
	public FinishAction(Driver d, int dir, int t) {
		super(d, Action.RIGHT_PARK_ACTION);
		direction = dir;
		parkSteps = t;
		isSlow = driver.getSlow(driver.getCurrentPath());
	}
	
	public void act(int leftColor, int rightColor) {
		if (step==0) {
			System.out.println(stepsTaken);
			if (leftColor==ColorIdentifier.BLACK && rightColor==ColorIdentifier.BLACK) {
				leftMotor = rightMotor = (isSlow) ? 0.5f : 1f;
				nextAction = this;
			}
			else if (leftColor==ColorIdentifier.WHITE
					|| leftColor==ColorIdentifier.YELLOW
					|| leftColor==ColorIdentifier.GRAY
					|| leftColor==ColorIdentifier.BLUE
					|| leftColor==ColorIdentifier.RED) {
				System.out.println("CL");
				nextAction = new CorrectionAction(driver,this,lastIncident,-1,isRound);
				lastIncident = 0;
			}
			else if (rightColor==ColorIdentifier.WHITE
					|| rightColor==ColorIdentifier.YELLOW
					|| rightColor==ColorIdentifier.GRAY
					|| rightColor==ColorIdentifier.BLUE
					|| rightColor==ColorIdentifier.RED) {
				System.out.println("CR");
				nextAction = new CorrectionAction(driver,this,lastIncident,1,isRound);
				lastIncident = 0;
			}
			
			lastIncident++;
			stepsTaken++;
			
			if (stepsTaken>parkSteps) {
				pivot = false;
				rotate = 0;
				leftMotor = 0;
				rightMotor = 0;
				step++;
			}
		}
		else if (step==1) {
			pivot = true;
			rotate = (direction<0) ? 90 : -90;
			leftMotor = 1;
			rightMotor = 1;
			step++;
		}
		else if (step==2) {
			pivot = false;
			rotate = 580;
			leftMotor = 1;
			rightMotor = 1;
			step++;
		}
		else if (step==3) {
			pivot = true;
			rotate = 180;
			leftMotor = 1;
			rightMotor = 1;
			step++;
		}
		else if (step==4) {
			System.out.println("park");
			pivot = false;
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			step++;
		}
		else {
			driver.notifyPathCompletion();
		}
	}

}
