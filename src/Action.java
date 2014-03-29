


public abstract class Action {
	public static final int
			NO_ACTION = -1,
			REST_ACTION = 0,
			DRIVE_ACTION = 1,
			
			LEFT_CORRECTION_ACTION = 10,
			RIGHT_CORRECTION_ACTION = 11,
			
			LEFT_TURN_ACTION = 20,
			RIGHT_TURN_ACTION = 21,
			FORWARD_TURN_ACTION = 22,
			
			LEFT_PARK_ACTION = 30,
			RIGHT_PARK_ACTION = 31,
			LEFT_UNPARK_ACTION = 40,
			RIGHT_UNPARK_ACTION = 41,
			
			VICTORY_LAP_ACTION = 9001
		;
	
	public Driver driver;
	public int
			step = 0,
			type = NO_ACTION,
			rotate = 0
		;
	public float
			leftMotor = 0,
			rightMotor = 0,
			lerp = 0
		;
	public boolean pivot = false;
	public Action
			nextAction = this
		;
	
	public Action(Driver d,int t) {
		System.out.println(d.getCurrentPath() + " " + t);
		this.driver = d;
		this.type = t;
	}
	
	public void performAction(int leftColor, int rightColor) {
		leftMotor = 0;
		rightMotor = 0;
		rotate = 0;
		pivot = false;
		
		act(leftColor,rightColor);
	}
	
	public abstract void act(int leftColor, int rightColor);
}
