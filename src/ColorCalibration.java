
import java.io.*;

import lejos.nxt.*;
import lejos.nxt.ColorSensor.Color;

public class ColorCalibration {

	public static void main(String[] args) {	
		final ColorSensor color = new ColorSensor(SensorPort.S1);
		color.setFloodlight(Color.WHITE);
		
		for (int i = 0; i < 1000; i ++)
		{
			Color col = color.getRawColor();
			TurnCalibration.print("------------------------------------------------");
			TurnCalibration.print("RED:\t" + col.getRed() + "\nGREEN:\t" + col.getGreen() + "\nBLUE:\t" + col.getBlue());
		}
//
//		FileOutputStream outRed = null;
//		FileOutputStream outGreen = null;
//		FileOutputStream outBlue = null;
//		File red = new File("red.txt");
//		File green = new File("green.txt");
//		File blue = new File("blue.txt");
//
//		try {
//			outRed = new FileOutputStream(red);
//			outGreen = new FileOutputStream(green);
//			outBlue = new FileOutputStream(blue);
//		}
//		catch (IOException e) {
//			System.err.println("It done fucked.");
//			Button.waitForAnyPress();
//			System.exit(1);
//		}
//
//		DataOutputStream dataRed = new DataOutputStream(outRed);
//		DataOutputStream dataGreen = new DataOutputStream(outGreen);
//		DataOutputStream dataBlue = new DataOutputStream(outBlue);
//
//		try {
//			for (int i = 0; i < 1000; i++) {
//				//System.out.println(color.getColor().toString());
//				Color col=color.getRawColor();
//				int r = col.getRed();
//				int g = col.getGreen();
//				int b = col.getBlue();
//				dataRed.write(r);
//				dataGreen.write(g);
//				dataBlue.write(b);
//			}
//			outRed.close();
//			outGreen.close();
//			outBlue.close();
//		}
//		catch (IOException e) {
//			System.err.println("Failed to write to output stream");
//		}
//		Sound.beep();
//		System.out.println("DONE!");
//		Button.waitForAnyPress();
	}
}
