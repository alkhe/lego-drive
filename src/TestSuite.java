import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.ColorSensor.Color;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.NXT;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.SensorSelector;

public class TestSuite {

	public TestSuite() {
		
	}
	
	public static void main(String[] args) {
		testRoundabout(340, 600);
		testColor();
		testColorDetect();
		Drive.ignore();
	}
	
	/**
	 * TestSuite test functions 
	 */
	
	public static void testRoundabout(int leftMotor, int rightMotor) {
		System.out.println("Test Roundabout");
		Drive.ignore();
		Drive.setSpeed(Drive.MOTOR_A, rightMotor);
		Drive.setSpeed(Drive.MOTOR_B, leftMotor);
		Drive.forward(Drive.MOTOR_A | Drive.MOTOR_B);
		Drive.ignore();
		Drive.stop(Drive.MOTOR_ALL);
		LCD.clear();
	}
	
	public static void testColor() {
		class ColorTest {
			public double RedTotal;
			public double RedAverage;
			public double GreenTotal;
			public double GreenAverage;
			public double BlueTotal;
			public double BlueAverage;
			public ColorSensor SensorColor;
			
			public void process(int i) {
				ColorSensor.Color color = SensorColor.getColor();
				RedTotal += color.getRed();
				GreenTotal += color.getGreen();
				BlueTotal += color.getBlue();
				
				RedAverage = RedTotal / i;
				GreenAverage = GreenTotal / i;
				BlueAverage = BlueTotal / i;
				
				RedAverage = Math.round(RedAverage);
				GreenAverage = Math.round(GreenAverage);
				BlueAverage = Math.round(BlueAverage);
			}
			
			public void Print() {
				System.out.println("Red: " + RedAverage);
				System.out.println("Green: " + GreenAverage);
				System.out.println("Blue: " + BlueAverage);
			}
			
			public ColorTest(ColorSensor sensor) {
				RedTotal = 0;
				RedAverage = 0;
				GreenTotal = 0;
				GreenAverage = 0;
				BlueTotal = 0;
				BlueAverage = 0;
				SensorColor = sensor;
			}
		};
		
		
		ColorSensor csLeft = new ColorSensor(SensorPort.S1);
		ColorSensor csRight = new ColorSensor(SensorPort.S2);
		csLeft.setLow(0); csLeft.setHigh(255);
		csRight.setLow(0); csRight.setHigh(255);
		
		ColorTest right = new ColorTest(csRight);
		ColorTest left = new ColorTest(csLeft);
		
		System.out.println("Test Color");
		Drive.ignore();
		
		for (int i = 1; i > -1; i++) {
			right.process(i);
			left.process(i);
			if (i % 20 == 0) {
				LCD.clear();
				right.Print();
				left.Print();
			}
			if (Button.ENTER.isDown())
				break;
			if (Button.ESCAPE.isDown()) {
				right = new ColorTest(csRight);
				left = new ColorTest(csLeft);
				i = 0;
			}
		}
		
		LCD.clear();
	}
	
	public static String codeToColor(int colorcode) {
		String color;
		switch(colorcode) {
		case ColorIdentifier.BLACK:
			color = "Black";
			break;
		case ColorIdentifier.BLUE:
			color = "Blue";
			break;
		case ColorIdentifier.GRAY:
			color = "Gray";
			break;
		case ColorIdentifier.GREEN:
			color = "Green";
			break;
		case ColorIdentifier.RED:
			color = "Red";
			break;
		case ColorIdentifier.WHITE:
			color = "White";
			break;
		case ColorIdentifier.YELLOW:
			color = "Yellow";
			break;
		default:
			color = "None";
			break;
		}
		return color;
	}
	
	public static void testColorDetect() {
		
		ColorSensor csLeft = new ColorSensor(SensorPort.S1);
		ColorSensor csRight = new ColorSensor(SensorPort.S2);
		
		int colorLeft;
		int colorRight;
		
		System.out.println("Test Color Detect");
		Drive.ignore();
		
		for (int i = 1; i > -1; i++) {
			colorLeft = ColorIdentifier.identify1(csLeft.getColor());
			colorRight = ColorIdentifier.identify2(csRight.getColor());
			
			System.out.println(codeToColor(colorLeft) + " " + codeToColor(colorRight));
			
			if (Button.ENTER.isDown())
				break;
		}
	}
}
