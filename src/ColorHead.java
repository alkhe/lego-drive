

import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.ColorSensor.Color;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.NXT;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.SensorSelector;

public class ColorHead {

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
	public static final double REV = 2.75;

	// One rotation per second
	public static final int RPS = 360;

	// Handles to motors
	public static NXTRegulatedMotor a = Motor.A;
	public static NXTRegulatedMotor b = Motor.B;
	public static NXTRegulatedMotor c = Motor.C;

	// Maximum speed of motors: Do not calculate max speed of individual motors, may not return full potential
	public static final float MAX_SPEED = 1000.f;

	public static void main(String[] args) throws InterruptedException {
		//forward(MOTOR_A | MOTOR_B);
		ignore();
		float s = 0;
		int p = 250;
		float calib = 0;
		ColorSensor c1 = new ColorSensor(SensorPort.S1,Color.WHITE);
		ColorSensor c2 = new ColorSensor(SensorPort.S2,Color.WHITE);
		c1.setFloodlight(Color.WHITE);
		c1.setFloodlight(Color.WHITE);
		c1.setLow(0); c1.setHigh(255);
		c2.setLow(0); c2.setHigh(255);
		
		int hs = 10;
		int[] head1,head2;
		head1 = new int[hs];
		head2 = new int[hs];
		int ind = 0;
		setSpeed(MOTOR_A, p);
		setSpeed(MOTOR_B, p);
		a.forward();
		b.forward();
		while (true) {
			head1[ind%head1.length] = ColorIdentifier.identify1(c1.getColor());
			head2[ind%head2.length] = ColorIdentifier.identify2(c2.getColor());
			ind++;
			if (ind>head1.length) ind = 0;
			
			int[] count = new int[7];
			for (int i=0; i<head1.length; i++) {
				count[head1[i]]++;
			}

			int max1 = 0;
			for (int i=1; i<count.length; i++) {
				if (count[i]>count[max1]) {
					max1 = i;
				}
			}
			
			count = new int[7];
			for (int i=0; i<head1.length; i++) {
				count[head2[i]]++;
			}
			int max2 = 0;
			for (int i=1; i<count.length; i++) {
				if (count[i]>count[max2]) {
					max2 = i;
				}
			}
			if (max1!=0 || max2!=0)
			System.out.println(max1 + " " + max2 + " : " + count[0] + " " + count[1] + " " + count[2] + " " + count[5] + " " + count[6]);
			
			try {
				Thread.sleep(5);
			}
			catch (Exception e) {
				
			}
		}
		
		// Roundabout Ratio 1.6 
		//setSpeed(MOTOR_A, 600);
		//setSpeed(MOTOR_B, 340);
		//forward(MOTOR_A | MOTOR_B);
			
			//System.out.println(clRight.getRed() + " " + clRight.getGreen() + " " + clRight.getBlue());
		
		//ignore();
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
			a.backward();
		if ((motor & MOTOR_B) != 0)
			b.backward();
		if ((motor & MOTOR_C) != 0)
			c.backward();
	}
	
	/**
	 * @param motor Bitfield of motor to be used
	 */
	public static void stop(int motor) {
		if ((motor & MOTOR_A) != 0)
			a.stop();
		if ((motor & MOTOR_B) != 0)
			b.stop();
		if ((motor & MOTOR_C) != 0)
			c.stop();
	}
	
	/**
	 * @param motor Bitfield of motor to be used
	 * @param angle Angle rotation difference
	 */
	public static void rotate(int motor, int angle) {
		if ((motor & MOTOR_A) != 0)
			a.rotate(angle, true);
		if ((motor & MOTOR_B) != 0)
			b.rotate(angle, true);
		if ((motor & MOTOR_C) != 0)
			c.rotate(angle, true);
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
		Button.ENTER.waitForPressAndRelease();
	}

	/**
	 * @return void
	 */
	public static void escape() {
		Button.ESCAPE.waitForPressAndRelease();
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