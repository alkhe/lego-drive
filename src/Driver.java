import lejos.nxt.ColorSensor;
import lejos.nxt.NXTRegulatedMotor;

public class Driver {
	// Path is an n x 4 array
	// Node (0-71), Next Node, Turn, Length
	private final float MOTOR_SPEED = 600f;
	private final float MOTOR_SPIN = 2.67f;
	
	int[][] path;
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
	}
	
	public void run() {
		if (path!=null && currentPath>=path.length) return; // End path for now
		
		if (currentAction==null) {
			if (path[currentPath][2]==-2 || path[currentPath][2]==2) {
				System.out.println("PA");
				currentAction = new ParkAction(this,path[currentPath][2],path[currentPath][3]);
			}
			else {
				currentAction = new DriveAction(this);
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
		rightMotor.setSpeed(Math.abs(rightMotorSpeed));
		
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
			5, 6, 10, 11, 34, 35, 36
	};
	public boolean getSlow(int p) {
		return contains(slow,p); // TODO
	}
	
	public boolean getRound(int p) {
		return contains(round,p);
	}
	
	public boolean contains(int[] a,int b) {
		for (int i=0; i<a.length; i++) {
			if (a[i]==b) return true;
		}
		return false;
	}
}