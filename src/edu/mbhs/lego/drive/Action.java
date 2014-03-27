package edu.mbhs.lego.drive;

public abstract class Action {
	public static final int NO_ACTION = -1;
	public static final int REST_ACTION = 0;
	public static final int DRIVE_ACTION = 1;
	public static final int LEFT_CORRECTION_ACTION = 10;
	public static final int RIGHT_CORRECTION_ACTION = 11;
	public static final int LEFT_TURN_ACTION = 20;
	public static final int RIGHT_TURN_ACTION = 21;
	public static final int FORWARD_TURN_ACTION = 22;
	public static final int LEFT_PARK_ACTION = 30;
	public static final int RIGHT_PARK_ACTION = 31;
	public static final int LEFT_UNPARK_ACTION = 40;
	public static final int RIGHT_UNPARK_ACTION = 41;
	public static final int VICTORY_LAP_ACTION = 9001;

	public Driver driver;
	public int step = 0;
	public int type = NO_ACTION;
	public float trim = 0;
	public float throttle = 0;
	public float lerp = 0;
	public Action nextAction = this;

	public Action(Driver d,int t) {
		this.driver = d;
		this.type = t;
	}

	public abstract void act(int leftColor, int rightColor);
}
