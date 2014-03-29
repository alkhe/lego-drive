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
		int[][] pathtest = {
				{  5,  5,  1,104},
				{  5, 17,  2,150}, //the distance between roundabout and parking space
				{ 17, 17,  2, 45},
				{ 17, 17,  2, 45},
				{ 17, 17,  1,150},
				{ 17, 18,  1, 89},
		};
		int[][] p2 = {
				{ 1, 1,  0, 10 },
				{ 1, 2, -2, 60 },
				{ 2, 2,  1, 10 }
		};
		int[][] path = {
				{  0,  0, -1,  0},
				{  0,  1, -1,207},
				{  1,  2, -1,284},
				{  2,  3, -2,130},
				{  2,  3,  0,146},
				{  3, 12,  0,117},
				{ 12, 30, -1,305},
				{ 30, 29,  0, 72},
				{ 29, 27,  0, 70},
				{ 27, 25,  0, 89},
				{ 25, 23, -1, 47},
				{ 23, 15, -1,211},
				{ 15, 16, -167, 47},
				{ 16, 18, -2,135},
				{ 16, 18,  0, 89},
				{ 18, 19, -1, 70},
				{ 19, 28,  0,104},
				{ 28, 37,  0,105},
				{ 37, 49,  0,147},
				{ 49, 53,  0, 88},
				{ 53, 60,  0, 68},
				{ 60, 65,  0,111},
				{ 65, 69, -1, 88},
				{ 69, 68, -2,135},
				{ 69, 68, -1,210},
				{ 68, 56,  0,201},
				{ 56, 43, -1,158},
				{ 43, 44,  0, 47},
				{ 44, 46,  2, 50},
				{ 44, 46,  0, 89},
				{ 46, 48, -1, 70},
				{ 48, 53,  0, 88},
				{ 53, 60,  0, 68},
				{ 60, 65, -1,111},
				{ 65, 64,  0,161},
				{ 64, 62, -1, 47},
				{ 62, 56, -1,201},
				{ 56, 57,  0, 47},
				{ 57, 59,  2, 90},
				{ 57, 59, -1,161},
				{ 59, 65, -1,111},
				{ 65, 64,  0,161},
				{ 64, 62, -1, 47},
				{ 62, 56, -1,201},
				{ 56, 57, -1, 47},
				{ 57, 63,  2,180},
				{ 57, 63, -1,111},
				{ 63, 62, -1, 47},
				{ 62, 56,  0,201},
				{ 56, 43,  0,158},
				{ 43, 72,  0,147},
				{ 72, 15,  0,211},
				{ 15,  4,  0,257},
				{  4,  1, -3,245},

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