

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
			turn = 0 // Turn direction
		;
	private boolean isSlow,isRound;
	
	public DriveAction(Driver d) {
		super(d,Action.DRIVE_ACTION);
		path = driver.getCurrentPath();
		turn = driver.getTurn(path);
		isSlow = driver.getSlow(path);
		isRound = driver.getRound(path);
	}
	
	public void act(int leftColor,int rightColor) {
		if (leftColor==ColorIdentifier.BLACK && rightColor==ColorIdentifier.BLACK) {
			leftMotor = rightMotor = (isSlow) ? slowSpeed : fullSpeed;
			nextAction = this;
		}
		else if (leftColor==ColorIdentifier.RED || rightColor==ColorIdentifier.RED) {
			nextAction = new TurnAction(driver,turn);
		}
		else if (leftColor==ColorIdentifier.WHITE
				|| leftColor==ColorIdentifier.YELLOW
				|| leftColor==ColorIdentifier.GRAY
				|| leftColor==ColorIdentifier.BLUE) {
			System.out.println("CL " + leftColor);
			nextAction = new RestAction(driver,new CorrectionAction(driver,this,lastIncident,-1,isRound),1000);
			lastIncident = 0;
		}
		else if (rightColor==ColorIdentifier.WHITE
				|| rightColor==ColorIdentifier.YELLOW
				|| rightColor==ColorIdentifier.GRAY
				|| rightColor==ColorIdentifier.BLUE) {
			System.out.println("CR " + rightColor);
			nextAction = new RestAction(driver,new CorrectionAction(driver,this,lastIncident,1,isRound),1000);
			lastIncident = 0;
		}
		
		lastIncident++;
		step++;
	}
}
