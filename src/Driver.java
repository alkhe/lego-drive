import lejos.nxt.ColorSensor;
import lejos.nxt.NXTRegulatedMotor;

public class Driver {
	// Path is an n x 4 array
	// Node (0-71), Next Node, Turn, Length
	public static final float MOTOR_SPEED = 400f;
	public static final float MOTOR_SPIN = 2.74f; // Spin factor (needs to be more)
	public static final int MOTOR_TURN = 480; // Red line to center of next road
	public static final int MOTOR_PARK = 60; // Road to parking spot (needs to be more)
	
	int[][] path;
	int[] pcolorl,pcolorr;
	int currentPath;
	NXTRegulatedMotor leftMotor,rightMotor;
	ColorSensor leftSensor,rightSensor;
	Action currentAction;
	
	public Driver(int[][] p,NXTRegulatedMotor lm,NXTRegulatedMotor rm,ColorSensor ls,ColorSensor rs) {
		path = p;
		currentPath = 0;
		leftMotor = lm;
		rightMotor = rm;
		leftSensor = ls;
		rightSensor = rs;
		currentAction = null;
		pcolorl = new int[10];
		
		for (int i=0; i<path.length; i++) {
			if (path[i][2]==0 || path[i][2]==-1 || path[i][2]==1)
				path[i][3] = path[i][3]*15/8;
			else if (path[i][2]==2 || path[i][2]==-2)
				path[i][3] = path[i][3]*17/16;
			else
				path[i][3] = path[i][3]*14/16;
		}
	}
	
	public void run() {
		if (path!=null && currentPath>=path.length) return; // End path for now
		
		if (currentAction==null) {
			if (path[currentPath][2]==-3 || path[currentPath][2]==3) {
				System.out.println("PF");
				currentAction = new FinishAction(this,path[currentPath][2],path[currentPath][3]);
			}
			else if (path[currentPath][2]==-2 || path[currentPath][2]==2) {
				System.out.println("PA");
				currentAction = new ParkAction(this,path[currentPath][2],path[currentPath][3]);
			}
			else {
				currentAction = new DriveAction(this,path[currentPath][3]);
			}
		}
		
		// Get colors
		ColorSensor.Color c;
		c = leftSensor.getColor();
		int leftColor = ColorIdentifier.identify1(c);
		c = rightSensor.getColor();
		int rightColor = ColorIdentifier.identify2(c);
		//System.out.println(leftColor + " " + rightColor);
		
		// Perform the action
		if (currentAction==null) return;
		currentAction.performAction(leftColor,rightColor);
		if (currentAction==null) return;
		
		float leftMotorSpeed = currentAction.leftMotor*MOTOR_SPEED;
		float rightMotorSpeed = currentAction.rightMotor*MOTOR_SPEED;
		//System.out.println(currentAction.type + " " + leftMotorSpeed + " " + rightMotorSpeed);
		
		// Set the motors
		leftMotor.setSpeed(Math.abs(leftMotorSpeed));
		rightMotor.setSpeed(Math.abs(rightMotorSpeed)+1);
		
		if (currentAction.pivot) { // Pivot in place
			int rotateMotor = (int) (currentAction.rotate * MOTOR_SPIN);
			leftMotor.stop();
			rightMotor.stop();
			
			leftMotor.rotate(rotateMotor,true);
			rightMotor.rotate(-rotateMotor,false);
		}
		else if (currentAction.rotate!=0) { // Exact rotation
			leftMotor.stop();
			rightMotor.stop();
			
			leftMotor.rotate(currentAction.rotate,true);
			rightMotor.rotate(currentAction.rotate,false);
		}
		else { // Generic driving
			if (leftMotorSpeed>0) leftMotor.forward();
			else if (leftMotorSpeed<0) leftMotor.backward();
			else leftMotor.stop();
			
			if (rightMotorSpeed>0) rightMotor.forward();
			else if (rightMotorSpeed<0) rightMotor.backward();
			else rightMotor.stop();
			
			try {
				Thread.sleep(10);
			}
			catch (Exception e) {
				System.out.println("Failed to sleep");
			}
		}
		
		//System.out.println(currentAction.type);
		
		// Set the next behavior
		currentAction = currentAction.nextAction;
	}
	
	public void setAction(Action a) {
		currentAction = a;
	}
	
	public void notifyPathCompletion() {
		currentPath++;
		currentAction = null;
	}
	
	public int getCurrentPath() {
		return currentPath;
	}
	
	public int getTurn(int p) {
		return path[p][2]; // Turning
	}
	
	final int[] slow = {
			5, 6, 10, 11, 13, 14, 26, 31, 33, 34, 35, 36, 38, 39, 52, 54, 68, 70
	};
	final int[] round = {
			10, 11, 5, 6, 34, 35, 36
	};
	public boolean getSlow(int p) {
		return arrayFind(slow,path[p][1])>=0;
	}
	
	public int getRound(int p) {
		return arrayFind(round,path[p][1]);
	}
	
	public int arrayFind(int[] a,int b) {
		for (int i=0; i<a.length; i++) {
			if (a[i]==b) return i;
		}
		return -1;
	}
}