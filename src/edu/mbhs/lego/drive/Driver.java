package edu.mbhs.lego.drive;

import lejos.nxt.ColorSensor;
import lejos.nxt.NXTRegulatedMotor;

public class Driver {
	int[][] path;
	NXTRegulatedMotor leftMotor;
	NXTRegulatedMotor rightMotor;
	ColorSensor leftSensor;
	ColorSensor rightSensor;
	Action currentAction;

	public Driver(int[][] p, NXTRegulatedMotor lm, NXTRegulatedMotor rm, ColorSensor ls, ColorSensor rs) {
		path = p;
		leftMotor = lm;
		rightMotor = rm;
		leftSensor = ls;
		rightSensor = rs;
		currentAction = new RestAction(this);
	}

	public void run() {
		// Get colors
		ColorSensor.Color c;
		c = leftSensor.getRawColor();
		int leftColor = ColorIdentifier.identify(c.getRed(), c.getGreen(), c.getBlue());
		c = rightSensor.getRawColor();
		int rightColor = ColorIdentifier.identify(c.getRed(), c.getGreen(), c.getBlue());

		// Perform the action
		currentAction.act(leftColor, rightColor);
		float leftMotorSpeed = currentAction.throttle;
		float rightMotorSpeed = currentAction.throttle;
		if (currentAction.trim < 0) {
			leftMotorSpeed += 2 * currentAction.throttle * currentAction.trim;
		}
		else {
			rightMotorSpeed -= 2 * currentAction.throttle * currentAction.trim;
		}

		// Set the motors
		leftMotor.setSpeed(leftMotorSpeed);
		rightMotor.setSpeed(rightMotorSpeed);

		// Set the next behavior
		currentAction = currentAction.nextAction;
	}
}
