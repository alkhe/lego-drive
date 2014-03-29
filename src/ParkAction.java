

public class ParkAction extends Action {
	private int parkSteps,direction;
	private int forwardAmount = 600;
	private int stepsTaken = 0,lastIncident = 0;
	private boolean isSlow,isRound;
	
	public ParkAction(Driver d, int dir, int t) {
		super(d, Action.LEFT_PARK_ACTION);
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
					|| leftColor==ColorIdentifier.BLUE) {
				System.out.println("CL");
				nextAction = new CorrectionAction(driver,this,lastIncident,-1,isRound);
				lastIncident = 0;
			}
			else if (rightColor==ColorIdentifier.WHITE
					|| rightColor==ColorIdentifier.YELLOW
					|| rightColor==ColorIdentifier.GRAY
					|| rightColor==ColorIdentifier.BLUE) {
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
			rotate = forwardAmount;
			leftMotor = 1;
			rightMotor = 1;
			step++;
		}
		else if (step==3) {
			System.out.println("park");
			pivot = false;
			try {
				Thread.sleep(5500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			step++;
		}
		else if (step==4) {
			pivot = false;
			rotate = -forwardAmount;
			leftMotor = 1;
			rightMotor = 1;
			step++;
		}
		else if (step==5) {
			pivot = true;
			rotate = (direction<0) ? -90 : 90;
			leftMotor = 1;
			rightMotor = 1;
			step++;
		}
		else {
			driver.notifyPathCompletion();
		}
	}

}
