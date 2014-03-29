

public class DriveAction extends Action {
	private final float
			slowSpeed = 0.5f,
			fullSpeed = 1f
		;
	private int
			lastIncident = 0,
			previousLeft = 0,
			previousRight = 0,
			path = 0, // Current path
			turn = 0, // Turn direction
			isRound = -1,
			dist
		;
	private boolean isSlow;
	
	public DriveAction(Driver d,int t) {
		super(d,Action.DRIVE_ACTION);
		path = driver.getCurrentPath();
		turn = driver.getTurn(path);
		isSlow = driver.getSlow(path);
		isRound = driver.getRound(path);
		dist = t;
		System.out.println("Round " + isRound);
	}
	
	public void act(int leftColor,int rightColor) {
		if (leftColor==ColorIdentifier.BLACK && rightColor==ColorIdentifier.BLACK) {
			/*
			if (isRound==0 || isRound==1) {
				leftMotor = 0.283f;
				rightMotor = 0.5f;
			}
			else if (isRound>=0) {
				leftMotor = 0.5f;
				rightMotor = 0.283f;
			}
			else {
			}
			*/
			leftMotor = rightMotor = (isSlow) ? slowSpeed : fullSpeed;
			nextAction = this;
		}
		else if ((leftColor==ColorIdentifier.RED || rightColor==ColorIdentifier.RED)) {
			System.out.println("Turn");
			nextAction = new TurnAction(driver,turn);
		}
		else if (leftColor==ColorIdentifier.WHITE
				|| leftColor==ColorIdentifier.YELLOW
				|| leftColor==ColorIdentifier.GRAY
				|| leftColor==ColorIdentifier.BLUE
				|| leftColor==ColorIdentifier.RED) {
			System.out.println("CL " + leftColor);
			nextAction = new CorrectionAction(driver,this,lastIncident,-1,isRound>=0);
			lastIncident = 0;
		}
		else if (rightColor==ColorIdentifier.WHITE
				|| rightColor==ColorIdentifier.YELLOW
				|| rightColor==ColorIdentifier.GRAY
				|| rightColor==ColorIdentifier.BLUE
				|| rightColor==ColorIdentifier.RED) {
			System.out.println("CR " + rightColor);
			nextAction = new CorrectionAction(driver,this,lastIncident,1,isRound>=0);
			lastIncident = 0;
		}
		
		lastIncident++;
		step++;
	}
}
