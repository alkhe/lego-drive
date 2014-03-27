package edu.mbhs.lego.drive;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.NXT;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;

public class Drive {

	// Bitfields for motors
	public static final int MOTOR_A = 1;
	public static final int MOTOR_B = 2;
	public static final int MOTOR_C = 4;
	public static final int MOTOR_ALL = 7;

	// Bitfields for sensors
	public static final int SENSOR_1 = 1;
	public static final int SENSOR_2 = 2;
	public static final int SENSOR_3 = 4;
	public static final int SENSOR_4 = 8;
	public static final int SENSOR_ALL = 15;

	// One rotation per second
	public static final int RPS = 360;

	// Handles to motors
	public static NXTRegulatedMotor a = Motor.A;
	public static NXTRegulatedMotor b = Motor.B;
	public static NXTRegulatedMotor c = Motor.C;

	// Maximum speed of motors: Do not calculate max speed of individual motors, may not return full potential
	public static final float MAX_SPEED = 1000.f;

	public static void main(String[] args) {
		
	}

	/**
	 * API Level 1
	 * forward(int)
	 * rotate(int, int)
	 * setSpeed(int, float)
	 * ignore()
	 * shutdown()
	 * print(String)
	 */

	/**
	 * @param motor Bitfield of motor to be used
	 */
	public static void forward(int motor) {
		if ((motor & MOTOR_A) != 0)
			a.forward();
		if ((motor & MOTOR_B) != 0)
			b.forward();
		if ((motor & MOTOR_C) != 0)
			c.forward();
	}

	/**
	 * @param motor Bitfield of motor to be used
	 * @deprecated setSpeed with a negative number
	 */
	public static void backward(int motor) {
		if ((motor & MOTOR_A) != 0)
			a.forward();
		if ((motor & MOTOR_B) != 0)
			b.forward();
		if ((motor & MOTOR_C) != 0)
			c.forward();
	}

	/**
	 * @param motor Bitfield of motor to be used
	 * @param angle Angle rotation difference
	 */
	public static void rotate(int motor, int angle) {
		if ((motor & MOTOR_A) != 0)
			a.rotate(angle);
		if ((motor & MOTOR_B) != 0)
			b.rotate(angle);
		if ((motor & MOTOR_C) != 0)
			c.rotate(angle);
	}

	/**
	 * @param motor Bitfield of motor to be used
	 * @param speed Motor speed
	 */
	public static void setSpeed(int motor, float speed) {
		if ((motor & MOTOR_A) != 0)
			a.setSpeed(speed);
		if ((motor & MOTOR_B) != 0)
			b.setSpeed(speed);
		if ((motor & MOTOR_C) != 0)
			c.setSpeed(speed);
	}

	/**
	 * @return void
	 */
	public static void ignore() {
		Button.waitForAnyPress();
	}

	/**
	 * @return void
	 */
	public static void shutdown() {
		NXT.shutDown();
	}

	/**
	 * @param s String to print
	 * @return void
	 */
	public static void print(String s) {
		System.out.println(s);
	}

}