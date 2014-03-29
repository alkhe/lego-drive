import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.ColorSensor.Color;


public class Main {
	public static void main(String args[]) throws IOException {
		/*
		File pathFile = new File("Path.txt");
		BufferedReader fin = new BufferedReader(new InputStreamReader(new FileInputStream(pathFile)));
		ArrayList<String> lines = new ArrayList<String>();
		String line;
		while ((line=fin.readLine())!=null) {
			line = line.trim();
			if (line.length()>0) {
				lines.add(line);
			}
		}
		
		int[][] path = new int[lines.size()][4];
		int nnum;
		String[] parts;
		for (int i=0; i<lines.size(); i++) {
			parts = split(lines.get(i));
			nnum = Integer.parseInt(parts[0]);
			path[i][0] = (i==0) ? nnum : path[i-1][0];
			path[i][1] = nnum;
			path[i][2] = Integer.parseInt(parts[1]);
			path[i][3] = Integer.parseInt(parts[2]);
		}
		*/
		int[][] path = {
				{ 5, 5, 1, 104},
				{ 5,17, 2, 120},
				{17,17, 1, 150},
				{17,18, 0,  89}
		};
		
		Button.waitForAnyPress();
		ColorSensor c1 = new ColorSensor(SensorPort.S1,Color.WHITE);
		ColorSensor c2 = new ColorSensor(SensorPort.S2,Color.WHITE);
		c1.setLow(0); c1.setHigh(255);
		c2.setLow(0); c2.setHigh(255);
		Driver robot = new Driver(path,Motor.A,Motor.B,c1,c2);
		
		//robot.setAction(s2);
		while (true) {
			robot.run();
		}
	}
	
	public static String[] split(String line) {
		String[] ret = new String[3];
		int s;
		
		s = line.indexOf(" ");
		ret[0] = line.substring(0,s);
		line = line.substring(s+1);
		
		s = line.indexOf(" ");
		ret[1] = line.substring(0,s);
		line = line.substring(s+1);
		
		ret[2] = line;
		
		return ret;
	}
}